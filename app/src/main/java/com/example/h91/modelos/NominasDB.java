package com.example.h91.modelos;

import android.util.Log;

import com.example.h91.Clases.Nominas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class NominasDB {
    public static ArrayList<Nominas> obtenerNominas(int idempleado){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null){
            System.out.println("no se ha podido conectar con la base de datos");
            return null;
        }
        ArrayList<Nominas> nominasDevueltas= new ArrayList<Nominas>();
        try {
            String ordenSQL= "select nomi.id, nomi.idEmpleado, nomi.nombre, nomi.fecha_subida from nominas nomi where nomi.idEmpleado like ?;";
            PreparedStatement pst= conexion.prepareStatement(ordenSQL);
            pst.setInt(1, idempleado);
            ResultSet resultado= pst.executeQuery();
            while (resultado.next()){
                int id= resultado.getInt("id");
                int idSolicitante = resultado.getInt("idEmpleado");
                Log.i("sql", "id del empleado " + ConfiguracionDB.IDUsuarioActual);
                String nombre= resultado.getString("nombre");
                Date fecha_subida=resultado.getDate("fecha_subida");
                Nominas n = new Nominas(id, idSolicitante, nombre, fecha_subida);
                nominasDevueltas.add(n);
            }
            resultado.close();
            pst.close();
            conexion.close();
            return nominasDevueltas;
        }catch (SQLException e){
            Log.i("sql", "error sql");
            return null;
        }
    }


    public static boolean insertarNominaTabla(Nominas n)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null){
            return false;
        }
        //-------------------------------
        try{
            String ordensql= "INSERT INTO nominas (id, idEmpleado, nombre, fecha_subida) VALUES (?,?,?,?);";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, n.getId());
            pst.setInt(2, n.getIdEmpleado());
            pst.setString(3, n.getNombre());
            pst.setDate(4, (java.sql.Date) n.getFecha_subida());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas>0){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            return false;
        }

    }
    //-----------------------------------
    public static boolean borrarNominaTabla(Nominas n)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "DELETE FROM nominas WHERE id = ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, n.getId());
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
        } catch (SQLException e) {
            return false;
        }
    }
//----------------------------------------------------------------------

}
