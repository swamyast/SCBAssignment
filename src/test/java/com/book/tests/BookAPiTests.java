package com.book.tests;

import com.book.api.StatusCode;
import com.book.api.applicationApi.ReqResponseApi;
import com.book.utils.DataLoader;
import com.book.utils.FakerUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookAPiTests {
    String delId;
    HashMap<String,Object> bookDetails=new HashMap<String,Object>();
    HashMap<String,Object> updateBookDetails=new HashMap<String,Object>();
     public HashMap<String,Object> getBook(){
         bookDetails.put("name", "HeadFirst Java");
         bookDetails.put("Refactoring","Improving the Design of Existing Code");
         bookDetails.put("author","Improving the Design of Existing Code");
         bookDetails.put("publication","Addison-Wesley Professional");
         bookDetails.put("category","Java Books");
         bookDetails.put("pages",448);
         bookDetails.put("price",35.50);
         return bookDetails;
     }
    public HashMap<String,Object> updateBook(){
        updateBookDetails.put("name", FakerUtils.generateName());
        updateBookDetails.put("Refactoring","Improving the Design of Existing Code");
        updateBookDetails.put("author","Improving the Design of Existing Code");
        updateBookDetails.put("publication","Addison-Wesley Professional");
        updateBookDetails.put("category","Python Books");
        updateBookDetails.put("pages",448);
        updateBookDetails.put("price",35.50);
        return updateBookDetails;
    }
    HashMap<String,Object> createdBook=getBook();
    HashMap<String,Object> updatedBook=updateBook();
    @Description("This is the description of Book")
    @Test(description = "Create a Book with necessary details")
    public void createBookWithDetails(){
        Response response = ReqResponseApi.post(createdBook);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        verifyAssertEqual(response.asString(),bookDetails);
        delId=response.path("id").toString();
        System.out.println(response.path("id").toString());
    }

    @Test(description = "Create a same Book again with existing details")
    public void createBookWithSameDetails(){
        Response response = ReqResponseApi.post(createdBook);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        verifyAssertEqual(response.asString(),bookDetails);
    }

    @Test(description = "Fetch all books details")
    public void getAllBookDetails(){
        Response response = ReqResponseApi.getAllBooks();
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    @Test(description = "Fetch Single book details by Id " )
    public void getSingleBookDetails(){
        Response response = ReqResponseApi.get(DataLoader.getInstance().getBookId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }
    @Test(description = "Delete Single book details by Id " )
    public void removeBook(){
        Response response = ReqResponseApi.deleteBook(delId);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }
    @Test(description = "Delete single book with same id which does not exist or already deleted " )
    public void removeSameBook(){
        Response response = ReqResponseApi.deleteBook(DataLoader.getInstance().getDeleteBookId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_500);
        System.out.println(response.asPrettyString());
        assertThat(response.asPrettyString(),containsString("Error while deleting book from database"));
    }

    @Test(description = "Update Single book details by Id " )
    public void updateBookDetails(){
        Response response = ReqResponseApi.update(DataLoader.getInstance().getUpdateBookId(),updatedBook);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        verifyAssertEqual(response.asString(),updatedBook);
    }
    @Test(description = "Update same book details by Id with same details " )
    public void updateSameBookDetails(){
        Response response = ReqResponseApi.update(DataLoader.getInstance().getUpdateBookId(),updatedBook);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        verifyAssertEqual(response.asString(),updatedBook);
    }

    @Step
    public void verifyAssertEqual(String response, HashMap bookDetails){
        JsonPath jsonPath=new JsonPath(response);
        assertThat(jsonPath.getString("name"),equalTo(bookDetails.get("name")));
        System.out.println(jsonPath.getString("name"));
        assertThat(jsonPath.getString("author"),equalTo(bookDetails.get("author")));
        assertThat(jsonPath.getString("category"),equalTo(bookDetails.get("category")));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }
}
