package chornarin.com.kh.online_course.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import chornarin.com.kh.online_course.models.Role;
import chornarin.com.kh.online_course.models.Enums.RoleEnum;

//Data access layer for The Role entity.
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(RoleEnum name);

}
