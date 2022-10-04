package model;

import com.google.gson.Gson;

public class BuildJSONModel {
    public static String parseJSON(PostBody postBody){
        if(postBody == null){
            throw new IllegalArgumentException("The post body cannot be null");
        }
        return new Gson().toJson(postBody);
    }
}
