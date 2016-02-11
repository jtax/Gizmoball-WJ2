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
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by baird on 06/02/2016.
 */
public class RunBoard extends JPanel implements BoardView, Observer {


    public RunBoard(){
        this.setSize(500,500);
        this.setBackground(Color.WHITE);
       // drawSomething();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int count = 20;
        int size = 22;

        for( int i = 0; i < count; i ++){
            for( int j = 0; j < count; j++){
                Rectangle grid = new Rectangle( 20 + i * size, 20 + j * size, size, size);
                g2.draw(grid);
            }
        }
        Coordinate cd = new Coordinate(23,4);
        Circle c = new Circle(cd, "pablo");
        //g2.draw();
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
