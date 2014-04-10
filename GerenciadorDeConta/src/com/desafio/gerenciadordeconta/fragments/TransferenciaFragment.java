package com.desafio.gerenciadordeconta.fragments;

import java.util.Calendar;
import java.util.List;

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

public class TransferenciaFragment extends Fragment {

	ContaCorrente contaCorrente = new ContaCorrente();

	public static TransferenciaFragment newInstance() {
		TransferenciaFragment fragment = new TransferenciaFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_transferencia,
				container, false);
		String idConta = getActivity().getIntent().getStringExtra("idConta");

		contaCorrente = new Select().from(ContaCorrente.class)
				.where("id = ?", idConta).executeSingle();

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

				if (stringConta.equalsIgnoreCase(contaCorrente.getConta())
						|| list == null || list.size() != 1) {
					Toast.makeText(getActivity(), "Conta inv‡lida.",
							Toast.LENGTH_LONG).show();
					return;
				}

				if (stringValor == null || stringValor.length() == 0
						|| Integer.parseInt(stringValor) < 0) {
					Toast.makeText(getActivity(), "Valor inv‡lido.",
							Toast.LENGTH_LONG).show();
					return;
				}
				
				double valorDebitado = contaCorrente.getVIP() ? Double.valueOf(stringValor) * 1.08 : Double.valueOf(stringValor) + 8.0;

				if (contaCorrente.getSaldo() < valorDebitado) {
					Toast.makeText(
							getActivity(),
							"Seu saldo Ž insuficiente para esta transfer�ncia.",
							Toast.LENGTH_LONG).show();
					return;
				}
				
				if (!contaCorrente.getVIP() && Float.valueOf(stringValor) > 1000.0F) {
					Toast.makeText(
							getActivity(),
							"Voc� ultrapassou o limite para transfer�ncia.",
							Toast.LENGTH_LONG).show();
					return;
				}

				ContaCorrente contaCorrenteDeposito = list.get(0);
				
				contaCorrente.setSaldo(contaCorrente.getSaldo()
						- Float.valueOf(Double.toString(valorDebitado)));
				contaCorrente.save();
				contaCorrenteDeposito.setSaldo(contaCorrenteDeposito.getSaldo()
						+ Float.valueOf(stringValor));
				contaCorrenteDeposito.save();
				
				
				

				Calendar currentDate = Calendar.getInstance();
				Transferencia transferenciaSaque = new Transferencia();
				transferenciaSaque.setConta(contaCorrente.getConta());
				transferenciaSaque.setData(currentDate.getTime());
				transferenciaSaque.setDescricao("Trans. para "
						+ contaCorrenteDeposito.getConta());
				transferenciaSaque.setValor(-Float.valueOf(Double.toString(valorDebitado)));
				transferenciaSaque.save();

				Transferencia transferenciaDeposito = new Transferencia();
				transferenciaDeposito.setConta(contaCorrenteDeposito.getConta());
				transferenciaDeposito.setData(currentDate.getTime());
				transferenciaDeposito.setDescricao("Trans. de "
						+ contaCorrente.getConta());
				transferenciaDeposito.setValor(Float.valueOf(stringValor));
				transferenciaDeposito.save();

				Toast.makeText(getActivity(),
						"Transfer�ncia realizado com sucesso.",
						Toast.LENGTH_LONG).show();
			}
		});
		return rootView;
	}
}