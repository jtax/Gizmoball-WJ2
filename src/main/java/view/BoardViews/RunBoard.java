package view.BoardViews;

import model.Coordinate;
import model.Gizmos.Circle;
import view.BoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class RunBoard extends JPanel implements BoardView, Observer {

    private List<Shape> shapes;

    public RunBoard(){
        this.setSize(500,500);
        this.setBackground(Color.WHITE);
       // drawSomething();
        shapes = new ArrayList<Shape>();
        int count = 20, size =22;
        for( int i = 0; i < count; i ++){
            for( int j = 0; j < count; j++){
                Rectangle grid = new Rectangle( 20 + i * size, 20 + j * size, size, size);
                shapes.add(grid);
            }
        }
    }

    public void paintElement(Shape shape){
        shapes.add((Line2D) shape);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        Coordinate cd = new Coordinate(23,4);
        Circle c = new Circle(cd, "pablo");
        for (Shape shape: shapes){
            g2.draw(shape);
        }
    }

    /*private void drawSomething(){
        Graphics2D g2 = (Graphics2D) new Line2D.Double(10, 10, 10, 10);

       // super.paint(g2);
    }*/
    @Override
    public JPanel getPanel() {
        return this;
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
