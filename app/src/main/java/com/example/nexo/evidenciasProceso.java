package com.example.nexo;

import static java.io.FileDescriptor.in;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;

public class evidenciasProceso extends AppCompatActivity {
    // Declaramos las variables primero
    final int CAPTURA_IMAGEN = 1;
    ImageView fotoE;

    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private MediaPlayer reproductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_evidencias_proceso);

        fotoE = findViewById(R.id.imageView2);
        archivoSalida = getExternalCacheDir().getAbsolutePath() + "/audioPrueba.3gp";

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 200);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void irestadoFinal(View view) {
        Intent intent = new Intent(this, estadoFinal.class);
        startActivity(intent);
    }


    public void tomarEvidencias(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURA_IMAGEN);
    }

    //Receptor de la imagen
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURA_IMAGEN && resultCode == RESULT_OK && data !=null) {
            Bundle extras = data.getExtras();
            Bitmap bit1 = (Bitmap) extras.get("data");
            fotoE.setImageBitmap(bit1);
        }
    }

    //Metodo para el boton de grabar
    public void iniciarGrabacion(View view){
        grabacion = new MediaRecorder();
        grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        grabacion.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        grabacion.setOutputFile(archivoSalida);

        try {
            grabacion.prepare();
            grabacion.start();
            Toast.makeText(this, "Grabando...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            //Error al preparar
        }
    }


    //Metodo para el boton de Detener
    public  void detenerGrabacion(View view){
        if (grabacion != null){
            grabacion.stop();
            grabacion.release();
            grabacion = null;
            Toast.makeText(this, "Grabación guardada", Toast.LENGTH_SHORT).show();
        }
    }


    //Metodo para REPRODUCIR
    public void reproducrAudio(View view){
        if (reproductor != null && reproductor.isPlaying()){
            reproductor.stop();
            reproductor.release();
        }
        reproductor = new MediaPlayer();
        try {
            reproductor.setDataSource(archivoSalida);
            reproductor.prepare();
            reproductor.start();
            Toast.makeText(this, "Reproduciendo audio...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            //esto dice que es error está sucediendo
            Log.e("AUDIO_ERROR", "Fallo al reproducir", e);
            Toast.makeText(this, "Error al reproducir: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}