package com.github.alexvishneuski.vkbestclient.repository.database.util;

public class Constants {

    public interface Types {

        String TEXT_TYPE = " TEXT";
        String BLOB_TYPE = " BLOB";
        String INTEGER_TYPE = " INTEGER";
        String REAL_TYPE = " REAL";
        String NUMERIC_TYPE = " NUMERIC";
        String NULL_TYPE = " NULL";
    }

    public interface Separators {

        String COMMA = ", ";
        String OPN_BRACKET = " (";
        String CLS_BRACKET = ")";

    }

    public interface RegEx {

        String PRIMARY_KEY = " PRIMARY KEY";
        String AUTOINCREMENT = "  AUTOINCREMENT";
        String NOT_NULL = " NOT NULL";
        String UNIQUE = " UNIQUE";

        String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";
        String INSERT_INTO = "INSERT INTO ";
        String SELECT_ALL_FROM = "SELECT * FROM ";

    }
}
