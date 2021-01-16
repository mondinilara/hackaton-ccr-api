package br.com.hackaton.ccr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hackaton.ccr.dto.UserTeen;
import br.com.hackaton.ccr.repository.UserTeenRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserTeenRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserTeenRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Let people login by email
		UserTeen user = userRepository.findFirstByMail(email);

		if (user == null)
			throw new UsernameNotFoundException("Usuário " + email + " não encontrado.");

		// Let people login by email
		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		UserTeen user = userRepository.findFirstById(id);

		if (user == null)
			throw new UsernameNotFoundException("Usuário " + id + " não encontrado.");
		return UserPrincipal.create(user);
	}

}
