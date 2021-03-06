package com.example.h91.Clases;

import android.content.Intent;
import android.os.Bundle;

import com.example.h91.ActivityAnadirEmpleado;
import com.example.h91.modelos.ConfiguracionDB;

import java.io.Serializable;
//import java.sql.Date;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Empleado implements Serializable {

    //atributos
    private int id;
    private int idDepartamento;
    private String usuario;
    private String pass;
    private String salt;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String correo;
    private String telefono;
    private Date fecha_incorporacion;
    private Date fecha_salida;

    //constructores
    public Empleado(int id, int idDepartamento, String usuario, String pass, String nombre, String apellido, String domicilio, String correo, String telefono, Date fecha_incorporacion) {
        this.id = id;
        this.idDepartamento = idDepartamento;
        this.usuario = usuario;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_incorporacion = fecha_incorporacion;
    }
    public Empleado(int id, int idDepartamento, String usuario, String pass, String nombre, String apellido, String domicilio, String correo, String telefono) {
        this.id = id;
        this.idDepartamento = idDepartamento;
        this.usuario = usuario;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
    }
    public Empleado(int id,String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Empleado() {
        this.id = 0;
        this.idDepartamento = 0;
        this.usuario = "";
        this.pass = "";
        this.nombre = "";
        this.apellido = "";
        this.domicilio = "";
        this.correo = "";
        this.telefono = "";
        this.fecha_incorporacion = null;
        this.fecha_salida = null;
    }

    public Empleado(int idDepartamento, String usuario,String pass,String salt, Date fecha_incorporacion)  {
        this.idDepartamento = idDepartamento;
        this.usuario = usuario;
        this.pass= pass;
        this.salt=salt;
        this.fecha_incorporacion = fecha_incorporacion;
    }
    public Empleado(int idDepartamento, String usuario,String pass, Date fecha_incorporacion)  {
        this.idDepartamento = idDepartamento;
        this.usuario = usuario;
        this.pass= pass;
        this.fecha_incorporacion = fecha_incorporacion;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Empleado(int id, int idDepartamento, String usuario, String pass, String salt, String nombre, String apellido, String domicilio, String correo, String telefono, Date fecha_incorporacion) {
        this.id = id;
        this.idDepartamento = idDepartamento;
        this.usuario = usuario;
        this.pass = pass;
        this.salt = salt;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_incorporacion = fecha_incorporacion;
    }

    public Empleado(int idDepartamento, String usuario, String pass, String nombre, String apellido, String domicilio, String correo, String telefono, Date fecha_incorporacion) {
        this.idDepartamento = idDepartamento;
        this.usuario = usuario;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_incorporacion = fecha_incorporacion;
    }

    public Empleado(String usuario, String pass, String nombre, String apellido, String domicilio, String correo, String telefono) {
        this.usuario = usuario;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
    }



    public Empleado(int idDepartamento, String nombre, String apellido) {
        this.idDepartamento=idDepartamento;
        this.nombre=nombre;
        this.apellido= apellido;
    }

    public Empleado(String usuario, String pass){
        this.usuario=usuario;
        this.pass=pass;
    }


    //getter y setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_incorporacion() {
        return  fecha_incorporacion;
    }

    public void setFecha_incorporacion(Date fecha_incorporacion) {
        this.fecha_incorporacion = fecha_incorporacion;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }
    //to string
   // @Override
   // public String toString2() {
     //   return
       //         "id=" + id + ", idDepartamento=" + idDepartamento + ", usuario='" + usuario + '\'' + ", pass='" + pass + '\'' + ", nombre='" + nombre + '\'' + ", apellido='" + apellido + '\'' + ", domicilio='" + domicilio + '\'' + ", correo='" + correo + '\'' + ", telefono='" + telefono + '\'' + ", fecha_incorporacion=" + fecha_incorporacion + '}';
   // }
    @Override
    public String toString(){
        return
                usuario + " -> " + nombre + " " + apellido;
    }



}
