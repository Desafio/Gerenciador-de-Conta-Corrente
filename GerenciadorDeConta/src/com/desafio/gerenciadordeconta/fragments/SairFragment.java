package com.desafio.gerenciadordeconta.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.desafio.gerenciadordeconta.activitys.LoginActivity;
import com.desafio.gerenciadordeconta.models.ContaCorrente;
import com.example.gerenciadordeconta.R;

public class SairFragment extends Fragment {
	
	ContaCorrente contaCorrente = new ContaCorrente();
	
	public static SairFragment newInstance() {
		SairFragment fragment = new SairFragment();
        return fragment;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sair, container, false);
        
        final Button button = (Button) rootView
				.findViewById(R.id.button_sair);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				getActivity().startActivity(intent);
			}
		});
        return rootView;
	}
}