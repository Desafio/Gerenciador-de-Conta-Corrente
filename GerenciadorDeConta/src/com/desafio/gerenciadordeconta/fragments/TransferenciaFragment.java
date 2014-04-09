package com.desafio.gerenciadordeconta.fragments;

import java.util.Calendar;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.desafio.gerenciadordeconta.activitys.HomeActivity;
import com.desafio.gerenciadordeconta.models.ContaCorrente;
import com.desafio.gerenciadordeconta.models.Transferencia;
import com.example.gerenciadordeconta.R;

public class TransferenciaFragment extends Fragment {
	
	ContaCorrente contaCorrente = new ContaCorrente();
	
	public static TransferenciaFragment newInstance() {
		TransferenciaFragment fragment = new TransferenciaFragment();
        return fragment;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transferencia, container, false);
        contaCorrente = (ContaCorrente) getActivity().getIntent().getSerializableExtra("conta");
        
        final EditText valorET = (EditText) rootView
				.findViewById(R.id.transferencia_valor);
		final EditText contaET = (EditText) rootView
				.findViewById(R.id.transferencia_conta);

		final Button button = (Button) rootView
				.findViewById(R.id.button_transferir);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String stringValor = valorET.getText().toString();
				String stringConta = contaET.getText().toString();

				List<ContaCorrente> list = new Select()
				.from(ContaCorrente.class)
				.where("conta = ?", stringConta).execute();
				
				if(stringConta.equalsIgnoreCase(contaCorrente.getConta()) || list == null || list.size() != 1) {
					Toast.makeText(getActivity(), "Conta inválida.", Toast.LENGTH_LONG).show();
					return;
				}
				
				if(stringValor == null || stringValor.length() == 0 || Integer.parseInt(stringValor) < 0) {
					Toast.makeText(getActivity(), "Valor inválido.", Toast.LENGTH_LONG).show();
					return;
				}
				
				Calendar currentDate = Calendar.getInstance();
				Transferencia transferenciaSaque = new Transferencia();
				transferenciaSaque.setConta(contaCorrente.getConta());
				transferenciaSaque.setData(currentDate.getTime());
				transferenciaSaque.setDescricao("Trans. para " + stringConta);
				transferenciaSaque.setValor(-Float.valueOf(stringValor));
				transferenciaSaque.save();
				
				Transferencia transferenciaDeposito = new Transferencia();
				transferenciaDeposito.setConta(stringConta);
				transferenciaDeposito.setData(currentDate.getTime());
				transferenciaDeposito.setDescricao("Trans. de " + contaCorrente.getConta());
				transferenciaDeposito.setValor(Float.valueOf(stringValor));
				transferenciaDeposito.save();
				
				Toast.makeText(getActivity(), "Transferência realizado com sucesso.", Toast.LENGTH_LONG).show();
			}
		});
        return rootView;
	}
}