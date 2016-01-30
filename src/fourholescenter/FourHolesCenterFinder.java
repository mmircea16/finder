/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourholescenter;

/**
 *
 * @author 3818
 */
public class FourHolesCenterFinder extends Finder{
    
    private final int[][] pattern;
 
    public FourHolesCenterFinder(float[] xCoord, float[] yCoord){
        super(xCoord, yCoord);
        
        if (Constants.DEBUG) System.out.println("Constructor of FourHolesCenter is called");
        
        pattern = new int[][]{
            {0,1,2,3},
            {0,1,3,2},
            {0,3,1,2}
        };
        
        determineOrder();
                if (order==null)
                    throw new IllegalArgumentException("The points you gave represent a concave quadrangle. Only convex ones accepted");
    }
    
    @Override
    protected void determineOrder(){
        //Assume the first pattern is the right one
        int index = 0;
        
        //Check all three possibilities, in case the quadrangle is not convex
        while (index < 3){
            //Calculate the intersection point
            calculateCenter(pattern[index]);    
            if (Constants.DEBUG) System.out.format("xP=%f; yP=%f%n",xP,yP);
            
            //Check if it is a valid solution
            if (isSolution(index)){
                if (Constants.DEBUG) System.out.format("Pattern %d is a solution.%n", index);
                order = pattern[index];
                rotateOrder();
                break;
            }
            else
            {
                if (Constants.DEBUG) System.out.format("Pattern %d is *not* a solution.%n", index);
                index++; //try the next pattern                
            }
        }
    }
    
    /**
     * Checks if the i-th pattern represents a solution
     * i.e. the points, connected in the order given by the pattern for a
     * non-intersecting quadrangle
     * @param i
     * @return 
     */    
    private boolean isSolution(int i){  
        //test if the point is within some plausible limits 
        //(in reality only this condition should be enough, as the quadrangle 
        //tends to be a rectangle)
        if ( !isBetweenCoordinates(Constants.LIM_MIN, Constants.LIM_MAX, xP) || !isBetweenCoordinates(Constants.LIM_MIN, Constants.LIM_MAX, yP) )
            return false;
        else
        {
            int[] aPattern = pattern[i];
            return isBetweenCoordinates(this.x[aPattern[0]], this.x[aPattern[2]], this.xP) &
                    isBetweenCoordinates(this.x[aPattern[1]], this.x[aPattern[3]], this.xP) &
                    isBetweenCoordinates(this.y[aPattern[0]], this.y[aPattern[2]], this.yP) &
                    isBetweenCoordinates(this.y[aPattern[1]], this.y[aPattern[3]], this.yP);
        }
    }

    private boolean isBetweenCoordinates(float oneEnd, float anotherEnd, float coordinate) {
        return (Math.min(oneEnd, anotherEnd) < coordinate) &&
                coordinate < Math.max(oneEnd, anotherEnd);
    }
    
    /**
     * Rotates private variable "order" until the order of the points corresponds
     * to the one in the figure on the screen (i.e LH->LL->RL->RH)
     */
    private void rotateOrder(){        
        
        // calculate the angles of the vectors PPi with Y+ axis
        // P(xP, yP), Pi(xi, yi)
        double[] angle = new double[4];
        for (int i=0;i<4;i++){
            System.out.format("x=%f,y=%f: ",x[order[i]],y[order[i]]);
            angle[i] = angleFromY(x[order[i]],y[order[i]]);
        }
        
        //order the points in decreasing order of the angle from Y+
        //the largest angle should be LH, the smallest should be RH
        Boolean ordered = false;
        while (!ordered){
            ordered = true;
            for (int i=0;i<3;i++){
                if (angle[i]<angle[i+1]){
                    ordered = false;
                    double auxAngle = angle[i];
                    angle[i] = angle[i+1];
                    angle[i+1] = auxAngle;
                    int auxOrder = order[i];
                    order[i] = order[i+1];
                    order[i+1] = auxOrder;
                }
            }   
        }
    }

    /**
     * Calculates the angle between axis PPi and Y+
     * P(xP,yP), Pi(xi,yi)
     * @param xCoord = xi
     * @param yCoord = yi
     * @return 
     */
    private double angleFromY(float xCoord, float yCoord){
        if (Constants.DEBUG) System.out.format("Calculating P(%f,%f): ",xCoord, yCoord);
        
        //calculate the sine (cross product)
        double sin = (xCoord - this.xP)/(Math.sqrt(Math.pow(xCoord-this.xP,2)+Math.pow(yCoord-this.yP,2)));
        //calculate the cosine: dot product
        double cos = (yCoord- this.yP)/Math.sqrt(Math.pow(xCoord-this.xP,2)+Math.pow(yCoord-this.yP,2));
        //there are two solutions for the angle
        //select the one which satisfies both cosine and sine 
        double angle = Math.acos(cos);
        if (Math.sin(angle)*sin<0)
            angle = -angle;
        //if (Constants.DEBUG) System.out.format("angle=%f%n",angle*180/Math.PI);
        
        //fit angle:
        angle = angle*180/Math.PI; //in degress
        if (angle<0)
            angle = 360+angle; //between 0~360 deg
        
        if (Constants.DEBUG) System.out.format("sin=%f, cos=%f, angle=%f%n",sin, cos, angle);
        return angle;
    }
    
}
