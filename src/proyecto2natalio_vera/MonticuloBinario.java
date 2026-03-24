/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 *
 * @author Coco
 */
class MonticuloBinario {
    RegistroImpresion[] heap;
    int size;
    
    public MonticuloBinario(int capacidad) {
        heap = new RegistroImpresion[capacidad];
        size = 0;
    }
}
