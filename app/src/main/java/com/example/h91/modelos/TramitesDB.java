package com.example.h91.modelos;

import android.util.Log;

import com.example.h91.Clases.Ausencias;
import com.example.h91.Clases.Tramites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TramitesDB {

    public static boolean insertarTramiteTabla(Tramites t)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        //---------------------------------
        try{
            String ordensql= "INSERT INTO tramites (idSolicitante, nombre_documento, asunto, comentario, fecha_solicitud, idEstado) VALUES(?,?,?,?,?,?);";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, t.getIdSolicitante());
            pst.setString(2, t.getNombre_documento());
            pst.setString(3, t.getAsunto());
            pst.setString(4, t.getComentario());
            DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd");
            String strDateSolicitud= dateFormat.format(t.getFecha_solicitud());
            Log.i("sql", "valor de la fecha " + strDateSolicitud);
            java.sql.Date sqlFechaSolicitud= java.sql.Date.valueOf(strDateSolicitud);
            pst.setDate(5, sqlFechaSolicitud);
            pst.setInt(6, t.getIdEstado());
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

    public static ArrayList<Tramites> obtenerTramites(){
        Connection conexion= BaseDB.conectarConBaseDeDatos();
        if(conexion ==null)
        {
            System.out.println("no se ha podido conectar con la base de datos");
            return null;
        }
        ArrayList<Tramites> tramitesDevueltos = new ArrayList<Tramites>();
        try{
            Statement sentencia = conexion.createStatement();
            //String ordenSQL = "select t.id, t.idSolicitante, t.nombre_documento, t.asunto, t.comentario, t.fecha_solicitud, t.idEstado from tramites t;"; // WHERE t.idSolicitante like ?
            String ordenSQL = "select t.id, t.idSolicitante, t.nombre_documento, t.asunto, t.comentario, t.fecha_solicitud, t.idEstado from tramites t WHERE t.idSolicitante like ?;"; //

            ResultSet resultado= sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                // System.out.println("llega al resultado del while pero no coge los atributos del empleado");
                int id= resultado.getInt("id");
                int idSolicitante= resultado.getInt("idSolicitante");
                String nombre_documento= resultado.getString("nombre_documento");
                String asunto= resultado.getString("asunto");
                String comentario= resultado.getString("comentario");
                Date fecha_solicitud = resultado.getDate("fecha_solicitud");
                int idEstado= resultado.getInt("idEstado");
                Tramites tramites= new Tramites(id, idSolicitante, nombre_documento, asunto, comentario, fecha_solicitud, idEstado);
                tramitesDevueltos.add(tramites);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return tramitesDevueltos;
        }catch (SQLException e){
            Log.i("sql", "error sql");
            return null;
        }
    }

    //---------------------------------------------------------------------------
    public static boolean borrarTramitesTabla(Tramites tramites)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        //---------------------
        try {
            String ordensql= "DELETE FROM tramites WHERE id = ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, tramites.getId());
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
//---------------------------------------------------------------------------
public static boolean IDEmpleadoAusencia(int idEmpleado) {
    Connection conexion = BaseDB.conectarConBaseDeDatos();
    if (conexion == null) {
        System.out.println("no se ha podido conectar con la base de datos");
        return false;
    }
    try {
        /////---------------------------------
        String ordenSQL = "select * from tramites where idSolicitante like ?";
        PreparedStatement pst=conexion.prepareStatement(ordenSQL);
        pst.setInt(1, idEmpleado);
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
        Log.i("sql", "entra en idempleado");
        System.out.println("entra al catch");
        return false;
    }
}



//-----------------------------------------------------------------------------
    public static Tramites buscarTramitesTabla(String nombre_documento){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return null;
        }
        //--------------------------------------------------
        Tramites tramiteEncontrado = null;
        try{
            String ordensql = "select * from empleado where nombre_documento like ?";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setString(1, nombre_documento);
            ResultSet resultadosql= pst.executeQuery();
            //---------------------------------------------
            while (resultadosql.next())
            {
                int id = resultadosql.getInt("id");
                int idSolicitante= resultadosql.getInt("idSolicitante");
                String nombre_documentoDos= resultadosql.getString("nombre_documento");
                String asunto = resultadosql.getString("asunto");
                String comentario = resultadosql.getString("comentario");
                Date fecha_solicitud = resultadosql.getDate("fecha_solicitud");
                int idEstado= resultadosql.getInt("idEstado");

                tramiteEncontrado= new Tramites(id, idSolicitante, nombre_documentoDos, asunto, comentario, fecha_solicitud, idEstado);

            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return tramiteEncontrado;
        }catch (SQLException e){
            return null;
        }
    }

    //----------------------------------------------------------------------
    public static boolean actualizarTramitesTabla(Tramites t) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "UPDATE tramites SET nombre_documento = ? WHERE idSolicitante = ?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, t.getNombre_documento());
            pst.setInt(2, t.getIdSolicitante());
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

}
