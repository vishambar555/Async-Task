package com.example.vishambar.webserviceusingretrofit.advanced.utils;

public interface Constants {
    String baseUrl = "http://todolistmobileapp-env.ap-south-1.elasticbeanstalk.com/webapi/";
    String register ="authors/";

    String login = register +"login/";

    String logout = register +"signout/";

    String addToDoItem = "todolists/";

    String getToDoItem = addToDoItem;

    String deleteToDo = addToDoItem;

    String modifyToDoUrl = addToDoItem;

}
