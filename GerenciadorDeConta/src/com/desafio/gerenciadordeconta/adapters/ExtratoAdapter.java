package com.desafio.gerenciadordeconta.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desafio.gerenciadordeconta.models.Transferencia;
import com.example.gerenciadordeconta.R;

public class ExtratoAdapter extends ArrayAdapter<Transferencia> {

	private List<Transferencia> extrato;
	private LayoutInflater inflater;
	
	public ExtratoAdapter(Context context, List<Transferencia> extrato) {
		super(context, 0, extrato);
		this.inflater = LayoutInflater.from(context);
		this.extrato = extrato;
	}
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView != null) {
			holder = (ViewHolder) convertView.getTag();
		} else {
			convertView = inflater.inflate(R.layout.adapter_extrato,
					parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		
		
		final Transferencia transferencia = getItem(position);

		holder.setTransferencia(transferencia);

		return convertView;
		
		
	}
	
	@Override
	public int getCount() {
		return this.extrato.size();
	}

	@Override
	public Transferencia getItem(int position) {
		return this.extrato.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	class ViewHolder {
		TextView data;
		TextView descricao;
		TextView valor;

		LinearLayout newsImageCaptionOrCredits;

		ViewHolder(View view) {
			this.data = (TextView) view
					.findViewById(R.id.extrato_adapter_data);
			this.descricao = (TextView) view
					.findViewById(R.id.extrato_adapter_descricao);
			this.valor = (TextView) view
					.findViewById(R.id.extrato_adapter_valor);
		}

		public void setTransferencia(Transferencia transferencia) {
			this.data.setText(transferencia.getData());
			this.descricao.setText(transferencia.getDescricao());
			this.valor.setText(transferencia.getValor());
		}
	}

}
