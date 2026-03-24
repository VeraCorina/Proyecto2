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
    
    
    private void swap(int i, int j) {
        RegistroImpresion temp = heap[i];
        heap[i] = heap[j];
        heap[i].posicionHeap = i;
        heap[j] = temp;
        heap[j].posicionHeap = j;
    }
    
    private void flotar(int i) {
        while (i > 0 && heap[padre(i)].etiquetaTiempo > heap[i].etiquetaTiempo) {
            swap(i, padre(i));
            i = padre(i);
        }
    }
    
    private void hundir(int i) {
        int minIndex = i;
        int izq = hijoIzq(i);
        int der = hijoDer(i);
        
        if (izq < size && heap[izq].etiquetaTiempo < heap[minIndex].etiquetaTiempo) {
            minIndex = izq;
        }
        if (der < size && heap[der].etiquetaTiempo < heap[minIndex].etiquetaTiempo) {
            minIndex = der;
        }
        if (i != minIndex) {
            swap(i, minIndex);
            hundir(minIndex);
        }
    }
    
    public void insertar(RegistroImpresion registro) {
        if (size == heap.length) return;
        registro.posicionHeap = size;
        heap[size] = registro;
        flotar(size);
        size++;
    }
    
    public RegistroImpresion eliminarMin() {
        if (size <= 0) return null;
        if (size == 1) {
            size--;
            return heap[0];
        }
        RegistroImpresion root = heap[0];
        heap[0] = heap[size - 1];
        heap[0].posicionHeap = 0;
        size--;
        hundir(0);
        return root;
    }
}
