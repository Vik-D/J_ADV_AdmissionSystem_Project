package ua.lviv.lgs.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.project.dao.FacultyRepository;
import ua.lviv.lgs.project.domain.Faculty;

@Service
public class FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;

	public List<Faculty> getAllFaculties() {
		return facultyRepository.findAll();
	}

	public Faculty findOneFacultyById(Short id) {
		return facultyRepository.findById(id).get();
	}

	public String getFacultyName(Short id) {
		return facultyRepository.findById(id).get().getFacultyName();
	}

	public List<String> getSubjectsListByFacultyID(Short id) {
		return facultyRepository.findById(id).get().getSubjectsList();
	}

}
