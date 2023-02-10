package my.service;

import my.dto.UserDetailsDto;
import my.dto.UserDto;
import my.exception.MyException;
import my.model.user.RoleEnum;
import my.model.user.User;
import my.dao.UserRepository;
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
public class UserServiceImplTest {

    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl targetObject;

    public UserServiceImplTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void addNewUserShouldThrowOnDuplicateId() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUserDetails(new UserDetailsDto());
        userDto.getUserDetails().setPhoneNumber("+375291112233");

        // When
        when(userRepository.existsById(userDto.getId()))
                .thenReturn(true);

        // Then
        assertThrows(MyException.class, () -> targetObject.addUser(userDto));
    }


    @Test
    public void addNewUserShouldThrowOnDuplicateEmail() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("user@user.com");
        userDto.setUserDetails(new UserDetailsDto());
        userDto.getUserDetails().setPhoneNumber("+375291112233");

        // When
        when(userRepository.existsByEmail(userDto.getEmail()))
                .thenReturn(true);

        // Then
        assertThrows(MyException.class, () -> targetObject.addUser(userDto));
    }

    @Test
    public void addNewUserShouldThrowOnDuplicateUsername() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("user@user.com");
        userDto.setUserDetails(new UserDetailsDto());
        userDto.getUserDetails().setPhoneNumber("+375291112233");

        // When
        when(userRepository.existsByEmail(userDto.getEmail()))
                .thenReturn(true);

        // Then
        assertThrows(MyException.class, () -> targetObject.addUser(userDto));
    }

    @Test
    public void addNewUserShouldThrowOnDuplicatePhoneNumber() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("user@user.com");
        userDto.setUserDetails(new UserDetailsDto());
        userDto.getUserDetails().setPhoneNumber("+375291112233");

        // When
        when(userRepository.existsByUserDetails_PhoneNumber(userDto.getUserDetails().getPhoneNumber()))
                .thenReturn(true);

        // Then
        assertThrows(MyException.class, () -> targetObject.addUser(userDto));
    }

    @Test
    public void addNewUserShouldCallUserDao() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("user@user.com");
        userDto.setPassword("password");
        userDto.setRoleEnum(RoleEnum.USER);
        userDto.setUserDetails(new UserDetailsDto());
        userDto.getUserDetails().setPhoneNumber("+375291112233");

        // When
        when(userRepository.save(any())).thenReturn(new User());
        targetObject.addUser(userDto);

        // Then
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argument.capture());
        assertEquals(argument.getValue().getId(), userDto.getId());
        assertEquals(argument.getValue().getEmail(), userDto.getEmail());
        assertEquals(argument.getValue().getUserDetails().getPhoneNumber(), userDto.getUserDetails().getPhoneNumber());
    }

    @Test
    public void deleteUserShouldThrowWhenUserDoesNotExist() {
        // Given
        int id = 1;

        // When
        when(userRepository.existsById(id))
                .thenReturn(false);

        // Then
        assertThrows(MyException.class, () -> targetObject.deleteUser(id));
    }

    @Test
    public void deleteUserShouldCallDao() {
        // Given
       Integer id = 1;

        // When
        when(userRepository.existsById(id))
                .thenReturn(true);
        targetObject.deleteUser(id);

        // Then
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(userRepository).deleteById(argument.capture());
        assertEquals(argument.getValue(), id);
    }
}
