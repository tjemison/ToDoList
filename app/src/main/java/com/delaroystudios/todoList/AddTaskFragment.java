package com.delaroystudios.todoList;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.delaroystudios.todoList.data.TaskContract;

public class AddTaskFragment extends Fragment {

    // Declare a member variable to keep track of a task's selected mPriority
    private int mPriority;
    private OnFragmentInteractionListener mListener;
    private Button addButton;
    private  RadioGroup priorityRadioGroup;
    public AddTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //Create view variable so we can access elements by finding them via resource id.
        View rootView = inflater.inflate(R.layout.fragment_add_task, container, false);

        //Create another view variable to hold the value of rootView, so that our view can be accessed
        //when we assign listeners to our buttons.
        final View rootViewCopy = rootView;


        //Setting a listener to our RadioGroup. Our priority will be set to the proper value
        //whenever the selected radio button changes.
        priorityRadioGroup = (RadioGroup)rootView.findViewById(R.id.priorityRadioGroup);
        priorityRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onPrioritySelected(rootViewCopy);
            }
        });

        addButton = (Button) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIfPriorityIsSelected(rootViewCopy)) {
                    onClickAddTask(rootViewCopy);
                }
                else {
                    Toast.makeText(getActivity(), "Please assign a priority to the task before continuing",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void onClickAddTask(View view) {
        // Not yet implemented
        // Check if EditText is empty, if not retrieve input and store it in a ContentValues object
        // If the EditText input is empty -> don't create an entry
        String input = ((EditText) view.findViewById(R.id.editTextTaskDescription)).getText().toString();
        if (input.length() == 0) {
            return;
        }

        // Insert new task data via a ContentResolver
        // Create new empty ContentValues object
        ContentValues contentValues = new ContentValues();
        // Put the task description and selected mPriority into the ContentValues
        contentValues.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, input);
        contentValues.put(TaskContract.TaskEntry.COLUMN_PRIORITY, mPriority);
        // Insert the content values via a ContentResolver
        Uri uri = getActivity().getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI, contentValues);

        // Display the URI that's returned with a Toast
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if(uri != null) {
            Toast.makeText(getActivity().getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }

        // Finish activity (this returns back to MainActivity)
        this.getActivity().finish();

    }


    /**
     * onPrioritySelected is called whenever a priority button is clicked.
     * It changes the value of mPriority based on the selected button.
     */
    public void onPrioritySelected(View view) {
        if (((RadioButton) view.findViewById(R.id.radButton1)).isChecked()) {
            mPriority = 1;
        } else if (((RadioButton) view.findViewById(R.id.radButton2)).isChecked()) {
            mPriority = 2;
        } else if (((RadioButton) view.findViewById(R.id.radButton3)).isChecked()) {
            mPriority = 3;
        }
    }

    public boolean checkIfPriorityIsSelected(View view) {
        if (((RadioButton) view.findViewById(R.id.radButton1)).isChecked()
                || ((RadioButton) view.findViewById(R.id.radButton2)).isChecked()
                || ((RadioButton) view.findViewById(R.id.radButton3)).isChecked()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void onBackPressed() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }
}
