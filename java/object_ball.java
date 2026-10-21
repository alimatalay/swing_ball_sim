import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

// Object class for ball. Field with position, velocity for physics calculations, and color value for rendering.

public class object_ball{

    public static double RADIUS = 20;   // same size for every ball. can be randomized for better visualization, static value for calculcations in physics_engine

    public Vector2D position;
    public Vector2D velocity;
    public Color color;

    public object_ball(double x, double y, Color color) {
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);
        this.color = color;
    }

    public void update(double tick_seconds) {
        position.x += velocity.x * tick_seconds;   
        position.y += velocity.y * tick_seconds;
    }

    public void draw(Graphics2D g){

        int d = (int) Math.round(RADIUS * 2);
        int px = (int) Math.round(position.x - RADIUS);
        int py = (int) Math.round(position.y - RADIUS);     //drawing method used in render_panel

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.fillOval(px, py, d, d);
    }
}
