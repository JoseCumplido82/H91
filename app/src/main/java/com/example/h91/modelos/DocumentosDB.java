package com.example.h91.modelos;

import android.se.omapi.SEService;
import android.util.Log;

import com.example.h91.Clases.Documentos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DocumentosDB {
    //----------------------------------------------
    public static boolean insertarDocumentoTabla(Documentos d)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null){
            return false;
        }
        //----------------------------
        try {
            String ordensql = "INSERT INTO documentos (nombre) VALUES (?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, d.getNombre());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }
    //------------------------------------------------------
    public static  Documentos buscarDocumentosTabla(String nombre)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null){
            return null;
        }
        //-----------------------------------
        Documentos documentosEncontrados =null;
        try {
            ResultSet resultadosql= BaseDB.buscarFilasEnTabla(conexion, "documentos", "nombre", nombre);
            //----------------------
            if(resultadosql==null){
                return null;
            }
            while (resultadosql.next())
            {
                int id = resultadosql.getInt("id");
                int idEmpleado = resultadosql.getInt("idDepartamento");
                String nombreDocumento = resultadosql.getString("nombre");
                int es_plantilla = resultadosql.getInt("es_plantilla");

            }
            resultadosql.close();
            conexion.close();
            return documentosEncontrados;
        }catch (SQLException e){
            return null;
        }
    }
    public static ArrayList<Documentos> obtenerDocumentos(){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null){
            return null;
        }
        ArrayList<Documentos> documentosDevueltos = new ArrayList<Documentos>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordensql = "select * from documentos";
            ResultSet resultado = sentencia.executeQuery(ordensql);
            while (resultado.next()){
                int id = resultado.getInt("id");
                int idEmpleado = resultado.getInt("idEmpleado");
                String nombre = resultado.getString("nombre");
                int es_plantilla = resultado.getInt("es_plantilla");
                Documentos d = new Documentos(id, idEmpleado, nombre, es_plantilla);
                documentosDevueltos.add(d);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return documentosDevueltos;
        }catch (SQLException e){
            Log.i("sql", "error sql");
            return documentosDevueltos;
        }
    }

    public static  boolean borrarDocumentosTabla(Documentos d){
        Connection conexion= BaseDB.conectarConBaseDeDatos();
        if(conexion== null){
            return false;
        }
        //-----------------
        try {
            String ordensql= "DELETE FROM documentos WHERE nombre LIKE ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1,d.getNombre());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas>0)
            {
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean actualizarDocumentosTabla(Documentos d)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null){
            return false;
        }
        //-------------------
        try {
            String ordensql = "UPDATE documentos SET nombre =? WHERE id=?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, d.getId());
            pst.setInt(2, d.getIdEmpleado());
            pst.setString(3, d.getNombre());
            pst.setInt(4, d.getEs_plantilla());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas >0){
                return true;
            }else{
                return false;
            }

        }catch (SQLException e){
            return false;
        }
    }
}
