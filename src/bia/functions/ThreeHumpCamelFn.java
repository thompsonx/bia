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
public class ThreeHumpCamelFn implements IFunction
{
    @Override
    public float fn(List<Float> input) {
        float x = input.get(0);
        float y = input.get(1);

        return (float) (2*Math.pow(x,2) - 1.05*Math.pow(x,4) + Math.pow(x,6)/6 +
                x*y + Math.pow(y, 2));
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
        return -10;
    }

    @Override
    public float getZMax() {
        return 2200;
    }
}
