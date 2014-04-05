package com.example.gerenciadordeconta.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.desafio.gerenciadordeconta.activitys.HomeActivity;
import com.example.gerenciadordeconta.R;

public class LoginFragment extends Fragment {
	
	public LoginFragment() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_login,
				container, false);
		
		
		final Button button = (Button) rootView.findViewById(R.id.button_entrar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(getActivity(), HomeActivity.class);
            	getActivity().startActivity(intent);
            }
        });
        
        return rootView;
	}
}
