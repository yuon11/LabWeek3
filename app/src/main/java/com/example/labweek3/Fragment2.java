package com.example.labweek3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private FragmentTracker ft;
    private View v;
    public static final String fragmentTitle="Address Info";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Toast.makeText(getContext(),"Visible",Toast.LENGTH_SHORT).show();
        ft.fragmentVisible(fragmentTitle);
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.second_fragment, container, false);

        Button b_next=v.findViewById(R.id.next_button);
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.goNext();
            }
        });

        Button b_back=v.findViewById(R.id.back_button);
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.goBack();
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
        EditText city=v.findViewById(R.id.city);
        EditText state=v.findViewById(R.id.state);
        EditText city_zip=v.findViewById(R.id.city_zip);

        ft.saveCityAndZip(city.getText().toString(), city_zip.getText().toString());
        v=null;
        //Toast.makeText(getContext(),uname.getText(),Toast.LENGTH_SHORT).show();
    }
}

