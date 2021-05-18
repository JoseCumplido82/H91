package com.example.h91.modelos;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class ConfiguracionDB {
    //PARA CONECTAR CON BBDD LOCAL
   // public static final String HOSTDB = "10.0.2.2";
   //public static final String NOMBREDB = "empleados";
    //public static final String USUARIODB = "root";
   //public static final String CLAVEDB = "";
   // private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   // public static final String PUERTOMYSQL = "3306";
   // public static final String URLMYSQL = "jdbc:mysql://" + HOSTDB + ":" + PUERTOMYSQL + "/" + NOMBREDB + OPCIONES;

    //----------------------------------------------------------------------------------------------------

    //PARA CONECTAR CON BBDD SERVIDOR EXTERNA RAIOLA
    //84.78.105.67 - ip publica equipo local
    //public static final String HOSTDB = "217.182.121.210";
    //public static final String HOSTDB = "hidalgo91.com";
    //public static final String NOMBREDB = "hidalgoc_wpD";
    //public static final String USUARIODB = "hidalgoc_wpD";
    //public static final String CLAVEDB = "eE08092020**";
    //private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   //  private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    //public static final String PUERTOMYSQL = "2083";
    //public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;

    //------------------------------------------------------------------------------------------------------

    //PARA CONECTAR CON BBDD SERVER INSTITUTO
    public static final String HOSTDB="infsalinas.sytes.net";
    public static final String NOMBREDB="empleados";
    public static final String USUARIODB="usuario";
    public static final String CLAVEDB="dam1234";
    private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    public static final String PUERTOMYSQL="5306";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;

    //variables staticas para usar en toda la APP
    public static String pass = "Madrid2021";
    public static int idEstado = 1;
    public static String UsuarioActual = "";
    public static String PassActual = "";
    public static int IDUsuarioActual = 0;
    public static byte[] salt;

    static {
        try {
            salt = getSalt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------
    //METODOS DE ENCRIPTACION SHA_1, SHA 256 Y SHA 512

    public static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static String get_SHA_512_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt)
    {
        //Use MessageDigest md = MessageDigest.getInstance("SHA-256");
        String generatedPassword = null;
        try {
             MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }


    //Add salt
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String saltToString(byte[] salt)
    {
	    /*	StringBuilder sb = new StringBuilder();
            for(int i=0; i< salt.length ;i++)
            {
                sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
            }
            String textoSalt= sb.toString();
            */
        String textoSalt = Base64.getEncoder().encodeToString(salt);
        return textoSalt;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] saltFromString(String textoSalt)
    {
        byte[] salt = Base64.getDecoder().decode(textoSalt);
        return salt;
    }


}
