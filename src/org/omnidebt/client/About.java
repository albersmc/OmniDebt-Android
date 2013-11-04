package org.omnidebt.client;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class About extends Activity {
	
	private ImageView ivJordan = null;
	private ImageView ivArnaud = null;
	private ImageView ivMatthias = null;
	private ImageView ivMathea = null;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		ivJordan = (ImageView) findViewById(R.id.imageJordan);
		ivJordan.setOnClickListener(onClickJordan);
		ivArnaud = (ImageView) findViewById(R.id.imageArnaud);
		ivArnaud.setOnClickListener(onClickArnaud);
		ivMatthias = (ImageView) findViewById(R.id.imageMatthias);
		ivMatthias.setOnClickListener(onClickMatthias);
		ivMathea = (ImageView) findViewById(R.id.imageMathea);
		ivMathea.setOnClickListener(onClickMathea);
		
	}
	
	private OnClickListener onClickJordan = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String url = "https://twitter.com/DjowIsGreat";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	};
	
	private OnClickListener onClickArnaud = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String url = "https://github.com/alacrampe";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	};
	
	private OnClickListener onClickMatthias = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String url = "https://github.com/mdevlamynck";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	};
	
	private OnClickListener onClickMathea = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String url = "http://www.viadeo.com/profile/002vvnwd3e2ne5i";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	};
		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

}
