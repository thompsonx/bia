/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.gui;

import bia.algorithms.IAlgorithm;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import net.sf.surfaceplot.SurfaceCanvas;

/**
 *
 * @author pan0068
 */
public class PlotThread extends Thread
{
    private SurfaceCanvas plot;
    private IAlgorithm algorithm;
    private JLabel result;
    private boolean play = true;
    private boolean finished = false;

    public PlotThread(SurfaceCanvas plot, IAlgorithm algorithm, JLabel result) {
        this.plot = plot;
        this.algorithm = algorithm;
        this.result = result;
    }

    @Override
    public void run() {
        
        while (this.algorithm.hasNext())
        {
            while (!this.play)
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            PlotModel pm = new PlotModel(this.algorithm.getFn(), 
                    this.algorithm.next());
            this.plot.setModel(pm);
            this.plot.repaint();
            
            this.result.setText(this.algorithm.getBest().toString());

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.finished = true;
    }
    
    synchronized public void pause()
    {
        this.play = false;
    }
    
    synchronized public void play()
    {
        this.play = true;
    }
    
    synchronized public boolean isFinished()
    {
        return this.finished;
    }
}
