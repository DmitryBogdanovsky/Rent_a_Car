package my.dto;


//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import my.model.car.FuelCarType;
import my.model.car.TypeCarBody;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Integer id;

    @NotNull(message = "You need to enter the year of manufacture of the car.")
    @Max(value = 2023, message = "Incorrect year")
    private int year;

    @NotEmpty(message = "Incorrect colour. You need to specify the color of the car.")
    private String color;

    @NotEmpty(message = "You need to provide the car body type.")
    private TypeCarBody typeCarBody;

    @NotEmpty(message = "You need to provide the car fuel type.")
    private FuelCarType fuelCarType;

    @NotNull
    private Boolean transmissionAutomatic;

    @NotNull
    private Boolean climateControl;

    @NotNull
    @Range(min = 0, max = 100000)
    private BigDecimal price;

    @NotNull
    private ModelCarDto modelCar;

    @NotNull
    @Valid
 //   @JsonIgnoreProperties({"user", "car"})
    private List<OrderDto> orders;

}
