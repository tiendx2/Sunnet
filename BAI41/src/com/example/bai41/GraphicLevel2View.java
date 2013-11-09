package com.example.bai41;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.View.OnTouchListener;

public class GraphicLevel2View extends View implements OnTouchListener {

	GestureDetector gestureDetector;

	private int WIDTH_FR, HEIGHT_FR; // do rong, do cao cua manh hinh
	private int OX, OY; // Toa do tam O
	private int initOX, initOY; // Toa do khoi tao bai dau

	private int disX; // do dai truc Ox
	private int disY; // do dai tru Oy
	private int initDisX, initDisy;

	private float a, b, c; // y=a*x^2+b*x+c

	private int RATE; // Ti le giua toa do thuc va toa do ao
	private int initRate;

	private int textSize;
	private int initTextSize;
	private int DistanceX; // khoang do dai [-b/2a-DistanceX, -b/2a+DistanceX]
	private float TRIA;
	private float initTRIA;

	private int LIMIT_ZOOM; // gioi han kha nang LIMIT_ZOOM do thi
	private int LIMIT_MOVE; // gioi han kha nang di chuyen do thi
	private Paint paint; // The paint (e.g. style, color) used for drawing

	public GraphicLevel2View(Context context) {
		super(context);

		gestureDetector = new GestureDetector(context, new GestureListener());

		this.a = GraphicLevel2Activity.a;
		this.b = GraphicLevel2Activity.b;
		this.c = GraphicLevel2Activity.c;
		this.WIDTH_FR = GraphicLevel2Activity.screenWidth;
		this.HEIGHT_FR = GraphicLevel2Activity.screenHeight - 80;
		paint = new Paint();

		// Toa do tam O
		OX = this.WIDTH_FR / 2;
		OY = this.HEIGHT_FR / 2;

		// Do dai truc Ox,Oy
		this.disX = 100;
		this.disY = 150;

		// Ti le toa do
		this.RATE = 20;
		this.LIMIT_ZOOM = 0;
		this.LIMIT_MOVE = 60;

		// khoang cua X
		this.DistanceX = 2;

		// co chu
		this.textSize = 11;

		// Do dai mui ten
		this.TRIA = 3.0f;
		// To enable keypad
		this.setFocusable(true);
		this.requestFocus();
		// To enable touch mode
		this.setFocusableInTouchMode(true);

		// copy thong so toa do ban dau
		initOX = OX;
		initOY = OY;
		initDisX = disX;
		initDisy = disY;
		initRate = RATE;
		initTextSize = textSize;
		initTRIA = TRIA;

	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return gestureDetector.onTouchEvent(e);
	}

	// lay hoanh do thuc man hinh
	float xScreen(float x) {
		return (OX + x * RATE);
	}

	// lay tung do thuc man hinh
	float yScreen(float y) {
		return (OY - y * RATE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// xac dinh cuc tri cua ham so
		float cucTri = -b / (2 * a);
		// xac dinh khoang max cua X va min cua X,
		int maxX = Math.round(cucTri) + DistanceX;
		int minX = -DistanceX + Math.round(cucTri);

		int size = maxX - minX;

		// chua toa do (x,y) trong he toa do OXY lan luot vao 2 mang arrX[] va
		// arrY[]
		float arrX[] = new float[size * RATE + 1];
		float arrY[] = new float[arrX.length];
		for (int i = RATE * minX; i <= RATE * maxX; i++) {
			int d = i - RATE * minX;
			arrX[d] = (float) i / (float) RATE;
			arrY[d] = a * arrX[d] * arrX[d] + b * arrX[d] + c;
		}

		// Thuc hien ve do thi
		for (int i = 0; i < size * RATE; i++) {
			canvas.drawLine(xScreen(arrX[i]), yScreen(arrY[i]),
					xScreen(arrX[i + 1]), yScreen(arrY[i + 1]), paint);
		}

		// TRUC OX
		canvas.drawLine(OX - disX, OY, OX + disX, OY, paint);
		// TRUC OY
		canvas.drawLine(OX, OY - disY, OX, OY + disY, paint);

		// Ve ox,oy,o
		paint.setTextSize(textSize);
		canvas.drawText("o", OX + 2, OY + 13, paint);
		canvas.drawText("oy", OX + 5, OY - disY + 5, paint);
		canvas.drawText("ox", OX + disX - 15, OY - 4, paint);
		// Ve mui ten oy
		Paint paint1 = new Paint();
		canvas.drawLine(OX - TRIA, OY - disY, OX + TRIA, OY - disY, paint1);
		canvas.drawLine(OX - TRIA, OY - disY, OX, OY - disY - TRIA, paint1);
		canvas.drawLine(OX + TRIA, OY - disY, OX, OY - disY - TRIA, paint1);
		// ve mui ten ox
		canvas.drawLine(OX + disX, OY - TRIA, OX + disX, OY + TRIA, paint1);
		canvas.drawLine(OX + disX, OY - TRIA, OX + disX + TRIA, OY, paint1);
		canvas.drawLine(OX + disX, OY + TRIA, OX + disX + TRIA, OY, paint1);

	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		switch (keyCode) {
		// Di chuyen do thi xuong duoi
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (OY < HEIGHT_FR - LIMIT_MOVE)
				OY = OY + 4;
			break;
		// Di chuyen len tren
		case KeyEvent.KEYCODE_DPAD_UP:
			if (OY > LIMIT_MOVE)
				OY = OY - 4;
			break;
		// Di chuyen sang trai
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (OX > LIMIT_MOVE)
				OX = OX - 4;
			break;
		// Di chuyen sang phai
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (OX < WIDTH_FR - LIMIT_MOVE)
				OX = OX + 4;
			break;

		// Lay lai do thi ban dau
		case KeyEvent.KEYCODE_DPAD_CENTER:
			RATE = initRate;
			disX = initDisX;
			disY = initDisy;
			OY = initOY;
			OX = initOX;
			textSize = initTextSize;
			LIMIT_ZOOM = 0;
			TRIA = initTRIA;
			break;
		}

		invalidate();
		return true;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	// Class su kien doubletap thi phong to do thi
	private class GestureListener extends
			GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		// LIMIT_ZOOM out
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// float x = e.getX();
			// float y = e.getY();

			// neu LIMIT_ZOOM out <3 thi cho LIMIT_ZOOM out
			if (LIMIT_ZOOM <= 3) {
				RATE = RATE + 5;
				disX = disX + DistanceX * 7;
				disY = disY + DistanceX * 7;
				OY = OY - DistanceX * 7;
				textSize = textSize + 1;

				LIMIT_ZOOM = LIMIT_ZOOM + 1;
				TRIA = TRIA + 0.5f;
				invalidate();
			}
			return true;
		}

		// LIMIT_ZOOM in
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// neu LIMIT_ZOOM in lon hon -2 thi cho LIMIT_ZOOM in
			if (LIMIT_ZOOM >= -2) {
				RATE = RATE - 5;
				disX = disX - DistanceX * 7;
				disY = disY - DistanceX * 7;
				OY = OY + DistanceX * 7;
				textSize = textSize - 1;
				LIMIT_ZOOM = LIMIT_ZOOM - 1;
				TRIA = TRIA - 0.5f;
				invalidate();
			}
			return true;
		}

	}

}
