package utils;

import Data.AuthenticationData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BuildJSONModel;
import model.RequestCapabilities;
import org.apache.commons.codec.binary.Base64;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectInfo implements RequestCapabilities{
    private String baseUri;
    private String projecKey;

    private List<Map<String, String>> issueTypes;
    private Map<String, List<Map<String, String>>> projectInfo;

    public ProjectInfo(String baseUri, String projecKey) {
        this.baseUri = baseUri;
        this.projecKey = projecKey;
        getProjectInfo();
    }

    public String getIssueTypeId(String issueTypeStr){
        getProjectIssueTypes();
        String issueTypeId = null;
        for (Map<String, String> issueType : issueTypes){
            if (issueType.get("name").equalsIgnoreCase(issueTypeStr)){
                issueTypeId = issueType.get("id");
                break;
            }
        }
        if(issueTypeId==null){
            throw new IllegalArgumentException("[Err]: Could not find the issue type ID");
        }
        return issueTypeId;
    }
    private void getProjectInfo(){
        String path = "/rest/api/3/project/".concat(projecKey);

        //Authentication
        String encodeCredStr = HandleAuthentication.encodeCredStr(AuthenticationData.EMAIL,AuthenticationData.TOKEN);

        //Form up request instance, header and baseUri
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header(RequestCapabilities.getAuthenticatedHeader(encodeCredStr));

        Response response = request.get(path);
        String string = new Gson().toJson(response);
        System.out.println(string);

        //        response.prettyPrint();
        // {} map
        // [] List
         projectInfo = JsonPath.from(response.asString()).get();
    }
    private void getProjectIssueTypes(){
        issueTypes = projectInfo.get("issueTypes");
    }
}
