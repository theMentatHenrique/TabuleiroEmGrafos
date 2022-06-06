package com.company;

import java.util.ArrayList;

public class Player extends Peça {
    Coord pos;
    ArrayList<Porta> portas;
    ArrayList<String> chaves;
    int contador;

    public Player() {
        this.identificador = "!";
        this.portas = new ArrayList<Porta>();
        this.chaves = new ArrayList<String>();
        this.contador = 0;
        this.pos = new Coord();
    }

    public Player(String identificador, int x, int y){
        this.identificador=identificador;
        this.portas=new ArrayList<Porta>();
        this.chaves=new ArrayList<String>();
        this.contador=0;
        this.pos=new Coord(x,y);
    }

    public void addChave(String chave) {
        chaves.add(String.valueOf(chave));
    }

    public void addPorta(String id, Coord c) {
        Porta portaAux = new Porta(id, c.posX, c.posY);
        portas.add(portaAux);
    }

    public void incrementaContador() {
        this.contador++;
    }

    public Porta getProximaPorta() {
        for (int i = 0; i < this.chaves.size(); i++) {
            for (int j = 0; j < this.portas.size(); j++) {
                if (this.chaves.get(i) == String.valueOf(this.portas.get(j).getIdentificador())) {
                    Porta aux = portas.get(j);
                    portas.remove(j);
                    return aux;
                }
            }
        }
        System.out.println("sem portas para ir");
        return null;
    }

    public boolean verificaPortasDisponiveis() {
        if (this.portas.isEmpty()) {
            return false;
        }
        return true;
    }

    public int getContador() {
        return contador;
    }
}