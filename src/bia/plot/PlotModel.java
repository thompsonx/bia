/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.plot;

import bia.functions.IFunction;
import java.util.ArrayList;
import net.sf.surfaceplot.SurfacePlotModel;

/**
 *
 * @author pan0068
 */
public class PlotModel implements SurfacePlotModel {

    private IFunction fun;
    
    public PlotModel (IFunction fn)
    {
        this.fun = fn;
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
        return true;
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
        return 75;
    }

    @Override
    public int getDispDivisions() {
        return 75;
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
