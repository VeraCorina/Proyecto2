/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 *
 * @author natalio
 */
class RegistroImpresion {
    Documento documento;
    int etiquetaTiempo;
    int posicionHeap;
    
    public RegistroImpresion(Documento documento, int etiquetaTiempo) {
        this.documento = documento;
        this.etiquetaTiempo = etiquetaTiempo;
    }
}