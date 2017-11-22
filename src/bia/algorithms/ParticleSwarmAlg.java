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
public class ParticleSwarmAlg implements IAlgorithm
{
    private final int generations;
    private final int population;
    private final IFunction fn;
    private final IGenerator gen;
    
    private int generation = 0;
    
    private Point3DHolder gBest;
    private List<Point3DHolder> pBest;
    private List<Point3DHolder> pop;
    private List<Point3DHolder> velocity;
    
    private static final float C1 = 0.1f;
    private static final float C2 = 0.1f;
    
    private float w = 1;
    private static final float W_START = 0.9f;
    private static final float W_END = 0.4f;
    
    public ParticleSwarmAlg(IFunction fn, IGenerator gen)
    {
        this(fn, gen, 100, 10);
    }
    
    public ParticleSwarmAlg(IFunction fn, IGenerator gen, int population,
            int generations)
    {
        this.fn = fn;
        this.gen = gen;
        this.population = population;
        this.generations = generations;
        
        this.pop = this.gen.generate(this.population, fn, fn.getXMin(), 
                fn.getXMax(), fn.getYMin(), fn.getYMax());
        this.pBest = this.pop;
        this.velocity = new ArrayList<>();
        
        Random random = new Random();
        
        this.gBest = this.pop.get(0);
        for (Point3DHolder e : this.pop)
        {
            if (this.gBest.z > e.z) this.gBest = e;
            this.velocity.add(new Point3DHolder(random.nextFloat(), 
                    random.nextFloat()));
        }
    }

    @Override
    public boolean hasNext() {
        return this.generation < this.generations;
    }

    @Override
    public List<Point3DHolder> next() {
        
        this.w = W_START - 
                (((W_START - W_END) * this.generation) / this.generations);
        
        for (int i = 0; i < this.pop.size(); i++)
        {
            Random random = new Random();
            float r1 = random.nextFloat();
            float r2 = random.nextFloat();
            
            float velocity_x = this.w * this.velocity.get(i).x + C1 * r1 * 
                    (this.pBest.get(i).x - this.pop.get(i).x) + 
                    C2 * r2 * (this.gBest.x - this.pop.get(i).x);
            float velocity_y = this.w * this.velocity.get(i).y + C1 * r1 * 
                    (this.pBest.get(i).y - this.pop.get(i).y) + 
                    C2 * r2 * (this.gBest.y - this.pop.get(i).y);
            Point3DHolder newVel = new Point3DHolder(velocity_x, velocity_y);
            this.velocity.set(i, newVel);
            
            float v_x = this.pop.get(i).x + velocity_x;
            if (v_x < this.fn.getXMin()) v_x = this.fn.getXMin();
            if (v_x > this.fn.getXMax()) v_x = this.fn.getXMax();
            float v_y = this.pop.get(i).y + velocity_y;
            if (v_y < this.fn.getXMin()) v_y = this.fn.getYMin();
            if (v_y > this.fn.getXMax()) v_y = this.fn.getYMax();
            List<Float> input = new ArrayList<>();
            input.add(v_x);
            input.add(v_y);
            float v_z = this.fn.fn(input);
            
            Point3DHolder newUnit = new Point3DHolder(v_x, v_y, v_z);
            this.pop.set(i, newUnit);
            
            if (newUnit.z < this.pBest.get(i).z)
            {
                this.pBest.set(i, newUnit);
            }
            if (this.pBest.get(i).z < this.gBest.z)
            {
                this.gBest = this.pBest.get(i);
            }
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
        return this.gBest;
    }

    @Override
    public List<Pair<String, String>> getParameters() {
        List<Pair<String, String>> ret = new ArrayList<>();
        
        ret.add(new Pair<>("Population", "100"));
        ret.add(new Pair<>("Generations", "10"));
        
        return ret;
    }
    
    
}

