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

public class estadoInicial extends AppCompatActivity {

    //  Declaramos las variables al principio de la clase única
    final int CAPTURA_IMAGEN = 1;
    ImageView imagV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estado_inicial);

        //  Inicializamos el ImageView aquí mismo
        imagV = findViewById(R.id.imageView4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodo para ir a otra pantalla
    public void irEvidenciasProceso(View view) {
        Intent intent = new Intent(this, evidenciasProceso.class);
        startActivity(intent);
    }

    // El metodo para tomar la foto ahora es "hermano" de los otros métodos
    public void tomarFoto(View view) {
        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(in, CAPTURA_IMAGEN);
    }

    //  El receptor de la imagen también debe estar en esta clase
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURA_IMAGEN && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap bit1 = (Bitmap) extras.get("data");
            imagV.setImageBitmap(bit1);
        }
    }
}