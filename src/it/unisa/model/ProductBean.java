package it.unisa.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ProductBean implements Serializable {

  private static final long serialVersionUID = 1L;

  int id;
  String nome;
  float prezzo;
  int saldo;
  LocalDate dataDiUscita;
  float voto;
  String descrizione;
  float peso;
  int quantita;
  boolean visible;

  public ProductBean() {
    id = -1;
    nome = "";
    prezzo = -1;
    saldo = -1;
    dataDiUscita = LocalDate.MIN;
    voto = -1;
    descrizione = "";
    peso = -1;
    quantita = -1;
    visible = false;

  }


  public boolean isVisible() {
    return visible;
  }


  public void setVisible(boolean visible) {
    this.visible = visible;
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


  public float getPrezzo() {

    return (float) (Math.round((prezzo) * 100.0) / 100.0);
  }


  public void setPrezzo(float f) {
    this.prezzo = f;
  }


  public int getSaldo() {
    return saldo;
  }


  public void setSaldo(int saldo) {
    this.saldo = saldo;
  }


  public LocalDate getDataDiUscita() {
    return dataDiUscita;
  }


  public void setDataDiUscita(LocalDate dataDiUscita) {
    this.dataDiUscita = dataDiUscita;
  }


  public float getVoto() {
    return voto;
  }


  public void setVoto(float voto) {
    this.voto = voto;
  }


  public String getDescrizione() {
    return descrizione;
  }


  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }


  public float getPeso() {
    return peso;
  }


  public void setPeso(float peso) {
    this.peso = peso;
  }


  public int getQuantita() {
    return quantita;
  }


  public void setQuantita(int quantita) {
    this.quantita = quantita;
  }


  @Override
  public String toString() {
    return nome + " (" + id + "), " + prezzo + " " + quantita + ". " + descrizione + " " + voto + " " + peso + " " + saldo + " " + dataDiUscita;
  }

}


