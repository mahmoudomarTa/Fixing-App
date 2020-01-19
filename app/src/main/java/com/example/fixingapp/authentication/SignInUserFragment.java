package com.example.fixingapp.authentication;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fixingapp.MainActivityUser;
import com.example.fixingapp.MainActivityWorker;
import com.example.fixingapp.R;
import com.example.fixingapp.firebase.Database;
import com.example.fixingapp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInUserFragment extends Fragment {
    EditText edPhoneNumberSIU,edPasswordSIU;
    ImageView imgShowHidePasswordSIU;
    Button btnSignInUser;
    TextView tvForgetPasswordSIU;
    Database database;
    public SignInUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in_user, container, false);
        database=new Database(getActivity());
        edPhoneNumberSIU = view.findViewById(R.id.edPhoneNumberSIU);
        edPasswordSIU = view.findViewById(R.id.edPasswordSIU);
        imgShowHidePasswordSIU = view.findViewById(R.id.imgShowHidePasswordSIU);
        btnSignInUser = view.findViewById(R.id.btnSignInUser);
        tvForgetPasswordSIU = view.findViewById(R.id.tvForgetPasswordSIU);


        btnSignInUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edPhoneNumberSIU.getText().toString().isEmpty()||
                    edPasswordSIU.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.completeFields), Toast.LENGTH_SHORT).show();
                }else {
                    database.signInUser(edPhoneNumberSIU.getText().toString(),edPasswordSIU.getText().toString());
                    database.setUserSignIn(new Database.UserSignIn() {
                        @Override
                        public void onUserSignInSuccess(String id) {
                            Intent intent = new Intent(getContext(), MainActivityUser.class);
                            intent.putExtra(Constants.User_UID,id);
                            startActivity(intent);
                        }

                        @Override
                        public void onUserSignInFailed(Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });

        return view;
    }

}
