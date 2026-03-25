/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;
/**
 * Estructura de tabla hash pa guardar los documentos y encontrarlos rapido.
 * Implementada con encadenamiento por si hay coliciones.
 * @author natalio
 */
public class HashTable {
    NodoHash[] tabla;
    int capacidad;
    
    /**
     * Crea la tabla con un tamaño fijo que le pases.
     * @param capacidad El tamaño de la tabla (mejor si es primo).
     */
    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new NodoHash[capacidad];
    }
    
    /**
     * Funcion hash pa saber en que posicion guardar la clave.
     * @param clave El nombre del usuario por lo jeneral.
     * @return El indice donde toca meter el dato.
     */
    private int hash(String clave) {
        int h = 0;
        for (int i = 0; i < clave.length(); i++) {
            h = (31 * h + clave.charAt(i)) % capacidad;
        }
        return Math.abs(h);
    }
    
    /**
     * Mete un registro nuevo en la tabla.
     * @param clave Nombre del usuario.
     * @param registro El registro de impresion con toda la info.
     */
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
    
    /**
     * Busca un documento en la tabla usando el usuario y el doc.
     * @param clave El nombre del dueño.
     * @param doc El documento que estamos buscando.
     * @return El registro encontrado o null si no existe.
     */
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
    
    /**
     * Borra un registro de la tabla hash.
     * @param clave El usuario.
     * @param doc El documento a sacar.
     */
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