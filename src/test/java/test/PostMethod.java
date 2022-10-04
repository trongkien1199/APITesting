package test;
import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.PostBody;

import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class PostMethod {
    public static void main(String[] args) {
        String baseUri= "https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();

        //Content-Type ---> header
        request.header(new Header("Content-Type","application/json; charset=UTF-8"));

        //Form up request body
        String postBody="{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"request's title\",\n" +
                "  \"body\": \"request's body\"\n" +
                "}";
        //Gson
        Gson gson = new Gson();
        PostBody postBody1 = new PostBody(1,1, "request's title","request's body");
        //Send post request
        request.baseUri(baseUri);
        Response response = request.body(gson.toJson(postBody1)).post("/posts");
        response.prettyPrint();

        //Status
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        //Verification
        response.then().statusCode(equalTo(201));
        response.then().statusLine(equalTo("HTTP/1.1 201 Created"));
        response.then().body("userId", equalTo(1));
        response.then().body("title",equalTo("request's title"));
        response.then().body("body",equalTo("request's body"));



    }
}
