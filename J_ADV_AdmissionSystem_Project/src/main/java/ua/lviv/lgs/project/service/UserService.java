package ua.lviv.lgs.project.service;

import java.util.List;
import java.util.stream.Collectors;

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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
		user.setRole(Role.ROLE_USER);
		userRepository.save(user);
	}

	public List<User> getAllUsersBySurnameAlphabeticalOrder() {
		return userRepository.findAll().stream().filter(user -> user.getRole().toString().equals("ROLE_USER"))
				.sorted((u1, u2) -> u1.getSurname().compareToIgnoreCase(u2.getSurname())).collect(Collectors.toList());
	}

	public User getUserByUsername(String email) {
		return userRepository.findOneByEmail(email).get();
	}

}
