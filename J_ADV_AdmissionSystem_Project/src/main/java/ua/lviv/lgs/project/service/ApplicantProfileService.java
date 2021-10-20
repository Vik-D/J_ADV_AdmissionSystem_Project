package ua.lviv.lgs.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.project.dao.ApplicantProfileRepository;
import ua.lviv.lgs.project.domain.ApplicantProfile;

@Service
public class ApplicantProfileService {

	private Logger LOGGER = LoggerFactory.getLogger(ApplicantProfileService.class);

	@Autowired
	private ApplicantProfileRepository applicantProfileRepository;

	public ApplicantProfile save(ApplicantProfile profile) {
		LOGGER.debug("Creating applicant profile {} : " + profile);
		return applicantProfileRepository.save(profile);
	}

	public ApplicantProfile update(ApplicantProfile profile) {
		LOGGER.debug("Updating applicant profile {} : " + profile);
		return applicantProfileRepository.saveAndFlush(profile);
	}

	public ApplicantProfile findProfileById(Integer id) {
		LOGGER.debug("Getting applicant profile {} by id : " + id);
		return applicantProfileRepository.getById(id);
	}

	public ApplicantProfile findProfileByEmail(String email) {
		LOGGER.debug("Getting applicant profile {} by email : " + email);
		return applicantProfileRepository.findAll().stream().filter(prf -> prf.getUser().getEmail().equals(email))
				.findAny().get();
	}

	public List<String> findAllProfileEmails() {
		LOGGER.debug("Getting all applicant profiles` emails");
		return applicantProfileRepository.findAll().stream().map(prf -> prf.getUser().getEmail())
				.collect(Collectors.toList());
	}

	public List<ApplicantProfile> findAllProfiles() {
		LOGGER.debug("Getting all applicant profiles, order by surnames alphabetically");
		return applicantProfileRepository.findAll().stream()
				.sorted((prf1, prf2) -> prf1.getUser().getSurname().compareToIgnoreCase(prf2.getUser().getSurname()))
				.collect(Collectors.toList());
	}

	public List<ApplicantProfile> findAllNonApprovedProfilesSortedById() {
		LOGGER.debug("Getting all applicant profiles : order by ID, non-approoved only");
		return applicantProfileRepository.findAll().stream().filter(prf -> prf.isApprooved() == false)
				.sorted((prf1, prf2) -> prf1.getProfileId() - prf2.getProfileId()).collect(Collectors.toList());
	}

	public List<ApplicantProfile> findAllProfilesByFacultyId(Integer id) {
		LOGGER.debug("Getting all applicant profiles belonging to one faculty by faculty id : " + id);
		return applicantProfileRepository.findAll().stream().filter(prf -> prf.getFaculty().getFacultyId() == id)
				.collect(Collectors.toList());
	}

	public List<ApplicantProfile> getAllApprovedApplicantsByFacultyIDSortedDesc(Integer id) {
		LOGGER.debug("Getting all applicant profiles by faculty id : " + id
				+ ", approved and enrolled only, order by profile marks-total desc");
		return applicantProfileRepository.findAll().stream().filter(prf -> prf.isEnrolled() == true)
				.filter(prf -> prf.isApprooved() == true).filter(prf -> prf.getFaculty().getFacultyId() == id)
				.sorted((prf1, prf2) -> prf2.getTotalMarksAmount() - prf1.getTotalMarksAmount())
				.collect(Collectors.toList());
	}
}
