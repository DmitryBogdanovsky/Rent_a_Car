package my.model.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "model_car", uniqueConstraints = {
        @UniqueConstraint(name = "un_modelCar_name", columnNames = {"modelCar_name", "brandCar_id"})})
public class ModelCar {
    @Id
    @Column(name = "modelCar_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private Integer id;

    @Column(name = "modelCar_name", nullable = false)
    private String modelName;

    @ManyToOne
    @JoinColumn(name = "brandCar_id", nullable = false)
    private BrandCar brandCar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ModelCar modelCar = (ModelCar) o;
        return id != null && Objects.equals(id,modelCar.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
