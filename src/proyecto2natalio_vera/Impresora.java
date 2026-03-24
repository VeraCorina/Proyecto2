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
    
    public void eliminarUsuario(String nombre) {
        Usuario u = buscarUsuario(nombre);
        if (u != null) {
            usuarios.remove(u);
        }
    }
    
    public void crearDocumento(String nombreUsuario, String nombreDoc, int tamano, String tipo) {
        Usuario u = buscarUsuario(nombreUsuario);
        if (u != null) {
            u.documentos.add(new Documento(nombreDoc, tamano, tipo));
        }
    }
    
    public void eliminarDocumentoNoEncolado(String nombreUsuario, String nombreDoc) {
        Usuario u = buscarUsuario(nombreUsuario);
        if (u != null) {
            for (int i = 0; i < u.documentos.size(); i++) {
                Documento d = u.documentos.get(i);
                if (d.nombre.equals(nombreDoc) && !d.enCola) {
                    u.documentos.remove(d);
                    break;
                }
            }
        }
    }
    
    public void enviarAImprimir(String nombreUsuario, String nombreDoc, boolean prioritario) {
        Usuario u = buscarUsuario(nombreUsuario);
        if (u == null) return;
        
        Documento target = null;
        for (int i = 0; i < u.documentos.size(); i++) {
            Documento d = u.documentos.get(i);
            if (d.nombre.equals(nombreDoc) && !d.enCola) {
                target = d;
                break;
            }
        }
        
        if (target != null) {
            reloj++;
            int etiqueta = reloj;
            if (prioritario) {
                if (u.tipo.equals("prioridad_alta")) etiqueta -= 1000;
                else if (u.tipo.equals("prioridad_media")) etiqueta -= 500;
                else if (u.tipo.equals("prioridad_baja")) etiqueta -= 100;
            }
            
            target.enCola = true;
            RegistroImpresion registro = new RegistroImpresion(target, etiqueta);
            colaImpresion.insertar(registro);
            tablaDisporcion.insertar(u.nombre, registro);
        }
    }
}
