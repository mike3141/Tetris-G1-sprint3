package com.darwin.tetris;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.darwin.tetris.db.AppDataBase;
import com.darwin.tetris.db.Puntuacion;
import com.darwin.tetris.db.PuntuacionDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinDeJuego extends AppCompatActivity {

    // Atributos
    private int points;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    private TextView puntuacion_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_de_juego);
        Intent it = getIntent();
        points = (int) it.getSerializableExtra("points");
        // para captura una foto del usuario
        capturaFotoConIntent();
        final AlertDialog.Builder savePoint = new AlertDialog.Builder(this);
        savePoint.setTitle("Guardar Puntuacion");
        LayoutInflater inflater = this.getLayoutInflater();
        View myview = inflater.inflate(R.layout.guardarpuntuacion,null);
        final EditText username = myview.findViewById(R.id.username);
        final TextView puntuacion = myview.findViewById(R.id.puntuacion_user);
        puntuacion.setText(Integer.toString(points));
        savePoint.setView(myview);
        imageView = findViewById(R.id.foto);
        puntuacion_foto = findViewById(R.id.puntuacion_foto);
        savePoint.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,"puntuacion").allowMainThreadQueries().enableMultiInstanceInvalidation().build();
                Puntuacion p = new Puntuacion(username.getText().toString(),points);
                PuntuacionDao pdao= db.puntuacionDao();
                pdao.insert(p);
                db.close();
            }
        });
        savePoint.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        savePoint.show();
    }
    public void Inicio(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void setPoints(int points) {
        this.points = points;
    }
    private void capturaFotoConIntent(){
        Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tomarFoto.resolveActivity(getPackageManager()) != null){
            startActivityForResult(tomarFoto,REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imagenBitmap = (Bitmap) extras.get("data");
            puntuacion_foto.setText("Tu puntuacion final ha sido: "+points);
            imageView.setImageBitmap(imagenBitmap);
            System.out.println("La rotacion de la imagen es : "+imageView.getRotation());

        }
    }
    public void compartir(View view) throws IOException {
        screenShot();
    }
    protected void screenShot() throws IOException {
        String Path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

        // creando el bitmap
        View vista = getWindow().getDecorView().getRootView();
        vista.setDrawingCacheEnabled(true);
        vista.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(vista.getDrawingCache());
        vista.setDrawingCacheEnabled(false);

        // crear archivo
        File imagen = new File(Path + File.separator);
        if (imagen.exists()) {
            imagen.delete();
        }
        String timestamp = new SimpleDateFormat("ddMMyyyy_hhmmss").format(new Date());
        String mImae = "Imagen" + timestamp + ".jpg";
        File mediaFile = new File(imagen.getPath() + File.separator + mImae);

        if(mediaFile == null){
            throw new NullPointerException("Error al crear la imagen, mira los permisos");
        }
        FileOutputStream fos = new FileOutputStream(mediaFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
        fos.close();

        MediaScannerConnection.scanFile(this, new String[]{mediaFile.toString()}, new String[]{"image/jpeg"},null);
        enviarWhatapp(mediaFile);

    }
    public void enviarWhatapp(File file){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setPackage("com.whatsapp");
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.getPath()));
        startActivity(intent);
    }


}
