/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.gui;

import bia.functions.AckleysFn;
import bia.functions.BealeFn;
import bia.functions.BoothFn;
import bia.functions.IFunction;
import bia.functions.MatyasFn;
import bia.functions.ParetFn;
import bia.functions.RastriginFn;
import bia.functions.SphereFn;
import bia.functions.StyblinskiTangFn;
import bia.functions.ThreeHumpCamelFn;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import net.sf.surfaceplot.SurfaceCanvas;

/**
 *
 * @author pan0068
 */
public class MainView extends javax.swing.JFrame {
    
    private SurfaceCanvas plot;
    
    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
        
        this.plot = new SurfaceCanvas();
        this.plotPanel.setLayout(new BorderLayout());
        this.plotPanel.add(this.plot, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFn = new javax.swing.JLabel();
        cmbFn = new javax.swing.JComboBox<>();
        lblPopSize = new javax.swing.JLabel();
        lblPopPrec = new javax.swing.JLabel();
        cmbPopPrec = new javax.swing.JComboBox<>();
        spnPopSize = new javax.swing.JSpinner();
        btnPlot = new javax.swing.JButton();
        plotPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblFn.setText("Function:");

        cmbFn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ackley's", "Beale", "Booth", "Matyas", "Parent", "Rastrigin", "Sphere", "Styblinski-Tang", "Three-hump camel" }));

        lblPopSize.setText("Population size:");

        lblPopPrec.setText("Precision:");

        cmbPopPrec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Float", "Integer" }));

        btnPlot.setText("Plot");
        btnPlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plotPanelLayout = new javax.swing.GroupLayout(plotPanel);
        plotPanel.setLayout(plotPanelLayout);
        plotPanelLayout.setHorizontalGroup(
            plotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        plotPanelLayout.setVerticalGroup(
            plotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFn)
                    .addComponent(lblPopSize)
                    .addComponent(lblPopPrec))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbFn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbPopPrec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spnPopSize))
                .addGap(0, 194, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(btnPlot)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plotPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbFn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPopSize)
                    .addComponent(spnPopSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPopPrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPopPrec))
                .addGap(18, 18, 18)
                .addComponent(btnPlot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plotPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblFn.getAccessibleContext().setAccessibleName("lblFn");
        cmbFn.getAccessibleContext().setAccessibleName("cmbFn");
        lblPopSize.getAccessibleContext().setAccessibleName("lblPop");
        lblPopPrec.getAccessibleContext().setAccessibleName("lblPopPrec");
        cmbPopPrec.getAccessibleContext().setAccessibleName("cmbPopPrec");
        spnPopSize.getAccessibleContext().setAccessibleName("editPopSize");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlotActionPerformed

        PlotModel model = new PlotModel(this.getSelectedFunction());
        this.plot.setModel(model);
        this.plot.repaint();
    }//GEN-LAST:event_btnPlotActionPerformed

    private IFunction getSelectedFunction()
    {
        int fn = this.cmbFn.getSelectedIndex();
        switch (fn)
        {
            case 0:
                return new AckleysFn();
            case 1:
                return new BealeFn();
            case 2:
                return new BoothFn();
            case 3:
                return new MatyasFn();
            case 4:
                return new ParetFn();
            case 5:
                return new RastriginFn();
            case 6:
                return new SphereFn();
            case 7:
                return new StyblinskiTangFn();
            case 8:
                return new ThreeHumpCamelFn();
            default:
                return new AckleysFn();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlot;
    private javax.swing.JComboBox<String> cmbFn;
    private javax.swing.JComboBox<String> cmbPopPrec;
    private javax.swing.JLabel lblFn;
    private javax.swing.JLabel lblPopPrec;
    private javax.swing.JLabel lblPopSize;
    private javax.swing.JPanel plotPanel;
    private javax.swing.JSpinner spnPopSize;
    // End of variables declaration//GEN-END:variables
}
