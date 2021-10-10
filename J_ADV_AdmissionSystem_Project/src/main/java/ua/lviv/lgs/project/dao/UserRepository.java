   
package ua.lviv.lgs.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.project.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findOneByEmail(String email);
	Optional<User> findById(Integer id);
}
