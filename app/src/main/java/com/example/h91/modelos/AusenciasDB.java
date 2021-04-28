package com.example.h91.modelos;

import android.util.Log;

import com.example.h91.Clases.Ausencias;
import com.example.h91.Clases.Departamento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AusenciasDB {
    //----------------------------------------------
    public static boolean insertarAusenciaTabla(Ausencias a)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        //---------------------------------
        try{
            String ordensql= "INSERT INTO ausencias (idSolicitante, fecha_inicio, hora_inicio, horas, fecha_solicitud, motivo, idEstado) VALUES(?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, a.getIdSolicitante());
            DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd");
            String strDateInicio= dateFormat.format(a.getFecha_inicio());
            Log.i("sql", "valor de la fecha " + strDateInicio);
            java.sql.Date sqlFechaInicio= java.sql.Date.valueOf(strDateInicio);
            pst.setDate(2, sqlFechaInicio);
            pst.setString(3,a.getHora_inicio());
            pst.setInt(4, a.getHoras());
            String strDateSolicitud= dateFormat.format(a.getFecha_solicitud());
            Log.i("sql", "valor de la fecha " + strDateSolicitud);
            java.sql.Date sqlFechaSolicitud= java.sql.Date.valueOf(strDateSolicitud);
            pst.setDate(5, sqlFechaSolicitud);
            pst.setString(6, a.getMotivo());
            pst.setInt(7, a.getIdEstado());

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


    //-----------------------------------------------------------------
    public static ArrayList<Ausencias> obtenerAusencia(){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Ausencias> ausenciaDevuelta = new ArrayList<Ausencias>();
        try{
            Statement sentencia = conexion.createStatement();
            String ordensql= "select * from ausencias";
            ResultSet resultado = sentencia.executeQuery(ordensql);
            while (resultado.next())
            {
                int id = resultado.getInt("id");
                int idSolicitante = resultado.getInt("idSolicitante");
                Date fecha_inicio= resultado.getDate("fecha_inicio");
                String hora_inicio=resultado.getString("hora_inicio");
                int horas=resultado.getInt("horas");
                Date fecha_solicitud = resultado.getDate("fecha_solicitud");
                String motivo=resultado.getString("motivo");
                int idEstado= resultado.getInt("idEstado");
                Ausencias a= new Ausencias(id, idSolicitante, fecha_inicio, hora_inicio, horas, fecha_solicitud,motivo, idEstado);
                ausenciaDevuelta.add(a);

            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return ausenciaDevuelta;
        }catch (SQLException e){
            Log.i("sql", "error sql");
            return  ausenciaDevuelta;
        }
    }
    //-------------------------------------------------------------------
    public static boolean IDEmpleadoAusencia(int dniUser) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            System.out.println("no se ha podido conectar con la base de datos");
            return false;
        }
        try {
            /////---------------------------------
            String ordenSQL = "select * from ausencias where idSolicitante like ?";
            PreparedStatement pst=conexion.prepareStatement(ordenSQL);
            pst.setInt(1, dniUser);
            ResultSet resultadosql= pst.executeQuery();
            int numerofilas=0;
            while (resultadosql.next())
            {
                numerofilas++;
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            if(numerofilas>0){
                return true;

            }
            else {
                return false;
            }

        } catch (SQLException e) {
            Log.i("sql", "error sql");
            System.out.println("entra al catch");
            return false;
        }
    }

    //--------------------------------------------------------------------
    public static boolean borrarAusenciaTabla(Ausencias ausencias){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null){
            return false;
        }
        //-------------------------------
        try {
            String ordensql = "DELETE FROM ausencias WHERE idSolicitante LIKE ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, ausencias.getIdSolicitante());
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

    public static boolean actualizarAusenciasTabla(Ausencias a)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        //-----------------------------------
        try{
            String ordensql = "UPDATE ausencias SET idSolicitante =? WHERE id =?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, a.getIdSolicitante());
            pst.setInt(2, a.getId());
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
