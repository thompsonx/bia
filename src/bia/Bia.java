/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia;

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
import bia.gui.MainView;
import bia.gui.PlotView;
import bia.population.PopulationGenerator;
import java.util.List;

/**
 *
 * @author pan0068
 */
public class Bia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        PlotView pv = new PlotView(new RastriginFn());
        MainView mv = new MainView();
        mv.setVisible(true);
    }
    
}
