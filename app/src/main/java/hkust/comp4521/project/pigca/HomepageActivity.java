package hkust.comp4521.project.pigca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;


public class HomepageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.postStatus:
                // take user to postActivity
                Intent intent = new Intent(this, postActivity.class);
                startActivity(intent);
                break;

            case R.id.logoutUser:
                // Logout the user
                ParseUser.logOut();

                // Redirect to login page
                Intent toLogin = new Intent(this, LoginActivity.class);
                startActivity(toLogin);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
