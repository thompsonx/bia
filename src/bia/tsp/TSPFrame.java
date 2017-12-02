/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bia.tsp;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

/**
 *
 * @author pan0068
 */
public class TSPFrame extends javax.swing.JFrame {

    class Path
    {
        public List<Point> cities;
        public float fitness;

        public Path(List<Point> cities, float fitness) {
            this.cities = cities;
            this.fitness = fitness;
        }
    }
    
    class PathComparator implements Comparator<Path>
    {

        @Override
        public int compare(Path t, Path t1) {
            if (t.fitness == t1.fitness)
                return 0;
            else if (t.fitness > t1.fitness)
                return 1;
            else
                return -1;
        }
        
    }
    
    private int NP;
    private int G;
    private int E;
    List<Path> population;
    
    /**
     * Creates new form TSPFrame
     */
    public TSPFrame() {
        initComponents();
        
        this.NP = 50;
        this.G = 1000;
        
        List<Point> cities = new ArrayList<>();
        cities.add(new Point(60, 200));
        cities.add(new Point(180, 200));
        cities.add(new Point(80, 180));
        cities.add(new Point(140, 180));
        cities.add(new Point(20, 160));
        cities.add(new Point(100, 160));
        cities.add(new Point(200, 160));
        cities.add(new Point(140, 140));
        cities.add(new Point(40, 120));
        cities.add(new Point(100, 120));
        cities.add(new Point(180, 100));
        cities.add(new Point(60, 80));
        cities.add(new Point(120, 80));
        cities.add(new Point(180, 60));
        cities.add(new Point(20, 40));
        cities.add(new Point(100, 40));
        cities.add(new Point(200, 40));
        cities.add(new Point(20, 20));
        cities.add(new Point(60, 20));
        cities.add(new Point(160, 20));
        
        this.E = cities.size();
        
        this.population = new ArrayList<>();
        for (int i = 0; i < NP; i++)
        {
            this.population.add(new Path(cities, 0));
            Collections.shuffle(this.population.get(i).cities);
            this.population.get(i).fitness = 
                    this.fitness(this.population.get(i).cities);
        }
        
        this.population.sort(new PathComparator());
        
        this.algorithm();
        
        DefaultListModel<String> model = new DefaultListModel<>();
        
        for (Point city : this.population.get(0).cities)
        {
            model.addElement(Integer.toString(city.x) + ", " + 
                    Integer.toString(city.y));
            
        }
        
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
        
        JPanel canvaspanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                
                int zoom = 2;
                
                for (int i = 0; i < E; i++)
                {
                    Point city = population.get(0).cities.get(i);
                    Point next = population.get(0).cities.get(0);
                    if ( (i + 1) < E ) 
                        next = population.get(0).cities.get(i + 1);
                    grphcs.setColor(Color.ORANGE);
                    grphcs.drawLine(city.x * zoom, this.getHeight() - city.y * zoom, 
                            next.x * zoom, this.getHeight() - next.y * zoom);
                    grphcs.setColor(Color.BLUE);
                    grphcs.drawOval(city.x * zoom, this.getHeight() - city.y * zoom, 3, 3);
                }
            }
            
        };
        
        this.panel.add(canvaspanel);
        
        this.listCities.setModel(model);
        
        this.lblFitnessValue.setText(
                Float.toString(this.population.get(0).fitness));
    }
    
    private void algorithm()
    {
        for (int g = 0; g < this.G; g++)
        {
            List<Path> nextGen = new ArrayList<>();
            for (Path parent1 : this.population)
            {
                Random rand = new Random();
                int second = rand.nextInt((int) (this.NP / 2));
                Path parent2 = this.population.get(second);
                
                // CROSSOVER
                int fst = rand.nextInt(this.E);
                int snd = rand.nextInt(this.E);
                
                if (fst > snd)
                {
                    int tmp = fst;
                    fst = snd;
                    snd = tmp;
                }
                
                List<Point> n_path = new ArrayList<>();
                for (int i = 0; i < this.E; i++)
                {
                    if (i == fst)
                    {
                        i = snd;
                        continue;
                    }
                    n_path.add(parent1.cities.get(i));
                }
                
                int insert_pos = fst;
                int limit = snd - fst + 1;
                int i_city = 0;
                for (Point city : parent2.cities)
                {
                    if (i_city >= limit) break;
                    if (!n_path.contains(city))
                            n_path.add(insert_pos++, city);
                }
                
                // MUTATION
                
                int swap1 = rand.nextInt(this.E);
                int swap2 = rand.nextInt(this.E);
                
                Point tmp = n_path.get(swap1);
                n_path.set(swap1, n_path.get(swap2));
                n_path.set(swap2, tmp);
                
                Path descendant = new Path(n_path, this.fitness(n_path));
                if (descendant.fitness < parent1.fitness)
                    nextGen.add(descendant);
                else
                    nextGen.add(parent1);
            }
            this.population = nextGen;
            this.population.sort(new PathComparator());
        }
    }
    
    private float fitness(List<Point> path)
    {
        if (path.size() <= 1) return 0.0f;
        
        float ret = 0;
        for (int i = 0; i < (path.size() - 1); i++)
        {
            Point a = path.get(i);
            Point b = path.get(i + 1);
            ret += Math.sqrt( Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) );
        }
        
        Point fst = path.get(0);
        Point lst = path.get(path.size() - 1);
        ret += Math.sqrt( Math.pow(fst.x - lst.x, 2) 
                + Math.pow(fst.y - lst.y, 2) );
        
        return ret;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFitness = new javax.swing.JLabel();
        lblFitnessValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCities = new javax.swing.JList<>();
        lblCities = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblFitness.setText("FITNESS:");

        lblFitnessValue.setText("none");

        jScrollPane1.setViewportView(listCities);

        lblCities.setText("Cities order:");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFitness)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFitnessValue)
                        .addGap(0, 374, Short.MAX_VALUE))
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCities))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFitness)
                    .addComponent(lblFitnessValue)
                    .addComponent(lblCities))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TSPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TSPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TSPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TSPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TSPFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCities;
    private javax.swing.JLabel lblFitness;
    private javax.swing.JLabel lblFitnessValue;
    private javax.swing.JList<String> listCities;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
