package com.skula.myfee.services;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.RectF;

import com.skula.myfee.models.Category;
import com.skula.myfee.models.CurveGraphic;
import com.skula.myfee.models.RingGraphic;
import com.skula.myfee.models.TimeUnit;


public class DrawerService {
	private Paint paint;
    private Resources res;
	private CurveGraphic cGraph;
	private RingGraphic rGraph;
	
	private static final int WIDTH = 1280;
	private static final int HEIGH = 800;
	private static final int X0 = 50;
	private static final int Y0 = 650;
	private static final int XPADDING = 50;
	private static final int YPADDING = 50;
	private static final int BORDER = 4;


	public static void drawCurves(Canvas canvas, CurveGraphic graph){
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
        paint.setTextSize(40f);
		
		canvas.drawText(graph.getTitle(), 400, 80, paint);
		
		// paint y-axe 
		canvas.drawRect(new Rect(X0 , 50, X0 + BORDER, Y0), paint);
		
		// paint x-axe 
		int xSpace = (WIDTH - (2*50))/graph.getCount();
		canvas.drawRect(new Rect(X0 , Y0, WIDTH - 50, Y0 + BORDER), paint); 
		for(int i = 0; i < graph.getCount(); i++){
			canvas.drawRect(new Rect(X0 + i*xSpace , Y0, X0 + i*xSpace + BORDER, Y0 + 12), paint); 
		}
		// TODO: libellé repaires
		double yMax = graph.getyMax();
		int index = 0;
		int lastXpos = 0;
		int lastYpos = 0;
		paint.setStrokeWidth(5f);
		// paint curves	
		for(String cat : graph.getTimeUnits().keySet()){
			List<TimeUnit> listTu = graph.getTimeUnits().get(cat);
			for(TimeUnit tu : listTu){
				int xPos = X0 + xSpace * index;
				int yPos = (int)((tu.getValue()*600)/yMax);
				yPos = (yPos-600) * -1 + 50;
				paint.setColor(Color.parseColor(tu.getColor()));
				canvas.drawRect(new Rect(xPos-4 , yPos-4, xPos + 4 , yPos + 4), paint); 
				
				if(index>0){
					canvas.drawLine(lastXpos, lastYpos, xPos, yPos, paint);
				}
				
				lastXpos = xPos;
				lastYpos = yPos;
				index++;
			}
			index = 0;
		}
	}
	
	public static void drawRings(Canvas canvas, RingGraphic graph) {
		float radius;
		if (WIDTH > HEIGH){
			radius = HEIGH/3;
		}else{
			radius = WIDTH/4;
		}
		
		//Path path = new Path();
		//path.addCircle(WIDTH/2, HEIGHT/2, radius, Path.Direction.CW);
		
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE); //Paint.Style.FILL
		paint.setStrokeWidth(50f);
		
		float center_x, center_y;
		final RectF oval = new RectF();
		center_x = WIDTH/3;
		center_y = HEIGH/2 -50;
		oval.set(center_x - radius, center_y - radius, center_x + radius, center_y + radius);
		
		/* 
		 * 0° est le point au milieu droit du cercle
		 * 180° est le point au milieu gauche du cercle
		 * les sens de rotation est anti horaire
		 */
		int dStart = 0;
		int dEnd = 0;
		int index = 0;
		for(Category cat : graph.getCategories()){
			paint.setStyle(Paint.Style.STROKE); //Paint.Style.FILL
			paint.setStrokeWidth(50f);
			int arc = (int) (Double.valueOf(cat.getPercent())*360/100);
			dEnd = dEnd + arc;
			paint.setColor(Color.parseColor(cat.getColor()));
			canvas.drawArc(oval, dStart, dEnd, false, paint); //true	
			dStart = dEnd;
		
			canvas.drawRect(new Rect(200, 100 + index * 180, 200 + 50, 100 + 50 + index * 180), paint); 
			paint.setTextSize(80f);
			paint.setTextAlign(Align.CENTER);
			paint.setStrokeWidth(1f);
			canvas.drawText(cat.getLabel(), 300, 100 + index * 180, paint);
			//canvas.drawText(cat.getTotal().replace(".",",") + " €", WIDTH/2, HEIGHT/2-80, paint);		
			//paint.setTextSize(60f);
			//canvas.drawText(cat.getPercent()+"%", WIDTH/2, HEIGHT/2+80, paint);
			
			
			/*paint.setStyle(Paint.Style.FILL);
			paint.setTextSize(80f);
			paint.setTextAlign(Align.CENTER)
			paint.setStrokeWidth(1f);
			canvas.drawText(cat.getTotal().replace(".",",") + " €", WIDTH/2, HEIGHT/2-80, paint);
			canvas.drawText(cat.getLabel(), WIDTH/2, HEIGHT/2, paint);
			paint.setTextSize(60f);
			canvas.drawText(cat.getPercent()+"%", WIDTH/2, HEIGHT/2+80, paint);*/
		}	
	}
}
