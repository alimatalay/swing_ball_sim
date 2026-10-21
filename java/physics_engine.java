import java.util.List;

// Physic class handles velocity, gravity, collison check

public class physics_engine {

    private double gravity = 500;     //how many pixels per second^2
    private double ball_bounce = 0.8;           // determines how much ball will keep its speed on collisons                 
    private double kill_movement_limit = 8;         // if vertical speed is lower than this value, the ball will stop

    private double plane_vertical_speed_keep_modifier = 0.75;    // determines how much vertical speed ball will keep after collison with plane
    private double plane_friction = 0.9;                         // determines how much horizontal speed ball will keep after collison with plane, if its set to 1 or never used, balls will move infinitely on ground

    private object_manager objects;

    public physics_engine(object_manager objects){
        this.objects = objects;
    }

    public void step(double tick_seconds){ // function that loop manager will be calling each time tick.

        List<object_ball> balls = objects.get_ball_list();

        for (object_ball b : balls){
            b.velocity.y += gravity * tick_seconds;     //updates the ball position and velocity
            b.update(tick_seconds);
        }

        collide_balls(balls);       //collison check between balls each step
        for (object_ball b : balls) collide_bounds(b);   // collison check for each ball between plane and side walls(app's window walls)
    }

    private void collide_bounds(object_ball b){
        object_plane plane = objects.get_plane();
        double r = object_ball.RADIUS;

        if (b.position.y + r > plane.y){        // if ball collided into the plane
            b.position.y = plane.y - r;     //ball collided into the plane so the position set back to right place
            if (b.velocity.y > 0){
                b.velocity.y = -b.velocity.y * plane_vertical_speed_keep_modifier; // bounces back with losing some of its speed
                if (b.velocity.y > -kill_movement_limit) b.velocity.y = 0;  // if its reach the stopping limit (kill_movement_limit) the speed is set to zero
            }
            b.velocity.x *= plane_friction;       // horizontal friction applied on collison with plane
        }

        if (b.position.x < r){  // if ball collidded into the left window wall
            b.position.x = r;
            b.velocity.x = Math.abs(b.velocity.x) * ball_bounce;
        } else if (b.position.x > plane.width - r){         //else if ball collided into the right window wall
            b.position.x = plane.width - r;
            b.velocity.x = -Math.abs(b.velocity.x) * ball_bounce;
        }
    }

    private void collide_balls(List<object_ball> balls){    //collison check ball to ball

        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++){     //nested loop to check every combination possible

                object_ball a = balls.get(i);
                object_ball b = balls.get(j);

                Vector2D vector_between_centers = b.position.sub(a.position);

                double dist = vector_between_centers.length();

                if (dist >= object_ball.RADIUS * 2) continue;                  //not colliding, no action neccesary

                if (dist == 0) {                                
                    vector_between_centers = new Vector2D(0.001, 0);      //if the window.resize shrinks them or somehow they became exactly at same spot, distance being 0 would break the math
                    dist = 0.001;                                           //so if they end up at the same spot, distance value is set to 0.001 for math operations
                }

                Vector2D normal = vector_between_centers.mul(1 / dist);           //unit vector for direction, its value is exactly 1, so it doesnt affect anything other then direction

                Vector2D push = normal.mul((object_ball.RADIUS * 2 - dist) / 2);    //check how deep they sunk to each other and splits the push into to by dividing it
                a.position = a.position.sub(push);                                  // and corrects the positions
                b.position = b.position.add(push);                      

                double velocity_normal = b.velocity.sub(a.velocity).dot(normal);     //velocity value to check how much balls moving to each other relatively.

                if (velocity_normal < 0){  // checks if balls are moving towards each other, if the value is positive they are getting away from each other already

                    Vector2D impulse = normal.mul(-(1 + ball_bounce) * velocity_normal / 2); // amount of speed they should gain is calculated and seperated by divding 2
                    a.velocity = a.velocity.sub(impulse);
                    b.velocity = b.velocity.add(impulse);

                }
            }
        }
    }
}
