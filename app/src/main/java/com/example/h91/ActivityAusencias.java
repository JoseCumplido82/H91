package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

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


    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Widgets
    EditText etHora;
    ImageButton ibObtenerHora;
    Button bt_solicitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausencias);

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


    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    public void SolicitarAusencia(View view) {

     String fecha=   etFecha.getText().toString();
     String hora=   etHora.getText().toString();
        String fechaYHora= hora + fecha;
        mostrarToast("HORA SOLICITADA " + etHora.getText().toString() + " DEL DIA " + etFecha.getText().toString());
        Log.i("logmostrado", "Muestro la fecha y la hora " + etFecha + etHora);
        Intent sendIntent= new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, fechaYHora);
        sendIntent.setType("text/plain");
        Intent shareintent= Intent.createChooser(sendIntent, null);
        startActivity(shareintent);

        finish();

    }
}