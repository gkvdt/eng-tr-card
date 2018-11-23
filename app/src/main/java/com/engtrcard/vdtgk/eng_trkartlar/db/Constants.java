package com.engtrcard.vdtgk.eng_trkartlar.db;

public interface Constants {

    String TABLE_NAME = "words";
    String ID = "id";
    String ENG = "eng";
    String TR = "tr";
    String TRUE = "true";

    String DROP_TABLE = "DROP TABLE "+TABLE_NAME;

    String GET_FROM_ID_SQL = "SELECT * FROM "+TABLE_NAME+" WHERE "+ID+"=";

    String CREATE_TABLE_SQL= "CREATE TABLE "+TABLE_NAME
            +"("
            +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +ENG+" TEXT,"
            +TR+" TEXT,"
            +TRUE+" INTEGER)";

    String FALSE_ENG = "false_eng";
    String FALSE_TR = "false_tr";

}
