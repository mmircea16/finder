/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourholescenter;

/**
 *
 * @author 3818
 * Base class for the other finders
 */
public abstract class Finder {
    protected float xP, yP;
    protected float[] x,y;
    protected int[] order;
    
    protected Finder(float[] xCoord, float[] yCoord){
        
        if (Constants.DEBUG) System.out.println("Constructor of Finder is called.");
        if (xCoord.length != yCoord.length)
            throw new IllegalArgumentException("The input does not represent valid points coordinates");
        else 
            if (xCoord.length != 4){
                throw new IllegalArgumentException("This program can only calculate quadrangles. Input exactly four points");
            }
            else{
                this.x = xCoord;
                this.y = yCoord;
                
                if (Constants.DEBUG) System.out.format("%n_____________________________%nThe points are:"
                + "%n   P0(%f,%f),%n   P1(%f,%f),%n   P2(%f,%f),%n   P3(%f,%f).%n%n",
                x[0],y[0],x[1],y[1],x[2],y[2],x[3],y[3]);                
            }
    }
    
    /**
     * Calculates the intersection of diagonals of the quadrangle defined
     * by the points in this.x and this.y in the order given as parameter
     * @param someOrder 
     */
    protected void calculateCenter(int[] someOrder){
        if (Constants.DEBUG) System.out.format("\n~~Testing pattern %d %d %d %d\n",
                someOrder[0],someOrder[1],someOrder[2], someOrder[3]);
        
        //slope of first line
        float mA = (y[someOrder[2]]-y[someOrder[0]])/(x[someOrder[2]]-x[someOrder[0]]);
        
        //slope of second line
        float mB = (y[someOrder[3]]-y[someOrder[1]])/(x[someOrder[3]]-x[someOrder[1]]);
        
        //calculate intersection point of first and second lines
        this.xP = (y[someOrder[1]]-y[someOrder[0]]+mA*x[someOrder[0]]-mB*x[someOrder[1]])/(mA-mB);
        this.yP = mA*(xP-x[someOrder[0]])+y[someOrder[0]];
    }

    public int[] getOrder(){
        if (Constants.DEBUG) System.out.format("%n__________________________________%n"
                + "The order is: %d -> %d -> %d -> %d from LH->LL->RL->RH%n----------------------------------%n", 
                order[0], order[1], order[2], order[3]);
        return this.order;
    }
    
    public float[] getCenter(){
        float[] temp = new float[]{this.xP,this.yP};
        return temp;
    }
    
    protected abstract void determineOrder();
    
}
