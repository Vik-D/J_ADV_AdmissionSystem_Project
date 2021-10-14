package ua.lviv.lgs.project.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.project.dao.FacultyRepository;
import ua.lviv.lgs.project.domain.Faculty;

@Service
public class FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;

	public List<Faculty> getAllFaculties() {
		return facultyRepository.findAll().stream()
				.sorted((s1, s2) -> s1.getFacultyName().compareToIgnoreCase(s2.getFacultyName()))
				.collect(Collectors.toList());
	}

	public Faculty findOneFacultyById(Integer id) {
		return facultyRepository.findById(id).get();
	}

	public String getFacultyName(Integer id) {
		return facultyRepository.findById(id).get().getFacultyName();
	}

	public Set<String> getSubjectsListByFacultyID(Integer id) {
		return facultyRepository.findById(id).get().getSubjectsList();
	}

	public Faculty save(Faculty faculty) {
		return facultyRepository.saveAndFlush(faculty);
	}
	
}
