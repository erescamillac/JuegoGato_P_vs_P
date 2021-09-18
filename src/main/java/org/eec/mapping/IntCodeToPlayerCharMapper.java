/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eec.mapping;

import java.util.HashMap;
/**
 *
 * @author erick
 */
public class IntCodeToPlayerCharMapper {
    
    private HashMap<Integer, Character> innerHashMap;
    
    public IntCodeToPlayerCharMapper(){
        innerHashMap = new HashMap<>();
    }
    
    public void setKVPair(Integer key, Character value){
        innerHashMap.put(key, value);
    }
    
    public char intCodeToPlayerChar(Integer intCode){
        Character charAsRef;
        char c;
        charAsRef = innerHashMap.get(intCode); //-- can return NULL if NO such key exists in the HashMap
        // returns NULL if the given KEY is NOT Registered in the HashMap
        c = charAsRef == null ? '-' : charAsRef; //-- implicit char Unboxing .charValue()
        return c;
    }
} //-- fin de clase : [IntCodeToPlayerCharMapper]
