package server.Services;

import eu.fraho.spring.securityJwt.base.dto.JwtUser;
import server.Daos.UserDao;
import server.Models.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser = userDao.getUserByUsername(username);
        List<String> roles = userDao.getUserRoles(username);
        
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername(username);
        jwtUser.setPassword(dbUser.getPassword());

        for (String role : roles) {
            jwtUser.getAuthorities().add(new SimpleGrantedAuthority(role));
        }

        jwtUser.setAccountNonExpired(true);
        jwtUser.setAccountNonLocked(true);
        jwtUser.setApiAccessAllowed(true);
        jwtUser.setCredentialsNonExpired(true);
        jwtUser.setEnabled(true);
        return jwtUser;
    }
}