package com.example.mayankjain.myapplication.sample_login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mayankjain.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;


public class login_firebase extends Fragment {
    View global_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_firebase, container, false);
        global_view = view;
        Button btn_login = view.findViewById(R.id.login_button);
        final Spinner spinner = (Spinner)view.findViewById(R.id.spinner_select_method);
        final String[] spinner_data  = new String[]{"By firebase","By php","By Mongo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,spinner_data);
        spinner.setAdapter(adapter);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) global_view.findViewById(R.id.user_login_id)).getText().toString();
                String password = ((EditText) global_view.findViewById(R.id.user_login_password)).getText().toString();
                String spinner_choice = spinner.getSelectedItem().toString();
                if(username.isEmpty() || password.isEmpty() || spinner_choice.isEmpty()){
                    Toast.makeText(getContext(),"Fields cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(spinner_choice.equalsIgnoreCase(spinner_data[0])){
                        do_by_fireBase(username,password);
                    }
                    else if(spinner_choice.equalsIgnoreCase(spinner_data[1])){
                        do_by_php(username,password);
                    }
                    else if(spinner_choice.equalsIgnoreCase(spinner_data[2])){
                        do_by_mongoDB(username,password);
                    }
                }
            }
        });
        return view;
    }

    void do_by_fireBase(String username,String password){
        /*username : temp@gmail.com
        password : password*/
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Logging In Please Wait");
        progressDialog.show();
        auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                 Toast.makeText(getContext(),"Login successfully",Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();
                }
                else {

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e)
                    {   // checking invalid email id or password
                        Toast.makeText(getContext(),"Invalid username or password",Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toast.makeText(getContext(),"Login failed",Toast.LENGTH_SHORT).show();
                        Log.e("Excptn Login","expn is "+e);
                    }
                }
            }
        });
    }
    void do_by_php(String username,String password){
        Snackbar.make(global_view,"Under development",Snackbar.LENGTH_SHORT).show();
    }
    void do_by_mongoDB(String username,String password){
        Snackbar.make(global_view,"Under development",Snackbar.LENGTH_SHORT).show();
    }


}
