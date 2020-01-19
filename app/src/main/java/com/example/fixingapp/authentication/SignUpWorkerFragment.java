package com.example.fixingapp.authentication;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fixingapp.R;
import com.example.fixingapp.utils.Constants;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpWorkerFragment extends Fragment {
    EditText edNameSUW,edProfessionSUW,edPasswordSUW,edConfirmPasswordSUW;
    Button btnSignUpSUW;
    CountryCodePicker ccp;
    AppCompatEditText edPhoneNumberSUW;
    public SignUpWorkerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_worker, container, false);

        ccp = (CountryCodePicker) view.findViewById(R.id.ccp);
        edPhoneNumberSUW = view.findViewById(R.id.edPhoneNumberSUW);


        ccp.registerPhoneNumberTextView(edPhoneNumberSUW);

        edNameSUW =view.findViewById(R.id.edNameSUW);
        edProfessionSUW = view.findViewById(R.id.edProfessionSUW);
         edPasswordSUW = view.findViewById(R.id.edPasswordSUW);
        edConfirmPasswordSUW =view.findViewById(R.id.edConfirmPasswordSUW);
        btnSignUpSUW = view.findViewById(R.id.btnSignUpSUW);



        btnSignUpSUW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edNameSUW.getText().toString().isEmpty()||
                        edProfessionSUW.getText().toString().isEmpty()||
                        edPasswordSUW.getText().toString().isEmpty()||
                        edConfirmPasswordSUW.getText().toString().isEmpty()||
                        edPhoneNumberSUW.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.completeFields), Toast.LENGTH_SHORT).show();
                }else if(edPhoneNumberSUW.getText().toString().trim().length()<7){
                    Toast.makeText(getContext(), getString(R.string.enterARealPhone), Toast.LENGTH_SHORT).show();
                } else if (!edPasswordSUW.getText().toString().equals(edConfirmPasswordSUW.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.samePassword), Toast.LENGTH_SHORT).show();
                }else {
                LocationFragment locationFragment = new LocationFragment();
                Bundle bundle = new Bundle();
                String phoneNumber ="+"+ccp.getSelectedCountryCode()+edPhoneNumberSUW.getText().toString();
                bundle.putString(Constants.PHONE_NUMBER,phoneNumber);
                bundle.putString(Constants.TYPE,Constants.TYPE_WORKER);
                bundle.putString(Constants.NAME,edNameSUW.getText().toString());
                bundle.putString(Constants.PROFESSION,edProfessionSUW.getText().toString());
                bundle.putString(Constants.PASSWORD,edPasswordSUW.getText().toString());

                locationFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.authenticationContainer,locationFragment).commit();
            }
            }
        });
        return view;
    }

}
