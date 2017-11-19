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
import net.sf.surfaceplot.Point3DHolder;

/**
 *
 * @author pan0068
 */
public class ImprovedBlindAlg implements IAlgorithm
{
    private IFunction fn;
    private IGenerator generator;
    private final int iterations;
    private final int population;
    private int generated = 0;
    private Point3DHolder best = null;
    
    private float xMin;
    private float xMax;
    private float yMin;
    private float yMax;
    
    public static final float SCALE = 0.7f;
    
    public ImprovedBlindAlg(IFunction fn, IGenerator g, int iterations, int population)
    {
        this.fn = fn;
        this.generator = g;
        this.iterations = iterations;
        this.population = population;
        
        this.xMin = this.fn.getXMin();
        this.xMax = this.fn.getXMax();
        this.yMin = this.fn.getYMin();
        this.yMax = this.fn.getYMax();
    }
    
    public ImprovedBlindAlg(IFunction fn, IGenerator g)
    {
        this(fn, g, 10, 100);
    }
    
    @Override
    public List<Pair<String, String>> getParameters() {
        List<Pair<String, String>> ret = new ArrayList<>();
        
        ret.add(new Pair<>("Generations", "10"));
        ret.add(new Pair<>("Population", "100"));
        
        return ret;
    }
    
    @Override
    public boolean hasNext()
    {
        return this.generated < this.iterations;
    }
    
    @Override
    public List<Point3DHolder> next()
    {
        List<Point3DHolder> generation = 
                this.generator.generate(this.population, this.fn, 
                        this.xMin, this.xMax,
                        this.yMin, this.yMax);
        
        Point3DHolder min = generation.get(0);
        for (Point3DHolder point : generation)
        {
            if (point.z < min.z) min = point;
        }
        if (this.best == null) this.best = min;
        else if (this.best.z > min.z) 
        {
            this.best = min;
            
            float xLen = Math.abs(this.xMax - this.xMin) * SCALE;
            float yLen = Math.abs(this.yMax - this.yMin) * SCALE;
            
            this.xMin = this.best.x - xLen / 2.0f;
            if (this.xMin < this.fn.getXMin()) this.xMin = this.fn.getXMin();
            this.xMax = this.best.x + xLen / 2.0f;
            if (this.xMax > this.fn.getXMax()) this.xMax = this.fn.getXMax();
            
            this.yMin = this.best.y - yLen / 2.0f;
            if (this.yMin < this.fn.getYMin()) this.yMin = this.fn.getYMin();
            this.yMax = this.best.y + yLen / 2.0f;
            if (this.yMax > this.fn.getYMax()) this.yMax = this.fn.getYMax();
        }
        
        this.generated++;
        
        return generation;
    }

    @Override
    public IFunction getFn() {
        return fn;
    }

    @Override
    public Point3DHolder getBest() {
        return (this.best == null) ? new Point3DHolder(0, 0, 0) : this.best;
    }
    
    
}
