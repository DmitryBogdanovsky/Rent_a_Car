package my.model.user;

import lombok.*;
import my.model.order.Order;
import my.model.order.OrderStatus;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private Integer id;

    @Column(name = "user_email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_enum", nullable = false)
    private RoleEnum roleEnum;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDetails userDetails;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public void addUserDetails(UserDetails userDetails) {
        userDetails.setUser(this);
        this.userDetails = userDetails;
    }
    public void removeUserDetails() {
        if (userDetails != null) {
            userDetails.setUser(null);
            this.userDetails = null;
        }
    }

    public RoleEnum getRole(){
        return  roleEnum;
    }
    public void  setRole(RoleEnum roleEnum){
        this.roleEnum = roleEnum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}