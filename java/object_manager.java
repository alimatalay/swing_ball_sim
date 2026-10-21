import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class object_manager{

    public List<object_ball> ball_list = new ArrayList<>();
    public object_plane plane;
    public Random random = new Random();

    public object_manager(int width, int height){
        this.plane = new object_plane(width, height);
    }

    public void addBall(double x, double y){
        Color color = Color.getHSBColor(random.nextFloat(), 0.65f, 0.95f);  //random hue value for each color.
        object_ball ball = new object_ball(x, y, color);
        ball_list.add(ball);
    }

    public void clear_ball_list(){
        ball_list.clear();
    }

    public List<object_ball> get_ball_list(){
        return ball_list;
    }

    public object_plane get_plane(){
        return plane;
    }
}
