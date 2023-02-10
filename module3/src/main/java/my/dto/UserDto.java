package my.dto;


import lombok.*;
import my.model.user.RoleEnum;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    @NotEmpty
    @NotNull
    @Email(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 4, max = 56)
    private String password;

    @NotEmpty
    @NotNull
    private RoleEnum roleEnum;

    @Valid
    @NotNull
    private UserDetailsDto userDetails;

    public UserDto(UserDetailsDto userDetailDto) {
        this.userDetails = userDetailDto;
    }

}
