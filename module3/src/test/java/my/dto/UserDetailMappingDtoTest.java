package my.dto;


import my.model.user.UserDetails;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDetailMappingDtoTest extends InitMappingDtoTest{

   UserDetailsDto targetObject;
    final int NUMBER_OF_FIELDS = 5;

    @Test
    public void mapModelToDto() {
        // Given


        // When
        targetObject = modelMapper.map(userDetails, UserDetailsDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(targetObject.getBirthDate(), userDetails.getBirthDate());
        assertEquals(targetObject.getPhoneNumber(), userDetails.getPhoneNumber());
        assertEquals(targetObject.getFirstName(), userDetails.getFirstName());
        assertEquals(targetObject.getLastName(), userDetails.getLastName());
    }

    @Test
    public void mapDtoToModel() {
        // Given
        targetObject = modelMapper.map(userDetails, my.dto.UserDetailsDto.class);

        // When
        UserDetails userDetailsResult = modelMapper.map(targetObject, UserDetails.class);

        // Then
        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(userDetailsResult.getBirthDate(), userDetails.getBirthDate());
        assertEquals(userDetailsResult.getPhoneNumber(), userDetails.getPhoneNumber());
        assertEquals(userDetailsResult.getLastName(), userDetails.getLastName());
        assertEquals(userDetailsResult.getFirstName(), userDetails.getFirstName());
    }

}
