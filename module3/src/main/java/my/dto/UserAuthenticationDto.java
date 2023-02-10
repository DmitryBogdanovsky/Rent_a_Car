package my.dto;

import lombok.*;
import my.model.user.RoleEnum;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationDto {
    private Integer id;
    @NotEmpty
    @NotNull
    @Email(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")
    private String email;

    @NotNull
    @Size(min = 4, max = 42)
    private String password;

    @NotEmpty
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @Valid
    @NotNull
    private UserDetailsDto userDetails;


    public UserAuthenticationDto(UserDetailsDto userDetailsDto) {
        this.userDetails = userDetailsDto;
    }
}
