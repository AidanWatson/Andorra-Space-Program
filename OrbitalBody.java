import ecs100.*;
/**
 * Write a description of interface OrbitalBody here.
 *
 * @author AidanWatson
 */
public interface OrbitalBody
{
    
    /**
     * An example of a method header - replace this comment with your own
     *
     * @param  y a sample parameter for a method
     * @return   the result produced by sampleMethod
     */
    public void applyForce(double x, double y);// Alters the speed of body in motion
    public void redraw();
    public void move();
    public double returnX();
    public double returnY();
    public double returnM();
}
