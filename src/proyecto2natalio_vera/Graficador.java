/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2natalio_vera;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Esta clase se encarga de dibujar el arbol binario pa que se vea bonito.
 * Usa la libreria GraphStream que bajamos pa la imprecion grafica.
 * @author Coco
 */
public class Graficador {

    /**
     * Metodo que genera el panel con el dibujo del monticulo.
     * @param cola Es el arreglo de registros que viene del monticulo.
     * @return Un JPanel que se puede pegar en la ventana principal.
     */
    public static JPanel obtenerPanelArbol(RegistroImpresion[] cola) {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Cola_Impresion");
        String css = "node { " +
                     "   fill-color: #3498db; " +
                     "   text-color: #333; " +
                     "   text-size: 14px; " +
                     "   text-alignment: under; " +
                     "   size: 20px; " +
                     "}";
        graph.setAttribute("ui.stylesheet", css);

        if (cola.length == 0) {
            return new JPanel();
        }
        for (int i = 0; i < cola.length; i++) {
            Node nodo = graph.addNode(String.valueOf(i));
            String etiqueta = cola[i].documento.nombre + " (T:" + cola[i].etiquetaTiempo + ")";
            nodo.setAttribute("ui.label", etiqueta);
        }
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
        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        JPanel panelContenedor = new JPanel(new BorderLayout());
        panelContenedor.add(viewPanel, BorderLayout.CENTER);
        
        return panelContenedor;
    }
}