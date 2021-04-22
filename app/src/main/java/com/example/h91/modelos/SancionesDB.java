package com.example.h91.modelos;

import android.util.Log;

import com.example.h91.Clases.Sanciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SancionesDB {
    public static boolean insertarSancionesTabla(Sanciones sanciones){
        Connection conexion= BaseDB.conectarConBaseDeDatos();
        if(conexion==null)
        {
            return false;
        }
        //------------------
        try {
            String ordensql= "INSERT INTO sanciones(idSancionado, motivo, observacion) VALUES (?,?,?);";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, sanciones.getIdSancionado());
            pst.setString(2, sanciones.getMotivo());
            pst.setString(3, sanciones.getObservacion());
            int filasAfectadas= pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas >0){
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }

    public static Sanciones buscarSacionesTabla(String motivo)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        //-------------
        Sanciones sancionesEncontradas=null;
        try {
            ResultSet resultadosql= BaseDB.buscarFilasEnTabla(conexion, "sanciones", "motivo", motivo);
            //---------------
            if(resultadosql==null)
            {
                return null;
            }
            while (resultadosql.next())
            {
                int id= resultadosql.getInt("id");
                int idSancionado = resultadosql.getInt("idSancionado");
                String motivodos= resultadosql.getString("motivo");
                String observacion= resultadosql.getString("observacion");
            }
            resultadosql.close();
            conexion.close();
            return sancionesEncontradas;
        }catch (SQLException e){
            return null;
        }
    }

    public static ArrayList<Sanciones> obtenerSanciones(){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Sanciones> sancionesDevueltas= new ArrayList<Sanciones>();
        try {
            Statement sentencia= conexion.createStatement();
            String ordenSQL= "SELECT * FROM sanciones";
            ResultSet resultado= sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                int id= resultado.getInt("id");
                int idSancionado = resultado.getInt("idSancionado");
                String motivo= resultado.getString("motivo");
                String observacion= resultado.getString("observacion");
                Sanciones sanciones= new Sanciones(id, idSancionado, motivo, observacion);
                sancionesDevueltas.add(sanciones);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return sancionesDevueltas;
        }catch (SQLException e){
            Log.i("sql", "error sql");
            return sancionesDevueltas;
        }
    }

    public static boolean borrarSancionesTabla(Sanciones s)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql= "DELETE FROM sanciones WHERE isSancionado LIKE ?;";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, s.getIdSancionado());
            int filasAfectadas= pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean actualizarSancionesTabla(Sanciones s){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql= "UPDATE sanciones SET idSancionado = ? WHERE id=?";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, s.getIdSancionado());
            pst.setInt(2, s.getId());
            int filasAfectadas= pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }

}
