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
    
    private int hash(String clave) {
        int h = 0;
        for (int i = 0; i < clave.length(); i++) {
            h = (31 * h + clave.charAt(i)) % capacidad;
        }
        return Math.abs(h);
    }
    
    public void insertar(String clave, RegistroImpresion registro) {
        int index = hash(clave);
        NodoHash nuevo = new NodoHash(clave, registro);
        if (tabla[index] == null) {
            tabla[index] = nuevo;
        } else {
            NodoHash actual = tabla[index];
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevo;
        }
    }
    
    public RegistroImpresion buscar(String clave, Documento doc) {
        int index = hash(clave);
        NodoHash actual = tabla[index];
        while (actual != null) {
            if (actual.clave.equals(clave) && actual.registro.documento.equals(doc)) {
                return actual.registro;
            }
            actual = actual.next;
        }
        return null;
    }
    
    public void eliminar(String clave, Documento doc) {
        int index = hash(clave);
        NodoHash actual = tabla[index];
        NodoHash previo = null;
        while (actual != null) {
            if (actual.clave.equals(clave) && actual.registro.documento.equals(doc)) {
                if (previo == null) {
                    tabla[index] = actual.next;
                } else {
                    previo.next = actual.next;
                }
                return;
            }
            previo = actual;
            actual = actual.next;
        }
    }
}
