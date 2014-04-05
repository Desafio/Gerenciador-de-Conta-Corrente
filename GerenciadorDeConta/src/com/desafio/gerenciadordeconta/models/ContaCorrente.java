package com.desafio.gerenciadordeconta.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "correntista")
public class ContaCorrente extends Model {

	@Column(name = "conta")
	private String conta;

	@Column(name = "nome")
	private String nome;

	@Column(name = "senha")
	private String senha;

	@Column(name = "VIP")
	private Boolean VIP;

	@Column(name = "saldo")
	private float saldo;
	
	public ContaCorrente() {
	}
	
	public void setConta(String conta) {
		this.conta = conta;
	}
	
	public String getConta() {
		return conta;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setVIP(Boolean VIP) {
		this.VIP = VIP;
	}
	
	public Boolean getVIP() {
		return VIP;
	}
	
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public float getSaldo() {
		return saldo;
	}

}
