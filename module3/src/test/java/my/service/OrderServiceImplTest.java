package my.service;

import my.dto.OrderRequestDto;
import my.dto.UserResponseDto;
import my.exception.MyException;
import my.model.car.Car;
import my.model.order.Order;
import my.model.user.User;
import my.dao.CarRepository;
import my.dao.OrderRepository;
import my.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    UserService userService;
    @Mock
    Validator validator;
    @Mock
    OrderRepository orderRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    CarRepository carRepository;
    @InjectMocks
    OrderServiceImpl targetObject;

    public OrderServiceImplTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void deleteOrderShouldThrow() {
        // Given
        int id = 1;

        // When
        when(orderRepository.existsById(id))
                .thenReturn(false);

        // Then
        assertThrows(MyException.class, () -> targetObject.deleteOrder(id));
    }

    @Test
    public void deleteOrderShouldCallDao() {
        // Given
        int id = 1;

        // When
        when(orderRepository.existsById(id))
                .thenReturn(true);
        targetObject.deleteOrder(id);

        // Then
        verify(orderRepository).deleteById(id);
    }

    @Test
    public void countAllOrdersShouldCallDao() {
        // Given
        // When
        targetObject.countAllOrders();

        // Then
        verify(orderRepository).count();
    }

    @Test
    public void addOrderShouldThrowOnInvalidUser() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setCarId(1);

        // When
        UserResponseDto user = new UserResponseDto();
        user.setEmail("User@user.com");
        when(userService.findUser()).thenReturn(user);
        when(userRepository.findByEmail(any())).thenReturn(null);

        // Then
        assertThrows(UsernameNotFoundException.class, () -> targetObject.addOrder(orderDto));
    }

    @Test
    public void addOrderShouldThrowOnInvalidCardId() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setCarId(1);

        // When
        UserResponseDto user = new UserResponseDto();
        user.setEmail("User@user.com");
        when(userService.findUser()).thenReturn(user);
        when(userRepository.findByEmail(any())).thenReturn(new User());

        // Then
        Exception exception = assertThrows(MyException.class, () -> targetObject.addOrder(orderDto));
        assertEquals(exception.getMessage(), "Car not found! Id: " + orderDto.getCarId());
    }

    @Test
    public void addOrderShouldThrowOnUserBusy() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setStartDate(LocalDate.now());
        orderDto.setEndDate((LocalDate.now()).plusDays(3));

        orderDto.setCarId(1);

        // When
        UserResponseDto user = new UserResponseDto();
        user.setEmail("User@user.com");
        when(userService.findUser()).thenReturn(user);
        when(userRepository.findByEmail(any())).thenReturn(new User());
        when(carRepository.findById(any())).thenReturn(Optional.of(new Car()));
        when(orderRepository.countActiveOrdersByUserInDateInterval(any(), any(), any())).thenReturn(1L);

        // Then
        Exception exception = assertThrows(MyException.class, () -> targetObject.addOrder(orderDto));
        assertEquals(exception.getMessage(), "The user is busy in this date interval!");
    }

    @Test
    public void addOrderShouldThrowOnCarBusy() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setStartDate(LocalDate.now());
        orderDto.setEndDate((LocalDate.now()).plusDays(3));
        orderDto.setCarId(1);

        // When
        UserResponseDto user = new UserResponseDto();
        user.setEmail("User@user.com");
        when(userService.findUser()).thenReturn(user);
        when(userRepository.findByEmail(any())).thenReturn(new User());
        when(carRepository.findById(any())).thenReturn(Optional.of(new Car()));
        when(orderRepository.countActiveOrdersByCarInDateInterval(any(), any(), any())).thenReturn(1L);

        // Then
        Exception exception = assertThrows(MyException.class, () -> targetObject.addOrder(orderDto));
        assertEquals(exception.getMessage(), "The car is busy in this date interval!");
    }

    @Test
    public void addOrderShouldCallDao() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
//        LocalDate date = LocalDate.now();
        orderDto.setStartDate(LocalDate.now());
        orderDto.setEndDate((LocalDate.now()).plusDays(3));

        orderDto.setCarId(1);

        // When
        UserResponseDto user = new UserResponseDto();
        user.setEmail("User@user.com");
        when(userService.findUser()).thenReturn(user);
        when(userRepository.findByEmail(any())).thenReturn(new User());
        Car car = new Car();
        car.setId(orderDto.getCarId());
        car.setPrice(BigDecimal.valueOf(50));
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        when(orderRepository.save(any())).thenReturn(new Order());

        targetObject.addOrder(orderDto);

        // Then
        ArgumentCaptor<Order> argument = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(argument.capture());
        assertEquals(orderDto.getCarId(), argument.getValue().getCar().getId());
    }
}
