package ua.lviv.lgs.project;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.lviv.lgs.project.dao.ApplicantProfileRepository;
import ua.lviv.lgs.project.dao.FacultyRepository;
import ua.lviv.lgs.project.dao.UserRepository;
import ua.lviv.lgs.project.domain.ApplicantProfile;
import ua.lviv.lgs.project.domain.Faculty;
import ua.lviv.lgs.project.domain.Role;
import ua.lviv.lgs.project.domain.User;
import ua.lviv.lgs.project.service.ApplicantProfileService;
import ua.lviv.lgs.project.service.FacultyService;
import ua.lviv.lgs.project.service.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
class JAdv_AdmissionSystem_ProjectApplicationTests {

	@Autowired
	private ApplicantProfileRepository applicantProfileRepository;

	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicantProfileService applicantProfileService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private UserService userService;
	

	@Test
	public void testSaveUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setName("1");
		user.setSurname("1");
		user.setEmail("1@1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(Role.ROLE_USER);

		boolean savedIsTrue = userService.save(user);
		assertTrue(savedIsTrue);
		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User userFromDB = users.get(0);
		assertTrue(userFromDB.getName().equals(user.getName()));
		assertTrue(userFromDB.getSurname().equals(user.getSurname()));
		assertTrue(userFromDB.getEmail().equals(user.getEmail()));
		assertTrue(userFromDB.getPassword().equals(user.getPassword()));
		assertTrue(userFromDB.getPasswordConfirm().equals(user.getPasswordConfirm()));
		assertTrue(userFromDB.getRole().equals(user.getRole()));

		User sameEmailUser = new User();
		sameEmailUser.setName("11");
		sameEmailUser.setSurname("11");
		sameEmailUser.setEmail("1@1"); // setting THE SAME email
		sameEmailUser.setPassword("11");
		sameEmailUser.setPasswordConfirm("11");
		sameEmailUser.setRole(Role.ROLE_USER);

		assertFalse(userService.save(sameEmailUser));
		assertThat(users, hasSize(1));
	}

	@Test
	public void testGetUserByUsername() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setName("1");
		user.setSurname("1");
		user.setEmail("1@1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(Role.ROLE_USER);

		userService.save(user);
		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User getByUsername = userService.getUserByUsername(user.getEmail());
		assertTrue(getByUsername.getName().equals(user.getName()));
		assertTrue(getByUsername.getSurname().equals(user.getSurname()));
		assertTrue(getByUsername.getEmail().equals(user.getEmail()));
		assertTrue(getByUsername.getPassword().equals(user.getPassword()));
		assertTrue(getByUsername.getPasswordConfirm().equals(user.getPasswordConfirm()));
		assertTrue(getByUsername.getRole().equals(user.getRole()));
	}

	@Test
	public void testUpdateUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setName("1");
		user.setSurname("1");
		user.setEmail("1@1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(Role.ROLE_USER);

		userService.save(user);
		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User userFromDB_ToUpdate = users.get(0);
		userFromDB_ToUpdate.setName("2");
		userFromDB_ToUpdate.setSurname("2");
		userFromDB_ToUpdate.setEmail("2@2");
		userFromDB_ToUpdate.setPassword("2");
		userFromDB_ToUpdate.setPasswordConfirm("2");
		userFromDB_ToUpdate.setRole(Role.ROLE_ADMIN);

		userService.updateUser(userFromDB_ToUpdate);
		assertThat(users, hasSize(1));

		User userFromDB = userRepository.findAll().get(0);
		assertTrue(userFromDB.getName().equals("2"));
		assertTrue(userFromDB.getSurname().equals("2"));
		assertTrue(userFromDB.getEmail().equals("2@2"));
		assertTrue(userFromDB.getPassword().equals("2"));
		assertTrue(userFromDB.getPasswordConfirm().equals("2"));
		assertTrue(userFromDB.getRole().equals(Role.ROLE_ADMIN));

//		assertFalse(userFromDB.getName().equals(user.getName()));
//		assertFalse(userFromDB.getSurname().equals(user.getSurname()));
//		assertFalse(userFromDB.getEmail().equals(user.getEmail()));
//		assertFalse(userFromDB.getPassword().equals(user.getPassword()));
//		assertFalse(userFromDB.getPasswordConfirm().equals(user.getPasswordConfirm()));
//		assertFalse(userFromDB.getRole().equals(user.getRole()));

	}

	@Test
	public void testGetAllUsersBySurnameAlphabeticalOrder() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User userA = new User();
		userA.setName("A");
		userA.setSurname("A");
		userA.setEmail("A@A");
		userA.setPassword("1");
		userA.setPasswordConfirm("1");
		userA.setRole(Role.ROLE_USER);

		User userB = new User();
		userB.setName("B");
		userB.setSurname("B");
		userB.setEmail("B@B");
		userB.setPassword("1");
		userB.setPasswordConfirm("1");
		userB.setRole(Role.ROLE_USER);

		User userC = new User();
		userC.setName("C");
		userC.setSurname("C");
		userC.setEmail("C@C");
		userC.setPassword("1");
		userC.setPasswordConfirm("1");
		userC.setRole(Role.ROLE_USER);

		User userD = new User();
		userD.setName("D");
		userD.setSurname("D");
		userD.setEmail("D@D");
		userD.setPassword("1");
		userD.setPasswordConfirm("1");
		userD.setRole(Role.ROLE_ADMIN);

		userRepository.saveAll(Arrays.asList(userC, userD, userB, userA));
		users = userRepository.findAll();
		assertThat(users, hasSize(4));

		List<User> processedList = userService.getAllUsersBySurnameAlphabeticalOrder();
		assertThat(processedList, hasSize(3));
		assertTrue(processedList.get(0).getSurname().equals("A"));
		assertTrue(processedList.get(1).getSurname().equals("B"));
		assertTrue(processedList.get(2).getSurname().equals("C"));
		assertTrue(processedList.get(0).getRole().equals(Role.ROLE_USER));
		assertTrue(processedList.get(1).getRole().equals(Role.ROLE_USER));
		assertTrue(processedList.get(2).getRole().equals(Role.ROLE_USER));
	}

	@Test
	public void testSaveFaculty() {
		Set<String> subjList = new HashSet<>();
		subjList.add("A");
		subjList.add("B");
		subjList.add("C");

		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty fcl1 = new Faculty();
		fcl1.setFacultyName("AA");
		fcl1.setAdmittanceQuota((short) 10);
		fcl1.setSubjectsList(subjList);

		facultyRepository.saveAll(Arrays.asList(fcl1));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		Faculty fclFromDB = faculties.get(0);
		assertTrue(fclFromDB.getAdmittanceQuota().equals(fcl1.getAdmittanceQuota()));
		assertTrue(fclFromDB.getFacultyName().equals(fcl1.getFacultyName()));
		assertTrue(fclFromDB.getSubjectsList().equals(fcl1.getSubjectsList()));

	}

	@Test
	public void testGetAllFaculties() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty fcl1 = new Faculty();
		fcl1.setFacultyName("AA");
		fcl1.setAdmittanceQuota((short) 10);

		Faculty fcl2 = new Faculty();
		fcl2.setFacultyName("FF");

		Faculty fcl3 = new Faculty();
		fcl3.setFacultyName("DD");

		Faculty fcl4 = new Faculty();
		fcl4.setFacultyName("HH");
		fcl4.setAdmittanceQuota((short) 40);

		facultyRepository.saveAll(Arrays.asList(fcl4, fcl2, fcl3, fcl1));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(4));
		List<Faculty> facultiesFromDB = facultyService.getAllFaculties();
		assertTrue(facultiesFromDB.get(0).getFacultyName().equals("AA"));
		assertTrue(facultiesFromDB.get(1).getFacultyName().equals("DD"));
		assertTrue(facultiesFromDB.get(2).getFacultyName().equals("FF"));
		assertTrue(facultiesFromDB.get(3).getFacultyName().equals("HH"));
	}

	@Test
	public void testFindOneFacultyById() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty fcl1 = new Faculty();
		fcl1.setFacultyName("AA");
		fcl1.setAdmittanceQuota((short) 10);

		facultyRepository.saveAll(Arrays.asList(fcl1));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		Faculty fclFromDB = facultyService.findOneFacultyById(faculties.get(0).getFacultyId());
		assertTrue(fclFromDB.getAdmittanceQuota().equals(fcl1.getAdmittanceQuota()));
		assertTrue(fclFromDB.getFacultyName().equals(fcl1.getFacultyName()));
	}

	@Test
	public void testGetFacultyName() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty fcl1 = new Faculty();
		fcl1.setFacultyName("AA");
		fcl1.setAdmittanceQuota((short) 10);

		facultyRepository.saveAll(Arrays.asList(fcl1));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		String fclName = facultyService.getFacultyName(faculties.get(0).getFacultyId());
		assertTrue(fclName.equals("AA"));
	}

	@Test
	public void testGetSubjectsListByFacultyID() {
		Set<String> initialSubjList = new HashSet<>();
		initialSubjList.add("A");
		initialSubjList.add("B");
		initialSubjList.add("C");
		int initialSubjListSize = initialSubjList.size();

		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty fcl1 = new Faculty();
		fcl1.setFacultyName("AA");
		fcl1.setAdmittanceQuota((short) 10);
		fcl1.setSubjectsList(initialSubjList);

		facultyRepository.save(fcl1);
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		Set<String> subjListFromDB = new HashSet<>();
		subjListFromDB = facultyService.getSubjectsListByFacultyID(faculties.get(0).getFacultyId());

		assertTrue(subjListFromDB.contains("A"));
		assertTrue(subjListFromDB.contains("B"));
		assertTrue(subjListFromDB.contains("C"));
		assertTrue(subjListFromDB.size() == initialSubjListSize);
	}

	@Test
	public void testSaveApplicantProfile() {
		User user = new User();
		user.setName("1");
		user.setSurname("1");
		user.setEmail("1@1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(Role.ROLE_USER);

		List<ApplicantProfile> profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(0));

		ApplicantProfile profile1 = new ApplicantProfile();
		profile1.setAdmitted(false);
		profile1.setApprooved(true);
		profile1.setEnrolled(false);
		profile1.setTotalMarksAmount((short) 10);
		profile1.setUser(user);

		applicantProfileRepository.save(profile1);
		profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(1));

		ApplicantProfile profilefromDB = profiles.get(0);
		assertTrue(profilefromDB.isAdmitted() == profile1.isAdmitted());
		assertTrue(profilefromDB.isApprooved() == profile1.isApprooved());
		assertTrue(profilefromDB.isEnrolled() == profile1.isEnrolled());
		assertTrue(profilefromDB.getTotalMarksAmount().equals(profile1.getTotalMarksAmount()));
		assertTrue(profilefromDB.getUser().getName().equals(profile1.getUser().getName()));
		assertTrue(profilefromDB.getUser().getSurname().equals(profile1.getUser().getSurname()));
		assertTrue(profilefromDB.getUser().getEmail().equals(profile1.getUser().getEmail()));
		assertTrue(profilefromDB.getUser().getPassword().equals(profile1.getUser().getPassword()));
		assertTrue(profilefromDB.getUser().getPasswordConfirm().equals(profile1.getUser().getPasswordConfirm()));
		assertTrue(profilefromDB.getUser().getRole().equals(profile1.getUser().getRole()));

	}

	@Test
	public void testUpdateApplicantProfile() {
		User user = new User();
		user.setName("1");
		user.setSurname("1");
		user.setEmail("1@1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(Role.ROLE_USER);

		List<ApplicantProfile> profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(0));

		ApplicantProfile profile1 = new ApplicantProfile();
		profile1.setAdmitted(false);
		profile1.setApprooved(false);
		profile1.setEnrolled(false);
		profile1.setTotalMarksAmount((short) 10);
		profile1.setUser(user);

		applicantProfileRepository.save(profile1);
		profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(1));

		ApplicantProfile profileFromDB_toUpdate = profiles.get(0);
		user.setName("12");
		user.setSurname("12");
		user.setEmail("12@12");
		user.setPassword("12");
		user.setPasswordConfirm("12");
		user.setRole(Role.ROLE_ADMIN);

		profileFromDB_toUpdate.setAdmitted(true);
		profileFromDB_toUpdate.setApprooved(true);
		profileFromDB_toUpdate.setEnrolled(true);
		profileFromDB_toUpdate.setTotalMarksAmount((short) 50);
		profileFromDB_toUpdate.setUser(user);

		applicantProfileService.update(profileFromDB_toUpdate);
		profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(1));

		ApplicantProfile profilefromDB = profiles.get(0);
		assertTrue(profilefromDB.isAdmitted() == true);
		assertTrue(profilefromDB.isApprooved() == true);
		assertTrue(profilefromDB.isEnrolled() == true);
		assertTrue(profilefromDB.getTotalMarksAmount().equals((short) 50));
		assertTrue(profilefromDB.getUser().getName().equals("12"));
		assertTrue(profilefromDB.getUser().getSurname().equals("12"));
		assertTrue(profilefromDB.getUser().getEmail().equals("12@12"));
		assertTrue(profilefromDB.getUser().getPassword().equals("12"));
		assertTrue(profilefromDB.getUser().getPasswordConfirm().equals("12"));
		assertTrue(profilefromDB.getUser().getRole().equals(Role.ROLE_ADMIN));
	}

	@Test
	public void testFindProfileById() {
		User user = new User();
		user.setName("1");
		user.setSurname("1");
		user.setEmail("1@1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(Role.ROLE_USER);

		List<ApplicantProfile> profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(0));

		ApplicantProfile profile1 = new ApplicantProfile();
		profile1.setAdmitted(false);
		profile1.setApprooved(true);
		profile1.setEnrolled(false);
		profile1.setTotalMarksAmount((short) 10);
		profile1.setUser(user);

		applicantProfileRepository.save(profile1);
		profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(1));

		ApplicantProfile profilefromDB = applicantProfileService.findProfileById(profiles.get(0).getProfileId());

		assertTrue(profilefromDB.isAdmitted() == profile1.isAdmitted());
		assertTrue(profilefromDB.isApprooved() == profile1.isApprooved());
		assertTrue(profilefromDB.isEnrolled() == profile1.isEnrolled());
		assertTrue(profilefromDB.getTotalMarksAmount().equals(profile1.getTotalMarksAmount()));
		assertTrue(profilefromDB.getUser().getName().equals(profile1.getUser().getName()));
		assertTrue(profilefromDB.getUser().getSurname().equals(profile1.getUser().getSurname()));
		assertTrue(profilefromDB.getUser().getEmail().equals(profile1.getUser().getEmail()));
		assertTrue(profilefromDB.getUser().getPassword().equals(profile1.getUser().getPassword()));
		assertTrue(profilefromDB.getUser().getPasswordConfirm().equals(profile1.getUser().getPasswordConfirm()));
		assertTrue(profilefromDB.getUser().getRole().equals(profile1.getUser().getRole()));
	}

	@Test
	public void testFindProfileByEmail() {
		User user = new User();
		user.setName("1");
		user.setSurname("1");
		user.setEmail("1@1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(Role.ROLE_USER);

		List<ApplicantProfile> profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(0));

		ApplicantProfile profile1 = new ApplicantProfile();
		profile1.setAdmitted(false);
		profile1.setApprooved(true);
		profile1.setEnrolled(false);
		profile1.setTotalMarksAmount((short) 10);
		profile1.setUser(user);

		applicantProfileRepository.save(profile1);
		profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(1));

		ApplicantProfile profilefromDB = applicantProfileService
				.findProfileByEmail(profiles.get(0).getUser().getEmail());

		assertTrue(profilefromDB.isAdmitted() == profile1.isAdmitted());
		assertTrue(profilefromDB.isApprooved() == profile1.isApprooved());
		assertTrue(profilefromDB.isEnrolled() == profile1.isEnrolled());
		assertTrue(profilefromDB.getTotalMarksAmount().equals(profile1.getTotalMarksAmount()));
		assertTrue(profilefromDB.getUser().getName().equals(profile1.getUser().getName()));
		assertTrue(profilefromDB.getUser().getSurname().equals(profile1.getUser().getSurname()));
		assertTrue(profilefromDB.getUser().getEmail().equals(profile1.getUser().getEmail()));
		assertTrue(profilefromDB.getUser().getPassword().equals(profile1.getUser().getPassword()));
		assertTrue(profilefromDB.getUser().getPasswordConfirm().equals(profile1.getUser().getPasswordConfirm()));
		assertTrue(profilefromDB.getUser().getRole().equals(profile1.getUser().getRole()));
	}

	@Test
	public void testFindAllProfileEmails() {
		User user1 = new User();
		user1.setName("1");
		user1.setSurname("1");
		user1.setEmail("1@1");
		user1.setPassword("1");
		user1.setPasswordConfirm("1");
		user1.setRole(Role.ROLE_USER);

		User user2 = new User();
		user2.setName("12");
		user2.setSurname("12");
		user2.setEmail("12@12");
		user2.setPassword("12");
		user2.setPasswordConfirm("12");
		user2.setRole(Role.ROLE_USER);

		User user3 = new User();
		user3.setName("123");
		user3.setSurname("123");
		user3.setEmail("123@123");
		user3.setPassword("123");
		user3.setPasswordConfirm("123");
		user3.setRole(Role.ROLE_USER);

		List<ApplicantProfile> profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(0));

		ApplicantProfile profile1 = new ApplicantProfile();
		profile1.setAdmitted(false);
		profile1.setApprooved(false);
		profile1.setEnrolled(false);
		profile1.setTotalMarksAmount((short) 10);
		profile1.setUser(user1);

		ApplicantProfile profile2 = new ApplicantProfile();
		profile2.setAdmitted(false);
		profile2.setApprooved(false);
		profile2.setEnrolled(false);
		profile2.setTotalMarksAmount((short) 10);
		profile2.setUser(user2);

		ApplicantProfile profile3 = new ApplicantProfile();
		profile3.setAdmitted(false);
		profile3.setApprooved(false);
		profile3.setEnrolled(false);
		profile3.setTotalMarksAmount((short) 10);
		profile3.setUser(user3);

		applicantProfileRepository.saveAll(Arrays.asList(profile1, profile2, profile3));
		profiles = applicantProfileRepository.findAll();
		int profilesSize = profiles.size();
		assertThat(profiles, hasSize(3));

		List<String> profileEmails = applicantProfileService.findAllProfileEmails();
		assertTrue(profileEmails.size() == profilesSize);
		assertTrue(profileEmails.get(0).equals("1@1"));
		assertTrue(profileEmails.get(1).equals("12@12"));
		assertTrue(profileEmails.get(2).equals("123@123"));
	}

	@Test
	public void testFindAllProfiles() {
		User userX = new User();
		userX.setName("X");
		userX.setSurname("X");
		userX.setEmail("1@1");
		userX.setPassword("1");
		userX.setPasswordConfirm("1");
		userX.setRole(Role.ROLE_USER);

		User userF = new User();
		userF.setName("F");
		userF.setSurname("F");
		userF.setEmail("12@12");
		userF.setPassword("12");
		userF.setPasswordConfirm("12");
		userF.setRole(Role.ROLE_USER);

		User userA = new User();
		userA.setName("A");
		userA.setSurname("A");
		userA.setEmail("123@123");
		userA.setPassword("123");
		userA.setPasswordConfirm("123");
		userA.setRole(Role.ROLE_USER);

		List<ApplicantProfile> profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(0));

		ApplicantProfile profile1 = new ApplicantProfile();
		profile1.setAdmitted(false);
		profile1.setApprooved(false);
		profile1.setEnrolled(false);
		profile1.setTotalMarksAmount((short) 10);
		profile1.setUser(userX);

		ApplicantProfile profile2 = new ApplicantProfile();
		profile2.setAdmitted(false);
		profile2.setApprooved(false);
		profile2.setEnrolled(false);
		profile2.setTotalMarksAmount((short) 10);
		profile2.setUser(userF);

		ApplicantProfile profile3 = new ApplicantProfile();
		profile3.setAdmitted(false);
		profile3.setApprooved(false);
		profile3.setEnrolled(false);
		profile3.setTotalMarksAmount((short) 10);
		profile3.setUser(userA);

		applicantProfileRepository.saveAll(Arrays.asList(profile1, profile2, profile3));
		profiles = applicantProfileRepository.findAll();
		int profilesSize = profiles.size();
		assertThat(profiles, hasSize(3));

		List<ApplicantProfile> profilesFromDB = applicantProfileService.findAllProfiles();
		assertTrue(profilesFromDB.size() == profilesSize);
		assertTrue(profilesFromDB.get(0).getUser().getSurname().equals("A"));
		assertTrue(profilesFromDB.get(1).getUser().getSurname().equals("F"));
		assertTrue(profilesFromDB.get(2).getUser().getSurname().equals("X"));
	}

	@Test
	public void testFindAllNonApprovedProfilesSortedById() {
		User userX = new User();
		userX.setName("X");
		userX.setSurname("X");
		userX.setEmail("1@1");
		userX.setPassword("1");
		userX.setPasswordConfirm("1");
		userX.setRole(Role.ROLE_USER);

		User userF = new User();
		userF.setName("F");
		userF.setSurname("F");
		userF.setEmail("12@12");
		userF.setPassword("12");
		userF.setPasswordConfirm("12");
		userF.setRole(Role.ROLE_USER);

		User userA = new User();
		userA.setName("A");
		userA.setSurname("A");
		userA.setEmail("123@123");
		userA.setPassword("123");
		userA.setPasswordConfirm("123");
		userA.setRole(Role.ROLE_USER);

		User user1 = new User();
		user1.setName("H");
		user1.setSurname("H");
		user1.setEmail("111@111");
		user1.setPassword("123");
		user1.setPasswordConfirm("123");
		user1.setRole(Role.ROLE_USER);

		User user2 = new User();
		user2.setName("C");
		user2.setSurname("C");
		user2.setEmail("22@22");
		user2.setPassword("123");
		user2.setPasswordConfirm("123");
		user2.setRole(Role.ROLE_USER);

		List<ApplicantProfile> profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(0));

		ApplicantProfile profile1 = new ApplicantProfile();
		profile1.setAdmitted(true);
		profile1.setApprooved(true);
		profile1.setEnrolled(true);
		profile1.setTotalMarksAmount((short) 10);
		profile1.setUser(userX);

		ApplicantProfile profile2 = new ApplicantProfile();
		profile2.setAdmitted(true);
		profile2.setApprooved(true);
		profile2.setEnrolled(true);
		profile2.setTotalMarksAmount((short) 20);
		profile2.setUser(userF);

		ApplicantProfile profileNA1 = new ApplicantProfile();
		profileNA1.setAdmitted(false);
		profileNA1.setApprooved(false);
		profileNA1.setEnrolled(false);
		profileNA1.setTotalMarksAmount((short) 30);
		profileNA1.setUser(userA);

		ApplicantProfile profile4 = new ApplicantProfile();
		profile4.setAdmitted(true);
		profile4.setApprooved(true);
		profile4.setEnrolled(true);
		profile4.setTotalMarksAmount((short) 23);
		profile4.setUser(user1);

		ApplicantProfile profileNA2 = new ApplicantProfile();
		profileNA2.setAdmitted(false);
		profileNA2.setApprooved(false);
		profileNA2.setEnrolled(false);
		profileNA2.setTotalMarksAmount((short) 56);
		profileNA2.setUser(user2);

		applicantProfileRepository.saveAll(Arrays.asList(profile1, profile2, profileNA2, profileNA1, profile4));
		profiles = applicantProfileRepository.findAll();
		int profilesSize = profiles.size();
		assertThat(profiles, hasSize(5));

		List<ApplicantProfile> nonApprovedProfilesFromDB = applicantProfileService
				.findAllNonApprovedProfilesSortedById();
		assertFalse(nonApprovedProfilesFromDB.size() == profilesSize);
		assertThat(nonApprovedProfilesFromDB, hasSize(2));
		assertFalse(nonApprovedProfilesFromDB.get(0).isApprooved());
		assertFalse(nonApprovedProfilesFromDB.get(1).isApprooved());
	}

	@Test
	public void testFindAllProfilesByFacultyId() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty fcl1 = new Faculty();
		fcl1.setFacultyName("AA");

		Faculty fcl2 = new Faculty();
		fcl2.setFacultyName("BB");

		facultyRepository.saveAll(Arrays.asList(fcl1, fcl2));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));

		Faculty fcl_AAA = facultyRepository.findAll().get(0);
		Faculty fcl_555 = facultyRepository.findAll().get(1);

		User userX = new User();
		userX.setName("X");
		userX.setSurname("X");
		userX.setEmail("X@X");
		userX.setPassword("1");
		userX.setPasswordConfirm("1");
		userX.setRole(Role.ROLE_USER);

		User userF = new User();
		userF.setName("F");
		userF.setSurname("F");
		userF.setEmail("F@F");
		userF.setPassword("12");
		userF.setPasswordConfirm("12");
		userF.setRole(Role.ROLE_USER);

		User user1 = new User();
		user1.setName("1");
		user1.setSurname("1");
		user1.setEmail("111@111");
		user1.setPassword("123");
		user1.setPasswordConfirm("123");
		user1.setRole(Role.ROLE_USER);

		User user2 = new User();
		user2.setName("2");
		user2.setSurname("2");
		user2.setEmail("22@22");
		user2.setPassword("123");
		user2.setPasswordConfirm("123");
		user2.setRole(Role.ROLE_USER);

		User user3 = new User();
		user3.setName("3");
		user3.setSurname("3");
		user3.setEmail("333@333");
		user3.setPassword("123");
		user3.setPasswordConfirm("123");
		user3.setRole(Role.ROLE_USER);

		User user4 = new User();
		user4.setName("4");
		user4.setSurname("4");
		user4.setEmail("1234@1234");
		user4.setPassword("1234");
		user4.setPasswordConfirm("1234");
		user4.setRole(Role.ROLE_USER);

		List<ApplicantProfile> profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(0));

		/*
		 * setting the next 4 profiles with fcl_555
		 */
		ApplicantProfile profile1 = new ApplicantProfile();
		profile1.setAdmitted(true);
		profile1.setApprooved(true);
		profile1.setEnrolled(true);
		profile1.setTotalMarksAmount((short) 10);
		profile1.setUser(user1);
		profile1.setFaculty(fcl_555);

		ApplicantProfile profile2 = new ApplicantProfile();
		profile2.setAdmitted(true);
		profile2.setApprooved(true);
		profile2.setEnrolled(true);
		profile2.setTotalMarksAmount((short) 20);
		profile2.setUser(user2);
		profile2.setFaculty(fcl_555);

		ApplicantProfile profile3 = new ApplicantProfile();
		profile3.setAdmitted(false);
		profile3.setApprooved(false);
		profile3.setEnrolled(false);
		profile3.setTotalMarksAmount((short) 30);
		profile3.setUser(user3);
		profile3.setFaculty(fcl_555);

		ApplicantProfile profile4 = new ApplicantProfile();
		profile4.setAdmitted(true);
		profile4.setApprooved(true);
		profile4.setEnrolled(true);
		profile4.setTotalMarksAmount((short) 23);
		profile4.setUser(user4);
		profile4.setFaculty(fcl_555);

		/*
		 * setting the next 2 profiles with fcl_AAA
		 */
		ApplicantProfile profileF = new ApplicantProfile();
		profileF.setAdmitted(false);
		profileF.setApprooved(false);
		profileF.setEnrolled(false);
		profileF.setTotalMarksAmount((short) 56);
		profileF.setUser(userF);
		profileF.setFaculty(fcl_AAA);

		ApplicantProfile profileX = new ApplicantProfile();
		profileX.setAdmitted(false);
		profileX.setApprooved(false);
		profileX.setEnrolled(false);
		profileX.setTotalMarksAmount((short) 56);
		profileX.setUser(userX);
		profileX.setFaculty(fcl_AAA);

		applicantProfileRepository.saveAll(Arrays.asList(profile3, profileX, profile4, profile2, profileF, profile1));
		profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(6));

		/*
		 * now having :
		 * 2 profiles in 'fcl_AAA'-faculty(#1)
		 * 4 profiles in 'fcl_555'-faculty(#2)
		 */
		
		int faculty1ID = facultyService.getAllFaculties().get(0).getFacultyId();
		int faculty2ID = facultyService.getAllFaculties().get(1).getFacultyId();

		List<ApplicantProfile> profilesFromDB1 = applicantProfileService.findAllProfilesByFacultyId(faculty1ID);
		List<ApplicantProfile> profilesFromDB2 = applicantProfileService.findAllProfilesByFacultyId(faculty2ID);
		assertFalse(profilesFromDB1.size() == profilesFromDB2.size());
		assertTrue(profilesFromDB1.size() == 2);
		assertTrue(profilesFromDB2.size() == 4);

	}
	
	@Test
	public void testGetAllApprovedApplicantsByFacultyIDSortedDesc() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty fcl1 = new Faculty();
		fcl1.setFacultyName("AA");

		Faculty fcl2 = new Faculty();
		fcl2.setFacultyName("BB");

		facultyRepository.saveAll(Arrays.asList(fcl1, fcl2));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));

		Faculty fcl_AAA = facultyRepository.findAll().get(0);
		Faculty fcl_555 = facultyRepository.findAll().get(1);

		User userX = new User();
		userX.setName("X");
		userX.setSurname("X");
		userX.setEmail("X@X");
		userX.setPassword("1");
		userX.setPasswordConfirm("1");
		userX.setRole(Role.ROLE_USER);

		User userF = new User();
		userF.setName("F");
		userF.setSurname("F");
		userF.setEmail("F@F");
		userF.setPassword("12");
		userF.setPasswordConfirm("12");
		userF.setRole(Role.ROLE_USER);

		User user1 = new User();
		user1.setName("1");
		user1.setSurname("1");
		user1.setEmail("111@111");
		user1.setPassword("123");
		user1.setPasswordConfirm("123");
		user1.setRole(Role.ROLE_USER);

		User user2 = new User();
		user2.setName("2");
		user2.setSurname("2");
		user2.setEmail("22@22");
		user2.setPassword("123");
		user2.setPasswordConfirm("123");
		user2.setRole(Role.ROLE_USER);

		User user3 = new User();
		user3.setName("3");
		user3.setSurname("3");
		user3.setEmail("333@333");
		user3.setPassword("123");
		user3.setPasswordConfirm("123");
		user3.setRole(Role.ROLE_USER);

		User user4 = new User();
		user4.setName("4");
		user4.setSurname("4");
		user4.setEmail("1234@1234");
		user4.setPassword("1234");
		user4.setPasswordConfirm("1234");
		user4.setRole(Role.ROLE_USER);

		List<ApplicantProfile> profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(0));

		/*
		 * setting the next 4 profiles with fcl_555,
		 * 1 profile totally non-approved
		 */
		ApplicantProfile profile1 = new ApplicantProfile();
		profile1.setAdmitted(true);
		profile1.setApprooved(true);
		profile1.setEnrolled(true);
		profile1.setTotalMarksAmount((short) 10);
		profile1.setUser(user1);
		profile1.setFaculty(fcl_555);

		ApplicantProfile profile2 = new ApplicantProfile();
		profile2.setAdmitted(true);
		profile2.setApprooved(true);
		profile2.setEnrolled(true);
		profile2.setTotalMarksAmount((short) 20);
		profile2.setUser(user2);
		profile2.setFaculty(fcl_555);

		ApplicantProfile profile3False = new ApplicantProfile();
		profile3False.setAdmitted(false);
		profile3False.setApprooved(false);
		profile3False.setEnrolled(false);
		profile3False.setTotalMarksAmount((short) 37);
		profile3False.setUser(user3);
		profile3False.setFaculty(fcl_555);

		ApplicantProfile profile4 = new ApplicantProfile();
		profile4.setAdmitted(true);
		profile4.setApprooved(true);
		profile4.setEnrolled(true);
		profile4.setTotalMarksAmount((short) 23);
		profile4.setUser(user4);
		profile4.setFaculty(fcl_555);

		/*
		 * setting the next 2 profiles with fcl_AAA,
		 * all profiles totally approved
		 */
		ApplicantProfile profileF = new ApplicantProfile();
		profileF.setAdmitted(true);
		profileF.setApprooved(true);
		profileF.setEnrolled(true);
		profileF.setTotalMarksAmount((short) 46);
		profileF.setUser(userF);
		profileF.setFaculty(fcl_AAA);

		ApplicantProfile profileTR = new ApplicantProfile();
		profileTR.setAdmitted(true);
		profileTR.setApprooved(true);
		profileTR.setEnrolled(true);
		profileTR.setTotalMarksAmount((short) 56);
		profileTR.setUser(userX);
		profileTR.setFaculty(fcl_AAA);

		applicantProfileRepository.saveAll(Arrays.asList(profile3False, profileTR, profile4, profile2, profileF, profile1));
		profiles = applicantProfileRepository.findAll();
		assertThat(profiles, hasSize(6));

		/*
		 * now having :
		 * 2 profiles in 'fcl_AAA'-faculty(#1)
		 * 4 profiles in 'fcl_555'-faculty(#2)
		 */
		
		int faculty1ID = facultyService.getAllFaculties().get(0).getFacultyId();
		int faculty2ID = facultyService.getAllFaculties().get(1).getFacultyId();
		
		List<ApplicantProfile> profilesFromDbById1 = applicantProfileService.getAllApprovedApplicantsByFacultyIDSortedDesc(faculty1ID);
		List<ApplicantProfile> profilesFromDbById2 = applicantProfileService.getAllApprovedApplicantsByFacultyIDSortedDesc(faculty2ID);
		assertTrue(profilesFromDbById1.size() == 2);
		assertTrue(profilesFromDbById2.size() == 3);
		assertTrue(profilesFromDbById1.get(0).isApprooved() && profilesFromDbById1.get(0).isEnrolled());
		assertTrue(profilesFromDbById1.get(1).isApprooved() && profilesFromDbById1.get(1).isEnrolled());
		
		assertTrue(profilesFromDbById1.get(0).getTotalMarksAmount() > profilesFromDbById1.get(1).getTotalMarksAmount());
		
		assertTrue(profilesFromDbById2.get(0).isApprooved() && profilesFromDbById2.get(0).isEnrolled());
		assertTrue(profilesFromDbById2.get(1).isApprooved() && profilesFromDbById2.get(1).isEnrolled());
		assertTrue(profilesFromDbById2.get(2).isApprooved() && profilesFromDbById2.get(2).isEnrolled());
		assertTrue(profilesFromDbById2.get(0).getTotalMarksAmount() > profilesFromDbById2.get(1).getTotalMarksAmount());
		assertTrue(profilesFromDbById2.get(1).getTotalMarksAmount() > profilesFromDbById2.get(2).getTotalMarksAmount());
		assertTrue(profilesFromDbById2.get(0).getTotalMarksAmount() > profilesFromDbById2.get(2).getTotalMarksAmount());
	}

}
