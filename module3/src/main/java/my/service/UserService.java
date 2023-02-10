package my.service;

import my.dto.UserDto;
import my.dto.UserResponseDto;
import my.model.order.OrderStatus;

import java.util.List;


public interface UserService {

    UserResponseDto addUser(UserDto addUser);


    UserResponseDto updateUser(UserDto updatedUser);


    void deleteUser(Integer id);


    long countAllUsers();


    List<UserResponseDto> findAllUsersPageable(int page, int size);


    long countAllUsersByOrderStatus(OrderStatus orderStatus);


    List<UserResponseDto> findAllUsersByOrderStatusPageable(OrderStatus orderStatus, int page, int size);


    UserResponseDto findUserById(Integer userId);


    UserResponseDto findUser();
}
