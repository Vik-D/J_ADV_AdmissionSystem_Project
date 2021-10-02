package ua.lviv.lgs.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ua.lviv.lgs.project.service.FacultyService;

@Controller
public class FacultyController {

	@Autowired
	private FacultyService facultyService;

	@GetMapping("/faculties")
	public String initFacultiesList(HttpServletRequest req) {
		req.setAttribute("faculties_list", facultyService.getAllFaculties());
		req.setAttribute("mode", "FACULTIES_LIST");
		req.setAttribute("list", "Faculties list");
		return "home";
	}

	@GetMapping("/enroll")
	public String enroll(HttpServletRequest req) {
		Short faculty_id = Short.parseShort(req.getQueryString().substring(3));
		req.setAttribute("subjects", facultyService.getSubjectsListByFacultyID(faculty_id));
		req.setAttribute("list", facultyService.getFacultyName(faculty_id));
		req.setAttribute("facultyID", faculty_id);
		return "faculty-page";
	}

//	********************* TO BE REVIEWED *************************

//	@GetMapping("/faculty-page")
//	public String subjects(HttpServletRequest req, Model model) {
//		return "faculty-page";
//	}

}
