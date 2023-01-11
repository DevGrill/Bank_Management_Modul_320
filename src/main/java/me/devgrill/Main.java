package me.devgrill;

import me.devgrill.UserHandler.UserHandler;

public class Main {
    public static void main(String[] args) {
        UserHandler userHandler = new UserHandler();
        userHandler.startUserSession();
    }
}