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
public class MatyasFn implements IFunction
{
    @Override
    public float fn(List<Float> input) {
        float squares = 0;
        float product = 0.48f;
        
        for (float x: input)
        {
            squares += Math.pow(x, 2);
            product *= x;
        }
        
        return 0.26f * squares - product;
    }

    @Override
    public float getXMin() {
        return (float) -10;
    }

    @Override
    public float getXMax() {
        return (float) 10;
    }

    @Override
    public float getYMin() {
        return this.getXMin();
    }

    @Override
    public float getYMax() {
        return this.getXMax();
    }

    @Override
    public float getZMin() {
        return -10;
    }

    @Override
    public float getZMax() {
        return 120;
    }
}
