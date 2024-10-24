package chornarin.com.kh.online_course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import chornarin.com.kh.online_course.models.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {

    Optional<Rate> findById(Long id); // Ensure proper method naming



}
