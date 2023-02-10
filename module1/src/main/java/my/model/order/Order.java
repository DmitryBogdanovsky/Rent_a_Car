package my.model.order;

import lombok.*;
import my.model.car.Car;
import my.model.user.User;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_car")
public class Order implements Serializable {
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private Integer id;

    @Column(name = "dateOfOrder", nullable = false)
    private LocalDate dateOfOrder;

    @Column(name = "orderStartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "ordeEndDate", nullable = false)
    private LocalDate endDate;

    @Column(name = "orderTotalPrice", nullable = false)
    private BigDecimal totalPrice;

    @Enumerated
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;


    public void addUser(User user){
        this.user = user;
        user.getOrders().add(this);
    }
    public void removeUser(User user){
        if (user != null){
            user.getOrders().remove(this);
        }

    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void addCar(Car car) {
        this.car = car;
        car.getOrders().add(this);
    }

    public void removeCar(Car car){
        if (car != null) {
            car.getOrders().remove(this);
            this.car = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}