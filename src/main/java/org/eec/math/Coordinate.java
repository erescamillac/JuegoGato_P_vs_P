/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eec.math;

/**
 *
 * @author erick
 */
public class Coordinate {
    private int x;
    private int y;
    
    public Coordinate(int xVal, int yVal){
        this.x = xVal;
        this.y = yVal;
    } //-- fin de Constructor

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Coordinate(").append( x ).append(", ").append( y ).append(")");
        return sb.toString();
    }
    
} //-- fin Clase: ['Coordinate']
