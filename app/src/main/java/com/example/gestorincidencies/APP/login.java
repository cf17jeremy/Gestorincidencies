package com.example.gestorincidencies.APP;

public class login {
    private String usuari = "Admin";
    private String contrasenya = "12345";

    public void setuser(String usuari) {
        this.usuari = usuari;
    }

    public String getuser() {
        return usuari;
    }

    public void setpasswd(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getpasswd() {
        return contrasenya;
    }
}
