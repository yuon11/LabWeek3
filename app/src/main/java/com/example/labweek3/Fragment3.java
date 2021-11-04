package com.example.labweek3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.regex.Pattern;

public class Fragment3 extends Fragment {
    private FragmentTracker ft;
    private View v;
    public static final String fragmentTitle="Details Info";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Toast.makeText(getContext(),"Visible",Toast.LENGTH_SHORT).show();
        ft.fragmentVisible(fragmentTitle);
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.third_fragment, container, false);

        Button b_next=v.findViewById(R.id.next_button);
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText detail1=v.findViewById(R.id.detail1);
                EditText detail2=v.findViewById(R.id.detail2);
                EditText detail3=v.findViewById(R.id.detail3);
                EditText detail4=v.findViewById(R.id.detail4);
                EditText detail5=v.findViewById(R.id.detail5);

                ft.saveDetail(detail1.getText().toString() + ", " + detail2.getText().toString()+ ", " + detail3.getText().toString()
                        + ", " + detail4.getText().toString()+ ", " + detail5.getText().toString());
                ft.finished();
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
        EditText detail1=v.findViewById(R.id.detail1);
        EditText detail2=v.findViewById(R.id.detail2);
        EditText detail3=v.findViewById(R.id.detail3);
        EditText detail4=v.findViewById(R.id.detail4);
        EditText detail5=v.findViewById(R.id.detail5);

        Log.d("Frag3 OnDetach", detail1.getText().toString() + ", " + detail2.getText().toString()+ ", " + detail3.getText().toString()
                + ", " + detail4.getText().toString()+ ", " + detail5.getText().toString());

        ft.saveDetail(detail1.getText().toString() + ", " + detail2.getText().toString()+ ", " + detail3.getText().toString()
                + ", " + detail4.getText().toString()+ ", " + detail5.getText().toString());
        v=null;
        //Toast.makeText(getContext(),uname.getText(),Toast.LENGTH_SHORT).show();
    }
}


