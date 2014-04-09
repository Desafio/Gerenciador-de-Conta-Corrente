package com.desafio.gerenciadordeconta.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Transferencia")
public class Transferencia extends Model {

	@Column(name = "conta")
	private String conta;
	
	@Column(name = "data")
	private String data;

	@Column(name = "valor")
	private float valor;

	@Column(name = "descricao")
	private String descricao;
	
	public Transferencia(){
	}
	
	public void setData(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		this.data = sdf.format(data);
	}
	
	public String getData() {
		return data;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setConta(String conta) {
		this.conta = conta;
	}
}
