package com.darwin.tetris;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.darwin.tetris.db.AppDataBase;
import com.darwin.tetris.db.Puntuacion;
import com.darwin.tetris.db.PuntuacionDao;

import java.util.List;


public class VistaRanking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // creacion del objeto table
        TableLayout table = new TableLayout(this);
        table.setShrinkAllColumns(true);
        table.setStretchAllColumns(true);

        // creacion de un objeto rowtable para poner el titulo
        TableRow rowTitle = new TableRow(this);
        TextView title = new TextView(this);
        title.setText("Ranking");
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        rowTitle.addView(title);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

        // creacion de los titulos de la table
        TableRow columnTitle = new TableRow(this);
        TextView nombre = new TextView(this);
        nombre.setText("Nombre");
        nombre.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView puntuacion = new TextView(this);
        puntuacion.setText("Puntuacion");
        puntuacion.setGravity(Gravity.CENTER_HORIZONTAL);
        columnTitle.addView(nombre);
        columnTitle.addView(puntuacion);

        // definicion de cuantas columnas vamos a tener
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span=2;

        AppDataBase db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"puntuacion").allowMainThreadQueries().enableMultiInstanceInvalidation().build();
        PuntuacionDao pdao= db.puntuacionDao();
        List<Puntuacion> punt = pdao.getAll();
        db.close();
        // añadimos las filas a la tabla
        table.addView(rowTitle);
        table.addView(columnTitle);

        for (Puntuacion p : punt){
            System.out.println(p.nombre);
            TableRow filaRanking = new TableRow(this);
            TextView name = new TextView(this);
            name.setText(p.nombre);
            name.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView points = new TextView(this);
            points.setText(Integer.toString(p.puntuacion));
            points.setGravity(Gravity.CENTER_HORIZONTAL);
            filaRanking.addView(name);
            filaRanking.addView(points);
            table.addView(filaRanking);
        }
        setContentView(table);
    }
}
