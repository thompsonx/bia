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
public class BoothFn implements IFunction
{
    @Override
    public float fn(List<Float> input) {
        float x = input.get(0);
        float y = input.get(1);
        
        return (float) (Math.pow( x + 2*y - 7, 2 ) + 
                Math.pow( 2*x + y - 5, 2 ));
        
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
        return 3000;
    }
}
