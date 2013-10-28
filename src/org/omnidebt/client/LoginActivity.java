package org.omnidebt.client;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.ViewById;

public class LoginActivity extends Activity {
	
	@ViewById(R.id.text_edit_login)
	EditText etLogin;
	
	@ViewById(R.id.text_edit_password)
	EditText etPassword;
	
	@Click(R.id.button_accept_connection)
	void onAcceptLoginClicked()
	{
		String strLogin		= etLogin.getText().toString();
		String strPassword	= etPassword.getText().toString();
		
		Log.d("login", strLogin + " " + strPassword);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
}