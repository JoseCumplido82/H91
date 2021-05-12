package com.example.h91;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.*;
import java.time.format.*;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.h91.Clases.Ausencias;
import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.AusenciasController;
import com.example.h91.modelos.AusenciasDB;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class ActivityAusencias extends AppCompatActivity implements View.OnClickListener{

    private static final String CERO = "0";
    private static final String BARRA = "-";
    private static final String DOS_PUNTOS= ":";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText etFecha;
    ImageButton ibObtenerFecha3;


    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Widgets
    EditText etHora;
    ImageButton ibObtenerHora;
    Button bt_solicitar;
    EditText edt_motivoAusencia;
    EditText edt_horasASolicitar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausencias);
        edt_motivoAusencia=(EditText)findViewById(R.id.edt_motivoAusencia);
        edt_horasASolicitar=(EditText)findViewById(R.id.edt_horasASolicitar);
        bt_solicitar=(Button)findViewById(R.id.bt_solicitar);
        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) findViewById(R.id.et_mostrar_fecha_picker3);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha3 = (ImageButton) findViewById(R.id.ib_obtener_fecha3);
        //Evento setOnClickListener - clic
        ibObtenerFecha3.setOnClickListener(this);
        //Widget EditText donde se mostrara la hora obtenida
        etHora = (EditText) findViewById(R.id.et_mostrar_hora_picker2);
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        ibObtenerHora = (ImageButton) findViewById(R.id.ib_obtener_hora);
        //Evento setOnClickListener - clic
        ibObtenerHora.setOnClickListener(this);


        //boton cerrar ausencias
        Button bt_volver9 = (Button) findViewById(R.id.bt_volver9);
        bt_volver9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), ActivitySolicitudes.class);
                startActivityForResult(intent3, 0);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_obtener_fecha3:
                obtenerFecha();
                break;
            case R.id.ib_obtener_hora:
                obtenerHora();
                break;
        }
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                //etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                etFecha.setText(year + BARRA + mesFormateado + BARRA + diaFormateado);



            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                etHora.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }


    public void mostrarToast(boolean insercionOK)
    {
        if(insercionOK)
        {
            Toast.makeText(this,"proveedor guardado correctamente", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this,"No se pudo guardar el proveedor", Toast.LENGTH_SHORT).show();
        }
    }



    public void mostrarToast2(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void SolicitarAusencia(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("¿Quieres solicitar la ausencia?");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(edt_motivoAusencia.getText().toString().isEmpty())
                {
                    mostrarToast2("indica un motivo para la ausencia");
                    return;
                }
                Ausencias ausencias=null;
                try {
                    boolean EmpleadoEnTabla= EmpleadoDB.EmpleadoEnTabla(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
                    if(EmpleadoEnTabla) {
                        System.out.println("entra al if");
                        Empleado empleado = (EmpleadoDB.buscarEmpleadoTabla(ConfiguracionDB.UsuarioActual));
                        System.out.println(empleado);
                        ConfiguracionDB.IDUsuarioActual= empleado.getId();
                        System.out.println(ConfiguracionDB.IDUsuarioActual);
                        int idActual= ConfiguracionDB.IDUsuarioActual;
                        boolean Idobtenido= AusenciasController.obtenerIDempleadoAusencia(ConfiguracionDB.IDUsuarioActual);
                          if(Idobtenido){
                              System.out.println("llega al if del Idobtenido");
                              //empleado
                              System.out.println("empleado " + empleado);
                              //id del empleado
                              System.out.println("id del empleado "+ idActual);
                              //motivo de la ausencia
                              String motivo= String.valueOf(edt_motivoAusencia.getText());
                              System.out.println("motivo de la ausencia " + motivo);
                              //fecha de la ausencia
                              String fechatextoAusencia= String.valueOf(etFecha.getText());
                              SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
                              Date datofecha= format.parse(fechatextoAusencia);
                              //String fechaausencia= format.format(datofecha);
                              System.out.println("fecha de la ausencia " +datofecha);
                              //fecha de la solicitud
                              System.out.println("llega al simple date format de la fecha de la solicitud");
                              SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
                              Date date= new Date();
                              String fechatextoSolicitud= formato.format(date);
                              System.out.println("fecha de hoy " + date);
                              System.out.println("fecha de la solicitud " + fechatextoSolicitud);
                              //horas pedidas
                              int horaspedidas= Integer.parseInt(edt_horasASolicitar.getText().toString());
                              System.out.println("horas solicitadas " + horaspedidas);
                              String horaInicio= etHora.getText().toString();
                              System.out.println("hora de inicio de solicitud " + horaInicio);
                              //hora del reloj
                              SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm:ss");
                              Date date1= new Date();
                              String hora_actual= formatoHora.format(date1);
                              System.out.println(hora_actual);
                              System.out.println("Id estado solicitud " + ConfiguracionDB.idEstado);
                              ausencias= new Ausencias(idActual, datofecha, hora_actual,horaspedidas, date, motivo, ConfiguracionDB.idEstado);
                              Log.i("mensaje", String.valueOf(ausencias));
                              System.out.println(ausencias.toString());
                              Log.i("errores", ausencias.toString());
                              boolean insertadoOK= AusenciasController.InsertarAusencias(ausencias);
                              if (insertadoOK){
                                  System.out.println("entra al if del insertado");
                                  mostrarToast2("ausencia creada correctamente");
                                  finish();
                              }
                              else {
                                  System.out.println("entra al else del insertado");
                                  mostrarToast2("no se pudo crear la ausencia");
                              }
                          }else
                          {
                              System.out.println(AusenciasDB.IDEmpleadoAusencia(ConfiguracionDB.IDUsuarioActual));
                              System.out.println(idActual);
                              System.out.println(AusenciasController.obtenerIDempleadoAusencia(ConfiguracionDB.IDUsuarioActual));
                              System.out.println(empleado);
                              System.out.println("no entra en el if de obtener id");
                          }

                    }else {
                        System.out.println("el empleado no esta en la bbdd");
                    }
                }catch (Exception e){
                    mostrarToast2("error, revisa los datos introducidos");
                }
                }
                });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edt_motivoAusencia.setText("");
                etFecha.setText("");
                etHora.setText("");
                edt_horasASolicitar.setText("");
                mostrarToast2("Los campos se han reiniciado");
            }
        });
        alerta1.show();


    }
}