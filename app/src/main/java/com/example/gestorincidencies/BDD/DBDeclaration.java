package com.example.gestorincidencies.BDD;

import android.provider.BaseColumns;

public class DBDeclaration {
    private DBDeclaration(){}
    public static class IncidenciaDBD implements BaseColumns {
        public static final String Nombre_Tabla ="Incidencias";
        public static final String ID = "id";
        public static final String Nombre_Incidencia = "NOM_I";
        public static final String Nombre_Emergencia = "Emergencia";
    }
}
