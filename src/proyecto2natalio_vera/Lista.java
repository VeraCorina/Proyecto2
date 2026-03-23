/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 *
 * @author natalio
 */
class Lista<T> {
    NodoLista<T> head;
    int size;
    
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
    
    public T get(int index) {
        NodoLista<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
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
    
    public int size() {
        return size;
    }
}
