/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eec.utils;

import org.eec.math.Coordinate;
/**
 *
 * @author erick
 * @param <T>
 */
public class ArrayUtils<T> {
        
    public T getValueAtCoords(Coordinate coords, T[][] bidimensionalArray){
        return bidimensionalArray[coords.getX()][coords.getY()];
    } //-- fin de método: getValueAtCoords(coords : Coordinate, bidimensionalArray : T[][]) : T
    
    public void setValueAtCoords(Coordinate coords, T value, T[][] bidimensionalArray){
        bidimensionalArray[coords.getX()][coords.getY()] = value;
    } //-- fin de método: setValueAtCoords
    
    public void fillWithValue(T value, T[][] bidimensionalArray){
        for(int i = 0; i < bidimensionalArray.length; i++){
            for(int j = 0; j < bidimensionalArray[i].length; j++){
                bidimensionalArray[i][j] = value;
            }
        }
    } //-- fin de método: fillWithValue(value : T, bidimensionalArray : T[][]) : void
    
    public String toBeautyString(T[][] bidimensionalArray){
        StringBuilder sb = new StringBuilder();
        sb.append("---------Inicia contenido de Array <").append(bidimensionalArray.getClass().getComponentType());
        sb.append(">-----------------\n");
        sb.append("{");
        for(int i = 0; i < bidimensionalArray.length; i++){
            for(int j = 0; j < bidimensionalArray[i].length; j++){
                sb.append(bidimensionalArray[i][j].toString());
                if(j != bidimensionalArray[i].length - 1){
                    sb.append(", ");
                }
            }
            
            if(i != bidimensionalArray.length - 1){
                sb.append("\n");
            }
        }
        sb.append("}");
        sb.append("\n----------Fin de contenido del Array---------------");
        return sb.toString();
    } //-- fin de método: toBeautyString(bidimensionalArray : T[][]) : String
    
} //-- fin de Clase : ArrayUtils
