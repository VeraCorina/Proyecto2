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
    

    private void asegurarCapacidad() {
        if (size == heap.length) {
            RegistroImpresion[] nuevoHeap = new RegistroImpresion[heap.length * 2];
            System.arraycopy(heap, 0, nuevoHeap, 0, size);
            heap = nuevoHeap;
        }
    }

    private void swap(int i, int j) {
        RegistroImpresion temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        heap[i].posicionHeap = i;
        heap[j].posicionHeap = j;
    }

    public void flotar(int i) {
        while (i > 0 && heap[padre(i)].etiquetaTiempo > heap[i].etiquetaTiempo) {
            swap(i, padre(i));
            i = padre(i);
        }
    }

    private void hundir(int i) {
        int min = i;
        int izq = hijoIzq(i);
        int der = hijoDer(i);
        if (izq < size && heap[izq].etiquetaTiempo < heap[min].etiquetaTiempo) min = izq;
        if (der < size && heap[der].etiquetaTiempo < heap[min].etiquetaTiempo) min = der;
        if (min != i) {
            swap(i, min);
            hundir(min);
        }
    }

    public void insertar(RegistroImpresion registro) {
        asegurarCapacidad();
        heap[size] = registro;
        registro.posicionHeap = size;
        flotar(size);
        size++;
    }

    public RegistroImpresion eliminarMin() {
        if (size == 0) return null;
        RegistroImpresion root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        if (size > 0) {
            heap[0].posicionHeap = 0;
            hundir(0);
        }
        return root;
    }

    public void eliminarEnPosicion(int index) {
        if (index < 0 || index >= size) return;
        if (index == size - 1) {
            size--;
            return;
        }
        heap[index] = heap[size - 1];
        heap[index].posicionHeap = index;
        size--;
        flotar(index);
        hundir(index);
    }

    public RegistroImpresion[] getArreglo() {
        RegistroImpresion[] copia = new RegistroImpresion[size];
        System.arraycopy(heap, 0, copia, 0, size);
        return copia;
    }
}