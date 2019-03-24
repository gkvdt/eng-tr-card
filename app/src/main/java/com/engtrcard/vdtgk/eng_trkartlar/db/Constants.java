package com.engtrcard.vdtgk.eng_trkartlar.db;

public interface Constants {

    String TABLE_NAME = "words";
    String ID = "id";
    String ENG = "eng";
    String TR1 = "tr1";
    String TR2 = "tr2";
    String TR3 = "tr3";
    String TR4 = "tr4";
    String TR5 = "tr5";


    String TRUE = "true";

    String DROP_TABLE = "DROP TABLE " + TABLE_NAME;

    String GET_FROM_ID_SQL = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + "=";

    String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME
            + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ENG + " TEXT,"
            + TR1 + " TEXT,"
            + TR2 + " TEXT,"
            + TR3 + " TEXT,"
            + TR4 + " TEXT,"
            + TR5 + " TEXT,"
            + TRUE + " INTEGER)";

    String FALSE_ENG = "false_eng";
    String FALSE_TR = "false_tr";

}
