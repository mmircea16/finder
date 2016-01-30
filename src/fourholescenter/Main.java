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
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Imput the list of points below. 
        // x[i] is the ith point's X-axis coordinate, y[i] is the ith point's Y-axis coordinate
        float[] x = new float[]{180.01f,120.04f, 181.13f, 119.88f };
        float[] y = new float[]{ 49.89f,-10.24f, -13.21f, 50.02f}; //order should be 3->1->2->0
        
        //x = new float[]{181.13f,120.04f,180.01f, 119.88f };
        //y = new float[]{-13.21f,-10.24f, 49.89f,  50.02f};  // order should be 3->1->0->3
        
        //Initialize a finder
        FourHolesCenterFinder finder1 = new FourHolesCenterFinder(x,y); 
        int order1[] = finder1.getOrder();
        float[] centerCoord = finder1.getCenter(); //get the center's coordinates
        
        // Random positions of the points
        float[] randomX = new float[4];
        float[] randomY = new float[4];
        java.util.Random r = new java.util.Random();
        
        for (int k=0;k<10;k++)
        {
            for (int p=0;p<4;p++){
                    randomX[p] = (r.nextInt(100)-50.0f+r.nextFloat());
                    randomY[p] = (r.nextInt(100)-50.0f+r.nextFloat());
                }
            try{
                order1 = (new FourHolesCenterFinder(randomX,randomY)).getOrder();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        /*try{           
            
            for (int k=0;k<10;k++){
                for (int p=0;p<4;p++){
                    randomX[p] = r.nextInt(5)+r.nextFloat();
                    randomY[p] = r.nextInt(5)+r.nextFloat();
                }
                order1 = (new FourHolesCenterFinder(randomX,randomY)).getOrder();
                System.out.println("Here");
                order2 = (new OrderFinder(randomX,randomY)).getOrder();
                if (order1!=null & order2!=null)
                    for (int i=0;i<order1.length;i++)
                        if (order1[i]!=order2[i]){
                            System.out.println(">>>>>>>>>>>>>>>>>>>NOT CORRECT>>>>>>>>>>>>>>>>>");
                        break;
                        }
                
            }
        
            float[] centerCoord;
            
        
            FourHolesCenterFinder finder1 = new FourHolesCenterFinder(x,y); //create a new calculator
            // Get the order of the points  
            order1 = finder1.getOrder();
            centerCoord = finder1.getCenter(); //get the center's coordinates
            
            OrderFinder finder2 = new OrderFinder(x,y);
            order2 = finder2.getOrder();
            for (int i=0;i<order1.length;i++)
                if (order1[i]!=order2[i]){
                    System.out.println("NOT CORRECT");
                    break;
                }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }*/
        
//        double[] x1 = new double[]{120.44, 199.97, 273.11};
//        double[] y1 = new double[]{-150.18, -350.02, -214.12};
//        double[] circle = (new threeholescenter.NewClass(x1,y1)).getCircle();
    }
    
}
