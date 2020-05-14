
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 *
 * @author vento
 */
public class Vertex {

    int x;
    int y;
    String name;
    int r;
    int shift ;
    boolean isSelect;
    boolean ap=false;
    int id;
    static public int idGen = 0;

    @Override
    public String toString() {
        return name; //To change body of generated methods, choose Tools | Templates.
    }
    
    Vertex(int x, int y) {
        this.id = idGen;
        idGen++;
        this.r = 36;
        this.shift = 30;
        this.x = x;
        this.y = y;
        this.name = "q"+String.valueOf(id);
        this.isSelect = false;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
    
    boolean inCircle(int x0, int y0) {
        return ((x0 - x) * (x0 - x) + (y0 - y) * (y0 - y)) <= r * r;
    }
    void  setap(){
        if(ap){
            ap=false;
        }
        else{
        ap = true;
        }
    }
    void draw(Graphics2D g) {
        if(!ap){
        g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.setStroke(new BasicStroke(2));

        g.fillOval(x - r, y - r, r * 2, r * 2);
        
        g.setColor(Color.WHITE);
        g.fillOval(x - r + (r - shift) / 2, y - r + (r - shift) / 2, r * 2 - (r - shift), r * 2 - (r - shift));

        g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.drawString(name, x - 10, y + 10);
        }
        else{
              g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.setStroke(new BasicStroke(2));
      int t =r+7;
        g.fillOval(x - t, y - t, t * 2, t * 2);
        
        g.setColor(Color.WHITE);
        t = r +5;
        g.fillOval(x - t, y - t, t * 2, t * 2);
          g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.setStroke(new BasicStroke(2));
         t =r+2;
        g.fillOval(x - t, y - t, t * 2, t * 2);
        
        g.setColor(Color.WHITE);
        t = r;
        g.fillOval(x - t, y - t, t * 2, t * 2);
        g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.drawString(name, x - 10, y + 10);
        }
    }

}

