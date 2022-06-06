package com.company;

public class Porta extends Peça {
    Coord posP;

    public Porta() {
        this.posP = new Coord();
    }

    public Porta(String id, int x, int y) {
        this.posP = new Coord(x, y);
        this.identificador=id;
    }

    public Coord getCoordP() {
        return this.posP;
    }
}