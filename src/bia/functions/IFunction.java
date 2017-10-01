/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.functions;

import java.util.List;

/**
 *
 * @author pan0068
 */
public interface IFunction {

    public float fn(List<Float> input);
    
    public float getXMin();

    public float getXMax();

    public float getYMin();

    public float getYMax();

    public float getZMin();

    public float getZMax();
    
}
