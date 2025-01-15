
/**
 *
 * @author KHUTSO
 */
public class TrafficSimulation {
    //Grid size in x and y direction
    private int gridSizeX;
    private int gridSizeY;
    
    //Traffic network represented as a 2D array
    private int[][] trafficNetwork;
    
    
    /**Constructor to initialize traffic simulation
     * 
     * @param gridSizeX Grid size in x direction
     * @param gridSizeY Grid size in y direction
     */
    public TrafficSimulation(int gridSizeX,int gridSizeY){
        
        this.gridSizeX=gridSizeX;
        this.gridSizeY=gridSizeY;
        this.trafficNetwork=new int[gridSizeX][gridSizeY];
    }
    
    
    /**
     * Simulates traffic flow by updating traffic conditions
     */
    public void simulateTraffic(){
        //Simple traffic simulation algorithm
        for (int x=0; x< gridSizeX; x++){
            for (int y=0; x< gridSizeY; y++){
                //Check of threre's traffic at current cell
                if(trafficNetwork[x][y]==1){
                    //move traffic to the next cell
                    
                    trafficNetwork[x][y]=0;
                    if(x< gridSizeX-1){
                        trafficNetwork[x +1][y]=1;
                    }
                }
            }
          }
    }
    /**
    *Prints the current traffic network
    */
    public void printTrafficNetwork(){
        for (int x=0;x<gridSizeX; x++){
            for(int y=0; y<gridSizeY; y++){
                System.out.print(trafficNetwork[x][y] +"");
            }
            System.out.println();
        }
    }

    int getGridSizeX() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int getGridSizeY() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int[][] getTrafficNetwork() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void updateTrafficNetwork(int emergencyVehicleX, int emergencyVehicleY) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void clearTraffic(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
