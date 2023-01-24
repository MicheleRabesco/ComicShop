package it.unisa.model;

import java.time.LocalDate;

public class UserBean {
  private String username;
  private String nome;
  private String cognome;
  private String email;
  private String password;
  private String numTel;
  private String paeseResidenza;
  private LocalDate dataNascita;
  private boolean valid;
  private boolean admin;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;

  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNumTel() {
    return numTel;
  }

  public void setNumTel(String numTel) {
    this.numTel = numTel;
  }

  public String getPaeseResidenza() {
    return paeseResidenza;
  }

  public void setPaeseResidenza(String paeseResidenza) {
    this.paeseResidenza = paeseResidenza;
  }

  public LocalDate getDataNascita() {
    return dataNascita;
  }

  public void setDataNascita(LocalDate dataNascita) {
    this.dataNascita = dataNascita;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public boolean IsAdmin() {

    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;

  }


}

