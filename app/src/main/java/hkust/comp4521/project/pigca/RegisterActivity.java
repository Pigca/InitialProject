package hkust.comp4521.project.pigca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegisterActivity extends ActionBarActivity {

    private EditText mUsername;
    private EditText mEmail;
    private EditText mPassword;

    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
/*
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "PpuOpKDNjNqJ5qVviZohBSZ0U98W1nVi6q5Lmf5H", "3O3uEgIykqI9LDcMda54N1FLZDh5Mim3tQlHIA2v");
*/
        // Initialize
        mUsername = (EditText) findViewById(R.id.txtUsername);
        mEmail = (EditText) findViewById(R.id.txtEmail);
        mPassword = (EditText) findViewById(R.id.txtPassword);

        mRegisterButton = (Button) findViewById(R.id.registerButton);

        // Listen to register button click
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get input from user
                final String username = mUsername.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                // store user information in Parse
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null) {
                            // user sign up successfully
                            Toast.makeText(RegisterActivity.this, "Success! Welcome " + username + "!", Toast.LENGTH_LONG).show();

                            // take user to home page
                            Intent toHome = new Intent(RegisterActivity.this, HomepageActivity.class);
                            startActivity(toHome);
                        }
                        else {
                            // error in signing up

                        }
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
