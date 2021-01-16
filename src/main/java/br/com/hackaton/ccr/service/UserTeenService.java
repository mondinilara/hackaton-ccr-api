package br.com.hackaton.ccr.service;

import java.util.List;

import br.com.hackaton.ccr.dto.UserTeen;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.payload.FindUserTeenRequest;
import br.com.hackaton.ccr.payload.JwtAuthenticationResponse;
import br.com.hackaton.ccr.payload.LoginRequest;

public interface UserTeenService {

	JwtAuthenticationResponse loginUserTeen(LoginRequest loginRequest) throws AppException;

	void registerUserTeen(UserTeen userTeen) throws AppException;

	void updateUserTeen(UserTeen userTeen) throws AppException, CloneNotSupportedException;

	List<UserTeen> listUsersTeen(String city, List<String> interests, String educationLevel);

	UserTeen findUser(FindUserTeenRequest findRequest) throws AppException;

}
