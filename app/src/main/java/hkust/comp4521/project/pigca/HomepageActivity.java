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

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // show user the home page

        } else {
            // show the signup or login screen
            Intent toLogin = new Intent(this, LoginActivity.class);
            startActivity(toLogin);
        }
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

/*
    private ParseQueryAdapter<Post> mainAdapter;
    //private FavoriteMealAdapter favoritesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homepage);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // show user the home page

        } else {
            // show the signup or login screen
            Intent toLogin = new Intent(this, LoginActivity.class);
            startActivity(toLogin);
        }

        getListView().setClickable(false);

        mainAdapter = new ParseQueryAdapter<Post>(this, Post.class);
        mainAdapter.setTextKey("title");
        mainAdapter.setImageKey("photo");

        // Subclass of ParseQueryAdapter
        //favoritesAdapter = new FavoriteMealAdapter(this);

        // Default view is all meals
        setListAdapter(mainAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }

    private void updateMealList() {
        mainAdapter.loadObjects();
        setListAdapter(mainAdapter);
    }

    //private void showFavorites() {
        //favoritesAdapter.loadObjects();
        //setListAdapter(favoritesAdapter);
    //}

    private void newMeal() {
        Intent i = new Intent(this, postActivity.class);
        startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            // If a new post has been added, update
            // the list of posts
            updateMealList();
        }
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

            case R.id.action_refresh: {
                updateMealList();
                break;
            }
/*
            case R.id.action_favorites: {
                showFavorites();
                break;
            }
*/
/*            case R.id.action_new: {
                newMeal();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
*/
}
