/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eec.mapping;

import javax.swing.JButton;
import org.eec.mapeo.ButtonIdxToCoordsConverter;
import org.eec.math.Coordinate;
import org.eec.utils.ArrayUtils;

/**
 *
 * @author erick
 */
public class JButtonToIntegerArray {
    
    private CharCodeToIntCode charCodeToInt;
    
    public JButtonToIntegerArray(){
        charCodeToInt = new CharCodeToIntCode();
        charCodeToInt.setKVPair('X', 1);
        charCodeToInt.setKVPair('O', 0);
    }
    
    public Integer[][] toIntegerArray(JButton[] arregloBotones){
        Integer[][] intArray;
        ButtonIdxToCoordsConverter buttonIdxToCoords;
        Coordinate coords;
        ArrayUtils<Integer> arrayUtils;
                
        int idxTmp = -1;
        
        intArray = new Integer[3][3];
        buttonIdxToCoords = new ButtonIdxToCoordsConverter(intArray.length, intArray[0].length);
        arrayUtils = new ArrayUtils<>();
        
        //-- primero INICIALIZAR 'Tablero' (Integer[][]) con TODAS la casillas Vacías (-1)
        arrayUtils.fillWithValue( -1, intArray );
        
        for( JButton boton : arregloBotones ){
            idxTmp++;
            // System.out.println(  idxTmp + ".- " + boton.getText() );
            coords = buttonIdxToCoords.linearIdxTo2DCoords( idxTmp );
            arrayUtils.setValueAtCoords( coords, this.charCodeToInt.charCodeToIntCode( boton.getText().charAt(0) ), intArray );
        } //-- fin: FOR, recorrido de Botones
        
        return intArray;
    } //-- fin de método : toIntegerArray(arregloBotones : JButton[]) : Integer[][]
    
} //-- fin de clase
