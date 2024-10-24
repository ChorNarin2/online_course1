package chornarin.com.kh.online_course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chornarin.com.kh.online_course.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,  Long> {

    Optional<Course> findByName(String name);
    
    @Override
    Optional<Course> findById(Long courseId);

}
