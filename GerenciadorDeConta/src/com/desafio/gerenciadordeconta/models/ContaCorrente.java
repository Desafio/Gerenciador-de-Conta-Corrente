package com.desafio.gerenciadordeconta.models;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "correntista")
public class ContaCorrente extends Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8063702355344341598L;

	@Column(name = "conta")
	private String conta;

	@Column(name = "senha")
	private String senha;

	@Column(name = "VIP")
	private Boolean VIP;

	public ContaCorrente() {
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
	
	public String getSaldo() {
		List<Transferencia> extratoList = new Select().from(Transferencia.class)
					.where("conta = ?", this.getConta()).execute();
		float saldo = 0.0F;
		
		for(Transferencia transferencia : extratoList) {
			saldo = saldo + transferencia.getValor();
		}
		
		return Float.toString(saldo);
	}
}
