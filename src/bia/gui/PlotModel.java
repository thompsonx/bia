/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.gui;

import bia.functions.IFunction;
import bia.population.PopulationGenerator;
import java.util.ArrayList;
import java.util.List;
import net.sf.surfaceplot.Point3DHolder;
import net.sf.surfaceplot.SurfacePlotModel;

/**
 *
 * @author pan0068
 */
public class PlotModel implements SurfacePlotModel {

    private IFunction fun;
    private List<Point3DHolder> population;
    
    public PlotModel (IFunction fn, List<Point3DHolder> population)
    {
        this.fun = fn;
        this.population = population;
    }

    @Override
    public List<Point3DHolder> getExtraPoints() {
        return this.population;
    }
    
    @Override
    public int getPlotMode() {
        return SurfacePlotModel.PLOT_MODE_SPECTRUM;
    }

    @Override
    public float calculateZ(float x, float y)
    {
        ArrayList<Float> inputs = new ArrayList<>();
        inputs.add(x);
        inputs.add(y);
        
        return this.fun.fn(inputs);
    }

    @Override
    public boolean isBoxed() {
        return true;
    }

    @Override
    public boolean isMesh() {
        return false;
    }

    @Override
    public boolean isScaleBox() {
        return false;
    }

    @Override
    public boolean isDisplayXY() {
        return true;
    }

    @Override
    public boolean isDisplayZ() {
        return true;
    }

    @Override
    public boolean isDisplayGrids() {
        return true;
    }

    @Override
    public int getCalcDivisions() {
        return 80;
    }

    @Override
    public int getDispDivisions() {
        return 80;
    }

    @Override
    public float getXMin()
    {
        return this.fun.getXMin();
    }

    @Override
    public float getXMax()
    {
        return this.fun.getXMax();
    }

    @Override
    public float getYMin()
    {
        return this.fun.getYMin();
    }

    @Override
    public float getYMax()
    {
        return this.fun.getYMax();
    }

    @Override
    public float getZMin()
    {
        return this.fun.getZMin();
    }

    @Override
    public float getZMax()
    {
        return this.fun.getZMax();
    }

    @Override
    public String getXAxisLabel() {
        return "X";
    }

    @Override
    public String getYAxisLabel() {
        return "Y";
    }

    @Override
    public String getZAxisLabel() {
        return "Z";
    }
    
}
