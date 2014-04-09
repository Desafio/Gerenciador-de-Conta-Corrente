package com.desafio.gerenciadordeconta.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerenciadordeconta.R;

public class TransferenciaFragment extends Fragment {
	
	String conta = "";
	
	public static TransferenciaFragment newInstance() {
		TransferenciaFragment fragment = new TransferenciaFragment();
        return fragment;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transferencia, container, false);
        conta = getActivity().getIntent().getStringExtra("conta");
        
        TextView text = (TextView) rootView.findViewById(R.id.section_label);
        text.setText(conta + " - Transferência");
        return rootView;
	}
}