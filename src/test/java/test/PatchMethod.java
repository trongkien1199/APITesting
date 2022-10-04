package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BuildJSONModel;
import model.PostBody;
import model.RequestCapabilities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
public class PatchMethod implements RequestCapabilities {
    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com";

        //Form up request instance, header and base Uri
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);

        //From up body
        PostBody postBody = new PostBody();
        postBody.setTitle("new Title");
        String patchBodyStr = BuildJSONModel.parseJSON(postBody);
        final String TARGET_PATCH_ID = "1";
        Response response = request.body(patchBodyStr).patch("posts/".concat(TARGET_PATCH_ID));
        response.then().body("title", equalTo(postBody.getTitle()));
        response.prettyPrint();
    }

}
