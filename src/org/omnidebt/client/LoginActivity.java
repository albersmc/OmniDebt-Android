package org.omnidebt.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	EditText	etLogin			= null;
	EditText	etPassword		= null;
	
	Button		bLogin			= null;
	Button		bCancel			= null;
	Button		bCreateAccount	= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		etLogin			= (EditText)findViewById(R.id.text_edit_login);
		etPassword		= (EditText)findViewById(R.id.text_edit_password);
		
		bLogin			= (Button)	findViewById(R.id.button_accept_connection);
		bCancel			= (Button)	findViewById(R.id.button_cancel_connection);
		bCreateAccount	= (Button)	findViewById(R.id.button_create_account);
		
		bLogin.			setOnClickListener(onClickLogin);
		bCancel.		setOnClickListener(onClickCancel);
		bCreateAccount.	setOnClickListener(onClickCreateAccount);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	// On Login clicked
	private OnClickListener onClickLogin = new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	String	strLogin	= etLogin.getText().toString();
	    	String	strPassword	= etPassword.getText().toString();
	    	Log.d("login", "Login cliecked : login : " + strLogin + " pass : " + strPassword);
	    	
	    	if(strLogin.equals("test") && strPassword.equals("pass")) {
	    		Log.d("login", "Successfuly authenticated, launching MainODActivity");
	    		Intent mainActivity = new Intent(v.getContext(), MainODActivity.class);
	            startActivity(mainActivity);
	    	}
	    }
	};
	
	// On Cancel clicked	  
	private OnClickListener onClickCancel = new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	etLogin.setText("");
			etPassword.setText("");
	    }
	};

	// On Create Account clicked	  
	private OnClickListener onClickCreateAccount = new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	Log.d("login", "Successfuly authenticated, launching MainODActivity");
    		Intent registerActivity = new Intent(v.getContext(), RegisterActivity.class);
            startActivity(registerActivity);
	    }
	};
}