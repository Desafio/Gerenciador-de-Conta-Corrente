package com.desafio.gerenciadordeconta.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        contaCorrente = (ContaCorrente) getActivity().getIntent().getSerializableExtra("conta");
        
        TextView text = (TextView) rootView.findViewById(R.id.section_label);
        text.setText(contaCorrente.getConta() + " - Sair");
        return rootView;
	}
}