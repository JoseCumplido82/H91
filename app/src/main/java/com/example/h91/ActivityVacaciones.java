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

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

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
    Empleado empleado;
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
        alerta1.setTitle("¿Quieres solicitar la ausencia?");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (edt_diasSolicitados.getText().toString().isEmpty()) {
                    mostrarToast2("introduce cantidad de dias");
                }
                Vacaciones vacaciones = null;
                try {
                    int idSolicitante = empleado.getId();
                    int dias = Integer.valueOf(edt_diasSolicitados.getText().toString());
                    String fechatextoInicio = String.valueOf(etFecha.getText());
                    Date fechaInicio = new SimpleDateFormat("yyyy-mm-dd").parse(fechatextoInicio);
                    String fechatextoFin = String.valueOf(etFecha.getText()) + dias;
                    Date fechaFin = new SimpleDateFormat("yyyy-mm-dd").parse(fechatextoFin);
                    String fechatextoSolicitud = String.valueOf(LocalDate.now());
                    Date fechaActual = new SimpleDateFormat("yyyy-mm-dd").parse(fechatextoSolicitud);
                    vacaciones = new Vacaciones(idSolicitante, fechaInicio, fechaFin, dias, fechaActual, ConfiguracionDB.idEstado);
                    boolean insertadoOK = VacacionesController.insertarVacaciones(vacaciones);
                    if (insertadoOK) {
                        mostrarToast2("Dia de vacaciones creado correctamente");
                        finish();
                    } else {
                        mostrarToast2("no se pudo crear la peticion del dia de vacaciones");
                    }
                } catch (Exception e) {
                    mostrarToast2("error, revisa los datos introducidos");
                    Log.i("vacaciones", "vacaciones no creadas " + " el idSolicitante no lo recoge " + " dias " + edt_diasSolicitados.getText().toString()
                            + " fecha pedida " + etFecha.getText() + "  fecha actual " + LocalDate.now().toString());
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


    int numero=0;


    //METODO PARA HACER LA SUMA DE DIAS A LA FECHA , NO CONSIGO QUE FUNCIONE
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sumarDiasAFecha() {


        numero= Integer.parseInt(edt_diasSolicitados.getText().toString());
        LocalDateTime today = LocalDateTime.now();     //Today

        LocalDateTime tomorrow = today.plusDays(numero);     //Plus 1 day
        LocalDateTime yesterday = today.minusDays(1);   //Minus 1 day
        System.out.println("Today:     "+today);
        System.out.println("dia solicitado:     "+tomorrow);
        System.out.println("ayer:     "+yesterday);
       // txt_diasPedidos.setText();
        txt_diasPedidos.setText(tomorrow.toString());
        System.out.println("el valor de numero es " + numero);
        System.out.println("///////////////////////////////////////");
        System.out.print(obtenerFecha());

        String fecha=   etFecha.getText().toString();
        System.out.println("fecha de obtenerfecha " + fecha + numero);
        System.out.println("fecha de dia " + dia + numero);
        System.out.println("sumar a calendar la fecha" );

        c.add(Calendar.DAY_OF_YEAR, numero);
        //c.setTime();
        //System.out.println(c.toString());
        System.out.println("+ " + numero + " dias a fecha seleccionada que es " + fecha + " y volveras el dia " + formatearCalendar(c) ) ;
        System.out.println("dias pedidos " + numero + " fecha indicada " + fecha + " = " + formatearCalendar(c));
        System.out.println("fecha de obtenerfecha " );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calcularFechaVuelta(View view) {
        sumarDiasAFecha();

    }



    public static String formatearCalendar(Calendar c) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());

        return df.format(c.getTime());
    }


}