package org.library;


import org.library.config.DBManager;

import static org.library.config.DBManager.initConnection;

public class Main {
    public static void main(String[] args) {
        DBManager.initConnection();

    }
}