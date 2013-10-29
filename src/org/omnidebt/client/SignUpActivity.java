package org.omnidebt.client;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		final Button signUpButton=(Button) findViewById(R.id.SignUpButton);
		signUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validate())
				{
					Intent i=new Intent(getApplicationContext(),LoginActivity.class);
					startActivity(i);
					
				}
				
			}
		});
		
	}
	
	public boolean validate()
	{
		return true;
	}
	
	
}
