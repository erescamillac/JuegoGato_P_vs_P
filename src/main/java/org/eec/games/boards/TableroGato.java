/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eec.games.boards;

import org.eec.utils.ArrayUtils;
import org.eec.mapping.IntCodeToPlayerCharMapper;
import org.eec.math.Coordinate;
/**
 *
 * @author erick
 */
public class TableroGato {
    
    private Integer[][] innerArray;
    private ArrayUtils arrayUtils;
    private IntCodeToPlayerCharMapper intCodeToPlayerCM;
    
    public enum EstadoTablero{
        VICTORIA_FILA, VICTORIA_COLUMA, VICTORIA_DIAG, VICTORIA_CONTRADIAG, ONGOING_GAME, TIE_GAME
    }
    
    private enum TipoDiagonal{
        DIAG_PRINC, DIAG_INV
    }
    
    public TableroGato(){
        innerArray = new Integer[3][3];
        arrayUtils = new ArrayUtils<Integer>();
        // Estado inicial del Tablero de Gato: 'VACÍO'
        // todas las posiciones establecidas con el valor -1 (-1 : 'Casilla VACÍA' / sin utilizar)
        arrayUtils.fillWithValue(-1, innerArray);
        intCodeToPlayerCM = new IntCodeToPlayerCharMapper();
        intCodeToPlayerCM.setKVPair(1, 'X');
        intCodeToPlayerCM.setKVPair(0, 'O');
    } //-- fin de constructor
    
    public TicTacToeBoardFullState checkBoardState(){
        TicTacToeBoardFullState boardFullState;
        boolean filaContieneCV, columnaContieneCV;
        int valorUnicoFila, valorUnicoCol, valorUnicoDiagonal;
        
        System.out.println(".call to : ${checkBoardState()}");
        // 1.- [PRIMERO] Validar las condiciones de Victoria de FILA.
        System.out.println("1.- FASE: Verificación de Condiciones de Victoria por FILA.");
        // si la Fila n-ésima contiene al menos 1 Celda VACÍA
        // entonces, la Fila NO puede establecer una Condición de VICTORIA
        for(int i = 0; i < innerArray.length; i++){
            filaContieneCV = rowContainsEmptyCell(i);
            if( !filaContieneCV ){
                // si la Fila i-ésima NO contiene celdas Vacías
                // entonces tiene sentido validar condición de Victoria...
                valorUnicoFila = getUniqueRowValue(i);
                
                if(valorUnicoFila != -1){
                    // Entonces se encontró condición de VICTORIA en la fila i-ésima
                    // con el valor único de Fila [int / valorUnicoFila]
                    // 1 : 'X' | 0 : 'O'
                    // Mapper : mapping intCodeToPlayerChar
                    
                    return new TicTacToeBoardFullState(EstadoTablero.VICTORIA_FILA, i, intCodeToPlayerCM.intCodeToPlayerChar(valorUnicoFila));
                }
            } //-- fin : IF (la fila NO contiene Celdas Vacías)
        } //-- fin : FOR (recorrido de las Filas)
        
        // 2.- [SEGUNDO] Validar las condiciones de Victoria de COLUMNA.
        for(int j = 0; j < innerArray[0].length; j++){
            columnaContieneCV = colContanisEmptyCell(j);
            if( !columnaContieneCV ){
                // si la Columna j-ésima NO contiene Celdas VACÍAS
                // entonces si vale la pena Validar la Condición de Victoria en la COLUMNA.
                valorUnicoCol = getUniqueColValue(j);
                if( valorUnicoCol != -1 ){
                    return new TicTacToeBoardFullState(EstadoTablero.VICTORIA_COLUMA, j, intCodeToPlayerCM.intCodeToPlayerChar(valorUnicoCol) );
                }
            }
        }
        
        // 3.- [TERCERO] Validar la Condición de Victoria de la DIAGONAL_PRINCIPAL
        // 3(a) Determinar si la Diagonal Principal contiene AL MENOS una Celda Vacía
        // 3(b) [Si la DIAG_PRINC] NO contiene Celdas Vacías -->> determinar el valor ÚNICO
        // presente en la Diag_Princip (0 / 1)
        if( !diagonalContainsEmptyCell(TipoDiagonal.DIAG_PRINC) ){
            // Si la [DIAG_PRINC] NO contiene Celdas Vacías
            // entonces, tiene sentido Validar Condición de Victoria sobre la Diagonal_PRINC...
            valorUnicoDiagonal = getUniqueDiagonalValue( TipoDiagonal.DIAG_PRINC );
            
            if(valorUnicoDiagonal != -1){
                // Se determina VICTORIA en DIAGONAL_PRINCIPAL
                return new TicTacToeBoardFullState(EstadoTablero.VICTORIA_DIAG, -2, intCodeToPlayerCM.intCodeToPlayerChar(valorUnicoDiagonal) );
            }
        }
        
        // 4.- [CUARTO] Validar la Condición de Victoria de la DIAGONAL_INVERSA
        if( !diagonalContainsEmptyCell(TipoDiagonal.DIAG_INV) ){
            // Si la [DIAG_INVERSA] NO contiene Celdas Vacías
            // entonces, tiene sentido Validar Condición de Victoria sobre la Diagonal_INVERSA...
            valorUnicoDiagonal = getUniqueDiagonalValue( TipoDiagonal.DIAG_INV );
            if( valorUnicoDiagonal != -1 ){
                // Se determina VICTORIA en DIAGONAL_INVERSA
                return new TicTacToeBoardFullState(EstadoTablero.VICTORIA_CONTRADIAG, -2, intCodeToPlayerCM.intCodeToPlayerChar(valorUnicoDiagonal) );
            }
        }
        
        // 5.- Al final de las VALIDACIONES de Condiciones de Victoria...
        // SI AÚN NO SE HA ALCANZADO alguno de los posibles Estados de VICTORIA
        // sólo Existen 2 posibilidades::
        
        // 5(b) : El JUEGO SIGUE EN PROGESO [ONGOING_GAME]
        // -- utilizar como criterio la Condición de 
        // -- "SI" el Tablero contiene por lo menos 1 Celda VACÍA...
        // Si el Tablero está LLENO (ya no hay casillas libres)
        if( isFull() ){
            // se trata de un empate
            boardFullState = new TicTacToeBoardFullState(EstadoTablero.TIE_GAME, -3, intCodeToPlayerCM.intCodeToPlayerChar(-3) );
        }else{
            // ONGOING_GAME : El juego sigue en Marcha...
            boardFullState = new TicTacToeBoardFullState(EstadoTablero.ONGOING_GAME, -3, intCodeToPlayerCM.intCodeToPlayerChar(-3) );
        }
        // 5(a) : Empate
        
        return boardFullState;
    } //-- fin de método : checkBoardState() : TicTacToeBoardFullState
    
    public boolean isFull(){
        boolean result = true;
        for(int i = 0; i < innerArray.length; i++){
            for(int j = 0; j < innerArray[i].length; j++){
                if( innerArray[i][j].equals(-1) ){
                    result = false;
                    break;
                }
            }
        }
        return result;
    } //-- fin de método isFull
    
    private boolean diagonalContainsEmptyCell(TipoDiagonal tipoDiagonal){
        Coordinate initialCoords;
        boolean result = false;
        int j;
        
        initialCoords = tipoDiagonal == TipoDiagonal.DIAG_PRINC ? new Coordinate(0, 0) : new Coordinate(0, 2);
        
        j = initialCoords.getY();
        // recorrido de Diagonales::
        for(int i = initialCoords.getX(); i < innerArray.length; i++){
                // manejo de fila 'i'-ésima es el mismo para AMBOS Recorridos
                // tanto DIAG_PRINC como DIAG_INV
                if( innerArray[i][j].equals(-1) ){
                    // se encontró Casilla VACÍA
                    result = true;
                    break;
                }
                // DIAG_PRINC : columna ++
                if( tipoDiagonal == TipoDiagonal.DIAG_PRINC ){
                    j++;
                }else{
                    j--;
                }
                // DIAG_INV : columna --
                
            } //-- fin de FOR: Recorrido fila 'i'-ésima
        
        return result;
    } //-- fin del método: diagonalContainsEmptyCell() : boolean
    
    private int getUniqueDiagonalValue(TipoDiagonal tipoDiagonal){
        // -- Primero.- Determinar las Coords Iniciales.
        Coordinate initialCoords;
        int uniqueVal, refVal, j;
        boolean diagonalUnivaluada = true;
        
        initialCoords = tipoDiagonal == TipoDiagonal.DIAG_PRINC ? new Coordinate(0, 0) : new Coordinate(0, 2);
        j = initialCoords.getY();
        //-- Segundo tomar como valor de REF, el 'primer' Valor disponible en la Diagonal
        // (initialCoords)
        refVal = innerArray[initialCoords.getX()][initialCoords.getY()];
        
        for(int i = initialCoords.getX(); i < innerArray.length; i++){
            if( refVal != innerArray[i][j] ){
                // Si el valor [refVal] NO COINCIDE con algunas de las posiciones de la Diagonal
                diagonalUnivaluada = false;
                uniqueVal = -1;
                break;
            }
            
            if(tipoDiagonal == TipoDiagonal.DIAG_PRINC){
                // columna++
                j++;
            }else{
                // columna--
                j--;
            }
        } //-- fin FOR: Recorrido de las Filas 'i'-ésimas
        
        if(diagonalUnivaluada){
            uniqueVal = refVal;
        }else{
            uniqueVal = -1;
        }
        
        return uniqueVal;
    } //-- fin de método : getUniqueDiagonalValue(tipoDiagonal : TipoDiagonal) : int
    
    private boolean rowContainsEmptyCell(int rowIdx){
        boolean result = false;
        for(int j = 0; j < innerArray[rowIdx].length; j++){
            if( innerArray[rowIdx][j].equals(-1) ){
                // se detectó Casilla VACÍA...
                result = true;
                break;
            }
        }
        
        return result;
    } //-- fin de método : rowContainsEmptyCell(rowIdx : int)
    
    private boolean colContanisEmptyCell(int colIdx){
        boolean result = false;
        for(int rowIdx = 0; rowIdx < this.innerArray.length; rowIdx++){
            if(this.innerArray[rowIdx][colIdx].equals(-1)){
                // se detecto casilla VACÍA
                result = true;
                break;
            }
        }
        return result;
    } //-- fin del método : colContanisEmptyCell(colIdx : int) : boolean
    
    private int getUniqueRowValue(int rowIdx){
        int uniqueValue, refValue;
        boolean filaUnivaluada = true;
        
        // -- Tomar como valor de REF, la primera celda de la Fila
        refValue = innerArray[rowIdx][0];
        
        if( !rowContainsEmptyCell(rowIdx) ){
            for(int j = 1; j < innerArray[rowIdx].length; j++){
                if( refValue != innerArray[rowIdx][j] ){
                    filaUnivaluada = false;
                    break;
                }
            }
        }else{
            uniqueValue = -1;
            filaUnivaluada = false;
        }
        
        if( filaUnivaluada ){
            uniqueValue = refValue;
        }else{
            uniqueValue = -1;
        }
        
        return uniqueValue;
    } //-- fin de método : getUniqueRowValue(rowIdx : int) : int
    
    private int getUniqueColValue(int colIdx){
        int uniqueValue, refValue;
        boolean columnaUnivaluada = true;
        //-- TOMAR como valor de REF : el primer valor disponible
        // en la Columna
        refValue = innerArray[0][colIdx];
        
        if( !colContanisEmptyCell(colIdx) ){
            // se procede a determinar el valor Único de Columna
            for(int i= 1; i < innerArray.length; i++){
                if(refValue != innerArray[i][colIdx]){
                    columnaUnivaluada = false;
                    break;
                }
            }
        }else{
            // la Columna contiene AL MENOS 1 Celda VACÍA
            // si la columna tiene al menos 1 Celda VACÍA
            // NO tiene sentido determinar el valor univaluado de la Columna
            // se indica 'flag' -1 de forma directa.
            uniqueValue = -1;
            columnaUnivaluada = false;
        }
        
        if(columnaUnivaluada){
            uniqueValue = refValue;
        }else{
            uniqueValue = -1;
        }
        
        return uniqueValue;
    } //-- fin de método : getUniqueColValue()
    
    // ... SOLO con propósitos de Testing ...
    public void setInnerArray(Integer[][] innerArray){
        this.innerArray = innerArray;
    } //-- fin de método: 
            
} //-- fin de clase: [TableroGato]
