package com.skula.myfee.services;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.skula.myfee.models.CurveGraphic;
import com.skula.myfee.models.RingGraphic;


public class DrawerService {
	private Paint paint;
    private Resources res;
	private CurveGraphic cGraph;
	private RingGraphic rGraph;

	public DrawerService(Context context) {
		this.res = res;
        this.paint = new Paint();
	}
	
	public DrawerService(Context context, CurveGraphic cGraph) {
		this.res = res;
        this.paint = new Paint();
		this.cGraph = cGraph;
	}
	
	public DrawerService(Context context, RingGraphic rGraph) {
		this.res = res;
        this.paint = new Paint();
		this.rGraph = rGraph;
	}

	public void draw(Canvas c){
		
	}
}
