/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eec.mapeo;

import org.eec.math.Coordinate;
/**
 *
 * @author erick
 */
public class ButtonIdxToCoordsConverter {
    
    private int nCols;
    private int nRows;
    
    public ButtonIdxToCoordsConverter(int nRows, int nCols){
        this.nCols = nCols;
        this.nRows = nRows;
    } //-- fin de Constructor
    
    // método determineRowIdx(buttonIdx : int) : int
    // OK (salvo manejo de Negativos)
    private int determineRowIdx(int buttonIdx){
        // if buttonIdx < 0 :: throw ... CustomException1 ...
        if(buttonIdx < 0 || buttonIdx >= nRows * nCols){
            throw new IndexOutOfBoundsException(String.format("El índice [%d] está FUERA los LÍMITES permitidos.", buttonIdx));
        }
        int limFila, rowIdx = -1;
        for(int row = 0; row < nRows; row++){
            // System.out.println("row : " + row);
            limFila = (row + 1) * nCols;
            rowIdx = row;
            if(buttonIdx < limFila){
                break;
            }
        }
        return rowIdx;
    } //-- fin de método : determineRowIdx(buttonIdx) : int
    
    private int determineColIdx(int buttonIdx, int rowIdx){
        int colIdx = -1;
        colIdx = buttonIdx - rowIdx * nCols;
        return colIdx;
    } //-- fin del método determineColIdx(buttonIdx : int, rowIdx : int) : int
    
    public Coordinate linearIdxTo2DCoords(int buttonIdx){
        Coordinate coords;
        int rowIdx, colIdx;
        // 1.- Determinar el index de la Fila (Row)
        rowIdx = determineRowIdx(buttonIdx);
        // 2.- En base a rowIdx (y buttonIdx) determinar colIdx
        colIdx = determineColIdx(buttonIdx, rowIdx);
        // 3.- Construir obj 'coords' para contener (rowIdx, colIdx)
        coords = new Coordinate(rowIdx, colIdx);
        return coords;
    }
    
} //-- fin de clase : [ButtonIdxToCoordsConverter]
