package dk.reibke.schedulefun;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private Button login_btn;
	private Button signup_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}

	private void initialize() {
		login_btn = (Button) findViewById(R.id.login_btn);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void openSignupFormular(View v){
		
		Intent intent = new Intent(this, SignupActivity.class);
		startActivity(intent);
		
	}
	
	public void openLoginFormular(View v){
		
		
		
	}
	

}
