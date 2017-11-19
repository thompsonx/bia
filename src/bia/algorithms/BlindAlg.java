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
public class BlindAlg implements IAlgorithm
{
    private IFunction fn;
    private IGenerator generator;
    private final int iterations;
    private final int population;
    private int generated = 0;
    private Point3DHolder best = null;
    
    public BlindAlg(IFunction fn, IGenerator g)
    {
        this(fn, g, 10, 100);
    }
    
    public BlindAlg(IFunction fn, IGenerator g, int iterations, int population)
    {
        this.fn = fn;
        this.generator = g;
        this.iterations = iterations;
        this.population = population;
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
                        this.fn.getXMin(), this.fn.getXMax(),
                        this.fn.getYMin(), this.fn.getYMax());
        
        Point3DHolder min = generation.get(0);
        for (Point3DHolder point : generation)
        {
            if (point.z < min.z) min = point;
        }
        if (this.best == null) this.best = min;
        else if (this.best.z > min.z) this.best = min;
        
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
