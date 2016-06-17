package com.ap.jesus.migsv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;
import graph.*;
import java.io.IOException;
import java.util.List;


/**
 * Created by jagui on 12/03/2016.
 */
public class GUIGraph extends View {
    Paint paint = new Paint();
    private int width = 20;
    private Graph G;
    private List<Pair<Label, Double>> userweights;
    private double delta;

    public GUIGraph(Context context, List<Pair<Label, Double>> uw, double d) {
        super(context);
        userweights = uw;
        delta = d;
    }

    public void onDraw(Canvas g2d) {

        G = createGraph();
        ItemList route = new ItemList(G.getady(), G.getnumitems(), G.getuserweights());
        route = RouteFinding.greedy(G, delta, false, true);
        SharedPreferences prefs = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("route", route.toString());
        editor.commit();

        route.sort();
        paint.setColor(Color.BLACK);

        //	g2d.setColor(Color.RED);
        int rw = 5, big_rect_length = 780,big_rect_height = 280;
        int r1x = 15, r1y = 35, r2x = r1x, r2y = r1y + big_rect_height + 60;
        paint.setTextSize(paint.getTextSize()*3);
        g2d.drawText("Planta 3", r1x, 25, paint);
        g2d.drawText("Planta 4", r2x, r1y + 45 + big_rect_height, paint);
        paint.setTextSize(paint.getTextSize() / 3);

        //Para pintar los rectángulos
        paint.setStyle(Paint.Style.FILL);
        //Primer Rectangulo grande
        Rect rect_big_1_up = new Rect(r1x, r1y, r1x + big_rect_length, r1y + rw); //Arriba
        Rect rect_big_1_left = new Rect(r1x, r1y, r1x + rw, r1y + big_rect_height); //Izda
        Rect rect_big_1_down = new Rect(r1x, r1y+big_rect_height, r1x + big_rect_length, r1y+big_rect_height + rw); //Abajo1
        //	g2d.fillRect(35, 675, 750, 5); //Abajo2
        Rect rect_big_1_right = new Rect(r1x + big_rect_length, r1y, r1x + big_rect_length + rw, r1y + big_rect_height + rw); //Dcha
        //Pequeño 1
        g2d.drawRect(rect_big_1_up, paint);
        g2d.drawRect(rect_big_1_left, paint);
        g2d.drawRect(rect_big_1_down, paint);
        g2d.drawRect(rect_big_1_right, paint);

        int small_rect_length = 650, small_rect_height = 45;
        int sr1x = 80, sr1y =  r1y + big_rect_height/2 - 45/2; //142;
        int h11 = r1y + 15, h12 = sr1y - 30, h13 = sr1y + small_rect_height + 15, h14 = r1y + big_rect_height - 30;
        Rect rect_small_1_up = new Rect(sr1x, sr1y, sr1x + small_rect_length, sr1y + rw); //Arriba
        Rect rect_small_1_left = new Rect(sr1x, sr1y, sr1x + rw, sr1y + 45); //Izda
        Rect rect_small_1_down = new Rect(sr1x, sr1y + small_rect_height, sr1x + small_rect_length, sr1y + small_rect_height + rw); //Abajo
        Rect rect_small_1_right = new Rect(sr1x + small_rect_length, sr1y, sr1x + small_rect_length + rw, sr1y + small_rect_height + rw);//730, 142, rw, 50); //Dcha

        g2d.drawRect(rect_small_1_up, paint);
        g2d.drawRect(rect_small_1_left, paint);
        g2d.drawRect(rect_small_1_down, paint);
        g2d.drawRect(rect_small_1_right, paint);

        //Segundo Rectangulo grande
        Rect rect_big_2_up = new Rect(r2x, r2y, r2x + big_rect_length, r2y + rw); //Arriba
        Rect rect_big_2_left = new Rect(r2x, r2y, r2x + rw, r2y + big_rect_height); //Izda
        Rect rect_big_2_down = new Rect(r2x, r2y+big_rect_height, r2x + big_rect_length, r2y+big_rect_height + rw); //Abajo1
        Rect rect_big_2_right = new Rect(r2x + big_rect_length, r2y, r2x + big_rect_length + rw, r2y + big_rect_height + rw); //Dcha

        g2d.drawRect(rect_big_2_up, paint);
        g2d.drawRect(rect_big_2_left, paint);
        g2d.drawRect(rect_big_2_down, paint);
        g2d.drawRect(rect_big_2_right, paint);
//		//Pequeño 2
        int sr2x = 80, sr2y =  r2y + big_rect_height/2 - 45/2; //142;
        int h21 = r2y + 15, h22 = sr2y - 30, h23 = sr2y + small_rect_height + 15, h24 = r2y + big_rect_height - 30;

        Rect rect_small_2_up = new Rect(sr2x, sr2y, sr2x + small_rect_length, sr2y + rw); //Arriba
        Rect rect_small_2_left = new Rect(sr2x, sr2y, sr2x + rw, sr2y + 45); //Izda
        Rect rect_small_2_down = new Rect(sr2x, sr2y + small_rect_height, sr2x + small_rect_length, sr2y + small_rect_height + rw); //Abajo
        Rect rect_small_2_right = new Rect(sr2x + small_rect_length, sr2y, sr2x + small_rect_length + rw, sr2y + small_rect_height + rw);//730, 142, rw, 50); //Dcha

        g2d.drawRect(rect_small_2_up, paint);
        g2d.drawRect(rect_small_2_left, paint);
        g2d.drawRect(rect_small_2_down, paint);
        g2d.drawRect(rect_small_2_right, paint);

        //Escaleras
        Rect rect_stairs3 = new Rect(sr1x + small_rect_length +10, sr1y + 10, sr1x + small_rect_length +10 + 50, sr1y + 10 + 30);

        Rect rect_stairs4 = new Rect(sr2x + small_rect_length +10, sr2y + 10, sr2x + small_rect_length +10 + 50, sr2y + 10 + 30); //A planta 3
        g2d.drawRect(rect_stairs3, paint);
        g2d.drawRect(rect_stairs4, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        g2d.drawText("Subir", sr1x + small_rect_length + 20, sr1y + 22, paint);
        g2d.drawText("planta", sr1x + small_rect_length + 19, sr1y + 36, paint);
        g2d.drawText("Bajar", sr2x + small_rect_length + 20, sr2y + 22, paint);
        g2d.drawText("planta", sr2x + small_rect_length + 19, sr2y + 36, paint);

        int id;
        for (int i = 0; i < G.getnumitems(); i++){

            if (!route.indexinroute(G.getitem(i).getid()))
                paint.setColor(Color.BLACK);
            else
                paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            fillOval(g2d, G.getitem(i).getposx(),G.getitem(i).getposy(), width);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            g2d.drawText("" + G.getitem(i).gettime(), G.getitem(i).getposx(), G.getitem(i).getposy() + width, paint);
            paint.setColor(Color.WHITE);
            if (G.getitem(i).getid() < 10) {
                id = G.getitem(i).getid() + 1;

                g2d.drawText("" + id, G.getitem(i).getposx() - 3 //+ g.getFontMetrics().stringWidth(""+G.getitem(i).getid())
                        , G.getitem(i).getposy() + 4, paint);//- g.getFontMetrics().getHeight()/2 );
            }else {
                id = G.getitem(i).getid() + 1;
                g2d.drawText("" + id, G.getitem(i).getposx() - 7 //+ g.getFontMetrics().stringWidth(""+G.getitem(i).getid())
                        , G.getitem(i).getposy() + 4, paint);//- g.getFontMetrics().getHeight()/2 );
            }
        }
        int x10,x20, y10,y20,x1,x2,y1,y2;
        paint.setStyle(Paint.Style.STROKE);

        //Pinta la ruta optima
        paint.setColor(Color.RED);
        boolean floor3 = false, floor4 = false, stairs = false;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        for (int i = 0; i < route.size() -1; i++){
            x10 = route.get(i).getposx();
            x20 = route.get(i).getposy();
            y10 = route.get(i+1).getposx();
            y20 = route.get(i+1).getposy();
            x1 = calculateCircPointx(x10,x20,y10,y20).getFirstElement();
            x2 = calculateCircPointx(x10,x20,y10,y20).getSecondElement();
            y1 = calculateCircPointy(x10,x20,y10,y20).getFirstElement();
            y2 = calculateCircPointy(x10,x20,y10,y20).getSecondElement();
            if (route.get(i+1).getid() >= 28 && route.get(i+1).getid() <= 44 &&!floor3){
                int aux11 = 50, aux12 =  h12;
                int aux21 = aux11, aux22 =  h13;//40-small_rect_height;
                x1 = calculateCircPointx(x10,x20,aux11, aux12).getFirstElement();
                x2 = calculateCircPointx(x10,x20,aux11, aux12).getSecondElement();
                y1 = calculateCircPointy(aux21, aux22,y10,y20).getFirstElement();
                y2 = calculateCircPointy(aux21, aux22,y10,y20).getSecondElement();

                g2d.drawLine(x1, x2, aux11, aux12, paint);
                g2d.drawLine(aux11, aux12, aux21, aux22, paint);
                drawArrowLine(g2d, aux21, aux22, y1, y2, 10, 5);
                floor3 = true;
            }
            else if (route.get(i+1).getid() >= 45 && !stairs){
                int aux11 = sr1x + small_rect_length + 35, aux12 =  sr1y + 40;
                int aux21 = sr2x + small_rect_length + 35, aux22 =  sr2y + 10;//40-small_rect_height;
                x1 = calculateCircPointx(x10, x20, aux11, aux12)
                        .getFirstElement();
                x2 = calculateCircPointx(x10, x20, aux11, aux12)
                        .getSecondElement();
                y1 = calculateCircPointy(aux21, aux22, y10, y20)
                        .getFirstElement();
                y2 = calculateCircPointy(aux21, aux22, y10, y20)
                        .getSecondElement();

                drawArrowLine(g2d, x1, x2, aux11, aux12, 10, 5);
                drawArrowLine(g2d, aux21, aux22, y1, y2, 10, 5);

                stairs = true;
            }else if (route.get(i+1).getid() >= 50 && route.get(i+1).getid() <= 52 &&!floor4){
                int aux11 = 50, aux12 =  h22;
                int aux21 = aux11, aux22 =  h23;//40-small_rect_height;
                x1 = calculateCircPointx(x10,x20,aux11, aux12).getFirstElement();
                x2 = calculateCircPointx(x10,x20,aux11, aux12).getSecondElement();
                y1 = calculateCircPointy(aux21, aux22,y10,y20).getFirstElement();
                y2 = calculateCircPointy(aux21, aux22,y10,y20).getSecondElement();

                g2d.drawLine(x1, x2, aux11, aux12, paint);
                g2d.drawLine(aux11, aux12, aux21, aux22, paint);
                drawArrowLine(g2d, aux21, aux22, y1, y2, 10, 5);
                floor4 = true;
            }else{
                drawArrowLine(g2d, x1, x2, y1, y2, 10, 5);
            }
        }
    }
    public Graph createGraph(){
        Graph G = null;
        try {
     //       G = ReadFile.readData("E:\\Users\\Jesús\\Downloads\\TFG\\PARTE 2\\ItemsMuseo.txt", userweights);
     //       G = ReadFile.readData("E:\\TFG\\ItemsMuseo.txt", userweights);
            G = ReadFile.readData(userweights);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return G;
    }
    void fillOval(Canvas g, int x1, int x2, int w){
        paint.setStyle(Paint.Style.FILL);
        g.drawCircle(x1 - w / 2, x2 - w / 2, w, paint);
        paint.setStyle(Paint.Style.STROKE);

    }
    private Pair<Integer, Integer> calculateCircPointx(int x1, int x2, int y1, int y2){
        int v1 = y1-x1, v2 = y2-x2;
        double vnorm = Math.sqrt(Math.pow(v1,2)+Math.pow(v2,2));
        double v1n =   (v1/vnorm);
        double v2n =  (v2/vnorm);
        return new Pair<Integer, Integer>((x1 +  (int)(v1n*width/2)), x2 + (int)(v2n*width/2));
    }
    private Pair<Integer, Integer> calculateCircPointy(int x1, int x2, int y1, int y2){
        int v1 = y1-x1, v2 = y2-x2;
        double vnorm = Math.sqrt(Math.pow(v1,2)+Math.pow(v2,2));
        double v1n =   (v1/vnorm);
        double v2n =  (v2/vnorm);
        return new Pair<Integer, Integer>(y1 -  (int)(v1n*width/2), y2- (int)(v2n*width/2));
    }

    private void drawArrowLine(Canvas canvas, int x1, int y1, int x2, int y2, int d, int h){
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy/D, cos = dx/D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        canvas.drawLine(x1, y1, x2, y2, paint);
        fillPolygon(canvas, xpoints, ypoints);

    }
    private void fillPolygon(Canvas canvas, int[] xpoints, int[] ypoints) {
        // line at minimum...
        if (xpoints.length < 2) {
            return;
        }

        // paint
        Paint polyPaint = new Paint();
       polyPaint.setStyle(Paint.Style.FILL);

        // path
        Path polyPath = new Path();
        polyPath.moveTo(xpoints[0], ypoints[0]);
        int i, len;
        len = xpoints.length;
        for (i = 0; i < len; i++) {
            polyPath.lineTo(xpoints[i], ypoints[i]);
        }
        polyPath.lineTo(xpoints[0], ypoints[0]);

        // draw
        canvas.drawPath(polyPath, polyPaint);
        polyPaint.setStyle(Paint.Style.STROKE);
    }

}
