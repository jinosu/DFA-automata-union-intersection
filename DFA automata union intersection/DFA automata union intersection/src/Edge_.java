
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author vento
 */
public class Edge_ {

    Vertex vertexA;
    Vertex vertexB;
    String weight;

    boolean isSelect;

    int x_center;
    int y_center;
    int r_center;
    Color color;
    
    Edge_(Vertex a, Vertex b) {
        this.vertexA = a;
        this.vertexB = b;

        this.r_center = 50;
        
        this.weight = "-";
        
        this.isSelect = false;
        if (a == null) {
            this.weight = null;
        }
    }

    boolean inLine(int x0, int y0) {
        return ((x0 - x_center) * (x0 - x_center) + (y0 - y_center) * (y0 - y_center)) <= r_center * r_center ;
    }

    void draw(Graphics2D g) {
        g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.setStroke(new BasicStroke(2));
        if(vertexA != vertexB){
       
            g.draw(new QuadCurve2D.Float(vertexA.x, vertexA.y, x_center, y_center, vertexB.x, vertexB.y));
            drawArrow(g, vertexB.x, vertexB.y,x_center, y_center,   vertexB.x,  vertexB.y);
        }else{
            double angle = Math.atan2(y_center - vertexA.y, x_center - vertexA.x);
            double dx = Math.cos(angle);
            double dy = Math.sin(angle);
            int rc = (int)(vertexA.r*Math.sqrt(2));
            int xloop = vertexA.x - vertexA.r + (int)(dx*rc);
            int yloop = vertexA.y - vertexA.r + (int)(dy*rc);
            g.drawArc(xloop, yloop , vertexA.r*2, vertexA.r*2, 0, 360); 
            
      drawArrow2(g, vertexB.x, vertexB.y,x_center, y_center,   vertexB.x,  vertexB.y);
        }
        g.drawString(weight, x_center, y_center);
    }
    void drawArrow(Graphics g1, int x1, int y1,int x_center, int y_center, int x2, int y2) {
                Graphics2D g = (Graphics2D) g1.create();

                double dx = (x2 - x1), dy = (y2 - y1);
                
                double angle =  Math.atan2(y_center - y1, x_center - x1);
                int len = (int) Math.sqrt((dx*dx) + (dy*dy));
                AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
                at.concatenate(AffineTransform.getRotateInstance(angle));
                g.transform(at);

                // Draw horizontal arrow starting in (0, 0)
              //  g.drawLine(0, 0, len, 0);
        int ARR_SIZE = (int) (vertexB.r/3.6);
            if(vertexB.ap)
        len+=(vertexA.r+6)*1.27;
        else
          len+=(vertexA.r)*1.27;  
                g.fillPolygon(new int[] { len-ARR_SIZE,len, len, len-ARR_SIZE},
                              new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
            }
  void drawArrow2(Graphics g1, int x1, int y1,int x_center, int y_center, int x2, int y2) {
                Graphics2D g = (Graphics2D) g1.create();
                double dx = (x2 - x1), dy = (y2 - y1);
                double angle =  Math.atan2(y_center - y1, x_center - x1);
                int len = (int) Math.sqrt((dx*dx) + (dy*dy));
                AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
                   if(vertexB.ap)
                at.concatenate(AffineTransform.getRotateInstance(angle+0.74));
                else
                   at.concatenate(AffineTransform.getRotateInstance(angle+0.8));
             
                g.transform(at);
                // Draw horizontal arrow starting in (0, 0)
              //  g.drawLine(0, 0, len, 0);
        int ARR_SIZE = (int) (vertexB.r/3.6);
        if(vertexB.ap)
        len+=(vertexA.r+6)*1.27;
        else
          len+=(vertexA.r)*1.27;  
                g.fillPolygon(new int[] { len-ARR_SIZE,len, len, len-ARR_SIZE},
                              new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
            }
  
}