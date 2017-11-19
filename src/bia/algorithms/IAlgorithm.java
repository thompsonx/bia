/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.algorithms;

import bia.functions.IFunction;
import bia.utils.Pair;
import java.util.List;
import net.sf.surfaceplot.Point3DHolder;

/**
 *
 * @author pan0068
 */
public interface IAlgorithm {
    
    boolean hasNext();
    List<Point3DHolder> next();
    IFunction getFn();
    Point3DHolder getBest();
    List<Pair<String, String>> getParameters();
    
}
