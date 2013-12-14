package com.skula.myfee.activities.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import com.skula.myfee.models.CurveGraphic;
import com.skula.myfee.models.RingGraphic;
import com.skula.myfee.services.DrawerService;

public class GraphicView extends View {
	private CurveGraphic cGraph;
	private RingGraphic rGraph;
	
	// android:clearTaskOnLaunch
	// android:noHistory
	
	public GraphicView(Context context) {
		 super(context);
	}
	
	public void init(RingGraphic gr){
		this.rGraph = gr;
		this.cGraph = null;
		invalidate();
	}
	
	public void init(CurveGraphic gr){
		this.cGraph = gr;
		this.rGraph = null;
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			break;
		case MotionEvent.ACTION_MOVE:

			break;
		case MotionEvent.ACTION_UP:
			
			break;
		}
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		if(rGraph!=null){
			DrawerService.drawRings(canvas, rGraph);
		}else if(cGraph!=null){
			//DrawerService.drawCurves(canvas, cGraph);
			DrawerService.drawRings(canvas, rGraph);
		}
	}
}