package my.service;

import my.dto.OrderDto;
import my.dto.OrderRequestDto;
import my.exception.MyException;
import my.model.car.Car;
import my.model.order.Order;
import my.model.order.OrderStatus;
import my.model.user.User;
import my.dao.CarRepository;
import my.dao.OrderRepository;
import my.dao.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;



import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    Validator validator;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
   private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    @Override
    @Transactional
    public OrderDto addOrder(OrderRequestDto orderRequestDto) {

        String email = userService.findUser().getEmail();
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found! Username: " + email);

        Car car = carRepository.findById(orderRequestDto.getCarId())
                .orElseThrow(() -> new MyException("Car not found! Id: " + orderRequestDto.getCarId()));

        Order order = modelMapper.map(orderRequestDto, Order.class);

        if (order.getId() != null && orderRepository.findById(order.getId()).isPresent())
            throw new MyException("Order already exists! Id: " + order.getId());

        order.setOrderStatus(OrderStatus.PAID);
        order.addCar(car);
        order.addUser(user);

        order.setStartDate(order.getStartDate());
        order.setEndDate(order.getEndDate());

        if (isUserBusyForOrder(order.getUser().getId(), order.getStartDate(), order.getEndDate()))
            throw new MyException("The user is busy in this date interval!");

        if (isCarBusyForOrder(order.getCar().getId(), order.getStartDate(), order.getEndDate()))
            throw new MyException("The car is busy in this date interval!");

        long days = ChronoUnit.DAYS.between(order.getStartDate(), order.getEndDate());
        order.setTotalPrice(car.getPrice().multiply(BigDecimal.valueOf(days)));

        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }

    @Override
    @Transactional
    public OrderDto updateOrder(OrderDto updatedOrder) {

        Order order = orderRepository.findById(updatedOrder.getId())
                .orElseThrow(() -> new MyException("Order not found! Id: " + updatedOrder.getId()));

        order.setOrderStatus(updatedOrder.getOrderStatus());
        order.setStartDate(updatedOrder.getStartDate());
        order.setEndDate(updatedOrder.getEndDate());

        if (isUserBusyForOrderExceptOrder(order.getId(), order.getUser().getId(), order.getStartDate(), order.getEndDate()))
            throw new MyException("The user is busy in this date interval!");

        if (isCarBusyForOrderExceptOrder(order.getId(), order.getCar().getId(), order.getStartDate(), order.getEndDate()))
            throw new MyException("The car is busy in this date interval!");

        long days = ChronoUnit.DAYS.between(order.getStartDate(), order.getEndDate());

        order.setTotalPrice(order.getCar().getPrice().multiply(BigDecimal.valueOf(days)));

        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }


    @Override
    @Transactional
    public boolean isUserBusyForOrder(Integer userId, LocalDate startDate, LocalDate endDate) {
        return orderRepository.countActiveOrdersByUserInDateInterval(userId, startDate, endDate) != 0;
    }

    @Override
    @Transactional
    public boolean isCarBusyForOrder(Integer carId, LocalDate startDate, LocalDate endDate) {
        return orderRepository.countActiveOrdersByCarInDateInterval(carId, startDate, endDate) != 0;
    }

    @Override
    @Transactional
    public boolean isUserBusyForOrderExceptOrder(Integer orderId, Integer userId, LocalDate startDate, LocalDate endDate) {
        return orderRepository.countActiveOrdersByUserInDateIntervalExceptOrderWithId(orderId, userId, startDate, endDate) != 0;
    }

    @Override
    @Transactional
    public boolean isCarBusyForOrderExceptOrder(Integer orderId, Integer carId, LocalDate startDate, LocalDate endDate) {
        return orderRepository.countActiveOrdersByCarInDateIntervalExceptOrderWithId(orderId, carId, startDate, endDate) != 0;
    }


    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteOrder(Integer id) {
        if (!orderRepository.existsById(id))
            throw new MyException("Order not found! Id: " + id);

        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public OrderDto findOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new MyException("Order not found! Id: " + orderId));

        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public long countAllOrdersByStatus(OrderStatus orderStatus) {
        return orderRepository.countByOrderStatus(orderStatus);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public List<OrderDto> findAllOrdersByStatusPageable(OrderStatus orderStatus, int page, int size) {
        return orderRepository.findAllByOrderStatus(orderStatus, PageRequest.of(page, size)).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public long countAllOrders() {
        return orderRepository.count();
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public List<OrderDto> findAllOrdersPageable(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size)).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

}
