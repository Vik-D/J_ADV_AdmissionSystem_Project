package ua.lviv.lgs.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.lgs.project.domain.ApplicantProfile;
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

	@GetMapping("/approvals")
	public String allApplicantsForApprovals(HttpServletRequest req) {
		req.setAttribute("profiles_list", applicantProfileService.findAllNonApprovedProfilesSortedById());

		return "approvals";
	}

	@RequestMapping(value = "/approvals", method = RequestMethod.POST)
	public String applicantCheckAndApprovals(HttpServletRequest req) {
		String[] subj_arr = req.getParameterValues("subjectName");
		String[] marks_arr = req.getParameterValues("markApprove");
		Map<String, Byte> marksTable = new HashMap<String, Byte>();
		Short marksTotal = 0;
		for (int i = 0; i < subj_arr.length; i++) {
			marksTable.put(subj_arr[i], Byte.parseByte(marks_arr[i]));
			marksTotal = (short) (marksTotal + Short.parseShort(marks_arr[i]));
		}
		ApplicantProfile profile = applicantProfileService
				.findProfileById(Integer.parseInt(req.getParameter("profileID")));
		profile.setMarksTable(marksTable);
		profile.setTotalMarksAmount(marksTotal);
		profile.setApprooved(true);
		applicantProfileService.update(profile);

		return "redirect:/approvals";
	}
	
	
//   *****************   TO BE REVIEWED  *****************
	
//	@GetMapping("/fileDownload?={profileID}")
//	public void getProfileCertificate(@PathVariable("profileID") Integer id, HttpServletRequest req, HttpServletResponse res)
//			throws IOException {
////		ApplicantProfile profile = applicantProfileService.findProfileById(id);
//		
//		byte[] resource = applicantProfileService.findProfileById(id).getMarksCertificate();
//		
////		res.setContentType("APPLICATION/OCTET-STREAM");
////		res.setHeader("Content-Disposition","attachment; filename=\"" + profile.getUser().getSurname() + "\"");
//		   
//		res.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//		res.getOutputStream().write(resource);
//		res.getOutputStream().close();
//
//	}

//	@GetMapping("/fileDownload?={profileID}")
//	public ResponseEntity<Resource> getProfileCertificate(@PathVariable("profileID") Integer id, HttpServletRequest req, HttpServletResponse res) throws IOException {
//		ApplicantProfile profile = applicantProfileService.findProfileById(id);
//
//		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION,
//						"attachment; filename=\"" + profile.getUser().getSurname() + "\"")
//				.body(new ByteArrayResource(profile.getMarksCertificate()));
//	}

}
