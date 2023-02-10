package my.dto;


import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelCarDto {
    private Integer id;

    @NotNull
    @Size(min = 2, max = 20)
    private String modelName;

    @NotNull
    private BrandCarDto brandCar;
}
