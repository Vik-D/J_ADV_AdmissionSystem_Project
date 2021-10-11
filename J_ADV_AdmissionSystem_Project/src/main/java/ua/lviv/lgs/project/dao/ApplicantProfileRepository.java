package ua.lviv.lgs.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.project.domain.ApplicantProfile;

@Repository
public interface ApplicantProfileRepository extends JpaRepository<ApplicantProfile, Integer> {
 
	Optional<ApplicantProfile> findById(Integer id);
}
 