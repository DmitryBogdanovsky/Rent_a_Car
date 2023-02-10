package my.dao;

import my.model.order.Order;
import my.model.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    long countByOrderStatus(OrderStatus orderStatus);

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE car.id = :carId " +
            "AND orderStatus <> my.model.order.OrderStatus.CLOSED " +
            "AND (:startDate BETWEEN startDate AND endDate OR :endDate BETWEEN startDate AND endDate)")
    long countActiveOrdersByCarInDateInterval(
            @Param("carId") Integer carId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE user.id = :userId " +
            "AND orderStatus <> my.model.order.OrderStatus.CLOSED " +
            "AND (:startDate BETWEEN startDate AND endDate OR :endDate BETWEEN startDate AND endDate)")
    long countActiveOrdersByUserInDateInterval(
            @Param("userId") Integer userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE car.id = :carId " +
            "AND id <> :orderId " +
            "AND orderStatus <> my.model.order.OrderStatus.CLOSED " +
            "AND (:startDate BETWEEN startDate AND endDate OR :endDate BETWEEN startDate AND endDate)")
    long countActiveOrdersByCarInDateIntervalExceptOrderWithId(
            @Param("orderId") Integer orderId,
            @Param("carId") Integer carId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE user.id = :userId " +
            "AND id <> :orderId " +
            "AND orderStatus <> my.model.order.OrderStatus.CLOSED " +
            "AND (:startDate BETWEEN startDate AND endDate OR :endDate BETWEEN startDate AND endDate)")
    long countActiveOrdersByUserInDateIntervalExceptOrderWithId(
            @Param("orderId") Integer orderId,
            @Param("userId") Integer userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    Page<Order> findAllByOrderStatus(OrderStatus orderStatus, Pageable pageable);

}