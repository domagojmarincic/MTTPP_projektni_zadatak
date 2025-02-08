    package com.example.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstTest {

    // URI baze API-ja
    final static String ROOT_URI = "http://localhost:7000/employees";

    // Test GET zahtjeva
    @Test
    public void simple_get_test() {
        Response response = get(ROOT_URI + "/list");
        System.out.println(response.asString());
        response.then().body("id", hasItems(1, 2));
        response.then().body("name", hasItems("Pankaj"));
    }

    // Test POST zahtjeva
    @Test
    public void post_test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"Lisa\",\"salary\": \"2000\"}")
                .when()
                .post(ROOT_URI + "/create");

        System.out.println("POST Response\n" + response.asString());
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("name", Matchers.is("Lisa"));
    }

    // Test PUT zahtjeva
    @Test
    public void put_test() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"Lisa Tamaki\",\"salary\": \"45000\"}")
                .when()
                .put(ROOT_URI + "/update/3");

        System.out.println("PUT Response\n" + response.asString());
        response.then().body("id", Matchers.is(3));
        response.then().body("name", Matchers.is("Lisa Tamaki"));
        response.then().body("salary", Matchers.is("45000"));
    }

    // Test DELETE zahtjeva
    @Test
    public void delete_test() {
        Response response = delete(ROOT_URI + "/delete/3");
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());

        response = get(ROOT_URI + "/list");
        System.out.println(response.asString());
        response.then().body("id", Matchers.not(3));
    }

    // Test GET s Data Providerom
    @Test(dataProvider = "dpGetWithParam")
    public void get_with_param(int id, String name) {
        get(ROOT_URI + "/get/" + id).then().body("name", Matchers.is(name));
    }

    @DataProvider
    public Object[][] dpGetWithParam() {
        return new Object[][]{
                {1, "Pankaj"},
                {2, "David"}
        };
    }
}
