package com.example.h91.modelos;

import android.util.Log;

import com.example.h91.Clases.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class EmpleadoDB {
    //------------------------------------------------------------
    public static ArrayList<Empleado> obtenerEmpleados(){
        Connection conexion= BaseDB.conectarConBaseDeDatos();
        if(conexion ==null)
        {
            System.out.println("no se ha podido conectar con la base de datos");
            return null;
        }
        ArrayList<Empleado> empleadosDevueltos = new ArrayList<Empleado>();
        try{
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select em.id, em.idDepartamento, em.usuario, em.pass, em.nombre, em.apellido, em.domicilio, em.correo, em.telefono, em.fecha_incorporacion,em.sanciones, em.fecha_salida from empleado em;";
            ResultSet resultado= sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                // System.out.println("llega al resultado del while pero no coge los atributos del empleado");
                int id= resultado.getInt("id");
                int idDepartamento= resultado.getInt("idDepartamento");
                String usuario= resultado.getString("usuario");
                String pass= resultado.getString("pass");
                String nombre= resultado.getString("nombre");
                String apellido= resultado.getString("apellido");
                String domicilio = resultado.getString("domicilio");
                String correo = resultado.getString("correo");
                String telefono = resultado.getString("telefono");
                //posible error con date, cambiar la importacion de util a sql
                Date fecha_incorporacion = resultado.getDate("fecha_incorporacion");
                char sanciones= (char) resultado.getInt("sanciones");
                Date fecha_salida = resultado.getDate("fecha_salida");
                //CORREGIR
                Empleado empleado= new Empleado(id, idDepartamento, usuario, pass, nombre, apellido, domicilio, correo, telefono, fecha_incorporacion, sanciones, fecha_salida);
                empleadosDevueltos.add(empleado);

            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return empleadosDevueltos;
        }catch (SQLException e){
            Log.i("sql", "error sql");
            return null;
        }
    }

    //----------------------------------------------------------------------
    public static boolean insertarEmpleadoTabla(Empleado empleado)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //------------
        try{
           String ordensql = "INSERT INTO empleado (idDepartamento, usuario, pass, fecha_incorporacion) VALUES (?, ?, ?, ?);";
           // String ordensql= "INSERT INTO `empleado`(`idDepartamento`, `usuario`, `pass`,`fecha_incorporacion`) VALUES (2,'47147133G','Madrid2021*','2020-01-07');"; PROBADO EN MYSQL Y FUNCIONA CORRECTAMENTE LA SENTENCIA
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, empleado.getIdDepartamento());
            pst.setString(2, empleado.getUsuario());
            pst.setString(3, empleado.getPass());
            pst.setDate(4, empleado.getFecha_incorporacion());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else{
                return false;
            }

        }catch (SQLException e) {
            return false;
        }
    }
    //---------------------------------------------------------------------------
    public static boolean borrarEmpleadoTabla(Empleado empleado)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        //---------------------
        try {
            String ordensql= "DELETE FROM empleado WHERE id = ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, empleado.getId());
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
    //-------------------------------------------------------------------------
    public static boolean actualizarEmpleadoTabla(Empleado empleado){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null)
        {
            return false;
        }
        //---------------------------------
        try {
            String ordensql = "UPDATE empleado SET idDepartamento =?, usuario=?, pass=?, nombre=?, apellido=?, domicilio=?, correo=?, telefono=?, fecha_incorporacion=? WHERE (id=?);";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, empleado.getIdDepartamento());
            pst.setString(2, empleado.getUsuario());
            pst.setString(3, empleado.getPass());
            pst.setString(4, empleado.getNombre());
            pst.setString(5, empleado.getApellido());
            pst.setString(6, empleado.getDomicilio());
            pst.setString(7, empleado.getCorreo());
            pst.setString(8, empleado.getTelefono());
            pst.setDate(9, empleado.getFecha_incorporacion());
            pst.setInt(11, empleado.getId());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas >0)
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
    //-------------------------------------------------------------------
    public static Empleado buscarEmpleadoTabla(String nombre){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return null;
        }
        //--------------------------------------------------
        Empleado empleadoEncontrado = null;
        try{
            String ordensql = "select * from empleado where nombre like ?";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setString(1, nombre);
            ResultSet resultadosql= pst.executeQuery();
            //---------------------------------------------
            while (resultadosql.next())
            {
                int id = resultadosql.getInt("id");
                int idDepartamento= resultadosql.getInt("idDepartamento");
                String usuario = resultadosql.getString("usuario");
                String pass = resultadosql.getString("pass");
                String nombreEmpleado = resultadosql.getString("nombre");
                String apellido = resultadosql.getString("apellido");
                String domicilio = resultadosql.getString("domicilio");
                String correo= resultadosql.getString("correo");
                String telefono = resultadosql.getString("telefono");
                Date fecha_incorporacion = resultadosql.getDate("fecha_incorporacion");
                Date fecha_salida = resultadosql.getDate("fecha_salida");

                //CORREGIR
                empleadoEncontrado = new Empleado(id, idDepartamento, usuario, pass, nombreEmpleado, apellido, domicilio, correo, telefono, fecha_incorporacion, fecha_salida);
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return empleadoEncontrado;
        }catch (SQLException e){
            return null;
        }
    }
}
