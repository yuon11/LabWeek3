package com.example.labweek3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    private FragmentTracker ft;
    private View v;
    public static final String fragmentTitle="Personal Info";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Toast.makeText(getContext(),"Visible",Toast.LENGTH_SHORT).show();
        ft.fragmentVisible(fragmentTitle);

        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.first_fragment, container, false);
        Button b_next=v.findViewById(R.id.next_button);
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.goNext();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            ft=(FragmentTracker) context;
        }
        catch (ClassCastException ex)
        {
            throw new ClassCastException(context.toString()+" must implement FragmentTracker");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EditText uname=v.findViewById(R.id.u_name);
        EditText lname=v.findViewById(R.id.u_lastname);
        EditText phone=v.findViewById(R.id.phone);
        EditText email=v.findViewById(R.id.email);

        ft.saveNameAndLastName(uname.getText().toString(),lname.getText().toString());

        v=null;
        //Toast.makeText(getContext(),uname.getText(),Toast.LENGTH_SHORT).show();
    }
}

