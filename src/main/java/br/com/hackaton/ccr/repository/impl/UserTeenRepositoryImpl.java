package br.com.hackaton.ccr.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import br.com.hackaton.ccr.dto.UserTeenDto;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.mongodb.Connection;
import br.com.hackaton.ccr.repository.UserTeenRepository;
import br.com.hackaton.ccr.utils.Constants;

@Repository
public class UserTeenRepositoryImpl implements UserTeenRepository {

	private MongoCollection<Document> collection;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserTeenRepositoryImpl(PasswordEncoder passwordEncoder) {
		collection = Connection.getInstance().getCollection(Constants.COLLECTION_USER_TEEN);
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void insertUserTeen(UserTeenDto userTeen) throws AppException {
		if (userExists(userTeen.getCpf()))
			throw new AppException("Usuário já cadastrado!");

		String pswEncoded = passwordEncoder.encode(userTeen.getPassword());
		userTeen.setPassword(pswEncoded);

		userTeen.setCreatedOn(LocalDateTime.now());

		Document teen = new Document(Constants.ID, System.currentTimeMillis());
		collection.insertOne(getUserTeenDocument(userTeen, teen));

	}

	@Override
	public UserTeenDto findUserTeenBy(String by, Object value) {
		Document userTeen = collection.find(new Document(by, value)).first();
		if (userTeen == null)
			return null;

		String json = userTeen.toJson();
		Gson g = new Gson();
		return g.fromJson(json, UserTeenDto.class);
	}

	@Override
	public List<UserTeenDto> listAllUsersTeen() {
		List<UserTeenDto> usersTeen = new ArrayList<>();

		FindIterable<Document> cursor = collection.find();

		while (cursor.iterator().hasNext()) {
			Document userTeen = cursor.iterator().next();

			String json = userTeen.toJson();
			Gson g = new Gson();
			usersTeen.add(g.fromJson(json, UserTeenDto.class));
		}

		return usersTeen;
	}

	@Override
	public void updateUserTeen(UserTeenDto userTeen) throws AppException {
		if (!userExists(userTeen.getCpf()))
			throw new AppException("Usuário " + userTeen.getCpf() + " não encontrado!");

		Document setData = getUserTeenDocument(userTeen, new Document());

		Document query = new Document();
		query.append(Constants.CPF, userTeen.getCpf());

		Document update = new Document();
		update.append("$set", setData);

		collection.updateOne(query, update);
	}

	private boolean userExists(String cpf) {
		return findUserTeenBy(Constants.CPF, cpf) != null;
	}

	private Document getUserTeenDocument(UserTeenDto userTeen, Document teen) {
		// @formatter:off
		return teen.append(Constants.NAME, userTeen.getName())
				.append(Constants.BIRTH, userTeen.getBirth())
				.append(Constants.CPF, userTeen.getCpf())
				.append(Constants.PHONES, userTeen.getPhones())
				.append(Constants.MAIL, userTeen.getMail())
				.append(Constants.PASSWORD, userTeen.getPassword())
				.append(Constants.INTERESTS, userTeen.getInterests())
				.append(Constants.EDUCATION_LEVEL, userTeen.getEducationLevel())
				.append(Constants.RESUME, userTeen.getResume())
				.append(Constants.OBJECTIVE, userTeen.getObjective())
				.append(Constants.LANGUAGES, userTeen.getLanguages())
				.append(Constants.COMPLEMENTARY_ACTIVITIES, userTeen.getComplementaryActivities())
				.append(Constants.CREATED_ON, userTeen.getCreatedOn())
				.append(Constants.ADDRESS,
						new Document(Constants.STREET, userTeen.getAddress().getStreet())
						.append(Constants.CITY, userTeen.getAddress().getCity())
						.append(Constants.STATE, userTeen.getAddress().getState())
						.append(Constants.NUMBER, userTeen.getAddress().getNumber()));
		// @formatter:on
	}

}
