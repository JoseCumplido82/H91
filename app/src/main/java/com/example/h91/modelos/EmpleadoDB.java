package com.example.h91.modelos;

import android.util.Log;

import com.example.h91.Clases.Empleado;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.sql.Date;
import java.util.Date;

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
            String ordenSQL = "select em.id, em.idDepartamento, em.usuario, em.pass, em.nombre, em.apellido, em.domicilio, em.correo, em.telefono, em.fecha_incorporacion from empleado em;";
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
                //Date fecha_salida = resultado.getDate("fecha_salida");
                //CORREGIR
                Empleado empleado= new Empleado(id, idDepartamento, usuario, pass, nombre, apellido, domicilio, correo, telefono, fecha_incorporacion);
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
           String ordensql = "INSERT INTO empleado (idDepartamento, usuario, pass, salt, fecha_incorporacion) VALUES (?, ?, ?, ?, ?);";
           // String ordensql= "INSERT INTO `empleado`(`idDepartamento`, `usuario`, `pass`,`fecha_incorporacion`) VALUES (2,'47147133G','Madrid2021*','2020-01-07');"; PROBADO EN MYSQL Y FUNCIONA CORRECTAMENTE LA SENTENCIA
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, empleado.getIdDepartamento());
            pst.setString(2, empleado.getUsuario());
            pst.setString(3, empleado.getPass());
            pst.setString(4,empleado.getSalt());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(empleado.getFecha_incorporacion());
            Log.i("sql", "valor de la fecha -> "+ strDate);
            java.sql.Date sqlFecha= java.sql.Date.valueOf(strDate);
            pst.setDate(5, sqlFecha);
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
            String ordensql = "UPDATE empleado SET idDepartamento =?, usuario=?, pass=?, salt=?, nombre=?, apellido=?, domicilio=?, correo=?, telefono=?, fecha_incorporacion=? WHERE (id=?);";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, empleado.getIdDepartamento());
            pst.setString(2, empleado.getUsuario());
            pst.setString(3, empleado.getPass());
            pst.setString(4, empleado.getSalt());
            pst.setString(5, empleado.getNombre());
            pst.setString(6, empleado.getApellido());
            pst.setString(7, empleado.getDomicilio());
            pst.setString(8, empleado.getCorreo());
            pst.setString(9, empleado.getTelefono());
            pst.setDate(10, (java.sql.Date) empleado.getFecha_incorporacion());
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
    //-----------------------------------------------------------------------
    public static boolean actualizarEmpleadoTabla2(Empleado empleado){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion==null)
        {
            return false;
        }
        //---------------------------------
        try {
            String ordensql = "UPDATE empleado SET idDepartamento =?, usuario=?, pass=?, salt=?, nombre=?, apellido=?, domicilio=?, correo=?, telefono=?, fecha_incorporacion=? WHERE (id=?);";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setInt(1, empleado.getIdDepartamento());
            pst.setString(2, empleado.getUsuario());
            pst.setString(3, empleado.getPass());
            pst.setString(4,empleado.getSalt());
            pst.setString(5, empleado.getNombre());
            pst.setString(6, empleado.getApellido());
            pst.setString(7, empleado.getDomicilio());
            pst.setString(8, empleado.getCorreo());
            pst.setString(9, empleado.getTelefono());
            pst.setDate(10, (java.sql.Date) empleado.getFecha_incorporacion());
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



    //---------------------------------------------
    public static boolean EmpleadoEnTabla(String usuario, String pass) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            System.out.println("no se ha podido conectar con la base de datos");
            return false;
        }
        try {
            /////---------------------------------
            String ordenSQL = "select * from empleado where usuario like ? and pass like ? ";
            PreparedStatement pst=conexion.prepareStatement(ordenSQL);
            pst.setString(1, usuario);
            pst.setString(2, pass);
            ResultSet resultadosql= pst.executeQuery();
            int numerofilas=0;
            while (resultadosql.next())
            {
                ConfiguracionDB.UsuarioActual = resultadosql.getString("usuario");
                ConfiguracionDB.PassActual = resultadosql.getString("pass");
                ConfiguracionDB.IDUsuarioActual = resultadosql.getInt("id");
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
    //-------------------------------------------------------------------

    //---------------------------------------------
    public static boolean EmpleadoID(int usuario) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            System.out.println("no se ha podido conectar con la base de datos");
            return false;
        }
        try {
            /////---------------------------------
            String ordenSQL = "select * from empleado where id like ?";
            PreparedStatement pst=conexion.prepareStatement(ordenSQL);
            pst.setInt(1, usuario);
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

    //---------------------------------------------
    public static boolean EmpleadoEnTabla2(String usuario, String pass) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            System.out.println("no se ha podido conectar con la base de datos");
            return false;
        }
        Empleado empleadoEncontrado=null;
        try {
            String ordenSQL = "select * from empleado em where usuario LIKE ? and pass LIKE ?";
            PreparedStatement pst=conexion.prepareStatement(ordenSQL);
            Log.i("sql", "usuario actual -> " + ConfiguracionDB.UsuarioActual);
            pst.setString(1, usuario);
            //pst.setString(1, usuario);
            Log.i("sql", "password actual -> " + ConfiguracionDB.PassActual);
            pst.setString(2, ConfiguracionDB.PassActual);
            ResultSet resultadosql= pst.executeQuery();
            //---------
            int numEmps = 0;
            while (resultadosql.next()){
                numEmps++;

            }
            resultadosql.close();
            pst.close();
            conexion.close();
            if(numEmps > 0)
            {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            //Log.i("sql", "error sql");
            return false;
        }
    }





    public static Empleado buscarEmpleadoTabla(String usuario){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null){
            return null;
        }
        //--------------------------------------------------
        Empleado empleadoEncontrado = null;
        try{
            String ordensql = "select * from empleado where usuario like ?";
            PreparedStatement pst= conexion.prepareStatement(ordensql);
            pst.setString(1, usuario);
            ResultSet resultadosql= pst.executeQuery();
            //---------------------------------------------
            while (resultadosql.next())
            {
                int id = resultadosql.getInt("id");
                int idDepartamento= resultadosql.getInt("idDepartamento");
                String usuariodos = resultadosql.getString("usuario");
                String pass = resultadosql.getString("pass");
                String salt = resultadosql.getString("salt");
                String nombreEmpleado = resultadosql.getString("nombre");
                String apellido = resultadosql.getString("apellido");
                String domicilio = resultadosql.getString("domicilio");
                String correo= resultadosql.getString("correo");
                String telefono = resultadosql.getString("telefono");
                Date fecha_incorporacion = resultadosql.getDate("fecha_incorporacion");

                empleadoEncontrado = new Empleado(id, idDepartamento, usuariodos, pass, salt, nombreEmpleado, apellido, domicilio, correo, telefono, fecha_incorporacion);
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
