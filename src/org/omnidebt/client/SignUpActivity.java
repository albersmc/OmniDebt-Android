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
		
		Button signUpButton=(Button) findViewById(R.id.SignUpButton);
	}
	
	public boolean validate()
	{
		return true;
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
        // The activity is no longer visible (it is now "stopped")
        overridePendingTransition(R.anim.none, R.anim.top_out);
    }	
}
