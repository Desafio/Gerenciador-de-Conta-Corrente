package com.desafio.gerenciadordeconta.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.desafio.gerenciadordeconta.adapters.ExtratoAdapter;
import com.desafio.gerenciadordeconta.models.ContaCorrente;
import com.desafio.gerenciadordeconta.models.Transferencia;
import com.example.gerenciadordeconta.R;

public class ExtratoFragment extends Fragment {

	ContaCorrente contaCorrente = new ContaCorrente();

	public static ExtratoFragment newInstance() {
		ExtratoFragment fragment = new ExtratoFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_extrato, container,
				false);
		ListView listView = (ListView) rootView.findViewById(R.id.listView);

		String idConta = getActivity().getIntent().getStringExtra("idConta");

		contaCorrente = new Select().from(ContaCorrente.class)
				.where("id = ?", idConta).executeSingle();

		List<Transferencia> extratoList = new Select()
				.from(Transferencia.class)
				.where("conta = ?", contaCorrente.getConta()).orderBy("data")
				.execute();

		ExtratoAdapter extratoAdapter = new ExtratoAdapter(getActivity(),
				extratoList);
		listView.setAdapter(extratoAdapter);

		return rootView;
	}

}
