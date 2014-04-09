package com.desafio.gerenciadordeconta.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "correntista")
public class ContaCorrente extends Model {

	@Column(name = "conta")
	private String conta;

	@Column(name = "senha")
	private String senha;

	@Column(name = "VIP")
	private Boolean VIP;
	
	public ContaCorrente() {
	}
	
	public ContaCorrente(String conta) {
		 ContaCorrente contaCorrente = (ContaCorrente) new Select().from(ContaCorrente.class).where("conta = ?",conta).execute().get(0);
		 
		 this.conta = contaCorrente.conta;
		 this.senha = contaCorrente.senha;
		 this.VIP = contaCorrente.VIP;
	}
	
	public void setConta(String conta) {
		this.conta = conta;
	}
	
	public String getConta() {
		return conta;
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

}
