package com.example.h91;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.Vacaciones;
import com.example.h91.controladores.VacacionesController;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ActivityVacaciones extends AppCompatActivity implements View.OnClickListener {
    private static final String CERO = "0";
    private static final String BARRA = "-";


    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText etFecha;
    ImageButton ibObtenerFecha2;
    TextView edt_dias;
    TextView txt_diasPedidos;
    Button bt_solicitar3;
    EditText edt_diasSolicitados=null;
    EditText et_mostrar_fecha_picker2;
    private static int valorVacaciones=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacaciones);
        edt_dias = (TextView) findViewById(R.id.edt_dias);
        bt_solicitar3 = (Button) findViewById(R.id.bt_solicitar3);
        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) findViewById(R.id.et_mostrar_fecha_picker2);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha2 = (ImageButton) findViewById(R.id.ib_obtener_fecha2);
        //Evento setOnClickListener - clic
        ibObtenerFecha2.setOnClickListener(this);
        edt_diasSolicitados=(EditText)findViewById(R.id.edt_diasSolicitados);
        txt_diasPedidos=(TextView)findViewById(R.id.txt_diasPedidos);
        et_mostrar_fecha_picker2=(EditText)findViewById(R.id.et_mostrar_fecha_picker2);
        //boton cerrar notificaciones
        Button bt_volver8 = (Button) findViewById(R.id.bt_volver8);
        bt_volver8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(), ActivitySolicitudes.class);
                startActivityForResult(intent2, 0);
            }
        });
    }


    public void solicitarVacaciones(View view) {

         String fecha=   etFecha.getText().toString();
             mostrarToast2("SOLICITUD ENVIADA");
            edt_dias.setText(String.valueOf(++valorVacaciones));
        txt_diasPedidos.setText(fecha+edt_diasSolicitados.getText().toString());
            
            Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, fecha);
        sendIntent.setType("text/plain");

            Intent shareIntent= Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            //startActivityForResult(intent,0);
            //finish();

    }
    public void mostrarToast2(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    //METODO BUENO PARA INSERTAR VACACIONES
    public void insertarVacaciones(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("¿Quieres solicitar los dias de vacaciones?");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (edt_diasSolicitados.getText().toString().isEmpty()) {
                    mostrarToast2("introduce cantidad de dias");
                    edt_diasSolicitados.setError("Introduce cantidad de dias");
                }
                Vacaciones vacaciones = null;
                try {
                    //sumarDias();
                    System.out.println("entra al try");
                    boolean EmpleadoEnTabla= EmpleadoDB.EmpleadoEnTabla(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
                   if(EmpleadoEnTabla) {
                       System.out.println("entra al if");
                       Empleado empleado = (EmpleadoDB.buscarEmpleadoTabla(ConfiguracionDB.UsuarioActual));
                       System.out.println(empleado);
                       ConfiguracionDB.IDUsuarioActual = empleado.getId();
                       int idEmpleado = ConfiguracionDB.IDUsuarioActual;
                       System.out.println(" id del empleado " + idEmpleado);
                       int dias = Integer.valueOf(edt_diasSolicitados.getText().toString());
                       System.out.println("dias pedidos "+dias);

                       String fechaTextoInicio = String.valueOf(etFecha.getText());
                       SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
                       Date fechaInicio= format.parse(fechaTextoInicio);
                       System.out.println("fecha de inicio " + fechaInicio);

                       Calendar calendario= Calendar.getInstance();
                       calendario.setTime(fechaInicio);
                        int num_dias_afectar=0;
                       String fecha_termino="";
                       Date fecha_fin=null;
                           while (num_dias_afectar<=dias)
                           {
                           if (calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
                           {

                                fecha_fin = calendario.getTime();
                               fecha_termino = format.format(fecha_fin);
                               num_dias_afectar++;
                           }
                           calendario.add(Calendar.DATE, 1);
                           System.out.println("fecha de termino de vacaciones" + fecha_termino);
                       }
                       System.out.println("fecha de termino de vacaciones fuera" + fecha_termino);

                       SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
                       Date fechaHoy= new Date();
                       String fechatextoSolicitud= formato.format(fechaHoy);
                       System.out.println("fecha de hoy " + fechatextoSolicitud);

                       vacaciones = new Vacaciones(ConfiguracionDB.IDUsuarioActual, fechaInicio, fecha_fin, dias, fechaHoy, ConfiguracionDB.idEstado);


                       System.out.println(vacaciones);

                       boolean insertadoOK = VacacionesController.insertarVacaciones(vacaciones);
                       System.out.println("pasa el boolean insertado");
                       if (insertadoOK) {
                           System.out.println("entra al if insertado");
                           mostrarToast2("Dia de vacaciones creado correctamente");
                           finish();
                       } else {
                           mostrarToast2("no se pudo crear la peticion del dia de vacaciones");
                       }
                   }
                } catch (Exception e) {
                    mostrarToast2("error, revisa los datos introducidos");
                    
                    Log.i("vacaciones", "vacaciones no creadas ");

                }
            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edt_diasSolicitados.setText("");
                etFecha.setText("");
                mostrarToast2(" los campos se han reiniciado");
            }
        });
        alerta1.show();
    }

            @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_obtener_fecha2:
                obtenerFecha();
                break;
        }
    }



    private Date obtenerFecha() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                //etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                etFecha.setText(year + BARRA + mesFormateado + BARRA + diaFormateado);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

        return null;
    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

}