import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph extends JPanel {

	public static final int WIDTH_FR = 410;
	public static final int HEIGHT_FR = 430;
	final int MARGIN = 30;
	final int RATE = 20;

	final int DISTANCE = 2;
	final int OX = 200;
	final int OY = 200;
	
	final int TRIA=6;

	private float a, b, c;

	public Graph(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	// Lay hoanh do diem M(x,y) tu he OXY sang toa do Component
	public int xFrame(float x) {
		float k = Math.round(x * RATE);
		int t = (int) k + OX;
		return t;
	}

	// Lay tung do diem M(x,y) tu he OXY sang toa do Component
	public int yFrame(float y) {
		float k = Math.round(y * RATE);

		int t = OY - (int) k;
		return t;
	}

	// Lay mang hoanh do x tren OXY trong [xMin,xMax]
	public float[] xArray(float xMin, float xMax) {
		float dis = Math.round((xMax - xMin) * RATE);
		int size = (int) dis;
		float[] arr = new float[size + 1];
		int i = 0;
		arr[0] = xMin;
		for (i = 1; i <= size; i++) {
			arr[i] = arr[i - 1] + 1f / (float) RATE;
			arr[i] = Math.round(arr[i] * 100) / 100f;
		}
		return arr;
	}

	// lay mang tung do tuong ung voi x tren OXY theo cong thuc y= a*x*x+b*x+c
	public float[] yArray(float xarr[]) {
		int i = 0;
		int size = xarr.length;
		float[] arr = new float[size];
		for (i = 0; i < size; i++) {
			arr[i] = a * xarr[i] * xarr[i] + b * xarr[i] + c;
			arr[i] = Math.round(arr[i] * 1000) / 1000f;
		}
		return arr;
	}

	public void paint(Graphics g) {
		// Ve truc tung
		g.drawLine(MARGIN, OY,WIDTH_FR-40, OY);
		// ve truc hoanh
		g.drawLine(OX, MARGIN, OX, HEIGHT_FR-60);
		
		
		int triaXX[]={ OX-TRIA,OX+TRIA,OX};
		int triaYX[]={MARGIN,MARGIN,MARGIN-TRIA};
		g.fillPolygon(triaXX, triaYX, 3);
		
		int triaXY[]={ WIDTH_FR-40,WIDTH_FR-40,WIDTH_FR-40+TRIA};
		int triaYY[]={OY-TRIA,OY+TRIA,OY};
		g.fillPolygon(triaXY, triaYY, 3);
		//
		float x1 = Math.round(-b / (2 * a) - DISTANCE);
		float y1 = Math.round(-b / (2 * a) + DISTANCE);

		float xfArr[] = xArray(x1, y1);
		float yfArr[] = yArray(xfArr);
		int size = xfArr.length;

		// Doi toa do tu OXY sang toa do Component
		int xiArr[] = new int[size];
		int yiArr[] = new int[size];
		for (int i = 0; i < size; i++) {
			xiArr[i] = this.xFrame(xfArr[i]);
			yiArr[i] = this.yFrame(yfArr[i]);

			System.out.println(xiArr[i] + " = " + yiArr[i]);
		}

		// Thuc hien ve do thi
		g.drawPolyline(xiArr, yiArr, size);
	}

}