package com.book.api.applicationApi;
import com.book.api.RestResource;
import com.book.api.Route;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.HashMap;

public class ReqResponseApi {

    @Step
    public static Response post(HashMap<String,Object> bookDetails){
        return RestResource.post(Route.BOOKS,bookDetails);
    }

    public static Response get(String bookId){
        return RestResource.get(Route.BOOKS + "/" + bookId);
    }
    public static Response getAllBooks(){
        return RestResource.get(Route.BOOKS + "/");
    }

    public static Response deleteBook(String bookId){
        return RestResource.delete(Route.BOOKS+ "/" + bookId);
    }

    public static Response update(String id,HashMap<String,Object> bookDetails){
        return RestResource.update(Route.BOOKS+"/"+id,bookDetails);
    }
}
