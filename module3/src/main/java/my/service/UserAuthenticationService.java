package my.service;

import my.dto.UserAuthenticationDto;

public interface UserAuthenticationService {
    UserAuthenticationDto findUserByEmail(String username);
}
