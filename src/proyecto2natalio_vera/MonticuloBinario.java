/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 * Implementacion del monticulo binario pa la cola de prioridad.
 * Los documentos con etiqueta mas baja suben a la sima.
 * @author Coco
 */
class MonticuloBinario {
    RegistroImpresion[] heap;
    int size;

    /**
     * Crea el monticulo con un tamaño inicial.
     * @param capacidad Cuantos registros caben al principio.
     */
    public MonticuloBinario(int capacidad) {
        heap = new RegistroImpresion[capacidad];
        size = 0;
    }

    /**
     * Calcula quien es el padre de un nodo.
     * @param i Indice del nodo hijo.
     * @return Indice del papa.
     */
    private int padre(int i) { 
        return (i - 1) / 2; 
    }
    
    /**
     * @param i Indice del padre.
     * @return Indice del hijo de la isquierda.
     */
    private int hijoIzq(int i) { 
        return 2 * i + 1; 
    }
    
    /**
     * @param i Indice del padre.
     * @return Indice del hijo de la derecha.
     */
    private int hijoDer(int i) { 
        return 2 * i + 2; 
    }

    /**
     * Agranda el arreglo si se llena el monticulo pa que no explote.
     */
    private void asegurarCapacidad() {
        if (size == heap.length) {
            RegistroImpresion[] nuevoHeap = new RegistroImpresion[heap.length * 2];
            System.arraycopy(heap, 0, nuevoHeap, 0, size);
            heap = nuevoHeap;
        }
    }

    /**
     * Cambia dos elementos de lugar en el arreglo.
     * @param i Primer nodo.
     * @param j Segundo nodo.
     */
    private void swap(int i, int j) {
        RegistroImpresion temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        heap[i].posicionHeap = i;
        heap[j].posicionHeap = j;
    }

    /**
     * Sube un nodo hasta que este en su lugar correcto segun su tiempo.
     * @param i El indice que queremos subir.
     */
    public void flotar(int i) {
        while (i > 0 && heap[padre(i)].etiquetaTiempo > heap[i].etiquetaTiempo) {
            swap(i, padre(i));
            i = padre(i);
        }
    }

    /**
     * Baja un nodo si es mas grande que sus hijos pa mantener el orden.
     * @param i Indice que va pa abajo.
     */
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

    /**
     * Mete un registro nuevo y lo acomoda flotandolo.
     * @param registro El nuevo documento pa imprimir.
     */
    public void insertar(RegistroImpresion registro) {
        asegurarCapacidad();
        heap[size] = registro;
        registro.posicionHeap = size;
        flotar(size);
        size++;
    }

    /**
     * Saca el que tiene menos tiempo (la rais) y reacomoda el arbol.
     * @return El registro con maxima prioridad.
     */
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

    /**
     * Borra un nodo en cualquier parte del monticulo.
     * @param index Donde esta el que queremos matar.
     */
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

    /**
     * @return Una copia del arreglo pa no dañar el original al leerlo.
     */
    public RegistroImpresion[] getArreglo() {
        RegistroImpresion[] copia = new RegistroImpresion[size];
        System.arraycopy(heap, 0, copia, 0, size);
        return copia;
    }
}