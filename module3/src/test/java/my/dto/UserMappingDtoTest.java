package my.dto;


import my.model.user.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserMappingDtoTest extends InitMappingDtoTest{

    UserDto targetObject;
    final int NUMBER_OF_FIELDS = 5;

    @Test
    public void mapModelToDto() {
        // Given
        // see parent class

        // When
        targetObject = modelMapper.map(user, UserDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), user.getId());
        assertEquals(targetObject.getEmail(), user.getEmail());
        assertEquals(targetObject.getPassword(), user.getPassword());
        assertEquals(targetObject.getRoleEnum(), user.getRoleEnum());

        assertEquals(targetObject.getUserDetails().getFirstName(), user.getUserDetails().getFirstName());
        assertEquals(targetObject.getUserDetails().getLastName(), user.getUserDetails().getLastName());
        assertEquals(targetObject.getUserDetails().getPhoneNumber(), user.getUserDetails().getPhoneNumber());
        assertEquals(targetObject.getUserDetails().getBirthDate(), user.getUserDetails().getBirthDate());
    }

    @Test
    public void mapDtoToModel() {
        // Given
        targetObject = modelMapper.map(user, UserDto.class);

        // When
        User userResult = modelMapper.map(targetObject, User.class);

        // Then
        assertEquals(targetObject.getClass().getDeclaredFields().length, NUMBER_OF_FIELDS);

        assertEquals(userResult.getId(), user.getId());
        assertEquals(userResult.getEmail(), user.getEmail());
        assertEquals(userResult.getPassword(), user.getPassword());
        assertEquals(userResult.getRoleEnum(), user.getRoleEnum());

        assertEquals(userResult.getUserDetails().getFirstName(), user.getUserDetails().getFirstName());
        assertEquals(userResult.getUserDetails().getLastName(), user.getUserDetails().getLastName());
        assertEquals(userResult.getUserDetails().getPhoneNumber(), user.getUserDetails().getPhoneNumber());
        assertEquals(userResult.getUserDetails().getBirthDate(), user.getUserDetails().getBirthDate());
    }

}
