package com.example.h91;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

public class ActivityVacaciones extends AppCompatActivity implements View.OnClickListener {
    private static final String CERO = "0";
    private static final String BARRA = "/";


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
    EditText edt_diasSolicitados;
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
             mostrarToast("SOLICITUD ENVIADA");
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
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


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



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sumarDiasAFecha() {
        LocalDateTime today = LocalDateTime.now();     //Today
        LocalDateTime tomorrow = today.plusDays(diapedido);     //Plus 1 day
        LocalDateTime yesterday = today.minusDays(1);   //Minus 1 day
        System.out.println("Today:     "+today);
        System.out.println("dia solicitado:     "+tomorrow);
        System.out.println("ayer:     "+yesterday);
       // txt_diasPedidos.setText();
        txt_diasPedidos.setText(tomorrow.toString());
    }

    int diapedido= 5;

    //int diauno= Integer.parseInt(edt_diasSolicitados.getText().toString());
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calcularFechaVuelta(View view) {
        sumarDiasAFecha();
        calcularFecha(obtenerFecha(), diapedido);
    }


    public Date calcularFecha(Date fecha, int dias){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        calendar.getTime();

        return fecha;


    }

}