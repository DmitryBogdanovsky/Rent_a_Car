package my.service;


import my.dto.OrderDto;
import my.dto.OrderRequestDto;
import my.model.order.OrderStatus;


import java.time.LocalDate;
import java.util.List;


public interface OrderService {


    OrderDto addOrder(OrderRequestDto orderRequestDto);

    OrderDto updateOrder(OrderDto updatedOrder);

    boolean isUserBusyForOrder(Integer userId, LocalDate startDate, LocalDate endDate);

    boolean isCarBusyForOrder(Integer carId, LocalDate startDate, LocalDate endDate);

    boolean isUserBusyForOrderExceptOrder(Integer orderId, Integer userId, LocalDate startDate, LocalDate endDate);

    boolean isCarBusyForOrderExceptOrder(Integer orderId, Integer carId, LocalDate startDate, LocalDate endDate);

    void deleteOrder(Integer id);

    OrderDto findOrderById(Integer orderId);

    long countAllOrdersByStatus(OrderStatus orderStatus);

    List<OrderDto> findAllOrdersByStatusPageable(OrderStatus orderStatus, int page, int size);

    long countAllOrders();

    List<OrderDto> findAllOrdersPageable(int page, int size);
}
