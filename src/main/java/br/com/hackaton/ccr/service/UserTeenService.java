package br.com.hackaton.ccr.service;

import java.util.List;

import br.com.hackaton.ccr.dto.UserTeenDto;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.payload.JwtAuthenticationResponse;
import br.com.hackaton.ccr.payload.LoginRequest;

public interface UserTeenService {

	JwtAuthenticationResponse loginUserTeen(LoginRequest loginRequest) throws AppException;

	void registerUserTeen(UserTeenDto userTeen) throws AppException;

	void updateUserTeen(UserTeenDto userTeen) throws AppException;

	UserTeenDto findUserTeenBy(String by, Object value);

	List<UserTeenDto> listAllUsersTeen();

}
