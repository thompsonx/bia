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
public class StyblinskiTangFn implements IFunction
{
    @Override
    public float fn(List<Float> input) {
        float sum = 0;
        
        for (float x: input)
        {
            sum += Math.pow(x, 4) - 16 * Math.pow(x, 2) + 5 * x;
        }

        return sum / 2.0f;
    }

    @Override
    public float getXMin() {
        return (float) -5;
    }

    @Override
    public float getXMax() {
        return (float) 5;
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
        return -100;
    }

    @Override
    public float getZMax() {
        return 250;
    }    
}
