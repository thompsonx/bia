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
public class ParetFn implements IFunction {
    
    @Override
    public float fn(List<Float> input) {
        float F = 2f;
        
        float x1 = input.get(0);
        float x2 = input.get(1);
        
        float f = x1;
        float g = x2 + 10;
        
        float g1 = 11;
        float g2 = 12;
        
        float alfa = 0.25f + 3.75f * (g - g2)/(g1 - g2);
        
        return (float) (Math.pow((float)f/(float)g, alfa) - 
                ((float)f/(float)g) * 
                Math.sin(Math.PI * F * f * g));
    }

    @Override
    public float getXMin() {
        return 0;
    }

    @Override
    public float getXMax() {
        return 1;
    }

    @Override
    public float getYMin() {
        return 0;
    }

    @Override
    public float getYMax() {
        return 1;
    }

    @Override
    public float getZMin() {
        return -0.15f;
    }

    @Override
    public float getZMax() {
        return 0.2f;
    }
    
}
