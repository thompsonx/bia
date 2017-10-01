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
public class AckleysFn implements IFunction
{
    @Override
    public float fn(List<Float> input) {
        float squares = 0;
        float cosines = 0;

        for (float i: input)
        {
            squares += Math.pow(i, 2);
            cosines += Math.cos(2 * Math.PI * i);
        }

        float fstexp = (float) (-0.2f * Math.sqrt(0.5 * squares));
        float sndexp = 0.5f * cosines;

        return (float)(-20 * Math.exp(fstexp) - Math.exp(sndexp) + Math.E + 20);
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
        return 20;
    }
}
