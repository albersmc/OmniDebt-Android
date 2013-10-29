package org.omnidebt.client.view;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.UserController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	EditText	etLogin			= null;
	EditText	etPassword		= null;

	Button		bLogin			= null;
	Button		bCancel			= null;
	Button		bCreateAccount	= null;

	TextView	tvLoginStatus	= null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		etLogin			= (EditText)findViewById(R.id.text_edit_login);
		etPassword		= (EditText)findViewById(R.id.text_edit_password);
		
		bLogin			= (Button)	findViewById(R.id.button_accept_connection);
		bCancel			= (Button)	findViewById(R.id.button_cancel_connection);
		bCreateAccount	= (Button)	findViewById(R.id.button_create_account);
		
		tvLoginStatus	= (TextView)findViewById(R.id.login_status);
		
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
	    	
	    	Log.i("login", "Try connection");
	    	UserController.tryConnect(strLogin, strPassword, loginListener);
	    }
	};

	// On Cancel clicked	  
	private OnClickListener onClickCancel = new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	// Clear form
	    	tvLoginStatus.setText("");
	    	etLogin.setText("");
			etPassword.setText("");
	    }
	};

	// On Create Account clicked	  
	private OnClickListener onClickCreateAccount = new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	Log.i("login", "Starting SignUpActivity");
	    	tvLoginStatus.setText("");
	    	etLogin.setText("");
			etPassword.setText("");

    		Intent registerActivity = new Intent(v.getContext(), SignUpActivity.class);
            startActivity(registerActivity);
            overridePendingTransition(R.anim.top_in, R.anim.none);
	    }
	};

	private LoginListener loginListener = new LoginListener() {
		@Override
		public void onConnectResult(ConnectResult code) {
			// Authentication
	    	if(code.equals(ConnectResult.Succeed)) {
	    		// Clear form and go to MainODActivity
	    		Log.i("login", "Successfuly authenticated, launching MainODActivity");
	    		tvLoginStatus.setText("");
	    		etLogin.setText("");
				etPassword.setText("");
	    		
	    		Intent mainActivity = new Intent(getApplicationContext(), MainODActivity.class);
	            startActivity(mainActivity);
	            overridePendingTransition(R.anim.right_in, R.anim.left_out);
	    	}
	    	else if(code.equals(ConnectResult.WrongIDs)) {
	    		// Clear form and explain error to the user
	    		Log.w("login", "Authentication failed : wrong ids");
	    		etLogin.setText("");
				etPassword.setText("");
				tvLoginStatus.setText(R.string.login_wrong_ids);
	    	}
	    	else if(code.equals(ConnectResult.Failed)) {
	    		tvLoginStatus.setText(R.string.login_failed);
	    	}
		}
	};
}