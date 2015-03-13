package hkust.comp4521.project.pigca;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends ActionBarActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginButton;
    private Button mCreateAccountButtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "PpuOpKDNjNqJ5qVviZohBSZ0U98W1nVi6q5Lmf5H", "3O3uEgIykqI9LDcMda54N1FLZDh5Mim3tQlHIA2v");

        // Initialization
        mUsername = (EditText) findViewById(R.id.usernameLoginTextBox);
        mPassword = (EditText) findViewById(R.id.passwordLoginTextBox);

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mCreateAccountButtton = (Button) findViewById(R.id.createAccountButton);

        // Listen to when the mLogin button is clicked
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the input from user
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                // Login he user using parse sdk
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {

                        if(e == null){
                            // Login successfully
                            Toast.makeText(LoginActivity.this, "Welcome back!", Toast.LENGTH_LONG).show();
                            Intent toHome = new Intent(LoginActivity.this, HomepageActivity.class);
                            startActivity(toHome);
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Sorry!");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Close the dialog
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
