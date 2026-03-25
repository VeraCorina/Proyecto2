/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 * Nodo generico pa la lista enlazada.
 * @author natalio
 */
class NodoLista<T> {
    T data;
    NodoLista<T> next;
    
    public NodoLista(T data) {
        this.data = data;
    }
}