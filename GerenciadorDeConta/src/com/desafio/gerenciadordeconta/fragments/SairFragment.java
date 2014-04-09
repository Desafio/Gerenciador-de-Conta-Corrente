package com.desafio.gerenciadordeconta.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerenciadordeconta.R;

public class SairFragment extends Fragment {
	
	String conta = "";
	
	public static SairFragment newInstance() {
		SairFragment fragment = new SairFragment();
        return fragment;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sair, container, false);
        conta = getActivity().getIntent().getStringExtra("conta");
        
        TextView text = (TextView) rootView.findViewById(R.id.section_label);
        text.setText(conta + " - Sair");
        return rootView;
	}
}