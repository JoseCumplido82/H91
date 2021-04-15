package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

public class ActivityNominas extends AppCompatActivity implements View.OnClickListener{

    private static final String CERO = "0";
    private static final String BARRA = "/";
    private String URL ="https://www.icv.csic.es/prevencion/Documentos/manuales/Guia_basica_sobre_Prevencion_de_Incendios.pdf";
    private String nameFile="incendios";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);


    //Widgets
    EditText etFecha;
    ImageButton ibObtenerFecha;

    Button bt_descargaprueba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominas);

        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) findViewById(R.id.et_mostrar_fecha_picker);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
        //Evento setOnClickListener - clic
        ibObtenerFecha.setOnClickListener(this);

        bt_descargaprueba=(Button)findViewById(R.id.bt_descargaprueba);


        //boton descargar nomina
        Button bt_verNomina = (Button) findViewById(R.id.bt_verNomina);
    //    bt_verNomina.setOnClickListener(new View.OnClickListener() {
       //     @Override
//                DescargarNomina();
         //   }
      //  });

        //boton cerrar notificaciones
        Button bt_volver6 = (Button) findViewById(R.id.bt_volver6);
        bt_volver6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent7 = new Intent (v.getContext(), PanelEmpleado.class);
                //startActivityForResult(intent7, 0);
                finish();
            }
        });

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_obtener_fecha:
                obtenerFecha();
                break;
        }
    }

    private void fechaParaDescargar(){


    }


    private void obtenerFecha() {

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







  //  public void DescargarNomina() {
    //    try {

      //      URL url = new URL("https://www.icv.csic.es/prevencion/Documentos/manuales/");
            //URL url = new URL("empleados.h91go.com/pr_nominas");
        //    HttpURLConnection c = (HttpURLConnection) url.openConnection();
         //   c.setRequestMethod("GET");
          //  c.setDoOutput(true);
           // c.connect();

            //String Path = Environment.getExternalStorageDirectory() + "/download/";
            //Log.v("PdfManager", "PATH: " + Path);
            //File file = new File(Path);
            //file.mkdirs();
            //FileOutputStream fos = new FileOutputStream("Guia_basica_sobre_Prevencion_de_Incendios.pdf");

//            InputStream is = c.getInputStream();

  //          byte[] buffer = new byte[702];
    //        int len1 = 0;
      //      while ((len1 = is.read(buffer)) != -1) {
        //        fos.write(buffer, 0, len1);
          //  }
            //fos.close();
            //is.close();
        //} catch (IOException e) {
          //  Log.d("PdfManager", "Error: " + e);
       // }
       // Log.v("PdfManager", "Check: ");
   // }



    //metodo para descargar pdf
    public void DescargarNomina(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));
    }


    public void descargaPrueba(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));
    }
}