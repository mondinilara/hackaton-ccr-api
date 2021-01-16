package br.com.hackaton.ccr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.hackaton.ccr.dto.UserTeenDto;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.payload.JwtAuthenticationResponse;
import br.com.hackaton.ccr.payload.LoginRequest;
import br.com.hackaton.ccr.repository.UserTeenRepository;
import br.com.hackaton.ccr.security.JwtTokenProvider;
import br.com.hackaton.ccr.service.UserTeenService;
import br.com.hackaton.ccr.utils.Constants;

@Service
public class UserTeenServiceImpl implements UserTeenService {

	private UserTeenRepository userTeenRepository;

	private AuthenticationManager authenticationManager;

	private JwtTokenProvider tokenProvider;

	@Autowired
	public UserTeenServiceImpl(UserTeenRepository userTeenRepository, AuthenticationManager authenticationManager,
			JwtTokenProvider tokenProvider) {
		this.userTeenRepository = userTeenRepository;
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

	@Override
	public JwtAuthenticationResponse loginUserTeen(LoginRequest loginRequest) throws AppException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getMail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserTeenDto user = userTeenRepository.findUserTeenBy(Constants.MAIL, loginRequest.getMail());

		if (user == null)
			throw new AppException("Usuário " + loginRequest.getMail() + " não encontrado!");

		String token = tokenProvider.generateToken(authentication);

		return new JwtAuthenticationResponse(token, user.getId());
	}

	@Override
	public void registerUserTeen(UserTeenDto userTeen) throws AppException {
		this.userTeenRepository.insertUserTeen(userTeen);
	}

	@Override
	public void updateUserTeen(UserTeenDto userTeen) throws AppException {
		this.userTeenRepository.updateUserTeen(userTeen);
	}

	@Override
	public List<UserTeenDto> listAllUsersTeen() {
		return userTeenRepository.listAllUsersTeen();
	}

	@Override
	public UserTeenDto findUserTeenBy(String by, Object value) {
		return userTeenRepository.findUserTeenBy(by, value);
	}

}
