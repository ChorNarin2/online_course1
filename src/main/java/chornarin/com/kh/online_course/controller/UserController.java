package chornarin.com.kh.online_course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chornarin.com.kh.online_course.services.configservice.UserService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/all")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getusers")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getallUsers(){
        return ResponseEntity.ok(userService.allUsers());
    }

    
    @GetMapping("/getadmin")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public String getalladmin(){
        return "I am your admin";
    }
}
