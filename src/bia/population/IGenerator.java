/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.population;

import bia.functions.IFunction;
import java.util.List;
import net.sf.surfaceplot.Point3DHolder;

/**
 *
 * @author pan0068
 */
public interface IGenerator<T> {

    List<Point3DHolder> generate(final int num, IFunction fn, T xMin, 
            T xMax, T yMin, T yMax);
    
}
