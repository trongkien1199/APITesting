package test;
import Data.AuthenticationData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapabilities;
import utils.HandleAuthentication;

import static io.restassured.RestAssured.given;
public class JiraCreateNewIssue  implements RequestCapabilities {
    public static void main(String[] args) {
        //Api info
        String baseUri = "https://kientestapi.atlassian.net/";
        String path = "rest/api/3/issue";
        String projectKey = "KAN";
        String encodeCredStr = HandleAuthentication.encodeCredStr(AuthenticationData.EMAIL, AuthenticationData.TOKEN);
        //Request object
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header(acceptJSONHeader);
        request.header(RequestCapabilities.getAuthenticatedHeader(encodeCredStr));
        //Define body data
        String fieldStr="{\n" +
                "  \"fields\":\n" +
                "  {\n" +
                "    \"summary\": \" Summary | From Jira API\",\n" +
                "    \"project\": {\n" +
                "      \"key\": \"KAN\"\n" +
                "    },\n" +
                "    \"issuetype\":\n" +
                "    {\n" +
                "      \"id\": \"10001\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        //Send request
        Response response = request.body(fieldStr).post(path);
        response.prettyPrint();
    }
}
