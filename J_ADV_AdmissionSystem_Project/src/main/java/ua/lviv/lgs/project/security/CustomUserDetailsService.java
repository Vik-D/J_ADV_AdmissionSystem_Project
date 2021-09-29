package ua.lviv.lgs.project.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.project.dao.UserRepository;
import ua.lviv.lgs.project.domain.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> optUser = userRepository.findByEmail(email);
		
		if(optUser.isPresent()) {
			User user = optUser.get();
			return new CustomUserDetails(user, Collections.singletonList(user.getRole().toString()));			
		}
		
		throw new UsernameNotFoundException("No user with "+ email + " found");
	}

	
}
