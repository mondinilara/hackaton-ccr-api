package br.com.hackaton.ccr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackaton.ccr.dto.UserTeenDto;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.repository.UserTeen;
import br.com.hackaton.ccr.service.UserTeenService;

@Service
public class UserTeenServiceImpl implements UserTeenService {

	private UserTeen userTeenRepository;

	@Autowired
	public UserTeenServiceImpl(UserTeen userTeenRepository) {
		this.userTeenRepository = userTeenRepository;
	}

	@Override
	public void insertUserTeen(UserTeenDto userTeen) throws AppException {
		this.userTeenRepository.insertUserTeen(userTeen);
	}

	@Override
	public void updateUserTeen(UserTeenDto userTeen) throws AppException {
		this.userTeenRepository.updateUserTeen(userTeen);
	}

	@Override
	public UserTeenDto findUserTeenByCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserTeenDto> listUserTeen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserTeenDto> findUserTeenByInteress() {
		// TODO Auto-generated method stub
		return null;
	}

}
