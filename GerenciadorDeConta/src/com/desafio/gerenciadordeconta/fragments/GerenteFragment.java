package com.desafio.gerenciadordeconta.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.activeandroid.query.Select;
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
		View rootView = inflater.inflate(R.layout.fragment_gerente, container,
				false);
		String idConta = getActivity().getIntent().getStringExtra("idConta");

		contaCorrente = new Select().from(ContaCorrente.class)
				.where("id = ?", idConta).executeSingle();

		final Button button = (Button) rootView.findViewById(R.id.button_sair);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (contaCorrente.getSaldo() < 50.0F) {
					Toast.makeText(getActivity(),
							"Seu saldo é insuficiente para esta ação.",
							Toast.LENGTH_LONG).show();
					return;
				}

				Transferencia transferencia = new Transferencia(contaCorrente
						.getConta(), "Visita Gerente", -50.00F);
				transferencia.save();
				
				Toast.makeText(getActivity(), "Agendamento feito com sucesso.",
						Toast.LENGTH_LONG).show();
			}
		});
		return rootView;
	}
}