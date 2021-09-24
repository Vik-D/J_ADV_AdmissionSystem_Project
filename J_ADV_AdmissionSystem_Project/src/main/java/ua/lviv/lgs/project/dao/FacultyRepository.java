package ua.lviv.lgs.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.project.domain.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Short> {

}
 