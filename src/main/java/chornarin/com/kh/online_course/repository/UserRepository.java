package chornarin.com.kh.online_course.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import chornarin.com.kh.online_course.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email); // Correct usage
    Optional<User> findById(Long id); // Correct method name
    
}