package my.dto;

import lombok.*;
import javax.validation.constraints.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    private Integer id;

    @NotNull
    @Size(min = 1, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 20)
    private String lastName;

    @NotNull
    @Size(min = 10, max = 14)
    @Pattern(regexp="\\+[1-9][0-9]{0,2}[0-9]{2,3}[0-9]{5}")
    private String phoneNumber;

    @Past
    @NotNull
    private Date birthDate;

}
