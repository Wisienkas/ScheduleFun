package dk.reibke.schedulefun;

import java.security.NoSuchAlgorithmException;

import dk.reibke.schedulefun.util.Utilities;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private EditText usernameField;
	private EditText passwordField;
	private TextView statusField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initialize();
		this.statusField = (TextView) findViewById(R.id.login_status_text);
	}

	private void initialize() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void forgotPasswordClick(View v) {

		this.statusField.setText("Status: Button not implemented");
	}

	public void loginClick(View v) {
		this.usernameField = (EditText) findViewById(R.id.login_username_field);
		if (!DbManager.isInitialized()) {
			DbManager.InitiateDatabase(this, 
					Utilities.getDatabaseName(), 
					null,
					Utilities.getVersion(this), 
					this.usernameField.toString());
		}
		if (DbManager.getInstance(this).hasUser(this.usernameField.toString())){
			this.statusField.setText("Status: Username already taken!");
			return;
		}
		
		this.passwordField = (EditText) findViewById(R.id.login_password_field);
		Cursor result = null;
		try {
			result = DbManager.getInstance(this).confirmUser(this.usernameField.toString(), 
					Utilities.SHA256(this.passwordField.toString()));
		} catch (NoSuchAlgorithmException e) {
			this.statusField.setText("Status: Something went wrong at login!");
		}
		if(result.getColumnCount() > 0){
			if(result.getString(1) == this.usernameField.toString()){
				//TODO start new intent with overview for logged in user
			}
		}
	}

}
