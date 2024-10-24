package chornarin.com.kh.online_course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chornarin.com.kh.online_course.models.Category;
import lombok.NonNull;


@Repository
@NonNull
public interface CategoryRepository  extends JpaRepository<Category, Long> {
    Optional<Category> findById(long l);
    Optional<Category> findByName(String name);


}
