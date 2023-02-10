package my.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso  = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfOrder;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso  = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso  = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @NotNull
    private Integer carId;

}
