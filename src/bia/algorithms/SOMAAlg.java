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
public class SOMAAlg implements IAlgorithm
{
    private final IFunction fn;
    private final IGenerator gen;
    private final float pathLength;
    private final float step;
    private final float prt;
    private final int population;
    private final int generations;
    
    private int generation;
    private Point3DHolder best;
    private List<Point3DHolder> pop;
    
    static private final int DIMENSION = 2;
    
    public SOMAAlg(IFunction fn, IGenerator gen, float pathLength, float step, 
            float prt, int population, int generations)
    {
        this.fn = fn;
        this.gen = gen;
        
        this.pathLength = pathLength;
        this.step = step;
        this.prt = prt;
        this.population = population;
        this.generations = generations;
        
        this.generation = 0;
        this.pop = this.gen.generate(this.population, fn, fn.getXMin(), fn.getXMax(),
                fn.getYMin(), fn.getYMax());
        this.best = this.pop.get(0);
        for (Point3DHolder e : this.pop)
        {
            if (this.best.z > e.z) this.best = e;
        }
    }
    
    public SOMAAlg(IFunction fn, IGenerator g)
    {
        this(fn, g, 3.0f, 0.11f, 0.1f, 100, 10);
    }

    @Override
    public List<Pair<String, String>> getParameters() {
        List<Pair<String, String>> ret = new ArrayList<>();
        
        ret.add(new Pair<>("Path length", "3"));
        ret.add(new Pair<>("Step", "0.11"));
        ret.add(new Pair<>("PRT", "0.1"));
        ret.add(new Pair<>("Population", "100"));
        ret.add(new Pair<>("Generations", "10"));
        
        return ret;
    }
    
    

    @Override
    public boolean hasNext() {
        return this.generation < this.generations;
    }

    @Override
    public List<Point3DHolder> next() {
        List<Point3DHolder> nextPop = new ArrayList<>();
        for (int i = 0; i < this.pop.size(); i++)
        {
            Point3DHolder start = this.pop.get(i);
            float t = this.step;
            Point3DHolder winner = start;
            while (t <= this.pathLength)
            {
                Random random = new Random();
                List<Float> prtvector = new ArrayList<>();
                for (int k = 0; k < DIMENSION; k++)
                {
                    if (random.nextFloat() < this.prt)
                        prtvector.add(1.0f);
                    else
                        prtvector.add(0.0f);
                }
                
                float v_x = start.x + (this.best.x - start.x) * t * prtvector.get(0);
                if (v_x < this.fn.getXMin()) v_x = this.fn.getXMin();
                if (v_x > this.fn.getXMax()) v_x = this.fn.getXMax();
                float v_y = start.y + (this.best.y - start.y) * t * prtvector.get(1);
                if (v_y < this.fn.getYMin()) v_y = this.fn.getYMin();
                if (v_y > this.fn.getYMax()) v_y = this.fn.getYMax();
                List<Float> input = new ArrayList<>();
                input.add(v_x);
                input.add(v_y);
                Point3DHolder v = new Point3DHolder(v_x, v_y, this.fn.fn(input));
                if (winner.z > v.z) winner = v;
                
                t += this.step;
            }
            nextPop.add(winner);
        }
        
        this.pop = nextPop;
        this.best = this.pop.get(0);
        for (Point3DHolder e : this.pop)
        {
            if (this.best.z > e.z) this.best = e;
        }
        
        this.generation++;
        
        return this.pop;
    }

    @Override
    public IFunction getFn() {
        return this.fn;
    }

    @Override
    public Point3DHolder getBest() {
        return this.best;
    }
    
}
