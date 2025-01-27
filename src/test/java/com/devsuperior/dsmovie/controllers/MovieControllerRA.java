package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.tests.TokenUtil;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class MovieControllerRA {

	private String clientUsername, clientPassword,
			adminUsername, adminPassword, clientToken, adminToken;

	private Long existingId, nonExistingId;

	@BeforeEach
	public void setUp() throws JSONException {
		baseURI = "http://localhost:8080";

		clientUsername = "maria@gmail.com";
		clientPassword = "123456";

		adminUsername = "alex@gmail.com";
		adminPassword = "123456";

		clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
		adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);

		existingId = 1L;
		nonExistingId = 999L;
	}
	
	@Test
	public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {

		given()
				.when()
				.get("/movies")

				.then()
				.statusCode(200);
	}
	
	@Test
	public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {		
	}
	
	@Test
	public void findByIdShouldReturnMovieWhenIdExists() {		
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {	
	}
	
	@Test
	public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {		
	}
	
	@Test
	public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
	}
	
	@Test
	public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
	}
}
