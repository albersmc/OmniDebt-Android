package org.omnidebt.client.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omnidebt.client.R;
import org.omnidebt.client.R.anim;
import org.omnidebt.client.R.id;
import org.omnidebt.client.R.layout;
import org.omnidebt.client.view.LoginListener.ConnectResult;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		Button signUpButton=(Button) findViewById(R.id.SignUpButton);
		signUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validate())
				{
					
					
					
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
	}
	
	public boolean validate()
	{
		String string_pattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern=Pattern.compile(string_pattern);
		EditText mail=(EditText) findViewById(R.id.editEmail);
		String mail_str=mail.getText().toString();
		Matcher matcher=pattern.matcher(mail_str);
		
		
		return matcher.matches();
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
        // The activity is no longer visible (it is now "stopped")
        overridePendingTransition(R.anim.none, R.anim.top_out);
    }
	
	private SignUpListener suListener=new SignUpListener(){
		@Override
		public void onConnectResult(ConnectResult code) {
			if(code.equals(ConnectResult.Succeed))
			{
				finish();
			}
			else
			{
				TextView errorLabel=(TextView) findViewById(R.id.errorLabel);
				if(code.equals(ConnectResult.UsedLogin))
				{
					errorLabel.setText(R.string.signup_error_used_login);
				}
				if(code.equals(ConnectResult.Failed))
				{
					errorLabel.setText(R.string.login_failed);
				}
			}
			
		}
		
		
	};
}
