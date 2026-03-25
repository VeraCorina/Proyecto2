/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 * Clase de lista enlasada simple pa guardar cualquier cosa genérica.
 * @param <T> El tipo de dato que va a guardar la lista.
 * @author natalio
 */
class Lista<T> {
    NodoLista<T> head;
    int size;
    
    /**
     * Agrega un elemento al final de la lista.
     * @param data El objeto que queremos meter.
     */
    public void add(T data) {
        NodoLista<T> nuevo = new NodoLista<>(data);
        if (head == null) {
            head = nuevo;
        } else {
            NodoLista<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = nuevo;
        }
        size++;
    }
    
    /**
     * Busca un elemento por su posicion en la lista.
     * @param index Numero del indice (empiesa en 0).
     * @return El objeto en esa posicion.
     */
    public T get(int index) {
        NodoLista<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * Quita un elemento de la lista comparando el objeto.
     * @param data El objeto que se quiere borrar.
     */
    public void remove(T data) {
        if (head == null) return;
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return;
        }
        NodoLista<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }
    
    /**
     * @return Cuantos elementos hay en la lista ahora mismo.
     */
    public int size() {
        return size;
    }
}