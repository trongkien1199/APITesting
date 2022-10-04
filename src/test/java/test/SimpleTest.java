package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.CoreMatchers.equalTo;
import static io.restassured.RestAssured.given;
public class SimpleTest {
    public static void main(String[] args) {
        String baseUri= "https://jsonplaceholder.typicode.com";

        //Request scope
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.basePath("/todos");

        //Response scope
        String FIRST_TODOS = "/1";
        Response response = request.get(FIRST_TODOS); // get first
        response.prettyPrint();
        response.then().body("userId", equalTo(1));
        response.then().body("id", equalTo(1));
        response.then().body("title", equalTo("delectus aut autem"));
        response.then().body("completed", equalTo(false));
    }
}
