/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;

/**
 *
 * @author Coco
 */
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Graficador {

    public static JPanel obtenerPanelArbol(RegistroImpresion[] cola) {
        // 1. Configurar propiedades de renderizado para Swing
        System.setProperty("org.graphstream.ui", "swing");
        
        // 2. Crear el Grafo
        Graph graph = new SingleGraph("Cola_Impresion");
        
        // Estilo CSS básico para los nodos
        String css = "node { " +
                     "   fill-color: #3498db; " +
                     "   text-color: #333; " +
                     "   text-size: 14px; " +
                     "   text-alignment: under; " +
                     "   size: 20px; " +
                     "}";
        graph.setAttribute("ui.stylesheet", css);

        if (cola.length == 0) {
            return new JPanel(); // Si está vacío, devuelve un panel vacío
        }

        // 3. Agregar los Nodos (cada elemento del arreglo)
        for (int i = 0; i < cola.length; i++) {
            Node nodo = graph.addNode(String.valueOf(i));
            // La etiqueta mostrará el nombre del doc y su prioridad
            String etiqueta = cola[i].documento.nombre + " (T:" + cola[i].etiquetaTiempo + ")";
            nodo.setAttribute("ui.label", etiqueta);
        }

        // 4. Agregar las Aristas (conexiones Padre -> Hijos)
        // Recordando la matemática del Heap: hijoIzq = 2*i + 1, hijoDer = 2*i + 2
        for (int i = 0; i < cola.length; i++) {
            int hijoIzq = 2 * i + 1;
            int hijoDer = 2 * i + 2;

            if (hijoIzq < cola.length) {
                graph.addEdge("E_I_" + i, String.valueOf(i), String.valueOf(hijoIzq));
            }
            if (hijoDer < cola.length) {
                graph.addEdge("E_D_" + i, String.valueOf(i), String.valueOf(hijoDer));
            }
        }

        // 5. Crear el visor y el panel de Swing
        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout(); // GraphStream acomodará los nodos automáticamente
        
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        
        // Retornamos el panel contenedor
        JPanel panelContenedor = new JPanel(new BorderLayout());
        panelContenedor.add(viewPanel, BorderLayout.CENTER);
        
        return panelContenedor;
    }
}
