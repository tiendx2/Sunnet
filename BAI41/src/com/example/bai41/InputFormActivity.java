package com.example.bai41;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputFormActivity extends Activity {

	EditText edNumA,edNumB,edNumC;
	Button btnVeDT;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_form);
		edNumA=(EditText)findViewById(R.id.edA);
		edNumB=(EditText)findViewById(R.id.edB);
		edNumC=(EditText)findViewById(R.id.edC);
		
		btnVeDT = (Button) findViewById(R.id.butVeDT);
		btnVeDT.setOnClickListener(new MyEvent());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_form, menu);
		return true;
	}
	private class MyEvent implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			processButtonDraw();
		}
		
	}
	
	public boolean check(String a,String b,String c){
		try {
			float n1 =Float.parseFloat(a);
			float n2= Float.parseFloat(b);
			float n3= Float.parseFloat(c);
			
			return true;
		} catch(Exception e){
			return false;
		}
	}
	public void processButtonDraw(){
		String sA=edNumA.getText().toString();
		String sB=edNumB.getText().toString();
		String sC=edNumC.getText().toString();
		if(check(sA, sB, sC)){
			float a=Float.parseFloat(sA);
			float b=Float.parseFloat(sB);
			float c=Float.parseFloat(sC);
			
			if(a==0)
				Toast.makeText(this,"Tham so a=0, nhap lai", Toast.LENGTH_LONG).show();
			else {
				Bundle bundle = new Bundle();
				bundle.putFloat("NA", a);
				bundle.putFloat("NB", b);
				bundle.putFloat("NC", c);
				Intent myIntent = new Intent(InputFormActivity.this,GraphicLevel2Activity.class);
				myIntent.putExtra("ThamSoPT", bundle);
				startActivity(myIntent);
			}
		}
		else {
			Toast.makeText(this,"Input Error !", Toast.LENGTH_LONG).show();
		}
	}

}
