package br.com.hackaton.ccr.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Connection {

	private Connection() {
	}

	private static MongoDatabase instance;

	public static MongoDatabase getInstance() {
		if (instance == null) {
			MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017/?gssapiServiceName=mongodb");
			MongoDatabase dataBase = mongoClient.getDatabase("hackatonccr");

			instance = dataBase;
		}
		return instance;
	}
}