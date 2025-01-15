
/**
 *
 * @author HKHUTSO
 */

/**
*Monitors traffic condition and detects congestion
*/
public class TrafficMonitoring {
    //Traffic simulation object
    private TrafficSimulation trafficSimulation;
    
    /**
     * Constructor to initialize traffic monitoring
     * 
     * @param trafficSimulation
     */
    
    public TrafficMonitoring(TrafficSimulation trafficSimulation){
        this.trafficSimulation=trafficSimulation;
    }
    
    /**
     * Monitors traffic conditions and detects congestion
     */
    public void monitorTraffic(){
        //Monitor traffic condition and detect congestion
        for(int x=0;x<trafficSimulation.getGridSizeX();x++){
            for(int y=0;y<trafficSimulation.getGridSizeY();y++){
                //Check if there's traffic at current cell
                if(trafficSimulation.getTrafficNetwork()[x][y]==1){
                    //Detect congestion and update traffic condition
                    if(x<trafficSimulation.getGridSizeX()-1){
                        trafficSimulation.getTrafficNetwork()[x+1][y]=1;
                    }
                }
            }
        }
    }
}
