package ua.lviv.lgs.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.project.domain.ApplicantProfile;

@Repository
public interface ApplicantProfileRepository extends JpaRepository<ApplicantProfile, Integer> {
 
	Optional<ApplicantProfile> findById(Integer id);
	Optional<ApplicantProfile> findByName(String name);
	Optional<ApplicantProfile> findByEmail(String email);
	List<ApplicantProfile> getAll();
	List<ApplicantProfile> getAllApprooved(boolean isApprooved);
	List<ApplicantProfile> getAllAdmitted(boolean isAdmitted);
}
 