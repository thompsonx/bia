/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.algorithms;

import bia.functions.IFunction;
import bia.population.IGenerator;
import bia.utils.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.sf.surfaceplot.Point3DHolder;

/**
 *
 * @author pan0068
 */
public class AnnealingAlg implements IAlgorithm
{
    private IFunction fn;
    private IGenerator generator;
    private final float temp_start;
    private final float temp_limit;
    private final float temp_dec_ratio;
    private float temp_current;
    private final int population;
    private Point3DHolder x0 = null;
    
    private int genNum = 0;
    
    public static final float SCALE = 1.0f;  

    public AnnealingAlg(IFunction fn, IGenerator g)
    {
        this(fn, g, 10, 0.1f, 0.9f, 100);
    }
    
    public AnnealingAlg(IFunction fn, IGenerator g, float temp_start, 
            float temp_limit, float temp_dec_ratio, int inNeighbourhood)
    {
        this.fn = fn;
        this.generator = g;
        this.temp_start = temp_start;
        this.temp_current = this.temp_start;
        this.temp_limit = temp_limit;
        this.temp_dec_ratio = temp_dec_ratio;
        this.population = inNeighbourhood;
        this.x0 = (Point3DHolder) this.generator.generate(1, this.fn, 
                        this.fn.getXMin(), this.fn.getXMax(),
                        this.fn.getYMin(), this.fn.getYMax()).get(0);
    }
    
    @Override
    public List<Pair<String, String>> getParameters() {
        List<Pair<String, String>> ret = new ArrayList<>();
        
        ret.add(new Pair<>("Start temperature", "10"));
        ret.add(new Pair<>("Temperature limit", "0.1"));
        ret.add(new Pair<>("Temperature decrease ratio", "0.9"));
        ret.add(new Pair<>("Elements in neighbourhood", "100"));
        
        return ret;
    }
    
    @Override
    public boolean hasNext() {
        return this.temp_current > this.temp_limit;
    }

    @Override
    public List<Point3DHolder> next() {
        
        float xLen = Math.abs(this.fn.getXMax() - this.fn.getXMin()) * SCALE;
        float yLen = Math.abs(this.fn.getYMax() - this.fn.getYMin()) * SCALE;
        
        float xMin = this.x0.x - xLen / 2.0f;
        if (xMin < this.fn.getXMin()) xMin = this.fn.getXMin();
        float xMax = this.x0.x + xLen / 2.0f;
        if (xMax > this.fn.getXMax()) xMax = this.fn.getXMax();

        float yMin = this.x0.y - yLen / 2.0f;
        if (yMin < this.fn.getYMin()) yMin = this.fn.getYMin();
        float yMax = this.x0.y + yLen / 2.0f;
        if (yMax > this.fn.getYMax()) yMax = this.fn.getYMax();
        
        List<Point3DHolder> neighbourhood = 
                this.generator.generate(this.population, this.fn, 
                        xMin, xMax,
                        yMin, yMax);
        
        Random generator = new Random();
        int index = generator.nextInt(neighbourhood.size());
        
        Point3DHolder x = neighbourhood.get(index);
        
        float fitness = this.x0.z - x.z;
        
        if (fitness > 0)
        {
            this.x0 = x;
        }
        else 
        {
            float R = generator.nextFloat();
            if (R < Math.exp(R / this.temp_current)) this.x0 = x;
        }
        
        this.temp_current *= this.temp_dec_ratio;
        
        System.out.printf("%d;%f\n", this.genNum++, this.x0.z);
        
        List<Point3DHolder> result = new ArrayList<>();
        result.add(this.x0);
        
        return result;
    }

    @Override
    public IFunction getFn() {
        return this.fn;
    }

    @Override
    public Point3DHolder getBest() {
        return this.x0;
    }
    
}
