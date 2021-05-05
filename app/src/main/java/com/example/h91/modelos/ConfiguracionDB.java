package com.example.h91.modelos;


public class ConfiguracionDB {

    //PARA LOCAL
    public static final String HOSTDB = "10.0.2.2";
    public static final String NOMBREDB = "empleados";
        public static final String USUARIODB = "root";
        public static final String CLAVEDB = "";
        private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      public static final String PUERTOMYSQL = "3306";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;
    //
    //


    //PARA EXTERNA
    //public static final String HOSTDB = "217.182.121.210";
    //public static final String HOSTDB = "hidalgo91.com";


    //public static final String NOMBREDB = "hidalgoc_wpD";
    //public static final String USUARIODB = "hidalgoc_wpD";
    //public static final String CLAVEDB = "eE08092020**";
    //private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   // private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
   //public static final String PUERTOMYSQL = "2083";
    //public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;


//variables staticas para usar en toda la APP
    public static String pass="Madrid2021*";
    public static int idEstado=1;
    public static String UsuarioActual="";
    public static String PassActual="";
    public static int IDUsuarioActual=0;
}
