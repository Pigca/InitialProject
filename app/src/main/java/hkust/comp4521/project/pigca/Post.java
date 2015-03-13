package hkust.comp4521.project.pigca;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by USER on 13/3/2015.
 */
@ParseClassName("Post")
public class Post extends ParseObject{

    public Post(){
        // Default constructor
    }

    public String getTitle(){
        return getString("title");
    }

    public void setTitle(String title){
        put("title", title);
    }

    public ParseUser getAuthor(){
        return getParseUser("author");
    }

    public void setAuthor(ParseUser user){
        put("author", user);
    }

    public ParseFile getPhotoFile(){
        return getParseFile("photo");
    }

    public void setPhotoFile(ParseFile file){
        put("photo", file);
    }
}
