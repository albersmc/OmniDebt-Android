package org.omnidebt.client.view.signup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.UserController;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends Activity{

	EditText etName		= null;
	EditText etEmail	= null;
	EditText etPasswd	= null;
	EditText etConfirm	= null;
	TextView tvStatus	= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		etName		= (EditText) findViewById(R.id.editLogin);
		etEmail		= (EditText) findViewById(R.id.editEmail);
		etPasswd	= (EditText) findViewById(R.id.editPassword);
		etConfirm	= (EditText) findViewById(R.id.editConfirmPassword);
		tvStatus	= (TextView) findViewById(R.id.errorLabel);
		
		Button signUpButton=(Button) findViewById(R.id.SignUpButton);
		signUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validate())
				{
					String strLogin			= etName.getText().toString();
					String strEmail			= etEmail.getText().toString();
					String strPasswd		= etPasswd.getText().toString();
					String strConfirmPasswd	= etConfirm.getText().toString();
					
					Button cancelButton=(Button) findViewById(R.id.CancelButton);
					UserController.trySignUp(strLogin, strEmail, strPasswd, strConfirmPasswd, suListener);

					tvStatus.setText(R.string.login_trying_login);
				}
				
			}
		});
		
		Button CancelButton=(Button) findViewById(R.id.CancelButton);
		CancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
				overridePendingTransition(R.anim.none, R.anim.top_out);
			}
		});

		if(savedInstanceState != null)
		{
			etName.setText(savedInstanceState.getString("name"));
			etEmail.setText(savedInstanceState.getString("email"));
			etPasswd.setText(savedInstanceState.getString("passwd"));
			etConfirm.setText(savedInstanceState.getString("confirm"));
			tvStatus.setText(savedInstanceState.getString("status"));
		}
	}
	
	@Override
	protected void onSaveInstanceState (Bundle outState) {
		outState.putString("name", etName.getText().toString());
		outState.putString("email", etEmail.getText().toString());
		outState.putString("passwd", etPasswd.getText().toString());
		outState.putString("confirm", etPasswd.getText().toString());
		outState.putString("status", tvStatus.getText().toString());
	}

	public boolean validate()
	{
		String string_pattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern=Pattern.compile(string_pattern);
		EditText mail=(EditText) findViewById(R.id.editEmail);
		String mail_str=mail.getText().toString();
		Matcher matcher=pattern.matcher(mail_str);
		if(!matcher.matches())
		{
			tvStatus.setText(R.string.signup_invalid_email);
		}
		boolean pweqcpw=((EditText) findViewById(R.id.editPassword)).getText().toString().equals(((EditText) findViewById(R.id.editConfirmPassword)).getText().toString());
		if(!pweqcpw)
		{
			tvStatus.setText(R.string.signup_pwd_different_cpwd);
		}
		boolean valid=(matcher.matches() && pweqcpw);
		return valid;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// The activity is no longer visible (it is now "stopped")
		overridePendingTransition(R.anim.none, R.anim.top_out);
	}
	
	private SignUpListener suListener=new SignUpListener(){
		@Override
		public void onConnectResult(SignUpResult code) {
			if(code.equals(SignUpResult.Succeed))
			{
				finish();
				overridePendingTransition(R.anim.none, R.anim.top_out);
			}
			else
			{
				if(code.equals(SignUpResult.UsedLogin))
				{
					tvStatus.setText(R.string.signup_error_used_login);
				}
				if(code.equals(SignUpResult.Failed))
				{
					tvStatus.setText(R.string.login_failed);
				}
			}
			
		}
		
		
	};
}
