/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 * Clase que representa un documento que se quiere imprimir. 
 * Guarda cosas como el nombre y cuantas paginas tiene.
 * @author natalio
 */
class Documento {
    String nombre;
    int tamano;
    String tipo;
    boolean enCola;
    
    /**
     * Constructor pa crear un documento nuevo desde cero.
     * @param nombre El nombre del archivo con su extencion.
     * @param tamano El numero de paguinas o peso del archivo.
     * @param tipo Si es PDF, Word o esa vaina.
     */
    public Documento(String nombre, int tamano, String tipo) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.tipo = tipo;
        this.enCola = false;
    }
    
    /**
     * Pasa la info del documento a un string pa mostrarlo en consola o interfaz.
     * @return El texto ya formateado con los datos.
     */
    @Override
    public String toString() {
        return "DOCUMENTO [" + nombre + "]\n" +
               "  > Tipo: " + tipo + "\n" +
               "  > Tamaño: " + tamano + " páginas";
    }
}