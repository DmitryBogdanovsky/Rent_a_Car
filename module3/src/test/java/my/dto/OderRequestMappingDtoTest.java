package my.dto;


import my.model.order.Order;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OderRequestMappingDtoTest extends InitMappingDtoTest{
    OrderRequestDto targetObject;
    final int NUMBER_OF_FIELDS = 4;

    @Test
    public void mapModelToDto() {
        // Given

        // When
        targetObject = modelMapper.map(order, OrderRequestDto.class);

        // Then

        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(targetObject.getCarId(), null);
        assertEquals(targetObject.getStartDate(), order.getStartDate());
        assertEquals(targetObject.getEndDate(), order.getEndDate());
    }

    @Test
    public void mapDtoToModel() {
        // Given
        targetObject = modelMapper.map(order, OrderRequestDto.class);

        // When
        Order orderResult = modelMapper.map(targetObject, Order.class);

        // Then
        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(orderResult.getStartDate(), order.getStartDate());
        assertEquals(orderResult.getEndDate(), order.getEndDate());
    }
}
