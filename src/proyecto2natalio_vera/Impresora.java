/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 *
 * @author Coco
 */
public class Impresora {
    Lista<Usuario> usuarios;
    MonticuloBinario colaImpresion;
    HashTable tablaDisporcion;
    int reloj;
    
    public Impresora() {
        usuarios = new Lista<>();
        colaImpresion = new MonticuloBinario(1000);
        tablaDisporcion = new HashTable(1009);
        reloj = 0;
    }
}
