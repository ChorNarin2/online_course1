package chornarin.com.kh.online_course.services.configservice;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import chornarin.com.kh.online_course.dtos.LoginUserDto;
import chornarin.com.kh.online_course.dtos.RegisterUserDto;
import chornarin.com.kh.online_course.models.Role;
import chornarin.com.kh.online_course.models.User;
import chornarin.com.kh.online_course.models.Enums.RoleEnum;
import chornarin.com.kh.online_course.repository.RoleRepository;
import chornarin.com.kh.online_course.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository repository;

    public User signup(RegisterUserDto input) {
        Optional<Role> optionalRole = repository.findByName(RoleEnum.USER);
        if(optionalRole.isEmpty()){
            throw new RuntimeException("Role of user is empty");
        }
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
