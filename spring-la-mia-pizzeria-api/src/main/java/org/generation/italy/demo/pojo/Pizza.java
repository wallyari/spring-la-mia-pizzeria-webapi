package org.generation.italy.demo.pojo;

 import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
 import jakarta.persistence.Table;

 @Entity
 @Table
 public class Pizza {

 	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private int id;

 	@Column
 	private String nome;

 	@Column
 	private String descrizione;

 	public Pizza() { }
 	public Pizza(String nome, String descrizione) {
 		setNome(nome);
 		setDescrizione(descrizione);
 	}

 	public int getId() {
 		return id;
 	}

 	public void setId(int id) {
 		this.id = id;
 	}

 	public String getNome() {
 		return nome;
 	}

 	public void setNome(String nome) {
 		this.nome = nome;
 	}

 	public String getDescrizione() {
 		return descrizione;
 	}

 	public void setDescrizione(String descrizione) {
 		this.descrizione = descrizione;
 	}

 	@Override
 	public String toString() {
 		return getId() + " - " + getNome()+ " - "+ getDescrizione();
 	}


 }