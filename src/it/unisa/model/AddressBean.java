package it.unisa.model;

public class AddressBean {
  private String via;
  private int numeroCivico;
  private int cap;
  private String citta;
  private String provincia;


  public String getVia() {
    return via;
  }

  public void setVia(String via) {
    this.via = via;
  }

  public int getNumeroCivico() {
    return numeroCivico;
  }

  public void setNumeroCivico(int numeroCivico) {
    this.numeroCivico = numeroCivico;
  }

  public int getCap() {
    return cap;
  }

  public void setCap(int cap) {
    this.cap = cap;
  }

  public String getCitta() {
    return citta;
  }

  public void setCitta(String citta) {
    this.citta = citta;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }


}
