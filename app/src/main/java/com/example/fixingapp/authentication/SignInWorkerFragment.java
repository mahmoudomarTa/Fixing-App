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

import com.example.fixingapp.MainActivityWorker;
import com.example.fixingapp.R;
import com.example.fixingapp.firebase.Database;
import com.example.fixingapp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInWorkerFragment extends Fragment {

    EditText edPhoneNumberSIW,edPasswordSIW;
    ImageView imgShowHidePasswordSIW;
    TextView tvForgetPasswordSIW;
    Button btnSignInWorker;
    Database database;
    public SignInWorkerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in_worker, container, false);
        database=new Database(getActivity());
        edPhoneNumberSIW = view.findViewById(R.id.edPhoneNumberSIW);
        edPasswordSIW = view.findViewById(R.id.edPasswordSIW);
        imgShowHidePasswordSIW = view.findViewById(R.id.imgShowHidePasswordSIW);
        tvForgetPasswordSIW = view.findViewById(R.id.tvForgetPasswordSIW);
        btnSignInWorker=view.findViewById(R.id.btnSignInWorker);

        btnSignInWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edPhoneNumberSIW.getText().toString().isEmpty()||edPasswordSIW.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.completeFields), Toast.LENGTH_SHORT).show();
                }else {
                    String phone=edPhoneNumberSIW.getText().toString();
                    String password = edPasswordSIW.getText().toString();
                        database.signInWorker(phone,password);
                        database.setWorkerSignIn(new Database.WorkerSignIn() {
                            @Override
                            public void onWorkerSignInSuccess(String id) {
                                Intent intent = new Intent(getContext(), MainActivityWorker.class);
                                intent.putExtra(Constants.WORKER_UID,id);
                                startActivity(intent);
                            }
                            @Override
                            public void onWorkerSignInFaild(Exception e) {
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                }
            }
        });

        return view;
    }

}
