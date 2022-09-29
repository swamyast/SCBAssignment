package com.book.api;

public enum StatusCode {
    CODE_200(200, ""),
    CODE_204(204,""),
    CODE_201(201, ""),
    CODE_400(400, "Missing required field: name"),
    CODE_401(401, "Invalid access token"),
    CODE_500(500, "Error while deleting book from database");
    public final int code;
    public final String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
