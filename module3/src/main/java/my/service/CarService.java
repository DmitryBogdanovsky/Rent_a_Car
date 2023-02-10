package my.service;


import my.dto.CarDto;
import org.springframework.security.access.annotation.Secured;
import java.util.List;


public interface CarService {

    @Secured("ROLE_ADMIN")
    CarDto addCar(CarDto newCar);

    @Secured("ROLE_ADMIN")
    CarDto updateCar(CarDto updatedCar);

    @Secured("ROLE_ADMIN")
    void deleteCar(Integer id);

    CarDto findCarById(Integer id);

    long countAllCars();

    List<CarDto> findAllCarsPageable(int page, int size);

    @Secured("ROLE_ADMIN")
    void updateCarPictures(Integer id, List<byte[]> images);

}
