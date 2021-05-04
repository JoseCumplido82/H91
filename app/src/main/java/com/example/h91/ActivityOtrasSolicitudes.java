package com.example.h91;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h91.Clases.Empleado;
import com.example.h91.Clases.Solicitud;
import com.example.h91.Clases.Tramites;
import com.example.h91.controladores.TramitesController;
import com.example.h91.modelos.ConfiguracionDB;
import com.example.h91.modelos.EmpleadoDB;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityOtrasSolicitudes extends AppCompatActivity  {

    public static final String EXTRA_OBJETO_SOLICITUD="tramite";
    TextView txt_tituloDocumento=null;
    EditText edt_asuntoSolicitud =null;
    EditText edt_observacionesMensaje = null;
    Button bt_adjuntarDocumentos = null;
    Button bt_imagenSeleccionada=null;
    Button bt_pdfSeleccionado= null;
    ImageView foto_galeria=null;
    ArrayList<Tramites> tramites=null;
    private static final int PHOTO_SELECTED=100;
    Uri imageUri;
    String nombreImagen ="";

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
                bt_imagenSeleccionada.setVisibility(View.VISIBLE);
                bt_pdfSeleccionado.setVisibility(View.VISIBLE);
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
        android.app.AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("¿Quieres solicitar los dias de vacaciones?");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (edt_asuntoSolicitud.getText().toString().isEmpty()) {
                            mostrarToast("Introduce un asunto para la solicitud");
                            edt_asuntoSolicitud.setError("Introduce un asunto para la solicitud");
                        }
                        Tramites tramites = null;
                        try {
                            System.out.println("entra al try");
                            boolean EmpleadoEnTabla = EmpleadoDB.EmpleadoEnTabla(ConfiguracionDB.UsuarioActual, ConfiguracionDB.PassActual);
                            if (EmpleadoEnTabla) {
                                System.out.println("entra al if del boolean");
                                Empleado empleado = (EmpleadoDB.buscarEmpleadoTabla(ConfiguracionDB.UsuarioActual));
                                System.out.println(empleado);
                                ConfiguracionDB.IDUsuarioActual = empleado.getId();
                                int idEmpleado = ConfiguracionDB.IDUsuarioActual;
                                System.out.println("el id del empleado es: " + idEmpleado);
                                String asunto = edt_asuntoSolicitud.getText().toString();
                                String comentario = edt_observacionesMensaje.getText().toString();

                                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                                Date fechaHoy = new Date();
                                String fechatextoSolicitud = formato.format(fechaHoy);
                                System.out.println("fecha de hoy " + fechatextoSolicitud);

                               // String nombreDocumento = foto_galeria.toString();
                                //String nombreDocumento="";
                                if (comentario.equals("")) {
                                    tramites = new Tramites(idEmpleado, nombreImagen, asunto, fechaHoy, ConfiguracionDB.idEstado);
                                } else {
                                    tramites = new Tramites(idEmpleado, nombreImagen, asunto, comentario, fechaHoy, ConfiguracionDB.idEstado);
                                }

                                System.out.println(tramites);
                                boolean insertadoOK = TramitesController.insertarTramites(tramites);
                                System.out.println("pasa el boolean insertado");
                                if (insertadoOK) {
                                    System.out.println("entra al if del insertadook");
                                    mostrarToast2("Dia de ausencia creado correctamente");
                                    finish();
                                } else {
                                    System.out.println("no se pudo crear el dia de ausencia");
                                }

                            }
                        } catch (Exception e) {
                            Log.i("tramites", "tramite no creado");
                        }
                    }
                });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edt_asuntoSolicitud.setText("");
                edt_observacionesMensaje.setText("");
                mostrarToast2(" los campos se han reiniciado");
            }
        });
        alerta1.show();
    }

    public void mostrarToast2(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void openFolder(){
    checkStoragePermission();
        //FUNCIONA PARA ELEGIR PDF
        Intent pdfGaleria = new Intent(Intent.ACTION_GET_CONTENT);
        pdfGaleria.setType("application/pdf");
        pdfGaleria.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(pdfGaleria, "Selecciona un archivo pdf"), 0);
            mostrarToast("PDF SELECCIONADO");


        } catch (ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
        }
    }

    private void openGallery(){
        checkStoragePermission();
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, PHOTO_SELECTED);
        //foto_galeria.setImageResource(PHOTO_SELECTED);
        mostrarToast("IMAGEN SELECCIONADA");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PHOTO_SELECTED) {
            imageUri = data.getData();
            foto_galeria.setImageURI(imageUri);
            txt_tituloDocumento.setText("IMAGEN CARGADA");
            //List<String> nombreImagen= imageUri.getPathSegments();
           //String nombreImagen= imageUri.getPath();
            File f= new File(imageUri.getPath());
            nombreImagen=  f.getName();
            System.out.println(nombreImagen);

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
