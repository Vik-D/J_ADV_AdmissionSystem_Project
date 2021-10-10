package ua.lviv.lgs.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ua.lviv.lgs.project.service.ApplicantProfileService;

@Controller
public class ApplicantController {
	
	@Autowired
	private ApplicantProfileService applicantProfileService;
	
	@GetMapping("/applicants")
	public String listAllApplicants(HttpServletRequest req) {
		req.setAttribute("applicants_list", applicantProfileService.findAllProfiles()); 
		req.setAttribute("mode", "APPLICANTS_LIST");
		req.setAttribute("list", "Applicants list");
		return "home";
	}
	
}
