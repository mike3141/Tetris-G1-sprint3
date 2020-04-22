package com.darwin.tetris;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class VistaPiezaSiguiente extends View {

    // Atributos
    private Bitmap cuadrado;
    private Bitmap piezaT;
    private Bitmap piezaZ;
    private Bitmap piezaS;
    private Bitmap piezaJ;
    private Bitmap piezaL;
    private Bitmap piezaI;
    private Tablero tablero;

    public VistaPiezaSiguiente(Context context, Tablero tablero){
        super(context);
        this.tablero = tablero;
        cuadrado = BitmapFactory.decodeResource(getResources(), R.drawable.pieza_1);
        piezaJ = BitmapFactory.decodeResource(getResources(), R.drawable.pieza_2);
        piezaL = BitmapFactory.decodeResource(getResources(), R.drawable.pieza_3);
        piezaZ = BitmapFactory.decodeResource(getResources(), R.drawable.pieza_4);
        piezaT = BitmapFactory.decodeResource(getResources(), R.drawable.pieza_5);
        piezaS = BitmapFactory.decodeResource(getResources(), R.drawable.pieza_6);
        piezaI = BitmapFactory.decodeResource(getResources(), R.drawable.pieza_7);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();

        // comprobacion por si la lista esta vacia
        if(tablero.getListaPiezas().size() > 0){
            Pieza pieza = tablero.getPiezaSiguiente();
            switch (pieza.getTipo()){
                case 1:
                    cuadrado = Bitmap.createScaledBitmap(cuadrado,200,200,false);
                    canvas.drawBitmap(cuadrado,50,500,p);
                    break;
                case 2:
                    piezaZ = Bitmap.createScaledBitmap(piezaZ,200,100,false);
                    canvas.drawBitmap(piezaZ,50,500,p);
                    break;
                case 3:
                    piezaI = Bitmap.createScaledBitmap(piezaI,200,80,false);
                    canvas.drawBitmap(piezaI,50,500,p);
                    break;
                case 4:
                    piezaT = Bitmap.createScaledBitmap(piezaT,200,100,false);
                    canvas.drawBitmap(piezaT,50,500,p);
                    break;
                case 5:
                    piezaS = Bitmap.createScaledBitmap(piezaS,200,100,false);
                    canvas.drawBitmap(piezaS,50,500,p);
                    break;
                case 6:
                    piezaJ = Bitmap.createScaledBitmap(piezaJ,200,100,false);
                    canvas.drawBitmap(piezaJ,50,500,p);
                    break;
                case 7:
                    piezaL = Bitmap.createScaledBitmap(piezaL,200,100,false);
                    canvas.drawBitmap(piezaL,50,500,p);
                    break;
            }
        }

    }
}
