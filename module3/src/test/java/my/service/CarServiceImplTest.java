package my.service;


import my.dao.CarRepository;
import my.dto.CarDto;
import my.exception.MyException;
import my.model.car.Car;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @Mock
    CarRepository carRepository;
    @InjectMocks
    CarServiceImpl targetObject;

    public CarServiceImplTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void addCarDuplicateId() {
        // Given
        CarDto carDto = new CarDto();
        carDto.setId(1);

        // When
        when(carRepository.existsById(carDto.getId()))
                .thenReturn(true);

        // Then
        Exception exception = assertThrows(MyException.class, () -> targetObject.addCar(carDto));
        assertEquals(exception.getMessage(), "Car already exists!:" + carDto.getId());
    }

    @Test
    public void addCarCallDao() {
        // Given
        CarDto carDto = new CarDto();
        carDto.setId(1);

        // When
        when(carRepository.save(any())).thenReturn(new Car());
        targetObject.addCar(carDto);

        // Then
        ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
        verify(carRepository).save(argument.capture());
        assertEquals(carDto.getId(), argument.getValue().getId());
    }

    @Test
    public void updateCarDoesNotExist() {
        // Given
        CarDto carDto = new CarDto();
        carDto.setId(1);

        // When
        when(carRepository.existsById(carDto.getId()))
                .thenReturn(false);

        // Then
        Exception exception = assertThrows(MyException.class, () -> targetObject.updateCar(carDto));
        assertEquals(exception.getMessage(), "Car not found!: " + carDto.getId());
    }

    @Test
    public void updateCallDao() {
        // Given
        CarDto carDto = new CarDto();
        carDto.setId(1);

        // When
        when(carRepository.existsById(carDto.getId()))
                .thenReturn(true);
        when(carRepository.save(any())).thenReturn(new Car());
        targetObject.updateCar(carDto);

        // Then
        ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
        verify(carRepository).save(argument.capture());
        assertEquals(carDto.getId(), argument.getValue().getId());
    }


}
