package my.dto;


import my.model.order.Order;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OrderMappingDtoTest extends InitMappingDtoTest{

    OrderDto targetObject;
    final int NUMBER_OF_FIELDS = 8;

    @Test
    public void mapModelToDto() {
        // Given

        // When
        targetObject = modelMapper.map(order, OrderDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), order.getId());
        assertEquals(targetObject.getDateOfOrder(), order.getDateOfOrder());
        assertEquals(targetObject.getOrderStatus(), order.getOrderStatus());
        assertEquals(targetObject.getStartDate(), order.getStartDate());
        assertEquals(targetObject.getEndDate(), order.getEndDate());
        assertEquals(targetObject.getTotalPrice(), order.getTotalPrice());
        assertEquals(targetObject.getUser().getId(), order.getUser().getId());
        assertEquals(targetObject.getCar().getId(), order.getCar().getId());
    }

    @Test
    public void mapDtoToModel() {
        // Given
        targetObject = modelMapper.map(order, OrderDto.class);

        // When
        Order orderResult = modelMapper.map(targetObject, Order.class);

        // Then
        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(orderResult.getId(), order.getId());
        assertEquals(orderResult.getDateOfOrder(), order.getDateOfOrder());
        assertEquals(orderResult.getOrderStatus(), order.getOrderStatus());
        assertEquals(orderResult.getStartDate(), order.getStartDate());
        assertEquals(orderResult.getEndDate(), order.getEndDate());
        assertEquals(orderResult.getTotalPrice(), order.getTotalPrice());
        assertEquals(orderResult.getUser().getId(), order.getUser().getId());
        assertEquals(orderResult.getCar().getId(), order.getCar().getId());
    }

}
