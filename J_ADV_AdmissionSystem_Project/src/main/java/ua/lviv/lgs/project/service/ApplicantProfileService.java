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

	public List<ApplicantProfile> findAllProfiles() {
		return applicantProfileRepository.findAll().stream()
				.sorted((prf1, prf2) -> prf1.getUser().getSurname().compareToIgnoreCase(prf2.getUser().getSurname()))
				.collect(Collectors.toList());
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
}
