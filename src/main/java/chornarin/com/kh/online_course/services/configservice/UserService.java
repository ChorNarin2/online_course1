package chornarin.com.kh.online_course.services.configservice;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import chornarin.com.kh.online_course.dtos.RegisterUserDto;
import chornarin.com.kh.online_course.models.Role;
import chornarin.com.kh.online_course.models.User;
import chornarin.com.kh.online_course.models.Enums.RoleEnum;
import chornarin.com.kh.online_course.repository.RoleRepository;
import chornarin.com.kh.online_course.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            throw new RuntimeException("Admin role not found");
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }
}