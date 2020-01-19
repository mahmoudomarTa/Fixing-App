package com.example.fixingapp.authentication;


import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.fixingapp.R;
import com.example.fixingapp.firebase.Authentication;
import com.example.fixingapp.firebase.Database;
import com.example.fixingapp.models.User;
import com.example.fixingapp.models.Worker;
import com.example.fixingapp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    AutoCompleteTextView actvLocation;
    Button btnStart;
    Authentication authentication ;
    Database database ;
    String phoneNumber;

    private String name , profession,password,location,type;


    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        authentication = new Authentication(getActivity());
        database = new Database(getActivity());
        if (getArguments()!=null){
            name=getArguments().getString(Constants.NAME);
            if (getArguments().getString(Constants.PROFESSION)!=null){
            profession=getArguments().getString(Constants.PROFESSION);
            }
            password=getArguments().getString(Constants.PASSWORD);
            type=getArguments().getString(Constants.TYPE);
            phoneNumber=getArguments().getString(Constants.PHONE_NUMBER);

        }



        actvLocation = view.findViewById(R.id.actvLocation);
        btnStart = view.findViewById(R.id.btnStart);

        ImageSpan imageSpan = new ImageSpan(getContext(), R.drawable.ic_search);
        SpannableString spannableString = new SpannableString(getString(R.string.searchLocation));
        int start = 0;
        int end = 1;
        int flag = 0;
        spannableString.setSpan(imageSpan, start, end, flag);

        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.arrayLocation));
        actvLocation.setAdapter(adapter);
        actvLocation.setHint(spannableString);

        actvLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnStart.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.btn_solid));
                btnStart.setClickable(true);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!actvLocation.getText().toString().isEmpty()) {
                    if (type.equals(Constants.TYPE_WORKER)) {
                        location=actvLocation.getText().toString();
                        if (name != null && password != null && profession != null && phoneNumber != null) {
                            Worker worker = new Worker(Authentication.UID,location, password, phoneNumber, name, profession, type, Authentication.UID);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(Constants.TYPE_WORKER, worker);
                            bundle.putString(Constants.TYPE,Constants.TYPE_WORKER);
                            VerificationCodeFragment verificationCodeFragment = new VerificationCodeFragment();
                            verificationCodeFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.authenticationContainer, verificationCodeFragment)
                                    .commit();
                       }


                    } else if (type.equals(Constants.TYPE_USER)) {
                        location=actvLocation.getText().toString();
                        if (name != null && password != null &&  phoneNumber != null) {
                            User user = new User(Authentication.UID,location, password, phoneNumber, name, type, Authentication.UID);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(Constants.TYPE_USER, user);
                            bundle.putString(Constants.TYPE,Constants.TYPE_USER);
                            VerificationCodeFragment verificationCodeFragment = new VerificationCodeFragment();
                            verificationCodeFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.authenticationContainer, verificationCodeFragment)
                                    .commit();
                        }
                    }
                }

                 }
        });

        return view;
    }


}
