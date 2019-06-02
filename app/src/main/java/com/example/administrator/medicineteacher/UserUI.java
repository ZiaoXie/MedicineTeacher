package com.example.administrator.medicineteacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.medicineteacher.Bian.BianBing;
import com.example.administrator.medicineteacher.Bian.BianZheng1;
import com.example.administrator.medicineteacher.Bian.BianZheng2;
import com.example.administrator.medicineteacher.toolClass.BottomNavigationViewHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link UserUI#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserUI extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    TextView tieba;

    TextView search_et_input;

    ImageView bianbing,bianzheng1,bianzheng2;

    public UserUI() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserUI.
     */
    // TODO: Rename and change types and number of parameters
    public static UserUI newInstance(String param1, String param2) {
        UserUI fragment = new UserUI();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_user_ui, container, false);

        search_et_input=(TextView)view.findViewById(R.id.search_et_input);
        search_et_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ChangJianSearch.class));
            }
        });

        tieba=(TextView)view.findViewById(R.id.tieba);
        tieba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),PatienceUse.class));
            }
        });



        bianbing=(ImageView)view.findViewById(R.id.bianbing);
        bianbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),BianBing.class));
            }
        });

        bianzheng1=(ImageView)view.findViewById(R.id.bian_zheng1);
        bianzheng1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),BianZheng1.class));
            }
        });

        bianzheng2=(ImageView)view.findViewById(R.id.bian_zheng2);
        bianzheng2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),BianZheng2.class));
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
