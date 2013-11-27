package dk.reibke.schedulefun;

import java.security.NoSuchAlgorithmException;

import dk.reibke.schedulefun.util.Utilities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends Activity {

	private String username;
	private EditText usernameField;
	private EditText passwordField1;
	private EditText passwordField2;
	private TextView intention;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		Intent intent = getIntent();
		this.username = intent.getStringExtra("username");
		this.intention = (TextView) findViewById(R.id.signup_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}
	
	public void registerUser(View v) throws NoSuchAlgorithmException{
		this.usernameField = (EditText) findViewById(R.id.signup_username_field);
		this.passwordField1 = (EditText) findViewById(R.id.signup_password_field);
		this.passwordField2 = (EditText) findViewById(R.id.signup_password2_field);
		
		this.username = this.usernameField.getText().toString();
		DbManager.InitiateDatabase(this, Utilities.getDatabaseName(), null, Utilities.getVersion(this), this.username);
		
		String pass1 = Utilities.SHA256(this.passwordField1.getText().toString());
		String pass2 = Utilities.SHA256(this.passwordField2.getText().toString());
		if(pass1.equals(pass2) && DbManager.getInstance(this).hasUser(this.username)){
			DbManager.getInstance(this).addUser(this.username, pass1);
			userAdded();
		}else{
			if(!pass1.equals(pass2)){
				noPassEqual();
			}else{
				UserTaken();
			}
		}
	}

	private void userAdded() {
		this.intention.setText("User added");
	}

	private void UserTaken() {
		this.intention.setText("Username already exists!");
	}

	private void noPassEqual() {
		this.intention.setText("Passwords not equal!");
	}

}
