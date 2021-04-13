package com.example.h91.modelos;

import android.util.Log;

import com.example.h91.Clases.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartamentoDB {
    //----------------------------------------------
    public static boolean insertarDepartamentoTabla(Departamento d)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        //---------------------------------
        try{
            String ordensql= "INSERT INTO departamento (nombre) VALUES(?);";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setString(1, d.getNombre());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }
    //---------------------------------------------------------
    public static Departamento buscarDepartamentoTabla(String nombre){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return  null;
        }
        //------------------------------------------
        Departamento departamentoEncontrado = null;
        try{
            ResultSet resultadosql = BaseDB.buscarFilasEnTabla(conexion, "departamento", "nombre", nombre);
            //-----------------------
            if(resultadosql == null){
                return null;
            }
            while (resultadosql.next())
            {
                int id = resultadosql.getInt("id");
                int idResponsable = resultadosql.getInt("idResponsable");
                String nombreDepartamento = resultadosql.getString("nombre");
            }
            resultadosql.close();
            conexion.close();
            return departamentoEncontrado;
        }catch (SQLException e){
            return null;
        }
    }

    //-----------------------------------------------------------------
    public static ArrayList<Departamento> obtenerDepartamento(){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Departamento> departamentoDevuelto = new ArrayList<Departamento>();
        try{
            Statement sentencia = conexion.createStatement();
            String ordensql= "select * from departamento";
            ResultSet resultado = sentencia.executeQuery(ordensql);
            while (resultado.next())
            {
                int id = resultado.getInt("id");
                int idResponsable = resultado.getInt("idResponsable");
                String nombre = resultado.getString("nombre");
                Departamento d = new Departamento(id, idResponsable, nombre);
                departamentoDevuelto.add(d);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return departamentoDevuelto;
        }catch (SQLException e){
            Log.i("sql", "error sql");
            return  departamentoDevuelto;
        }
    }
    //--------------------------------------------------------------------
    public static boolean borrarDepartamentoTabla(Departamento d){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null){
            return false;
        }
        //-------------------------------
        try {
            String ordensql = "DELETE FROM departamento WHERE nombre LIKE ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, d.getNombre());
            int filasAfectadas = pst.executeUpdate();
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

    public static boolean actualizarDepartamentoTabla(Departamento d)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        //-----------------------------------
        try{
            String ordensql = "UPDATE departamento SET nombre =? WHERE id =?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, d.getNombre());
            pst.setInt(2, d.getId());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }

}
