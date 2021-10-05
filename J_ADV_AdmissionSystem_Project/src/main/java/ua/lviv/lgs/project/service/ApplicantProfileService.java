package ua.lviv.lgs.project.service;

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
}
