package test;
import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.PostBody;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static io.restassured.RestAssured.given;

public class PutMethod {
    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com";

        RequestSpecification request = given();

        // Content-Type -> Header
        request.header(new Header("Content-Type","application/json; charset=UTF-8"));
        //Gson
        Gson gson = new Gson();
        PostBody postBody = new PostBody(1,1,"foo","bar");
        PostBody postBody2 = new PostBody(2,2,"foo2","bar2");
        PostBody postBody3 = new PostBody(3,3,"foo3","bar3");

        //Add to list
        List<PostBody> listPostBody = Arrays.asList(postBody,postBody2,postBody3);

        for (PostBody body : listPostBody){
            //Send request
            request.baseUri(baseUri);
            Response response = request.body(gson.toJson(body)).put("/posts/1");

            response.prettyPrint();

            //Status
            System.out.println(response.statusCode());
            System.out.println(response.statusLine());

            //Verification
            response.then().statusCode(equalTo(200));
            response.then().statusLine(equalTo("HTTP/1.1 200 OK"));
            response.then().body("userId", equalTo(body.getUserId()));
            response.then().body("title",equalTo(body.getTitle()));
            response.then().body("body",equalTo(body.getBody()));
        }
    }

}
