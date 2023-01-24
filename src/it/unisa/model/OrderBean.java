package it.unisa.model;

import java.time.LocalDate;

public class OrderBean {
  private long id;
  private long numOrdine;
  private float costoTotale;
  private String indirizzospedizione;
  private LocalDate dataSpedizione;
  private String metododipagamento;
  private String Username;
  private LocalDate dataOrdine;

  public long getId() {
    return id;
  }


  public void setId(long l) {
    this.id = l;
  }


  public long getNumOrdine() {
    return numOrdine;
  }

  public void setNumOrdine(long numOrdine) {
    this.numOrdine = numOrdine;
  }

  public float getCostoTotale() {
    return costoTotale;
  }

  public void setCostoTotale(float costoTotale) {
    this.costoTotale = costoTotale;
  }

  public String getIndirizzoSpedizione() {
    return indirizzospedizione;
  }

  public void setIndirizzoSpedizione(String indirizzospedizione) {
    this.indirizzospedizione = indirizzospedizione;
  }

  public LocalDate getDataSpedizione() {
    return dataSpedizione;
  }

  public void setDataSpedizione(LocalDate dataSpedizione) {
    this.dataSpedizione = dataSpedizione;
  }

  public String getMetodoDiPagamento() {
    return metododipagamento;
  }

  public void setMetodoDiPagamento(String metododipagamento) {
    this.metododipagamento = metododipagamento;
  }

  public String getUsername() {
    return Username;
  }

  public void setUsername(String username) {
    Username = username;
  }

  public LocalDate getDataOrdine() {
    return dataOrdine;
  }

  public void setDataOrdine(LocalDate dataOrdine) {
    this.dataOrdine = dataOrdine;
  }

}
