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
public class PopulationGenerator {
    
    public static List<Point3DHolder> genInFloat(final int num, IFunction fn)
    {
        Random r = new Random();
        List<Point3DHolder> ret = new ArrayList<>();
        
        for (int i = 0; i < num; i++)
        {
            float x = fn.getXMin() + r.nextFloat() * 
                    (fn.getXMax() - fn.getXMin());
            float y = fn.getYMin() + r.nextFloat() * 
                    (fn.getYMax() - fn.getYMin());
            List<Float> input = new ArrayList<>();
            input.add(x);
            input.add(y);
            float z = fn.fn(input);
            
            ret.add(new Point3DHolder(x, y, z));
        }
        
        return ret;
    }
    
    public static List<Point3DHolder> genInInt(final int num, IFunction fn)
    {
        Random r = new Random();
        List<Point3DHolder> ret = new ArrayList<>();
        
        for (int i = 0; i < num; i++)
        {
            int maxX = (int)fn.getXMax();
            int minX = (int)fn.getXMin();
            int x = r.nextInt((maxX - minX) + 1) + minX;
            
            int maxY = (int)fn.getYMax();
            int minY = (int)fn.getYMin();
            int y = r.nextInt((maxY - minY) + 1) + minY;
            
            List<Float> input = new ArrayList<>();
            input.add((float)x);
            input.add((float)y);
            float z = fn.fn(input);
            
            ret.add(new Point3DHolder(x, y, z));
        }
        
        return ret;
    }
}
