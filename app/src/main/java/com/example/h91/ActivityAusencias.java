package com.example.h91;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.h91.Clases.Ausencias;
import com.example.h91.Clases.Empleado;
import com.example.h91.controladores.AusenciasController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;

public class ActivityAusencias extends AppCompatActivity implements View.OnClickListener{

    private static final String CERO = "0";
    private static final String BARRA = "/";
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
    Empleado empleado;

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
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertarAusencia(View view) throws ParseException {
            String motivo= String.valueOf(edt_motivoAusencia.getText());
            String fecha_inicio= etFecha.getText().toString();
            String hora_inicio= etHora.getText().toString();
            int horas= Integer.parseInt(edt_horasASolicitar.getText().toString());
            LocalDate fecha_solicitud= LocalDate.now();
            Date date_solicitud= Date.from(fecha_solicitud.atStartOfDay(ZoneId.systemDefault()).toInstant());

//  fallo aqui
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        Date date= format.parse(fecha_inicio);


        DateTimeFormatter formatter= DateTimeFormatter.ofLocalizedDate(FormatStyle.valueOf("yyyy-MM-dd"));
            LocalDate ld= LocalDate.parse(fecha_inicio, formatter);
            Date date_inicio= Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());


            if(motivo.isEmpty()){
                edt_motivoAusencia.setError("escribe un motivo para la ausencia");
                return;
            }
        AlertDialog.Builder alerta= new AlertDialog.Builder(this);
        alerta.setTitle("¿guardar la ausencia?");
        alerta.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Ausencias ausencias = new Ausencias(empleado.getId(), date, Integer.parseInt(hora_inicio), horas, date_solicitud, motivo);
                boolean insercionOK= AusenciasController.InsertarAusencias(ausencias);
                mostrarToast(insercionOK);
                Log.i("recoge", "recoge " + " " + ausencias);
            }
        });
        alerta.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.show();
    }

    public void SolicitarAusencia(View view) {

        String fecha= etFecha.getText().toString();
        String hora= etHora.getText().toString();
        String fechaYHora= hora + fecha;
        //mostrarToast("HORA SOLICITADA " + etHora.getText().toString() + " DEL DIA " + etFecha.getText().toString());
        Log.i("logmostrado", "Muestro la fecha y la hora " + etFecha.getText().toString()+ " " + etHora.getText().toString());
        Intent sendIntent= new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, fechaYHora);
        sendIntent.setType("text/plain");
        Intent shareintent= Intent.createChooser(sendIntent, null);
        startActivity(shareintent);

        finish();

    }
}