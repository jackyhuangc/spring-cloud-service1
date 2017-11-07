package com.example.demo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MTestData")
public class MTestData {
	@Id
	private String id;

	private String a;
	private String b;
	private String c;
}