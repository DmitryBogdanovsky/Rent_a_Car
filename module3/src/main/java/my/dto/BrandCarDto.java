package my.dto;


import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandCarDto {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 20)
    private String brandName;
}
