package chornarin.com.kh.online_course.seeders;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import chornarin.com.kh.online_course.models.Course;
import chornarin.com.kh.online_course.models.Rate;
import chornarin.com.kh.online_course.models.User;
import chornarin.com.kh.online_course.repository.CourseRepository;
import chornarin.com.kh.online_course.repository.RateRepository;
import chornarin.com.kh.online_course.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RateSeeder implements CommandLineRunner  {

    private final RateRepository rateRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        this.RateSeed();
    }

    private void RateSeed(){

        // Optional<User> user = userRepository.findByEmail("narinkk@gmail.com");
        // Optional<Course> course = courseRepository.findByName("Complete Web Development");
        // if(user.isPresent() && course.isPresent()){
        //     Rate rate = new Rate();
        //     rate.setCourse(course.get());
        //     rate.setUser(user.get());
        //     rate.setRatingValue(5);
        //     rateRepository.save(rate);
        //     System.out.println("Rate saved successfully");
        // }

        Optional<User> user = userRepository.findById(1l); 
        Optional<Course> course = courseRepository.findById(1l);

        if(user.isPresent() && course.isPresent()) {
            Rate rate = new Rate();
            rate.setCourse(course.get());
            rate.setUser(user.get());
            rate.setRatingValue(5);
            rateRepository.save(rate);
            System.out.println("Rate saved successfully");
        }


    }

}
