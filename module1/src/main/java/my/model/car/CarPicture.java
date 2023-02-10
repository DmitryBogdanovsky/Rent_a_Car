package my.model.car;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_picture")
public class CarPicture implements Serializable {
    @Id
    @Column(name = "carPicture_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator")
    private Integer id;

    @Lob
    @Column(name = "picture", columnDefinition = "MEDIUMBLOB NOT NULL")
    private byte[] picture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarPicture carPicture = (CarPicture) o;
        return id != null && Objects.equals(id, carPicture.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}