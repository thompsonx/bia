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
public class EvolutionStrategyAlg implements IAlgorithm {

    private IFunction fn;
    private IGenerator gen;
    private final int popSize;
    private float deviation;
    private final int generations;
    private int generation;
    private List<Point3DHolder> population;
    private Point3DHolder best;
    private final float intensity;
    
    public EvolutionStrategyAlg(IFunction fn, IGenerator gen)
    {
        this(fn, gen, 100, 10, 1, 0.817f);
    }

    public EvolutionStrategyAlg(IFunction fn, IGenerator gen, int population, 
            int generations, float deviation, float intensity) {
        this.fn = fn;
        this.gen = gen;
        this.popSize = population;
        this.deviation = deviation;
        this.generations = generations;
        this.intensity = intensity;
        
        this.generation = 0;
        this.population = this.gen.generate(popSize, fn, fn.getXMin(),
                fn.getXMax(), fn.getYMin(), fn.getYMax());
        
        this.best = this.population.get(0);
        for (Point3DHolder unit : this.population)
        {
            if (unit.z < this.best.z) this.best = unit;
        }
    }
    
    @Override
    public boolean hasNext() {
        return this.generation < this.generations;
    }

    @Override
    public List<Point3DHolder> next() {
        if (!hasNext()) return this.population;
        
        List<Point3DHolder> nextPop = new ArrayList<>();
        int s_mutations = 0;
        
        Random rand = new Random();
        
        for (Point3DHolder parent : this.population)
        {
            float v_x = parent.x + (float)rand.nextGaussian()*this.deviation;
            if (v_x < this.fn.getXMin()) v_x = this.fn.getXMin();
            if (v_x > this.fn.getXMax()) v_x = this.fn.getXMax();
            
            float v_y = parent.y + (float)rand.nextGaussian()*this.deviation;
            if (v_y < this.fn.getXMin()) v_y = this.fn.getYMin();
            if (v_y > this.fn.getXMax()) v_y = this.fn.getYMax();
            
            List<Float> input = new ArrayList<>();
            input.add(v_x);
            input.add(v_y);
            float v_z = this.fn.fn(input);
            
            Point3DHolder descendant = new Point3DHolder(v_x, v_y, v_z);
            
            Point3DHolder winner;
            if (descendant.z < parent.z) 
            {
                winner = descendant;
                nextPop.add(descendant);
                s_mutations++;
            }
            else 
            {
                winner = parent;
                nextPop.add(parent);
            }
            
            if (winner.z < this.best.z) this.best = winner;
        }
        
        float ratio = (float)s_mutations / (float)this.popSize;
        
        if ( ratio > 1.5f )
        {
            this.deviation = this.deviation / this.intensity;
        }
        else if ( ratio < 1.5f )
        {
            this.deviation = this.deviation * this.intensity;
        }
        
        this.population = nextPop;
        
        this.generation++;
        
        return this.population;
    }

    @Override
    public IFunction getFn() {
        return this.fn;
    }

    @Override
    public Point3DHolder getBest() {
        return this.best;
    }

    @Override
    public List<Pair<String, String>> getParameters() {
        List<Pair<String, String>> ret = new ArrayList<>();
        
        ret.add(new Pair<>("Population", "100"));
        ret.add(new Pair<>("Generations", "10"));
        ret.add(new Pair<>("Deviation", "1"));
        ret.add(new Pair<>("Mutation intensity", "0.817"));
        
        return ret;
    }
    
}
