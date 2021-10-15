package ua.lviv.lgs.project.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.project.dao.FacultyRepository;
import ua.lviv.lgs.project.domain.Faculty;

@Service
public class FacultyService {

	private Logger LOGGER = LoggerFactory.getLogger(FacultyService.class);

	@Autowired
	private FacultyRepository facultyRepository;

	public List<Faculty> getAllFaculties() {
		LOGGER.debug("Getting all faculties");
		return facultyRepository.findAll().stream()
				.sorted((s1, s2) -> s1.getFacultyName().compareToIgnoreCase(s2.getFacultyName()))
				.collect(Collectors.toList());
	}

	public Faculty findOneFacultyById(Integer id) {
		LOGGER.debug("Getting faculty {} by id : " + id);
		return facultyRepository.findById(id).get();
	}

	public String getFacultyName(Integer id) {
		LOGGER.debug("Getting faculty name by id : " + id);
		return facultyRepository.findById(id).get().getFacultyName();
	}

	public Set<String> getSubjectsListByFacultyID(Integer id) {
		LOGGER.debug("Getting faculty`s subjects-list by faculty id : " + id);
		return facultyRepository.findById(id).get().getSubjectsList();
	}

	public Faculty save(Faculty faculty) {
		LOGGER.debug("Creating faculty {} : " + faculty);
		return facultyRepository.saveAndFlush(faculty);
	}

}
