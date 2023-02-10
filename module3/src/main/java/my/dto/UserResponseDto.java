package my.dto;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import my.model.user.RoleEnum;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Integer id;

    @NotEmpty
    @NotNull
    @Email(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")
    private String email;

    @NotEmpty
    @NotNull
    private RoleEnum roleEnum;

    @Valid
    @NotNull
    private UserDetailsDto userDetails;

    @Valid
    @NotNull
//    @JsonIgnoreProperties({"user", "car"})
    private List<OrderDto> orders;

    public UserResponseDto(UserDetailsDto userDetailsDto) {
        this.userDetails = userDetailsDto;
    }


}
