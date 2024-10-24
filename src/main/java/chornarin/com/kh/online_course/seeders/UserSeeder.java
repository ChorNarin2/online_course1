package chornarin.com.kh.online_course.seeders;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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
public class UserSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    private void SeedUser(){
        // Logic to seed users into the database
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
       
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setEmail("user@gmail.com");
        registerUserDto.setFullName("narin");
        registerUserDto.setPassword("12345");

        Optional<chornarin.com.kh.online_course.models.User> optionalUser = userRepository.findByEmail(registerUserDto.getEmail());

        if(optionalRole.isEmpty() || optionalUser.isPresent()){
            return;
        }

        User user = new User();
        user.setEmail(registerUserDto.getEmail());
        user.setFullName(registerUserDto.getFullName());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }


    @Override
    public void run(String... args) throws Exception {
        this.SeedUser();
    }
}
