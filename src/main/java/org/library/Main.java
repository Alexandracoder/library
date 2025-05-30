package org.library;

import org.library.config.DBManager;

public class Main {
    public static void main(String[] args) {
        DBManager.initConnection();
    }
}