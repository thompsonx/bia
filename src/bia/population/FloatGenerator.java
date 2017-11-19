/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.population;

import bia.functions.IFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.sf.surfaceplot.Point3DHolder;

/**
 *
 * @author pan0068
 */
public class FloatGenerator implements IGenerator<Float>
{

    @Override
    public List<Point3DHolder> generate(int num, IFunction fn, Float xMin, 
            Float xMax, Float yMin, Float yMax) {
        Random r = new Random();
        List<Point3DHolder> ret = new ArrayList<>();
        
        for (int i = 0; i < num; i++)
        {
            float x = xMin + r.nextFloat() * 
                    (xMax - xMin);
            float y = yMin + r.nextFloat() * 
                    (yMax - yMin);
            List<Float> input = new ArrayList<>();
            input.add(x);
            input.add(y);
            float z = fn.fn(input);
            
            ret.add(new Point3DHolder(x, y, z));
        }
        
        return ret;
    }
    
}
