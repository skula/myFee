package com.skula.myfee.services;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.skula.myfee.models.TimeUnit;


public class DrawerService {
	private Paint paint;
    private Resources res;
	private Map<String, List<TimeUnit>> timeUnits;

	public DrawerService(Context context, Map<String, List<TimeUnit>> timeUnits) {
		this.res = res;
        this.paint = new Paint();
		this.timeUnits = timeUnits;
	}

	public void draw(Canvas c){
		
	}
}
