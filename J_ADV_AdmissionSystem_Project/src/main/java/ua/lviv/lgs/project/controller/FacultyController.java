package ua.lviv.lgs.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.lviv.lgs.project.domain.ApplicantProfile;
import ua.lviv.lgs.project.domain.Faculty;
import ua.lviv.lgs.project.domain.User;
import ua.lviv.lgs.project.service.ApplicantProfileService;
import ua.lviv.lgs.project.service.FacultyService;
import ua.lviv.lgs.project.service.UserService;

@Controller
public class FacultyController {

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicantProfileService applicantProfileService;

	@GetMapping("/faculties")
	public String initFacultiesList(HttpServletRequest req) {
		req.setAttribute("faculties_list", facultyService.getAllFaculties());
		req.setAttribute("mode", "FACULTIES_LIST");
		req.setAttribute("list", "Faculties list");
		return "home";
	}

	@RequestMapping(value = "/enroll_{facultyID}", method = RequestMethod.GET)
	public String enroll(@PathVariable("facultyID") String facultyID, HttpServletRequest req) {
		Integer faculty_id = Integer.parseInt(facultyID);
		req.getSession().setAttribute("subjects",
				facultyService.getSubjectsListByFacultyID(faculty_id).stream().sorted().collect(Collectors.toList()));
		req.setAttribute("list", facultyService.getFacultyName(faculty_id));
		req.getSession().setAttribute("facultyID", faculty_id);
		return "enroll";
	}

	@RequestMapping(value = "/faculty-page", method = RequestMethod.POST)
	public String applicantEnrollment(Model model, HttpServletRequest req) {
		User user = userService.getUserByUsername(req.getUserPrincipal().getName().trim().toString());
		ApplicantProfile profile = new ApplicantProfile();
		Faculty faculty = facultyService.findOneFacultyById((Integer) req.getSession().getAttribute("facultyID"));
		@SuppressWarnings("unchecked")
		List<String> subj_list = (List<String>) req.getSession().getAttribute("subjects");
		String[] marks_arr = req.getParameterValues("markinput");
		Map<String, Byte> marks = new HashMap<String, Byte>();
		Short marksTotal = 0;
		for (int i = 0; i < subj_list.size(); i++) {
			marks.put(subj_list.get(i), Byte.parseByte(marks_arr[i]));
			marksTotal = (short) (marksTotal + Short.parseShort(marks_arr[i]));
		}

		user.setName(req.getParameter("nameinput"));
		user.setSurname(req.getParameter("surnameinput"));
		user.setEmail(req.getParameter("emailinput"));
		userService.updateUser(user);
		profile.setUser(user);
		profile.setMarksTable(marks);
		profile.setEnrolled(true);
		profile.setTotalMarksAmount(marksTotal);
		profile.setFaculty(faculty);
		applicantProfileService.save(profile);
		req.getSession().setAttribute("applicant",
				applicantProfileService.findProfileByEmail(req.getUserPrincipal().getName()));

		return "redirect:/applicants";
	}

//	@GetMapping("/faculty-page")
//	public String enrolledApplicantsList(HttpServletRequest req) {
//		Integer user_id = userService.getUserByUsername(req.getUserPrincipal().getName().trim().toString()).getId();
//		String fclt_name = facultyService.findOneFacultyById(applicantProfileService.findProfileById(user_id).getProfileId()).getFacultyName();
//		req.setAttribute("applicants_list",
//				facultyService.findOneFacultyById(applicantProfileService.findProfileById(user_id).getProfileId())
//						.getApplicantProfiles().stream().collect(Collectors.toList())); 
//		req.setAttribute("list", fclt_name);
//		return "faculty-page";
//	}

}
