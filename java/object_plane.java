import java.awt.Color;
import java.awt.Graphics2D;

//Plane object class, stands at the bottom of the screen as ground.

public class object_plane {

    public int height = 40;

    public double y;
    public double width;
 
    public object_plane(double width, double window_height) { //takes width and windows height as parameters
        resize(width, window_height);
    }

    public void resize(double width, double window_height) {
        this.width = width;
        this.y = window_height - height;  // bottom is equal to window_height so top of plane will be equal to this value.
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(60, 60, 70));
        g.fillRect(0, (int) y, (int) (width), height);     //draw method, called in render_panel
        g.setColor(new Color(120, 120, 140));
    }
}
