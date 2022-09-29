package com.book.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        return "Book " + faker.regexify("[A-Za-z]{10}");
    }

    public static String generateDescription(){
        Faker faker = new Faker();
        return "Book Description " + faker.regexify("[ A-Za-z]{50}");
    }
}
