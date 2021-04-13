package com.example.h91.modelos;

public class ConfiguracionDB {
      public static final String HOSTDB = "10.0.2.2";
    //public static final String HOSTDB = "217.182.121.210";
    //public static final String HOSTDB = "https://ha413.raiolanetworks.es";
    //public static final String NOMBREDB = "hidalgoc_wpD";
    //public static final String USUARIODB = "hidalgoc_wpD";
    //public static final String CLAVEDB = "eE08092020**";
    public static final String NOMBREDB = "empleados";
    public static final String USUARIODB = "root";
    public static final String CLAVEDB = "";
    private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    public static final String PUERTOMYSQL = "3306";
   // public static final String PUERTOMYSQL = "8080";
   //public static final String PUERTOMYSQL = "2083";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;

}
