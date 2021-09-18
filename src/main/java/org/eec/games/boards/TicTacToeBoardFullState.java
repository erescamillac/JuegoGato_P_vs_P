/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eec.games.boards;

/**
 *
 * @author erick
 */
public class TicTacToeBoardFullState {
    
    private TableroGato.EstadoTablero estadoTablero;
    private int rowOrColIdx;
    private char playerChar;

    public TicTacToeBoardFullState(TableroGato.EstadoTablero estadoTablero, int rowOrColIdx, char playerChar) {
        this.estadoTablero = estadoTablero;
        this.rowOrColIdx = rowOrColIdx;
        this.playerChar = playerChar;
    }

    public TableroGato.EstadoTablero getEstadoTablero() {
        return estadoTablero;
    }

    public void setEstadoTablero(TableroGato.EstadoTablero estadoTablero) {
        this.estadoTablero = estadoTablero;
    }

    public int getRowOrColIdx() {
        return rowOrColIdx;
    }

    public void setRowOrColIdx(int rowOrColIdx) {
        this.rowOrColIdx = rowOrColIdx;
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public void setPlayerChar(char playerChar) {
        this.playerChar = playerChar;
    }
 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch(estadoTablero){
            case VICTORIA_FILA:
                sb.append("Se detectó Condición de VICTORIA, en la FILA [");
                sb.append(rowOrColIdx + 1).append("] y el Jugador GANADOR FUE: '");
                sb.append(playerChar).append("'");
                break;
            case VICTORIA_COLUMA:
                sb.append("Se detectó Condición de VICTORIA, en la COLUMNA [");
                sb.append(rowOrColIdx + 1).append("] y el Jugador GANADOR FUE: '");
                sb.append(playerChar).append("'");
                break;
            case VICTORIA_DIAG:
                sb.append("Se detectó Condición de VICTORIA, en la Diagonal Principal y");
                sb.append(" el Jugador GANADOR FUE: '").append(playerChar).append("'");
                break;
            case VICTORIA_CONTRADIAG:
                sb.append("Se detectó Condición de VICTORIA, en la Contradiagonal y");
                sb.append(" el Jugador GANADOR FUE: '").append(playerChar).append("'");
                break;
            case ONGOING_GAME:
                sb.append("ONGOING_GAME");
                break;
            case TIE_GAME:
                sb.append("El juego ha finalizado en EMPATE.");
                break;
        }
        return sb.toString();
        // return "TicTacToeBoardFullState{" + "estadoTablero=" + estadoTablero + ", rowOrColIdx=" + rowOrColIdx + ", playerChar=" + playerChar + '}';
    }
    
} //-- fin de clase : [TicTacToeBoardFullState]
