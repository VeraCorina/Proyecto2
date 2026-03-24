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
    
    private int padre(int i) { 
        return (i - 1) / 2; 
    }
    
    private int hijoIzq(int i) { 
        return 2 * i + 1;
    }
    
    private int hijoDer(int i) { 
        return 2 * i + 2; 
    }
    
}
