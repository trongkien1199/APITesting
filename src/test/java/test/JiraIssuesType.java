package test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapabilities;
import org.apache.commons.codec.binary.Base64;
import utils.ProjectInfo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
public class JiraIssuesType implements RequestCapabilities {
    public static void main(String[] args) {
        String baseUri = "https://kientestapi.atlassian.net/";
        String projectKey = "KAN";

        ProjectInfo projectInfo = new ProjectInfo(baseUri,projectKey);
        System.out.println("TaskID: " + projectInfo.getIssueTypeId("task"));
    }
}
