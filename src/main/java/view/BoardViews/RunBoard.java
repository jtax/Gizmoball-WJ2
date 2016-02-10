package view.BoardViews;

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

/**
 * Created by baird on 06/02/2016.
 */
public class RunBoard extends JPanel implements BoardView {


    public RunBoard(){
        this.setSize(500,500);
        this.setBackground(Color.RED);
        drawSomething();
    }


    private void drawSomething(){
        //Graphics2D g2 = (Graphics2D) new Line2D.Double(10, 10, 10, 10);

       // super.paint(g2);
    }
    @Override
    public JPanel getPanel() {
        return this;
    }
}
