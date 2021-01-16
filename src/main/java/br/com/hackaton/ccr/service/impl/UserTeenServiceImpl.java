package br.com.hackaton.ccr.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hackaton.ccr.dto.UserTeen;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.payload.FindUserTeenRequest;
import br.com.hackaton.ccr.payload.JwtAuthenticationResponse;
import br.com.hackaton.ccr.payload.LoginRequest;
import br.com.hackaton.ccr.repository.UserTeenRepository;
import br.com.hackaton.ccr.security.JwtTokenProvider;
import br.com.hackaton.ccr.service.UserTeenService;

@Service
public class UserTeenServiceImpl implements UserTeenService {

	private UserTeenRepository userTeenRepository;

	private AuthenticationManager authenticationManager;

	private JwtTokenProvider tokenProvider;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserTeenServiceImpl(UserTeenRepository userTeenRepository, AuthenticationManager authenticationManager,
			JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
		this.userTeenRepository = userTeenRepository;
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public JwtAuthenticationResponse loginUserTeen(LoginRequest loginRequest) throws AppException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getMail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserTeen user = userTeenRepository.findFirstByMail(loginRequest.getMail());

		if (user == null)
			throw new AppException("Usuário " + loginRequest.getMail() + " não encontrado!");

		String token = tokenProvider.generateToken(authentication);

		return new JwtAuthenticationResponse(token, user.getId());
	}

	@Override
	public void registerUserTeen(UserTeen userTeen) throws AppException {
		if (this.userTeenRepository.findFirstByCpfOrMail(userTeen.getCpf(), userTeen.getMail()) != null)
			throw new AppException("Usuário já cadastrado!");

		String pswEncoded = passwordEncoder.encode(userTeen.getPassword());
		userTeen.setPassword(pswEncoded);

		userTeen.setCreatedOn(LocalDateTime.now());
		userTeen.setId(System.currentTimeMillis());

		this.userTeenRepository.insert(userTeen);
	}

	@Override
	public void updateUserTeen(UserTeen userTeen) throws AppException, CloneNotSupportedException {
		UserTeen user = this.userTeenRepository.findFirstByCpf(userTeen.getCpf());

		if (user == null)
			throw new AppException("Usuário não encontrado!");

		userTeen.setId(user.getId());
		userTeen.setObjectId(user.getObjectId());
		userTeen.setCreatedOn(user.getCreatedOn());
		userTeen.setPassword(passwordEncoder.encode(userTeen.getPassword()));

		this.userTeenRepository.save(userTeen);
	}

	@Override
	public List<UserTeen> listUsersTeen(String city, List<String> interests, String educationLevel) {
		List<UserTeen> users = userTeenRepository.findAll();

		if (Strings.isNotEmpty(educationLevel))
			users = users.stream().filter(x -> x.getEducationLevel().equalsIgnoreCase(educationLevel))
					.collect(Collectors.toList());

		if (Strings.isNotEmpty(city))
			users = users.stream().filter(x -> x.getAddress().getCity().equalsIgnoreCase(city))
					.collect(Collectors.toList());

		if (interests != null && !interests.isEmpty()) {
			for (String item : interests) {
				users = users.stream().filter(x -> x.getInterests().stream().anyMatch(item::equalsIgnoreCase))
						.collect(Collectors.toList());
			}
		}

		return users;
	}

	@Override
	public UserTeen findUser(FindUserTeenRequest findRequest) throws AppException {
		return this.userTeenRepository.findFirstByCpfAndMail(findRequest.getCpf(), findRequest.getMail());
	}

}
