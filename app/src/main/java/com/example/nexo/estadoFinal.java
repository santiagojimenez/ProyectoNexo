package com.example.nexo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class estadoFinal extends AppCompatActivity {
    final int CAPTURE_IMAGEN = 1;
    ImageView estadoFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estado_final);

        estadoFinal = findViewById(R.id.imageView5);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void irFirmaCliente(View view) {
        Intent intent = new Intent(this, firmaCliente.class);
        startActivity(intent);
    }

    //metodo para tomar la foto
    public void evidenciaFinal(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_IMAGEN);
    }

    //Receptor de la imagen
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAPTURE_IMAGEN && resultCode==RESULT_OK && data !=null) {
            Bundle extras = data.getExtras();
            Bitmap bit1 = (Bitmap) extras.get("data");
            estadoFinal.setImageBitmap(bit1);
        }

    }
}