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
import static org.hamcrest.CoreMatchers.*;

public class MovieControllerRA {

	private String clientUsername, clientPassword,
			adminUsername, adminPassword, clientToken, adminToken;

	private Long existingId, nonExistingId;
	private Map<String, Object> postMovie;

	@BeforeEach
	public void setUp() throws JSONException {
		baseURI = "http://localhost:8080";

		postMovie = new HashMap<>();
		postMovie.put("title", "Meu Filme Favorito");
		postMovie.put("score", "5.0");
		postMovie.put("count", "1");
		postMovie.put("image", "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg");

		clientUsername = "maria@gmail.com";
		clientPassword = "123456";

		adminUsername = "alex@gmail.com";
		adminPassword = "123456";

		clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
		adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);

		existingId = 2L;
		nonExistingId = 999L;
	}
	
	@Test
	@DisplayName("findAll Should Return Ok When Movie No Arguments Given")
	public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {

		given()
				.when()
				.get("/movies")

				.then()
				.statusCode(200);
	}
	
	@Test
	@DisplayName("findAll Should Return Paged Movies When Movie Title Param Is Not Empty")
	public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {
		String title = "Witcher";

		given()
				.when()
				.get("/movies?title={title}", title)

				.then()
				.statusCode(200)
				.body("content[0].id", is(1))
				.body("content[0].title", equalTo("The Witcher"));
	}
	
	@Test
	@DisplayName("findById Should Return Movie When Id Exists")
	public void findByIdShouldReturnMovieWhenIdExists() {

		given()
				.when()
				.get("/movies/{id}", existingId)

				.then()
				.statusCode(200)
				.body("id", is(2))
				.body("title", equalTo("Venom: Tempo de Carnificina"));
	}
	
	@Test
	@DisplayName("findById Should Return Not Found When Id Does Not Exist")
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {
		given()
				.when()
				.get("/movies/{id}", nonExistingId)

				.then()
				.statusCode(404);
	}
	
	@Test
	@DisplayName("insert Should Return Unprocessable Entity When Admin Logged And Blank Title")
	public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() {

		postMovie.put("title", "");
		JSONObject meuFilme = new JSONObject(postMovie);

		given()
				.header("Content-type", "application-json")
				.header("Authorization", "Bearer " + adminToken)
				.body(meuFilme)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)

				.when()
				.post("/movies")

				.then()
				.statusCode(422);
	}
	
	@Test
	public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
	}
	
	@Test
	public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
	}
}
