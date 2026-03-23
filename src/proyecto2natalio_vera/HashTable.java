/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 *
 * @author natalio
 */
public class HashTable {
    NodoHash[] tabla;
    int capacidad;
    
    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new NodoHash[capacidad];
    }
}
