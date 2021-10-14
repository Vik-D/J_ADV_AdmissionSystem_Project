package ua.lviv.lgs.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.project.dao.ApplicantProfileRepository;
import ua.lviv.lgs.project.domain.ApplicantProfile;

@Service
public class ApplicantProfileService {

	@Autowired
	private ApplicantProfileRepository applicantProfileRepository;

	public ApplicantProfile save(ApplicantProfile profile) {
		return applicantProfileRepository.save(profile);
	}
	
	public ApplicantProfile update(ApplicantProfile profile) {
		return applicantProfileRepository.saveAndFlush(profile);   
	}

	public List<ApplicantProfile> findAllProfiles() {
		return applicantProfileRepository.findAll().stream()
				.sorted((prf1, prf2) -> prf1.getUser().getSurname().compareToIgnoreCase(prf2.getUser().getSurname()))
				.collect(Collectors.toList());
	}

	public List<ApplicantProfile> findAllNonApprovedProfilesSortedById() {
		return applicantProfileRepository.findAll().stream().filter(prf -> prf.isApprooved() == false)
				.sorted((prf1, prf2) -> prf1.getProfileId() - prf2.getProfileId()).collect(Collectors.toList());
	}

	public ApplicantProfile findProfileById(Integer id) {
		return applicantProfileRepository.getById(id);
	}

	public ApplicantProfile findProfileByEmail(String email) {
		return applicantProfileRepository.findAll().stream()
				.filter(prf -> prf.getUser().getEmail().equalsIgnoreCase(email)).findAny().get();
	}

	public List<String> findAllProfileEmails() {
		return applicantProfileRepository.findAll().stream().map(prf -> prf.getUser().getEmail())
				.collect(Collectors.toList());
	}

	public List<ApplicantProfile> findAllProfilesByFacultyId(Integer id) {
		return applicantProfileRepository.findAll().stream().filter(prf -> prf.getFaculty().getFacultyId() == id)
				.collect(Collectors.toList());
	}
	
	public List<ApplicantProfile> getAllApprovedApplicantsByFacultyIDSortedDesc(Integer id) {
		
		return applicantProfileRepository.findAll().stream().filter(prf -> prf.isEnrolled() == true)
				.filter(prf -> prf.isApprooved() == true).filter(prf -> prf.getFaculty().getFacultyId() == id)
				.sorted((prf1, prf2) -> prf2.getTotalMarksAmount() - prf1.getTotalMarksAmount())
				.collect(Collectors.toList()); 
	}
}
