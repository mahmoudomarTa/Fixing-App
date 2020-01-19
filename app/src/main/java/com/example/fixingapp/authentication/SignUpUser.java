package com.example.fixingapp.authentication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
public class SignUpUser extends Fragment {
    EditText edNameSUU , edPhoneNumberSUU,edPasswordSUU,edConfirmPasswordSUU;
    Button btnSignUpSUU;
    CountryCodePicker ccpu;
    public SignUpUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_user, container, false);
        edNameSUU = view.findViewById(R.id.edNameSUU);
        edPhoneNumberSUU = view.findViewById(R.id.edPhoneNumberSUU);
        edPasswordSUU = view.findViewById(R.id.edPasswordSUU);
        edConfirmPasswordSUU = view.findViewById(R.id.edConfirmPasswordSUU);
        btnSignUpSUU = view.findViewById(R.id.btnSignUpSUU);
        ccpu=view.findViewById(R.id.ccpU);

        btnSignUpSUU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edNameSUU.getText().toString().isEmpty()||
                        edPasswordSUU.getText().toString().isEmpty()||
                        edConfirmPasswordSUU.getText().toString().isEmpty()||
                        edPhoneNumberSUU.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.completeFields), Toast.LENGTH_SHORT).show();
                }else if(edPhoneNumberSUU.getText().toString().trim().length()<7){
                    Toast.makeText(getContext(), getString(R.string.enterARealPhone), Toast.LENGTH_SHORT).show();
                } else if (!edPasswordSUU.getText().toString().equals(edConfirmPasswordSUU.getText().toString())) {
                    Toast.makeText(getContext(), getString(R.string.samePassword), Toast.LENGTH_SHORT).show();
                }else {
                    LocationFragment locationFragment = new LocationFragment();
                    Bundle bundle = new Bundle();
                    String phoneNumber ="+"+ccpu.getSelectedCountryCode()+edPhoneNumberSUU.getText().toString();
                    bundle.putString(Constants.PHONE_NUMBER,phoneNumber);
                    bundle.putString(Constants.TYPE,Constants.TYPE_USER);
                    bundle.putString(Constants.NAME,edNameSUU.getText().toString());
                    bundle.putString(Constants.PASSWORD,edPasswordSUU.getText().toString());

                    locationFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.authenticationContainer,locationFragment).commit();
                }
            }
        });
        return view;
    }

}
