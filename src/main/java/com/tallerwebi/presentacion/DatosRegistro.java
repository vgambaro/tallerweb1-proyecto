package com.tallerwebi.presentacion;

public class DatosRegistro {

    private String email;
    private String password;
    private String passwordRepetida;

    public DatosRegistro(){

    }

    public DatosRegistro(String email, String password, String passwordRepetida) {
        this.email = email;
        this.password = password;
        this.passwordRepetida = passwordRepetida;
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

    public String getPasswordRepetida() {
        return passwordRepetida;
    }

    public void setPasswordRepetida(String passwordRepetida) {
        this.passwordRepetida = passwordRepetida;
    }
}
