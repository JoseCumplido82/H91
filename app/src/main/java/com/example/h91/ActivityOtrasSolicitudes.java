package com.example.h91;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Solicitud;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ActivityOtrasSolicitudes extends AppCompatActivity {

    public static final String EXTRA_OBJETO_SOLICITUD="solicitud";
    TextView txt_tituloDocumento=null;
    EditText edt_asuntoSolicitud =null;
    EditText edt_observacionesMensaje = null;
    Button bt_adjuntarDocumentos = null;
    Button bt_imagenSeleccionada=null;
    Button bt_pdfSeleccionado= null;
    ImageView foto_galeria=null;
    private static final int PHOTO_SELECTED=100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //para ocultar la barra de status
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otras_solicitudes);
        edt_asuntoSolicitud =(EditText) findViewById(R.id.edt_asuntoSolicitud);
        edt_observacionesMensaje = (EditText) findViewById(R.id.edt_observacionesMensaje);
        txt_tituloDocumento = (TextView)findViewById(R.id.txt_tituloDocumento);
        foto_galeria=(ImageView)findViewById(R.id.foto_galeria);
        bt_imagenSeleccionada=(Button)findViewById(R.id.bt_imagenSeleccionada);
        bt_imagenSeleccionada.setVisibility(View.INVISIBLE);
        bt_pdfSeleccionado=(Button)findViewById(R.id.bt_pdfSeleccionado);
        bt_pdfSeleccionado.setVisibility(View.INVISIBLE);


        //boton para adjuntar documentos pdf
        bt_pdfSeleccionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });


        //boton para adjuntar imagenes
        bt_imagenSeleccionada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        //boton para mostrar botones adjuntar
        bt_adjuntarDocumentos= (Button)findViewById(R.id.bt_adjuntarDocumentos);
        bt_adjuntarDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent intentImagen= new Intent();
              //  intentImagen.setType("image/*");
               // intentImagen.setAction(Intent.ACTION_GET_CONTENT);
                //startActivityForResult(intentImagen, PHOTO_SELECTED);
                bt_imagenSeleccionada.setVisibility(View.VISIBLE);
                bt_pdfSeleccionado.setVisibility(View.VISIBLE);
                //openGallery();
            }
        });


        //boton cerrar ausencias
        Button bt_volver5 = (Button) findViewById(R.id.bt_volver5);
        bt_volver5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (v.getContext(), ActivitySolicitudes.class);
                startActivityForResult(intent3, 0);
            }
        });
    }


    public void solicitarAusencia(View view) {
        Solicitud solicitud=null;
     //   try {
            String asunto= edt_asuntoSolicitud.getText().toString();
            String comentario= edt_observacionesMensaje.getText().toString();
            solicitud= new Solicitud(asunto, comentario);
       // }catch (Exception e){
            mostrarToast("error, revisa los datos introducidos");
        //}

        

        Intent intent= new Intent(this, ActivityMisSolicitudes.class);

         solicitud= new Solicitud(edt_asuntoSolicitud.getText().toString(), edt_observacionesMensaje.getText().toString());
        Bundle bundle= new Bundle();
        bundle.putSerializable("imagen", (Serializable) foto_galeria);
        intent.putExtra("imagen", (Parcelable) foto_galeria);
        startActivity(intent);




    }

    private void openFolder(){


        //FUNCIONA PARA ELEGIR PDF
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select txt file"), 0);

                mostrarToast("PDF SELECCIONADO");


        } catch (ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog

        }



    }

    private void openGallery(){
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, PHOTO_SELECTED);

//        foto_galeria.setImageResource(PHOTO_SELECTED);
        mostrarToast("IMAGEN SELECCIONADO");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PHOTO_SELECTED) {
            imageUri = data.getData();
            foto_galeria.setImageURI(imageUri);
            txt_tituloDocumento.setText("imagen cargada");
        }
    }




    public boolean checkStoragePermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }



    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }





}
