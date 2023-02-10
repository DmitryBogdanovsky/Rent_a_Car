package my.dao;

import my.model.order.OrderStatus;
import my.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserDetails_PhoneNumber(String phoneNumber);

    long countByOrdersOrderStatus(OrderStatus orderStatus);

    Page<User> findByOrders_OrderStatus(OrderStatus orderStatus, Pageable pageable);

}