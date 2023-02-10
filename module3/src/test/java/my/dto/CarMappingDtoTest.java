package my.dto;


import my.model.car.Car;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CarMappingDtoTest extends InitMappingDtoTest {
    CarDto targetObject;
    final int NUMBER_OF_FIELDS = 10;

    @Test
    public void mapModelToDto() {
        // Given

        // When
        targetObject = modelMapper.map(car, CarDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }


        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), car.getId());
        assertEquals(targetObject.getColor(), car.getColor());
        assertEquals(targetObject.getTypeCarBody(), car.getTypeCarBody());
        assertEquals(targetObject.getFuelCarType(), car.getFuelCarType());
        assertEquals(targetObject.getYear(), car.getYear());
        assertEquals(targetObject.getPrice(), car.getPrice());

        assertEquals(targetObject.getClimateControl(), car.getClimateControl());
        assertEquals(targetObject.getTransmissionAutomatic(), car.getTransmissionAutomatic());

    }

    @Test
    public void mapDtoToModel() {
        // Given
        targetObject = modelMapper.map(car, CarDto.class);

        // When
        Car carResult = modelMapper.map(targetObject, Car.class);

        // Then
        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(carResult.getId(), car.getId());
        assertEquals(carResult.getColor(), car.getColor());
        assertEquals(carResult.getTypeCarBody(), car.getTypeCarBody());
        assertEquals(carResult.getFuelCarType(), car.getFuelCarType());
        assertEquals(carResult.getYear(), car.getYear());
        assertEquals(carResult.getPrice(), car.getPrice());
        assertEquals(carResult.getClimateControl(), car.getClimateControl());
        assertEquals(carResult.getTransmissionAutomatic(), car.getTransmissionAutomatic());

    }
}