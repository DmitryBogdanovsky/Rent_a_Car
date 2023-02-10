package my.service;


import my.dao.UserRepository;
import my.dto.UserAuthenticationDto;
import my.exception.MyException;
import my.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserAuthenticationDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new MyException("User not found! Name: " + email);

        return modelMapper.map(user, UserAuthenticationDto.class);
    }
}
