package br.com.hackaton.ccr.service;

import java.util.List;

import br.com.hackaton.ccr.dto.UserTeenDto;
import br.com.hackaton.ccr.exceptions.AppException;

public interface UserTeenService {

	void insertUserTeen(UserTeenDto userTeen) throws AppException;
	
	void updateUserTeen(UserTeenDto userTeen) throws AppException;

	UserTeenDto findUserTeenByCpf(String cpf);

	List<UserTeenDto> listUserTeen();

	List<UserTeenDto> findUserTeenByInteress();

}
