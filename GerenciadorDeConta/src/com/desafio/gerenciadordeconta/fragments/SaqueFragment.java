package com.desafio.gerenciadordeconta.fragments;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.desafio.gerenciadordeconta.models.ContaCorrente;
import com.desafio.gerenciadordeconta.models.Transferencia;
import com.example.gerenciadordeconta.R;

public class SaqueFragment extends Fragment {

	ContaCorrente contaCorrente = new ContaCorrente();

	public static SaqueFragment newInstance() {
		SaqueFragment fragment = new SaqueFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_saque, container,
				false);
		String idConta = getActivity().getIntent().getStringExtra("idConta");

		contaCorrente = new Select().from(ContaCorrente.class)
				.where("id = ?", idConta).executeSingle();

		final EditText loginSenha = (EditText) rootView
				.findViewById(R.id.saque_valor);

		final Button button = (Button) rootView.findViewById(R.id.button_sacar);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String valor = loginSenha.getText().toString();

				if (valor.length() == 0 || Float.valueOf(valor) <= 0) {
					Toast.makeText(getActivity(), "Valor inv‡lido",
							Toast.LENGTH_LONG).show();
					return;
				}

				if (contaCorrente.getSaldo() < Float.valueOf(valor)) {
					Toast.makeText(getActivity(),
							"Seu saldo Ž insuficiente para este saque.",
							Toast.LENGTH_LONG).show();
					return;
				}

				contaCorrente.setSaldo(contaCorrente.getSaldo()
						- Float.valueOf(valor));
				contaCorrente.save();

				Calendar currentDate = Calendar.getInstance();
				Transferencia transferencia = new Transferencia();
				transferencia.setConta(contaCorrente.getConta());
				transferencia.setData(currentDate.getTime());
				transferencia.setDescricao("Saque");
				transferencia.setValor(-Float.valueOf(valor));
				transferencia.save();

				Toast.makeText(getActivity(), "Saque realizado com sucesso.",
						Toast.LENGTH_LONG).show();
			}
		});
		return rootView;
	}
}
