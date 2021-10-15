package ua.lviv.lgs.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.project.dao.UserRepository;
import ua.lviv.lgs.project.domain.Role;
import ua.lviv.lgs.project.domain.User;

/*
 * Encrypting the password and setting it into a User.class instance
 */
@Service
public class UserService {

	private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public boolean save(User user) {
		if (!userRepository.existsByEmail(user.getEmail())) {
			LOGGER.info("Registering new user {} : " + user + ", existing-email check passed");
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
			user.setRole(Role.ROLE_USER);
			userRepository.save(user);
			return true;
		}
		LOGGER.info(
				"User registration failed, provided email address " + user.getEmail() + " already exists in database");
		return false;
	}

	public List<User> getAllUsersBySurnameAlphabeticalOrder() {
		LOGGER.info("Getting all users: order by surname alphabetically");
		return userRepository.findAll().stream().filter(user -> user.getRole().toString().equals("ROLE_USER"))
				.sorted((u1, u2) -> u1.getSurname().compareToIgnoreCase(u2.getSurname())).collect(Collectors.toList());
	}

	public User getUserByUsername(String email) {
		LOGGER.info("Getting user {} by provided username : " + email);
		return userRepository.findOneByEmail(email).get();
	}

	public User updateUser(User user) {
		LOGGER.info("Updating user {} : " + user);
		return userRepository.save(user);
	}

}
