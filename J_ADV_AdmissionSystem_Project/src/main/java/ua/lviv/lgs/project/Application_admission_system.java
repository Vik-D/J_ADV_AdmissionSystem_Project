package ua.lviv.lgs.project;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import ua.lviv.lgs.project.domain.Faculty;
import ua.lviv.lgs.project.service.FacultyService;

@SpringBootApplication
@ComponentScan({"ua.lviv.lgs.project"})
public class Application_admission_system {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application_admission_system.class, args);
		FacultyService facultyService = ctx.getBean(FacultyService.class);

//		Faculty faculty1 = new Faculty();
//		faculty1.setFacultyName("Mathematics");
//		Set <String> subjectsSet1 = new HashSet<>();
//		subjectsSet1.add("Algebra");
//		subjectsSet1.add("Geometry");
//		subjectsSet1.add("Ukr. language");
//		faculty1.setSubjectsList(subjectsSet1);
//		faculty1.setAdmittanceQuota((short) 4);
//		
//		Faculty faculty2 = new Faculty();
//		faculty2.setFacultyName("Philology");
//		Set <String> subjectsSet2 = new HashSet<>();
//		subjectsSet2.add("English");
//		subjectsSet2.add("Ukr. language");
//		subjectsSet2.add("Ukr. literature");
//		faculty2.setSubjectsList(subjectsSet2);
//		faculty2.setAdmittanceQuota((short) 4);
//		
//		Faculty faculty3 = new Faculty();
//		faculty3.setFacultyName("Chemistry");
//		Set <String> subjectsSet3 = new HashSet<>();
//		subjectsSet3.add("Chemistry");
//		subjectsSet3.add("Physics");
//		subjectsSet3.add("Ukr. language");
//		faculty3.setSubjectsList(subjectsSet3);
//		faculty3.setAdmittanceQuota((short) 4);
//		
//		Faculty faculty4 = new Faculty();
//		faculty4.setFacultyName("Biology");
//		Set <String> subjectsSet4 = new HashSet<>();
//		subjectsSet4.add("Botanics");
//		subjectsSet4.add("Ukr. language");
//		subjectsSet4.add("Zoology");
//		faculty4.setSubjectsList(subjectsSet4);
//		faculty4.setAdmittanceQuota((short) 4);
//		
//		Faculty faculty5 = new Faculty();
//		faculty5.setFacultyName("Informatics");
//		Set <String> subjectsSet5 = new HashSet<>();
//		subjectsSet5.add("Informatics");
//		subjectsSet5.add("Computer basics");
//		subjectsSet5.add("Ukr. language");
//		faculty5.setSubjectsList(subjectsSet5); 
//		faculty5.setAdmittanceQuota((short) 4);
//		
//		facultyService.save(faculty1);
//		facultyService.save(faculty2);
//		facultyService.save(faculty3);
//		facultyService.save(faculty4);
//		facultyService.save(faculty5);

	}

}
