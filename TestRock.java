import ecs100.*;
/**
 * Write a description of class TestRock here.
 *
 * @author Aidan Watson
 */
public class TestRock implements OrbitalBody
{
    // kinematic fields
    double x;
    double y;
    double Vx;
    double Vy;
    double mass;
    private double radius = 30;

    /**constructor for TestRock objects*/
    public TestRock(double x1,double y1,double Vx1, double Vy1){
        x=x1;
        y=y1;
        Vx=Vx1;
        Vy=Vy1;
        mass=15;
    }

    public void applyForce(double Fx, double Fy)//applys a force of size in the direction 
    {
        Vx=Vx+(Fx/mass);
        Vy=Vy+(Fy/mass);

    }

    public void move(){
        x=x+Vx;
        y=y+Vy;
    }

    public boolean touching(double x, double y, double radius){
        return false;
    }

    public void redraw(){
        UI.fillOval(x,y,radius*2,radius*2);
    }

    public double returnX(){
        return x;
    }

    public double returnY(){
        return y;
    }
    public double returnM(){
    return mass;}

}
