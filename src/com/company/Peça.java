package com.company;

public class Peça {
     String identificador;
     boolean verificado;

    public Peça(){
        this.identificador="!";
        this.verificado=false;
    }

    public Peça(String identificador){
        this.identificador=identificador;
        this.verificado=false;
    }

    public void verificado(){
        this.verificado=true;
    }

    public String getIdentificador(){
        return this.identificador;
    }

    public boolean getVerificado(){
        return this.verificado;
    }

    public void setIdentificador(String identificador){
        this.identificador=identificador;
    }
}