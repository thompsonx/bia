/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.gui;

import bia.algorithms.AnnealingAlg;
import bia.algorithms.BlindAlg;
import bia.algorithms.DiffEvolutionAlg;
import bia.algorithms.IAlgorithm;
import bia.algorithms.ImprovedBlindAlg;
import bia.algorithms.JDEEvolutionAlg;
import bia.algorithms.ParticleSwarmAlg;
import bia.algorithms.SOMAAlg;
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
import bia.population.FloatGenerator;
import bia.population.IGenerator;
import bia.population.IntGenerator;
import java.awt.BorderLayout;
import java.util.List;
import net.sf.surfaceplot.SurfaceCanvas;

/**
 *
 * @author pan0068
 */
public class MainView extends javax.swing.JFrame {
    
    private SurfaceCanvas plot;
    private PlotThread thread = null;
    private AlgorithmPanel algPanel;
    
    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
        
        this.plot = new SurfaceCanvas();
        this.plotPanel.setLayout(new BorderLayout());
        this.plotPanel.add(this.plot, BorderLayout.CENTER);
        
        this.algPanel = new AlgorithmPanel();
        this.algPanel.setAlgorithm(new BlindAlg(this.getSelectedFunction(), 
                this.getSelectedGenerator()));
        this.panelAlg.setLayout(new BorderLayout());
        this.panelAlg.add(this.algPanel, BorderLayout.CENTER);
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
        lblPopPrec = new javax.swing.JLabel();
        cmbPopPrec = new javax.swing.JComboBox<>();
        btnPlot = new javax.swing.JButton();
        plotPanel = new javax.swing.JPanel();
        btnPause = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        lblBest = new javax.swing.JLabel();
        lblBestValue = new javax.swing.JLabel();
        lblAlg = new javax.swing.JLabel();
        cmbAlg = new javax.swing.JComboBox<>();
        panelAlg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblFn.setText("Function:");

        cmbFn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ackley's", "Beale", "Booth", "Matyas", "Parent", "Rastrigin", "Sphere", "Styblinski-Tang", "Three-hump camel" }));

        lblPopPrec.setText("Precision:");

        cmbPopPrec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Float", "Integer" }));

        btnPlot.setText("Start generating");
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
            .addGap(0, 335, Short.MAX_VALUE)
        );

        btnPause.setText("Pause");
        btnPause.setEnabled(false);
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnPlay.setText("Play");
        btnPlay.setEnabled(false);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        lblBest.setText("Best:");

        lblBestValue.setText("none");

        lblAlg.setText("Algorithm:");

        cmbAlg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blind search", "Improved blind search", "Simulated annealing", "Differential Evolution", "jDE", "SOMA", "Particle Swarm Optimization" }));
        cmbAlg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAlgLayout = new javax.swing.GroupLayout(panelAlg);
        panelAlg.setLayout(panelAlgLayout);
        panelAlgLayout.setHorizontalGroup(
            panelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelAlgLayout.setVerticalGroup(
            panelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(plotPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPlot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBestValue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 424, Short.MAX_VALUE)
                        .addComponent(btnPlay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPause))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblPopPrec)
                                .addComponent(lblFn))
                            .addComponent(lblAlg))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbFn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPopPrec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbAlg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelAlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbFn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPopPrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPopPrec))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbAlg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAlg)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelAlg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlot)
                    .addComponent(btnPause)
                    .addComponent(btnPlay)
                    .addComponent(lblBest)
                    .addComponent(lblBestValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plotPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblFn.getAccessibleContext().setAccessibleName("lblFn");
        cmbFn.getAccessibleContext().setAccessibleName("cmbFn");
        lblPopPrec.getAccessibleContext().setAccessibleName("lblPopPrec");
        cmbPopPrec.getAccessibleContext().setAccessibleName("cmbPopPrec");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlotActionPerformed

        IAlgorithm alg = this.getAlgorithm();
        
        boolean started = false;
        if (this.thread == null)
        {
            this.thread = new PlotThread(this.plot, alg, this.lblBestValue);
            this.thread.start();
            started = true;
        }
        else if (this.thread.isFinished())
        {
            this.thread = new PlotThread(this.plot, alg, this.lblBestValue);
            this.thread.start();
            started = true;
        }
        
        if (started)
        {
            this.btnPlay.setEnabled(true);
            this.btnPause.setEnabled(true);
        }
    }//GEN-LAST:event_btnPlotActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        this.thread.play();
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        this.thread.pause();
    }//GEN-LAST:event_btnPauseActionPerformed

    private void cmbAlgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlgActionPerformed
        int index = this.cmbAlg.getSelectedIndex();
        
        switch(index)
        {
            case 0:
                this.algPanel.setAlgorithm(
                        new BlindAlg(this.getSelectedFunction(),
                        this.getSelectedGenerator()));
                break;
            case 1:
                this.algPanel.setAlgorithm(
                        new ImprovedBlindAlg(this.getSelectedFunction(),
                        this.getSelectedGenerator()));
                break;
            case 2:
                this.algPanel.setAlgorithm(
                        new AnnealingAlg(this.getSelectedFunction(),
                        this.getSelectedGenerator()));
                break;
            case 3:
                this.algPanel.setAlgorithm(
                        new DiffEvolutionAlg(this.getSelectedFunction(),
                        this.getSelectedGenerator()));
                break;
            case 4:
                this.algPanel.setAlgorithm(
                        new JDEEvolutionAlg(this.getSelectedFunction(),
                        this.getSelectedGenerator()));
                break;
            case 5:
                this.algPanel.setAlgorithm(
                        new SOMAAlg(this.getSelectedFunction(),
                        this.getSelectedGenerator()));
                break;
            case 6:
                this.algPanel.setAlgorithm(
                        new ParticleSwarmAlg(this.getSelectedFunction(),
                        this.getSelectedGenerator()));
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cmbAlgActionPerformed

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
    
    private IGenerator getSelectedGenerator()
    {
        int gen = this.cmbPopPrec.getSelectedIndex();
        switch (gen)
        {
            case 0:
                return new FloatGenerator();
            case 1:
                return new IntGenerator();
            default:
                return new FloatGenerator();
        }
    }
    
    private IAlgorithm getAlgorithm()
    {
        int index = this.cmbAlg.getSelectedIndex();
        List<Float> args = this.algPanel.values();
        IFunction fn = this.getSelectedFunction();
        IGenerator g = this.getSelectedGenerator();
        
        switch(index)
        {
            case 0:
                return new BlindAlg(fn, g, args.get(0).intValue(), 
                        args.get(1).intValue());
            case 1:
                return new ImprovedBlindAlg(fn, g, args.get(0).intValue(),
                        args.get(1).intValue());
            case 2:
                return new AnnealingAlg(fn, g, args.get(0), args.get(1), 
                        args.get(2), args.get(3).intValue());
            case 3:
                return new DiffEvolutionAlg(fn, g, args.get(0).intValue(), 
                        args.get(1).intValue(), args.get(2), args.get(3));
            case 4:
                return new JDEEvolutionAlg(fn, g, args.get(0).intValue(),
                        args.get(1).intValue(), args.get(2), args.get(3));
            case 5:
                return new SOMAAlg(fn, g, args.get(0), args.get(1),
                        args.get(2), args.get(3).intValue(), 
                        args.get(4).intValue());
            case 6:
                return new ParticleSwarmAlg(fn, g, args.get(0).intValue(),
                        args.get(1).intValue());
            default:
                return new BlindAlg(fn, g, args.get(0).intValue(), 
                        args.get(1).intValue());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnPlot;
    private javax.swing.JComboBox<String> cmbAlg;
    private javax.swing.JComboBox<String> cmbFn;
    private javax.swing.JComboBox<String> cmbPopPrec;
    private javax.swing.JLabel lblAlg;
    private javax.swing.JLabel lblBest;
    private javax.swing.JLabel lblBestValue;
    private javax.swing.JLabel lblFn;
    private javax.swing.JLabel lblPopPrec;
    private javax.swing.JPanel panelAlg;
    private javax.swing.JPanel plotPanel;
    // End of variables declaration//GEN-END:variables
}
