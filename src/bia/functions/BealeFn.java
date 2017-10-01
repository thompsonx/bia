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
public class BealeFn implements IFunction 
{
    @Override
    public float fn(List<Float> input) {
        float x = input.get(0);
        float y = input.get(1);
        
        return (float) ( Math.pow( (1.5 - x + x * y), 2 ) + 
                Math.pow( (2.25 - x + x * Math.pow(y, 2)), 2 ) +
                Math.pow( (2.625 - x + x * Math.pow(y, 3)), 2 ) );
    }

    @Override
    public float getXMin() {
        return (float) -4.5;
    }

    @Override
    public float getXMax() {
        return (float) 4.5;
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
        return -5;
    }

    @Override
    public float getZMax() {
        return 200000;
    }
}
