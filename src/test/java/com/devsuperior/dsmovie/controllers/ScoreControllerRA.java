package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.tests.TokenUtil;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class ScoreControllerRA {

	private String clientUsername, clientPassword, invalidToken,
			adminUsername, adminPassword, clientToken, adminToken;

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
		adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
		invalidToken = TokenUtil.obtainAccessToken("invalidUser", "invalidPassword");

		existingId = 2L;
		nonExistingId = 999L;
	}
	
	@Test
	public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist() throws Exception {		
	}
	
	@Test
	public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId() throws Exception {
	}
	
	@Test
	public void saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero() throws Exception {		
	}
}
