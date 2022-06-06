package com.company;

public class Coord {
     int posX;
     int posY;

    public Coord(){
        this.posX=-1;
        this.posY=-1;
    }

    public Coord(int pX, int pY){
        this.posX=pX;
        this.posY=pY;
    }

    public int getY(){
        return this.posY;
    }

    public int getX(){
        return this.posX;
    }
}