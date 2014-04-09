package com.desafio.gerenciadordeconta.fragments;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.desafio.gerenciadordeconta.activitys.HomeActivity;
import com.desafio.gerenciadordeconta.models.ContaCorrente;
import com.example.gerenciadordeconta.R;

public class LoginFragment extends Fragment {

	public LoginFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);

		final EditText loginConta = (EditText) rootView
				.findViewById(R.id.contaCorrente);
		final EditText loginSenha = (EditText) rootView
				.findViewById(R.id.senha);

		final Button button = (Button) rootView
				.findViewById(R.id.button_entrar);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String stringConta = loginConta.getText().toString();
				String stringSenha = loginSenha.getText().toString();

				List<ContaCorrente> list = new Select()
						.from(ContaCorrente.class)
						.where("conta = ? AND senha = ?", stringConta,
								stringSenha).execute();

				if (list == null || list.size() != 1) {
					Toast.makeText(getActivity(), "Conta ou Senha inválida.", Toast.LENGTH_LONG).show();
					return;
				}
				
				Intent intent = new Intent(getActivity(), HomeActivity.class);
				intent.putExtra("conta", list.get(0).getConta());
				getActivity().startActivity(intent);

			}
		});

		return rootView;
	}
}
