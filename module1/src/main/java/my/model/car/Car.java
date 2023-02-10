package my.model.car;


import lombok.*;
import my.model.order.Order;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car implements Serializable {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private Integer id;

    @Column(name = "car_year", nullable = false)
    private int year;

    @Column(name = "car_color", nullable = false)
    private String color;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "typeCarBody", nullable = false)
    private TypeCarBody typeCarBody;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fuel_car_type", nullable = false)
    private FuelCarType fuelCarType;

    @Column(name = "car_automat", nullable = false)
    private Boolean transmissionAutomatic;

    @Column(name = "car_climate", nullable = false)
    private Boolean climateControl;

    @Column(name = "car_price", precision = 6, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "modelCar_id", nullable = false)
    private ModelCar modelCar;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<CarPicture> carPictures;

    @OneToMany(mappedBy = "car")
    private List<Order> orders = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}