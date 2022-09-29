package com.book.api;
import com.book.utils.ConfigLoader;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.book.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, HashMap createUserDetails){
        return given(getRequestSpec()).
                body(createUserDetails).
                auth().basic(ConfigLoader.getInstance().getUser_Name(),ConfigLoader.getInstance().getPassword()).
        when().post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }


    public static Response get(String path){
        return given(getRequestSpec()).
        auth().basic(ConfigLoader.getInstance().getUser_Name(),ConfigLoader.getInstance().getPassword()).
        when().get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path, HashMap updateBookDetails){
        return given(getRequestSpec()).
                auth().basic(ConfigLoader.getInstance().getUser_Name(),ConfigLoader.getInstance().getPassword()).
                body(updateBookDetails).
        when().put(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
    public static Response delete(String path){
        return given(getRequestSpec()).
                auth().basic(ConfigLoader.getInstance().getUser_Name(),ConfigLoader.getInstance().getPassword()).
                when().delete(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
}
