package almacen;
import java.awt.*;
import becker.robots.icons.*;

public class Robotamazon extends Icon {
    
    public Robotamazon(){
        super();
    }
    
    public void paintIcon(Graphics g){
        // Draw body
        g.setColor(Color.ORANGE);
        g.fillRect(30, 28, 40, 40);
        // Draw shoulders
        g.setColor(Color.BLUE);
        g.fillRect(20, 45, 10, 10);
        g.fillRect(70, 45, 10, 10);
        // Draw arms
        g.setColor(Color.BLACK);
        g.fillOval(20, 20, 10, 35);
        g.fillOval(70, 20, 10, 35);
        // Draw eyes
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(30, 30, 38, 38);
    }
}
