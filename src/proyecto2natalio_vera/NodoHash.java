/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 *
 * @author natalio
 */
class NodoHash {
    String clave;
    RegistroImpresion registro;
    NodoHash next;
    
    public NodoHash(String clave, RegistroImpresion registro) {
        this.clave = clave;
        this.registro = registro;
    }
}
