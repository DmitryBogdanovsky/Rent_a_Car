package my.dto;


import my.model.car.BrandCar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BrandCarMappingDtoTest extends InitMappingDtoTest {
    BrandCarDto targetObject;
    final int NUMBER_OF_FIELDS = 2;

    @Test
    public void mapModelToDto() {
        // Given

        // When
        targetObject = modelMapper.map(brandCar, BrandCarDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), brandCar.getId());
        assertEquals(targetObject.getBrandName(), brandCar.getBrandName());
    }

    @Test
    public void mapDtoToModel() {
        // Given
        targetObject = modelMapper.map(brandCar, BrandCarDto.class);

        // When
        BrandCar carBrandResult = modelMapper.map(targetObject, BrandCar.class);

        // Then
        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(carBrandResult.getId(), brandCar.getId());
        assertEquals(carBrandResult.getBrandName(), brandCar.getBrandName());
    }
}