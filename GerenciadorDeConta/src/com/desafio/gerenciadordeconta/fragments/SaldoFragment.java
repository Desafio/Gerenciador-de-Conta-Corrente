package com.desafio.gerenciadordeconta.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.desafio.gerenciadordeconta.models.ContaCorrente;
import com.example.gerenciadordeconta.R;

public class SaldoFragment extends Fragment {

	ContaCorrente contaCorrente = new ContaCorrente();

	public static SaldoFragment newInstance() {
		SaldoFragment fragment = new SaldoFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_saldo, container,
				false);
		String idConta = getActivity().getIntent().getStringExtra("idConta");

		contaCorrente = new Select().from(ContaCorrente.class)
				.where("id = ?", idConta).executeSingle();

		TextView text = (TextView) rootView.findViewById(R.id.valor_saldo);
		text.setText(contaCorrente.getSaldo() + " R$");
		return rootView;
	}

}
