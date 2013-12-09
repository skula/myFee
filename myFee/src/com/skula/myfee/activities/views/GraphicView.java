package com.skula.myfee.activities.views;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.skula.myfee.models.TimeUnit;
import com.skula.myfee.services.DrawerService;

public class GraphicView extends View {
	private DrawerService drawer;
	// android:clearTaskOnLaunch
	// android:noHistory
	
	public GraphicView(Context context) {
		 super(context);
	}
	
	public GraphicView(Context context, Map<String, List<TimeUnit>> timeUnits) {
		 super(context);
		 //this.drawer = new DrawerService(context.getResources(), timeUnits);
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
		drawer.draw(canvas);
	}
}