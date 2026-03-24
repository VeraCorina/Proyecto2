/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    
    public void agregarUsuario(String nombre, String tipo) {
        usuarios.add(new Usuario(nombre, tipo));
    }
    
    public void cargarUsuariosCSV(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    agregarUsuario(datos[0].trim(), datos[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Usuario buscarUsuario(String nombre) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).nombre.equals(nombre)) {
                return usuarios.get(i);
            }
        }
        return null;
    }
    
    
}
