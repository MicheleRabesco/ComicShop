package it.unisa.model;

import java.time.LocalDate;

public class PaymentMethodBean {
  private int id;
  private int tipo;
  private String titolare;
  private LocalDate scadenza;
  private boolean predefinito;
  private String indirizzofatturazione;
  private Long numero;



  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRawTipo() {
    return tipo;
  }

  public String getTipo() {
    return (tipo == 0) ? "carta di credito" : "conto bancario";
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public String getTitolare() {
    return titolare;
  }

  public void setTitolare(String titolare) {
    this.titolare = titolare;
  }

  public boolean isPredefinito() {
    return predefinito;
  }

  public void setPredefinito(boolean predefinito) {
    this.predefinito = predefinito;
  }

  public String getIndirizzoFatturazione() {
    return indirizzofatturazione;
  }

  public void setIndirizzoFatturazione(String indirizzofatturazione) {
    this.indirizzofatturazione = indirizzofatturazione;
  }

  public Long getNumero() {
    return numero;
  }

  public void setNumero(Long numero) {
    this.numero = numero;
  }

  public LocalDate getScadenza() {
    return scadenza;
  }

  public void setScadenza(LocalDate scadenza) {
    this.scadenza = scadenza;
  }



}
