package ua.lviv.lgs.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.project.domain.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Short> {

	
}
 