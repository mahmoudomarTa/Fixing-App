package com.example.fixingapp.authentication;


import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fixingapp.MainActivityUser;
import com.example.fixingapp.MainActivityWorker;
import com.example.fixingapp.R;
import com.example.fixingapp.firebase.Authentication;
import com.example.fixingapp.firebase.Database;
import com.example.fixingapp.models.User;
import com.example.fixingapp.models.Worker;
import com.example.fixingapp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerificationCodeFragment extends Fragment {
    Authentication authentication;
    Button btnContinue;
    ProgressBar progressBar1;
    EditText edVerificationCode;
    private String phoneNumber;
    private  Worker worker;
    private User user;
    private Database database;

    public VerificationCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_verification_code, container, false);
        authentication=new Authentication(getActivity());
        database=new Database(getActivity());
        edVerificationCode=view.findViewById(R.id.edVerificationCode);
        btnContinue=view.findViewById(R.id.btnContinue);
        progressBar1=view.findViewById(R.id.progressBar1);

        if (getArguments()!=null){
            if (getArguments().getString(Constants.TYPE).equals(Constants.TYPE_WORKER)) {
                 worker = (Worker) getArguments().get(Constants.TYPE_WORKER);
                if (worker != null) {
                    phoneNumber = worker.getPhoneNumber();
                }
            }else if (getArguments().getString(Constants.TYPE).equals(Constants.TYPE_USER)){
                 user = (User) getArguments().get(Constants.TYPE_USER);
                if (user != null) {
                    phoneNumber=user.getPhoneNumber();
                }
            }
            sendVerificationCode(phoneNumber);
        }else {
            Toast.makeText(getContext(), getString(R.string.errorReTryAgane), Toast.LENGTH_SHORT).show();
        }


        return view;
    }
    private void sendVerificationCode(String phoneNumber){
        authentication.sendVerificationCode(phoneNumber);
        authentication.setOnSignUpListener(new Authentication.OnSignUpListener() {
            @Override
            public void onCodeSent(String code) {
                if (code!=null) {
                    edVerificationCode.setText(code);
                }
                progressBar1.setVisibility(View.GONE);
                btnContinue.setClickable(true);
                edVerificationCode.setClickable(true);
                edVerificationCode.setFocusable(true);
                btnContinue.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.btn_solid));
            }

            @Override
            public void onCodeDosesNotSent(Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSignUpSuccess() {
                if (worker!=null) {
                    database.addWorker(worker);
                    database.setWorkerSignUp(new Database.WorkerSignUp() {
                        @Override
                        public void onWorkerSignUpSuccess(String id) {
                            Intent intent = new Intent(getContext(),MainActivityWorker.class);
                            intent.putExtra(Constants.WORKER_UID,id);
                            startActivity(intent);
                        }

                        @Override
                        public void onWorkerSignUpFailed(Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if (user!=null){
                    database.addUser(user);
                    database.setUserSignUp(new Database.UserSignUp() {
                        @Override
                        public void onSignUpSuccess(String id) {
                            Intent intent = new Intent(getContext(), MainActivityUser.class);
                            intent.putExtra(Constants.User_UID,id);
                            startActivity(intent);
                        }

                        @Override
                        public void onSignUpFailed(Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onSignUpFailed(Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                getActivity().recreate();
            }
        });
    }
}