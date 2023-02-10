package my.model.car;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand_car")
public class BrandCar implements Serializable {
    @Id
    @Column(name = "brandCar_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private Integer id;

    @Column(name = "brandCar_name", unique = true, nullable = false)
    private String brandName;

    @OneToMany(mappedBy = "brandCar", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ModelCar> modelCars = new ArrayList<>();

//    <------- Synchronization methods -------->
    public void addModelCar(ModelCar modelCar) {
        modelCars.add(modelCar);
        modelCar.setBrandCar(this);
    }

    public void removeModelCar(ModelCar modelCar) {
        modelCars.remove(modelCar);
        modelCar.setBrandCar(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BrandCar brandCar = (BrandCar) o;
        return id != null && Objects.equals(id, brandCar.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
