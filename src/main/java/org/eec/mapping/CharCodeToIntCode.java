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
public class CharCodeToIntCode {
    
    private HashMap<Character, Integer> innerHashMap;
    
    public CharCodeToIntCode(){
        this.innerHashMap = new HashMap<>();
    } //-- fin de Constructor
    
    
     public void setKVPair(Character key, Integer value){
        innerHashMap.put(key, value);
    }
    
    public int charCodeToIntCode(Character charCode){
        Integer intAsRef;
        int intValue;
        intAsRef = innerHashMap.get(charCode); //-- can return NULL if NO such key exists in the HashMap
        // returns NULL if the given KEY is NOT Registered in the HashMap
        // indicar -1 : casilla 'VACÍA'
        intValue = intAsRef == null ? -1 : intAsRef; //-- implicit int Unboxing .intValue()
        return intValue;
    }
    
} //-- fin de Clase [CharCodeToIntCode]
