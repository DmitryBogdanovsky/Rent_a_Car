package my.service;

import my.dao.UserRepository;
import my.dto.UserDto;
import my.dto.UserResponseDto;
import my.exception.MyException;
import my.model.order.OrderStatus;
import my.model.user.RoleEnum;
import my.model.user.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserResponseDto addUser(UserDto userDto) {
        final String DEFAULT_ROLE = "USER";

        if (userRepository.existsByEmail(userDto.getEmail()))
            throw new MyException("Email is already used to register");

        if (userRepository.existsByUserDetails_PhoneNumber(userDto.getUserDetails().getPhoneNumber()))
            throw new MyException("Phone number is already used to register");

        User user = modelMapper.map(userDto, User.class);

        if (user.getId() != null && userRepository.existsById(user.getId())) {
            throw new MyException("User already exists! Id: " + user.getId());
        }
        user.getUserDetails().setUser(user);
        user.setPassword("{noop}" +  user.getPassword());

        user.setRoleEnum(RoleEnum.valueOf(DEFAULT_ROLE));

        return modelMapper.map(userRepository.save(user), UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(UserDto updateUser) {
        User user =userRepository.findById(updateUser.getId())
                .orElseThrow(() -> new MyException("User not found!: " + updateUser.getId()));
        user.setEmail(updateUser.getEmail());
        return modelMapper.map(userRepository.save(user), UserResponseDto.class);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id))
            throw new MyException("User not found! Id: " + id);

        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public long countAllUsers() {
        return userRepository.count();
    }

    @Override
    @Transactional
    public List<UserResponseDto> findAllUsersPageable(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size)).stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public long countAllUsersByOrderStatus(OrderStatus orderStatus) {
        return userRepository.countByOrdersOrderStatus(orderStatus);
    }

    @Override
    @Transactional
    public List<UserResponseDto> findAllUsersByOrderStatusPageable(OrderStatus orderStatus, int page, int size) {
        Page<User> users = userRepository.findByOrders_OrderStatus(orderStatus, PageRequest.of(page, size));
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public UserResponseDto findUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new MyException("User not found! Id: " + userId));

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    @Transactional
    public UserResponseDto findUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        if (user == null)
            throw new UsernameNotFoundException("User not found! Username: " + auth.getName());

        return modelMapper.map(user, UserResponseDto.class);
    }

}
