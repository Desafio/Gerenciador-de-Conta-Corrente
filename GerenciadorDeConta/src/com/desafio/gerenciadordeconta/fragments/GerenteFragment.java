package com.desafio.gerenciadordeconta.fragments;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.desafio.gerenciadordeconta.models.ContaCorrente;
import com.desafio.gerenciadordeconta.models.Transferencia;
import com.example.gerenciadordeconta.R;

public class GerenteFragment extends Fragment {
	
	ContaCorrente contaCorrente = new ContaCorrente();
	
	public static GerenteFragment newInstance() {
		GerenteFragment fragment = new GerenteFragment();
        return fragment;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gerente, container, false);
        contaCorrente = (ContaCorrente) getActivity().getIntent().getSerializableExtra("conta");
        
        final Button button = (Button) rootView
				.findViewById(R.id.button_sair);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				if(contaCorrente.getSaldo() < 50.0F) {
					Toast.makeText(getActivity(), "Seu saldo Ž insuficiente para esta a‹o.", Toast.LENGTH_LONG).show();
					return;
				}
				
				Calendar currentDate = Calendar.getInstance();
				Transferencia transferencia = new Transferencia();
				transferencia.setConta(contaCorrente.getConta());
				transferencia.setData(currentDate.getTime());
				transferencia.setDescricao("Visita Gerente");
				transferencia.setValor(-50.00F);
				transferencia.save();
				Toast.makeText(getActivity(), "Agendamento feito com sucesso.", Toast.LENGTH_LONG).show();
			}
		});
        return rootView;
	}
}