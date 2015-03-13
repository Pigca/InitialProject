package hkust.comp4521.project.pigca;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by USER on 13/3/2015.
 */
public class Application extends android.app.Application {

    public void onCreate() {
        ParseObject.registerSubclass(Post.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "PpuOpKDNjNqJ5qVviZohBSZ0U98W1nVi6q5Lmf5H", "3O3uEgIykqI9LDcMda54N1FLZDh5Mim3tQlHIA2v");
    }

}
