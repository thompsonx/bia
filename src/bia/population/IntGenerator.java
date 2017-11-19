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
public class IntGenerator implements IGenerator<Integer>
{

    @Override
    public List<Point3DHolder> generate(int num, IFunction fn, Integer xMin, 
            Integer xMax, Integer yMin, Integer yMax) {
        Random r = new Random();
        List<Point3DHolder> ret = new ArrayList<>();
        
        for (int i = 0; i < num; i++)
        {
            int x = r.nextInt((xMax - xMin) + 1) + xMin;
            
            int y = r.nextInt((yMax - yMin) + 1) + yMin;
            
            List<Float> input = new ArrayList<>();
            input.add((float)x);
            input.add((float)y);
            float z = fn.fn(input);
            
            ret.add(new Point3DHolder(x, y, z));
        }
        
        return ret;
    }
    
}
