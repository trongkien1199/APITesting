package model;

import io.restassured.http.Header;

public interface RequestCapabilities {
    Header defaultHeader = new Header("Content-Type","application/json; charset=UTF-8");
    Header acceptJSONHeader = new Header("Accept", "application/json");
    static Header getAuthenticatedHeader(String encodeCredStr){
        if(encodeCredStr == null){
            throw  new IllegalArgumentException("ERR] encodeCredStr cannot be null");
        }

        return  new Header("Authorization", "Basic "+encodeCredStr);
    }
}
