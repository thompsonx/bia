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
public class JDEEvolutionAlg implements IAlgorithm
{
    private IFunction fn;
    private IGenerator generator;
    private final int iterations;
    private final int populationSize;
    private int generated = 0;
    private Point3DHolder best = null;
    private List<Point3DHolder> population;
    private List<Float> Fi;
    private List<Float> CRi;
    
    public static final float T = 0.1f;
    
    public JDEEvolutionAlg(IFunction fn, IGenerator g, int iterations, 
            int populationSize, float F, float CR)
    {
        this.fn = fn;
        this.generator = g;
        this.iterations = iterations;
        this.populationSize = populationSize;
        this.population = this.generator.generate(this.populationSize, this.fn, 
                        this.fn.getXMin(), this.fn.getXMax(),
                        this.fn.getYMin(), this.fn.getYMax());
        this.Fi = new ArrayList<>();
        this.CRi = new ArrayList<>();
        for (int i = 0; i < populationSize; i++)
        {
            this.Fi.add(F);
            this.CRi.add(CR);
        }
    }
    
    public JDEEvolutionAlg(IFunction fn, IGenerator g)
    {
        this(fn, g, 10, 100, 0.5f, 0.9f);
    }
    
    @Override
    public List<Pair<String, String>> getParameters() {
        List<Pair<String, String>> ret = new ArrayList<>();
        
        ret.add(new Pair<>("Generations", "10"));
        ret.add(new Pair<>("Population", "100"));
        ret.add(new Pair<>("F", "0.5"));
        ret.add(new Pair<>("CR", "0.9"));
        
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
        List<Point3DHolder> nextGeneration = new ArrayList<>();
        
        int i = 0;
        for (Point3DHolder target : this.population)
        {
            Random random = new Random();
            
            float[] params = new float[4];
            params[0] = this.Fi.get(i);
            params[1] = this.CRi.get(i);
            float F_next = this.Fi.get(i);
            if (random.nextFloat() < T)
            {
                F_next = 0.1f + random.nextFloat() * 1;
                if (F_next > 1.0f) F_next = 1.0f;
            }
            params[2] = F_next;
            float CR_next = this.CRi.get(i);
            if (random.nextFloat() < T)
                CR_next = random.nextFloat();
            params[3] = CR_next;
            
            List<Point3DHolder> us = new ArrayList<>();
            
            for (int n = 0; n < 4; n+=2)
            {
                Point3DHolder[] solution = new Point3DHolder[3];
                for (int s = 0; s < 3; s++)
                    solution[s] = this.population.get(random.nextInt(this.populationSize));

                float v_x = solution[0].x + params[n] * (solution[1].x - solution[2].x);
                if (v_x < this.fn.getXMin()) v_x = this.fn.getXMin();
                if (v_x > this.fn.getXMax()) v_x = this.fn.getXMax();
                float v_y = solution[0].y + params[n] * (solution[1].y - solution[2].y);
                if (v_y < this.fn.getYMin()) v_y = this.fn.getYMin();
                if (v_y > this.fn.getYMax()) v_y = this.fn.getYMax();
                Point3DHolder v = new Point3DHolder(v_x, v_y);

                int rand = random.nextInt(2);
                float u_x, u_y;
                if (rand == 0)
                {
                    u_x = v.x;
                    u_y = (random.nextFloat() < params[n+1]) ? v.y : target.y;
                }
                else
                {
                    u_x = (random.nextFloat() < params[n+1]) ? v.x : target.x;
                    u_y = v.y;
                }            
                List<Float> input = new ArrayList<>();
                input.add(u_x);
                input.add(u_y);
                float u_z = this.fn.fn(input);
                Point3DHolder u = new Point3DHolder(u_x, u_y, u_z);
                us.add(u);
            }
            
            Point3DHolder u_winner;
            
            if (us.get(0).z <= us.get(1).z)
                u_winner = us.get(0);
            else
            {
                u_winner = us.get(1);
                this.Fi.set(i, F_next);
                this.CRi.set(i, CR_next);
            }
            
            if (u_winner.z <= target.z)
                nextGeneration.add(u_winner);
            else
                nextGeneration.add(target);
            i++;
        }
        
        Point3DHolder min = nextGeneration.get(0);
        for (Point3DHolder point : nextGeneration)
        {
            if (point.z < min.z) min = point;
        }
        if (this.best == null) this.best = min;
        else if (this.best.z > min.z) this.best = min;
        
        this.generated++;
        
        this.population = nextGeneration;
        
        return nextGeneration;
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
