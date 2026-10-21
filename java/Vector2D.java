//simple 2D vector class with basic operations, needed for physic calculations

public class Vector2D{

    public double x, y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D vec){
        return new Vector2D(x + vec.x, y + vec.y);
    }
    public Vector2D sub(Vector2D vec){
        return new Vector2D(x - vec.x, y - vec.y);
    }

    public Vector2D mul(double num){
        return new Vector2D(x * num, y * num);
    }

    public double dot(Vector2D vec) {
        return x * vec.x + y * vec.y;
    }

    public double length(){
        return Math.sqrt(x * x + y * y);
    }

}
