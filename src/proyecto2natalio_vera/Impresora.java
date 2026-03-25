/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Esta es la clase que controla todo el sistema de la impresora.
 * Une los usuarios, la cola de impresion y la tabla hash.
 * @author Coco
 */
public class Impresora {

    Lista<Usuario> usuarios;
    MonticuloBinario colaImpresion;
    HashTable tablaDisporcion;
    int reloj;

    /**
     * Inicialisa todas las estructuras de datos del sistema.
     */
    public Impresora() {
        usuarios = new Lista<>();
        colaImpresion = new MonticuloBinario(100);
        tablaDisporcion = new HashTable(1009);
        reloj = 0;
    }

    /**
     * Registra un usuario nuevo en la lista.
     * @param nombre Como se llama la persona.
     * @param tipo Su prioridad (alta, media o baja).
     */
    public void agregarUsuario(String nombre, String tipo) {
        usuarios.add(new Usuario(nombre, tipo));
    }

    /**
     * Lee un archivo de texto pa cargar usuarios mas rapido.
     * @param ruta Donde esta el archivo en la compu.
     */
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

    /**
     * Busca un usuario por su nombre.
     * @param nombre El nombre que buscamos.
     * @return El objeto usuario o null si no se halla.
     */
    public Usuario buscarUsuario(String nombre) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).nombre.equals(nombre)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    /**
     * Saca a un usuario del sistema pero no borra sus docs de la cola.
     * @param nombre Quien se va.
     */
    public void eliminarUsuario(String nombre) {
        Usuario u = buscarUsuario(nombre);
        if (u != null) {
            usuarios.remove(u);
        }
    }

    /**
     * Crea un doc nuevo pa un usuario espesifico.
     * @param nombreUsuario Dueño del doc.
     * @param nombreDoc Nombre del archivo.
     * @param tamano Cuantas pajinas.
     * @param tipo Extension del doc.
     */
    public void crearDocumento(String nombreUsuario, String nombreDoc, int tamano, String tipo) {
        Usuario u = buscarUsuario(nombreUsuario);
        if (u != null) {
            u.documentos.add(new Documento(nombreDoc, tamano, tipo));
        }
    }

    /**
     * Borra un doc que todavia no se ha mandado a imprimir.
     * @param nombreUsuario Del usuario.
     * @param nombreDoc Del documento.
     */
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

    /**
     * Manda un documento a la cola de impresion calculando su tiempo.
     * @param nombreUsuario Quien lo manda.
     * @param nombreDoc Que manda.
     * @param prioritario Si quiere usar su prioridad de usuario.
     */
    public void enviarAImprimir(String nombreUsuario, String nombreDoc, boolean prioritario) {
        Usuario u = buscarUsuario(nombreUsuario);
        if (u == null) {
            return;
        }
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
                if (u.tipo.equals("prioridad_alta")) {
                    etiqueta -= 1000;
                } else if (u.tipo.equals("prioridad_media")) {
                    etiqueta -= 500;
                } else if (u.tipo.equals("prioridad_baja")) {
                    etiqueta -= 100;
                }
            }
            target.enCola = true;
            RegistroImpresion registro = new RegistroImpresion(u.nombre, target, etiqueta);
            colaImpresion.insertar(registro);
            tablaDisporcion.insertar(u.nombre, registro);
        }
    }

    /**
     * Saca el documento con mas prioridad de la cola (el menor tiempo).
     * @return El documento que se acaba de imprimir.
     */
    public Documento liberarImpresora() {
        RegistroImpresion r = colaImpresion.eliminarMin();
        if (r != null) {
            tablaDisporcion.eliminar(r.nombreUsuario, r.documento);
            r.documento.enCola = false;
            return r.documento;
        }
        return null;
    }

    /**
     * Elimina un documento que ya esta en la cola usando el truco de la etiqueta infinita.
     * @param nombreUsuario Dueño.
     * @param nombreDoc Nombre del archivo.
     */
    public void eliminarDocumentoDeCola(String nombreUsuario, String nombreDoc) {
        Usuario u = buscarUsuario(nombreUsuario);
        if (u == null) {
            return;
        }

        Documento doc = null;
        for (int i = 0; i < u.documentos.size(); i++) {
            if (u.documentos.get(i).nombre.equals(nombreDoc)) {
                doc = u.documentos.get(i);
                break;
            }
        }

        if (doc != null && doc.enCola) {
            RegistroImpresion reg = tablaDisporcion.buscar(nombreUsuario, doc);
            if (reg != null) {
                reg.etiquetaTiempo = -999999;
                colaImpresion.flotar(reg.posicionHeap);
                colaImpresion.eliminarMin();
                tablaDisporcion.eliminar(nombreUsuario, doc);
                doc.enCola = false;
            }
        }
    }

    /**
     * @return Devuelve la lista de usuarios del sistema.
     */
    public Lista<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @return El arreglo con los documentos en espera pal grafico o tablas.
     */
    public RegistroImpresion[] getVistaColaArreglo() {
        return colaImpresion.getArreglo();
    }

    /**
     * Crea un reporte de texto de los documentos de un usuario.
     * @param nombreUsuario Nombre del pana.
     * @return El string pal area de texto.
     */
    public String obtenerDocumentosUsuario(String nombreUsuario) {
        Usuario u = buscarUsuario(nombreUsuario);
        if (u == null) {
            return "Error: El usuario '" + nombreUsuario + "' no existe.";
        }

        if (u.documentos.size() == 0) {
            return "El usuario " + nombreUsuario + " no tiene documentos registrados.";
        }

        String reporte = "Documentos de: " + u.nombre + " (" + u.tipo + ")\n";
        reporte += "--------------------------------------------------\n";

        for (int i = 0; i < u.documentos.size(); i++) {
            Documento d = u.documentos.get(i);
            reporte += "Nombre: " + d.nombre + "\n";
            reporte += "  - Tamano: " + d.tamano + " paginas\n";
            reporte += "  - Tipo: " + d.tipo + "\n";
            reporte += "  - Estado: " + (d.enCola ? "[EN COLA]" : "[DISPONIBLE]") + "\n";
            reporte += "--------------------------------------------------\n";
        }

        return reporte;
    }

    /**
     * Muesta todo lo que hay en el sistema.
     * @return El reporte gigante.
     */
    public String mostrarUsuariosYDocumentos() {
        if (usuarios.size() == 0) {
            return "No hay usuarios registrados en el sistema.";
        }

        String reporte = "--- REPORTE GENERAL DE USUARIOS Y DOCUMENTOS ---\n\n";

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            reporte += "USUARIO: " + u.nombre + " [Prioridad: " + u.tipo + "]\n";

            if (u.documentos.size() == 0) {
                reporte += "  (Sin documentos registrados)\n";
            } else {
                for (int j = 0; j < u.documentos.size(); j++) {
                    Documento d = u.documentos.get(j);
                    reporte += "  - " + d.nombre + " (" + d.tipo + ") -> " + (d.enCola ? "EN COLA" : "DISPONIBLE") + "\n";
                }
            }
            reporte += "--------------------------------------------------\n";
        }
        return reporte;
    }

    /**
     * @return Texto con solo usuarios y sus rangos.
     */
    public String mostrarListaUsuariosPrioridad() {
        if (usuarios.size() == 0) {
            return "No hay usuarios registrados.";
        }

        String lista = "--- LISTA DE USUARIOS REGISTRADOS ---\n";
        lista += "NOMBRE\t\tPRIORIDAD\n";
        lista += "-------------------------------------\n";

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            lista += u.nombre + "\t\t" + u.tipo + "\n";
        }

        return lista;
    }
    
    /**
     * @return El estado de la cola de espera en texto.
     */
    public String mostrarColaEspera() {
        RegistroImpresion[] cola = colaImpresion.getArreglo();
        
        if (cola.length == 0) {
            return "La cola de impresion esta vacia.";
        }

        String reporte = "--- COLA DE IMPRESION ACTUAL (Orden de Prioridad) ---\n";
        reporte += "POS\tUSUARIO\t\tDOCUMENTO\t\tETIQUETA\n";
        reporte += "------------------------------------------------------------\n";

        for (int i = 0; i < cola.length; i++) {
            RegistroImpresion reg = cola[i];
            reporte += (i + 1) + "\t" + 
                       reg.nombreUsuario + "\t\t" + 
                       reg.documento.nombre + "\t\t" + 
                       reg.etiquetaTiempo + "\n";
        }
        
        reporte += "------------------------------------------------------------\n";
        reporte += "Total de documentos en espera: " + cola.length;
        
        return reporte;
    }
}