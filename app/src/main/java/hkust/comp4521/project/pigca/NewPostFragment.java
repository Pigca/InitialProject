package hkust.comp4521.project.pigca;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewPostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewPostFragment extends Fragment {

    private ImageButton photoButton;
    private Button saveButton;
    private Button cancelButton;
    private TextView postName;
    //private Spinner postRating;
    private ParseImageView postPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle SavedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_post, parent, false);

        postName = ((EditText) v.findViewById(R.id.post_name));

        // The mealRating spinner lets people assign favorites of meals they've
        // eaten.
        // Meals with 4 or 5 ratings will appear in the Favorites view.
/*        mealRating = ((Spinner) v.findViewById(R.id.rating_spinner));
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
                .createFromResource(getActivity(), R.array.ratings_array,
                        android.R.layout.simple_spinner_dropdown_item);
        mealRating.setAdapter(spinnerAdapter);
*/
        photoButton = ((ImageButton) v.findViewById(R.id.photo_button));
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(postName.getWindowToken(), 0);
                startCamera();
            }
        });

        saveButton = ((Button) v.findViewById(R.id.save_button));
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Post post = ((postActivity) getActivity()).getCurrentPost();

                // When the user clicks "Save," upload the meal to Parse
                // Add data to the meal object:
                post.setTitle(postName.getText().toString());

                // Associate the meal with the current user
                post.setAuthor(ParseUser.getCurrentUser());

                // Add the rating
                //meal.setRating(mealRating.getSelectedItem().toString());

                // If the user added a photo, that data will be
                // added in the CameraFragment

                // Save the meal and return
                post.saveInBackground(new SaveCallback() {

                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            getActivity().setResult(Activity.RESULT_OK);
                            getActivity().finish();
                        } else {
                            Toast.makeText(
                                    getActivity().getApplicationContext(),
                                    "Error saving: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        });

        cancelButton = ((Button) v.findViewById(R.id.cancel_button));
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        });

        // Until the user has taken a photo, hide the preview
        postPreview = (ParseImageView) v.findViewById(R.id.post_preview_image);
        postPreview.setVisibility(View.INVISIBLE);

        return v;
    }

    /*
     * All data entry about a Meal object is managed from the NewMealActivity.
     * When the user wants to add a photo, we'll start up a custom
     * CameraFragment that will let them take the photo and save it to the Meal
     * object owned by the NewMealActivity. Create a new CameraFragment, swap
     * the contents of the fragmentContainer (see activity_new_meal.xml), then
     * add the NewMealFragment to the back stack so we can return to it when the
     * camera is finished.
     */
    public void startCamera() {
        Fragment cameraFragment = new CameraFragment();
        FragmentTransaction transaction = getActivity().getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.fragmentContainer, cameraFragment);
        transaction.addToBackStack("NewMealFragment");
        transaction.commit();
    }

    /*
     * On resume, check and see if a meal photo has been set from the
     * CameraFragment. If it has, load the image in this fragment and make the
     * preview image visible.
     */
    @Override
    public void onResume() {
        super.onResume();
        ParseFile photoFile = ((postActivity) getActivity())
                .getCurrentPost().getPhotoFile();
        if (photoFile != null) {
            postPreview.setParseFile(photoFile);
            postPreview.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    postPreview.setVisibility(View.VISIBLE);
                }
            });
        }
    }



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewPostFragment newInstance(String param1, String param2) {
        NewPostFragment fragment = new NewPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NewPostFragment() {
        // Required empty public constructor
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
