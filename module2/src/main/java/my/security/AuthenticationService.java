package my.security;


import my.dto.UserAuthenticationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authService")
public class AuthenticationService implements UserDetailsService {
    @Autowired
    my.service.UserAuthenticationService userAuthService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserAuthenticationDto user = userAuthService.findUserByEmail(email);

            if (user == null)
                throw new UsernameNotFoundException("User not found: " + email);

            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    true, true, true, true,
                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRoleEnum()))
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User not found: " + email, e);
        }
    }

}
