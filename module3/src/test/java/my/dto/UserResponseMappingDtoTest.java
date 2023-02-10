package my.dto;

import my.model.user.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserResponseMappingDtoTest extends InitMappingDtoTest{

    UserResponseDto targetObject;
    final int NUMBER_OF_FIELDS = 5;

    @Test
    public void mapModelToDto() {
        // Given

        // When
        targetObject = modelMapper.map(user, UserResponseDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), user.getId());
        assertEquals(targetObject.getEmail(), user.getEmail());
        assertEquals(targetObject.getRoleEnum(), user.getRoleEnum());

        assertEquals(targetObject.getUserDetails().getFirstName(), user.getUserDetails().getFirstName());
        assertEquals(targetObject.getUserDetails().getLastName(), user.getUserDetails().getLastName());
        assertEquals(targetObject.getUserDetails().getPhoneNumber(), user.getUserDetails().getPhoneNumber());
        assertEquals(targetObject.getUserDetails().getBirthDate(), user.getUserDetails().getBirthDate());
    }

    @Test
    public void mapDtoToModel() {
        // Given
        targetObject = modelMapper.map(user, UserResponseDto.class);

        // When
        User userResult = modelMapper.map(targetObject, User.class);

        // Then
        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(userResult.getId(), user.getId());
        assertEquals(userResult.getEmail(), user.getEmail());
        assertEquals(userResult.getRoleEnum(), user.getRoleEnum());

        assertEquals(userResult.getUserDetails().getFirstName(), user.getUserDetails().getFirstName());
        assertEquals(userResult.getUserDetails().getLastName(), user.getUserDetails().getLastName());
        assertEquals(userResult.getUserDetails().getPhoneNumber(), user.getUserDetails().getPhoneNumber());
        assertEquals(userResult.getUserDetails().getBirthDate(), user.getUserDetails().getBirthDate());
    }

}
