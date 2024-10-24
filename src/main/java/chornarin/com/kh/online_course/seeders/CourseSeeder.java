package chornarin.com.kh.online_course.seeders;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import chornarin.com.kh.online_course.models.Course;
import chornarin.com.kh.online_course.models.Category;
import chornarin.com.kh.online_course.repository.CategoryRepository;
import chornarin.com.kh.online_course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CourseSeeder implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;  
    @Override
    public void run(String... args) throws Exception {
        seedCourses();
    }

    private void seedCourses() {
        if (courseRepository.count() == 0) {
            Category webDevCategory = categoryRepository.findByName("Web Development").orElse(null);

            // seed Course if the table is empty
            if (webDevCategory != null) {
                Course webCourse = new Course();
                webCourse.setName("Complete Web Development");
                webCourse.setDescription("Learn to build full-stack web applications.");
                webCourse.setCategory(webDevCategory);
                courseRepository.save(webCourse);
            }
        }
    }
}
