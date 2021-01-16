package br.com.hackaton.ccr.repository;

import java.util.List;

import br.com.hackaton.ccr.dto.UserTeenDto;
import br.com.hackaton.ccr.exceptions.AppException;

public interface UserTeenRepository {

	void insertUserTeen(UserTeenDto userTeen) throws AppException;

	void updateUserTeen(UserTeenDto userTeen) throws AppException;

	UserTeenDto findUserTeenBy(String by, Object value);

	List<UserTeenDto> listAllUsersTeen();

}
