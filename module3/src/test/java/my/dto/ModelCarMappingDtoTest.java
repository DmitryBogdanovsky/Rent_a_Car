package my.dto;

import my.model.car.ModelCar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ModelCarMappingDtoTest extends InitMappingDtoTest {
    ModelCarDto targetObject;
    final int NUMBER_OF_FIELDS = 3;

    @Test
    public void mapModelToDto() {
        // Given

        // When
        targetObject = modelMapper.map(modelCar, ModelCarDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), modelCar.getId());
        assertEquals(targetObject.getBrandCar().getId(), modelCar.getBrandCar().getId());
        assertEquals(targetObject.getBrandCar().getBrandName(), modelCar.getBrandCar().getBrandName());
    }

    @Test
    public void mapDtoToModel() {
        // Given
        targetObject = modelMapper.map(modelCar, ModelCarDto.class);

        // When
        ModelCar carModelResult = modelMapper.map(targetObject, ModelCar.class);

        // Then
        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(carModelResult.getId(), modelCar.getId());
        assertEquals(carModelResult.getBrandCar().getId(), modelCar.getBrandCar().getId());
        assertEquals(carModelResult.getBrandCar().getBrandName(), modelCar.getBrandCar().getBrandName());
    }
}