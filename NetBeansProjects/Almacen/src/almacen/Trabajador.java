package almacen;
import java.awt.*;
import becker.robots.icons.*;

public class Trabajador extends Icon{
    
    public Trabajador(){
        super();
    }
    
    public void paintIcon(Graphics g){
        // Draw body
        g.setColor(Color.GRAY);
        g.fillOval(30, 30, 40, 40);
        // Draw shoulders
        g.setColor(Color.BLACK);
        g.fillRect(20, 45, 10, 10);
        g.fillRect(70, 45, 10, 10);
        // Draw arms
        g.setColor(Color.GRAY);
        g.fillOval(20, 20, 10, 35);
        g.fillOval(70, 20, 10, 35);
        // Draw eyes
        g.setColor(Color.BLACK);
        g.fillOval(35, 40, 10, 10);
        g.fillOval(55, 40, 10, 10);
    }
}
