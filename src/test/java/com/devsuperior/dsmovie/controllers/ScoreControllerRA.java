package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.tests.TokenUtil;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ScoreControllerRA {

	private String clientUsername, clientPassword,
			adminUsername, adminPassword, clientToken;

	private Long existingId, nonExistingId;
	private Map<String, Object> postScore;

	@BeforeEach
	public void setUp() throws JSONException {
		baseURI = "http://localhost:8080";

		postScore = new HashMap<>();
		postScore.put("movieId", "1");
		postScore.put("score", "5.0");

		clientUsername = "alex@gmail.com";
		clientPassword = "123456";

		adminUsername = "maria@gmail.com";
		adminPassword = "123456";

		clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);

	}
	
	@Test
	@DisplayName("SAVE Score Should Return Not Found When Movie Id Does Not Exist")
	public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist() throws Exception {
		postScore.put("movieId", "999");
		JSONObject addScore = new JSONObject(postScore);

		given()
				.header("Content-type", "application-json")
				.header("Authorization", "Bearer " + clientToken)
				.body(addScore)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)

				.when()
				.put("/scores")

				.then()
				.statusCode(404);
	}
	
	@Test
	@DisplayName("SAVE Score Should Return Unprocessable Entity When Missing MovieId")
	public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId() throws Exception {
		postScore.put("movieId", "");
		JSONObject addScore = new JSONObject(postScore);

		given()
				.header("Content-type", "application-json")
				.header("Authorization", "Bearer " + clientToken)
				.body(addScore)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)

				.when()
				.put("/scores")

				.then()
				.statusCode(422);
	}
	
	@Test
	@DisplayName("SAVE Score Should Return Unprocessable Entity When Score Is Less Than Zero")
	public void saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero() throws Exception {
		postScore.put("score", "-1.0");
		JSONObject addScore = new JSONObject(postScore);

		given()
				.header("Content-type", "application-json")
				.header("Authorization", "Bearer " + clientToken)
				.body(addScore)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)

				.when()
				.put("/scores")

				.then()
				.statusCode(422);
	}
}
