package chornarin.com.kh.online_course.seeders;


import lombok.RequiredArgsConstructor;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import chornarin.com.kh.online_course.models.Role;
import chornarin.com.kh.online_course.models.Enums.RoleEnum;
import chornarin.com.kh.online_course.repository.RoleRepository;

import java.util.*;

// Marks this class as a Spring component to be managed by the Spring container
@Component
// Implements ApplicationListener to listen for the ContextRefreshedEvent, which is fired when the Spring context is fully initialized
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    // Injecting the RoleRepository to interact with the database for Role entity operations
    private final RoleRepository roleRepository;

    // Constructor-based dependency injection
    // public RoleSeeder(RoleRepository roleRepository) {
    //     this.roleRepository = roleRepository;
    // }

    // This method is triggered automatically when the application context is refreshed/loaded
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // Calling the method to load default roles into the database
        this.loadRoles();
    }

    // Private method to seed roles into the database
    private void loadRoles() {
        // Define an array of RoleEnum values (USER, ADMIN, SUPER_ADMIN)
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN };

        // Create a map that associates each role with its description
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
            RoleEnum.USER, "Default user role",
            RoleEnum.ADMIN, "Administrator role",
            RoleEnum.SUPER_ADMIN, "Super Administrator role"
        );

        // Iterate through the roleNames array using a stream
        Arrays.stream(roleNames).forEach((roleName) -> {
            // Check if the role already exists in the database by querying the RoleRepository
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            // If the role is present, print it; otherwise, create and save a new role
            optionalRole.ifPresentOrElse(System.out::println, () -> {
                // Create a new Role entity
                Role roleToCreate = new Role();

                // Set the name and description of the new role
                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                // Save the newly created role to the database
                roleRepository.save(roleToCreate);
            });
        });
    }
}
