/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.plot;

import bia.functions.IFunction;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import net.sf.surfaceplot.SurfaceCanvas;

/**
 *
 * @author pan0068
 */
public class PlotView extends javax.swing.JFrame {

    public PlotView(IFunction fn) {
        getContentPane().setLayout(new BorderLayout());
        setSize(1000, 800);
        PlotModel model = new PlotModel(fn);
        SurfaceCanvas canvas = new SurfaceCanvas();
        canvas.setModel(model);
        getContentPane().add(canvas, BorderLayout.CENTER);
        canvas.repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
}
