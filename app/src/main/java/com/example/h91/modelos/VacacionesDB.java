package com.example.h91.modelos;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.h91.Clases.Vacaciones;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VacacionesDB {
    //---------------------------------------------------------------------
    public static boolean insertarVacacionesTabla(Vacaciones v)
    {
        Connection conexion= BaseDB.conectarConBaseDeDatos();
        if(conexion==null)
        {
            return false;
        }
        //-----------------------------------------
        try {
            String ordensql= "INSERT INTO vacaciones(idSolicitante, fecha_inicio, fecha_fin, dias, fecha_solicitud, idEstado) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, v.getIdSolicitante());
            //
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(v.getFecha_inicio());
            Log.i("sql", "valor de la fecha elegida-> "+ strDate);
            java.sql.Date sqlFecha= java.sql.Date.valueOf(strDate);
            pst.setDate(2, sqlFecha);
            //
            String strDateFin= dateFormat.format(v.getFecha_fin());
            Log.i("sql", "valor de la fecha de fin " +strDateFin);
            java.sql.Date sqlFechaFin= java.sql.Date.valueOf(strDateFin);
            pst.setDate(3, sqlFechaFin);
            //
            pst.setInt(4, v.getDias());
            //
            String strDateSolicitud= dateFormat.format(v.getFecha_solicitud());
            Log.i("sql", "valor de la fecha de solicitud " + strDateSolicitud);
            java.sql.Date sqlFechaSolicitud= java.sql.Date.valueOf(strDateSolicitud);
            pst.setDate(5, sqlFechaSolicitud);
            //
            pst.setInt(6, v.getIdEstado());
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

    public static ArrayList<Vacaciones> obtenerVacaciones()
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            System.out.println("no se ha podido conectar con la base datos");
            return null;
        }
        ArrayList<Vacaciones> vacacionesDevueltas = new ArrayList<Vacaciones>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select v.id, v.idSolicitante, v.fecha_inicio, v.fecha_fin, v.dias, v.fecha_solicitud, v.idEstado from vacaciones v;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                int id= resultado.getInt("id");
                int idSolicitante= resultado.getInt("idSolicitante");
                Date fecha_inicio= resultado.getDate("fecha_inicio");
                Date fecha_fin= resultado.getDate("fecha_fin");
                int dias= resultado.getInt("dias");
                Date fecha_solicitud= resultado.getDate("fecha_solicitud");
                int idEstado= resultado.getInt("idEstado");
                Vacaciones v= new Vacaciones(id, idSolicitante, fecha_inicio, fecha_fin, dias, fecha_solicitud, idEstado);
                vacacionesDevueltas.add(v);

            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return vacacionesDevueltas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }

    //---------------------------------------------------------------------------
    public static boolean borrarVacacionesTabla(Vacaciones vacaciones)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        //---------------------
        try {
            String ordensql= "DELETE FROM vacaciones WHERE id = ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, vacaciones.getId());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas >0)
            {
                return true;

            }else{

                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }
    //-------------------------------------------------------------------
    public static Vacaciones buscarVacacionesTabla(int IdSolicitante){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return null;
        }
        //--------------------------------------------------
        Vacaciones vacacionesEncontradas = null;
        try{
            String ordensql = "select * from vacaciones where idSolicitante like ?";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, IdSolicitante);
            ResultSet resultadosql= pst.executeQuery();
            //---------------------------------------------
            while (resultadosql.next())
            {
                int id = resultadosql.getInt("id");
                int IdSolicitantesegundo= resultadosql.getInt("idDepartamento");
                Date fecha_inicio= resultadosql.getDate("fecha_inicio");
                Date fecha_fin= resultadosql.getDate("fecha_fin");
                int dias= resultadosql.getInt("dias");
                Date fecha_solicitud= resultadosql.getDate("fecha_solicitud");
                int idEstado= resultadosql.getInt("idEstado");
                vacacionesEncontradas = new Vacaciones(id, idEstado, fecha_inicio, fecha_fin, dias, fecha_solicitud, idEstado);
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return vacacionesEncontradas;
        }catch (SQLException e){
            return null;
        }
    }

}
