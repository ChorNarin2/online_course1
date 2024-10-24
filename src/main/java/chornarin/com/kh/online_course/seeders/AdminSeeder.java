package chornarin.com.kh.online_course.seeders;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import chornarin.com.kh.online_course.dtos.RegisterUserDto;
import chornarin.com.kh.online_course.models.Role;
import chornarin.com.kh.online_course.models.User;
import chornarin.com.kh.online_course.models.Enums.RoleEnum;
import chornarin.com.kh.online_course.repository.RoleRepository;
import chornarin.com.kh.online_course.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AdminSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    private void createSuperAdministrator() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setFullName("narin");
        registerUserDto.setEmail("narinkk@gmail.com");
        registerUserDto.setPassword("12345678");


        Optional<Role> optionalRole =  roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser =  userRepository.findByEmail(registerUserDto.getEmail());

        if(optionalRole.isEmpty() || optionalUser.isPresent()){
            return;
        }

        User user = new User();
        user.setFullName(registerUserDto.getFullName());
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
        

    }

    @Override
    public void run(String... args) throws Exception {
        this.createSuperAdministrator();
    }
}
