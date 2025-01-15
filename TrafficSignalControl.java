

/**
 *
 * @author KHUTSO
 */

/**
*Control traffic signals to optimize traffic flow
*/
public class TrafficSignalControl {
    
    //Traffic simulation object
    private  TrafficSimulation  trafficSimulation;
    
    /**
     * Constructor to initialize traffic signal control
     * @param trafficSimulation Traffic simulation object
     */
    
    public TrafficSignalControl(TrafficSimulation trafficSimulation){
        this.trafficSimulation=trafficSimulation;
    }
    
    /**
     * Controls traffic signals to minimize congestion
     */
    public void controlTrafficSignals(){
        //Traffic signal controll algorithm
        for(int x=0;x<trafficSimulation.getGridSizeX();x++){
            for(int y=0; y<trafficSimulation.getGridSizeY();y++){
                //Check if there's traffic at current cell
                if(trafficSimulation.getTrafficNetwork()[x][y]==1){
                
                    //Control traffic signals to minimize congestion
                    if(x<trafficSimulation.getGridSizeX()-1){
                       trafficSimulation.getTrafficNetwork()[x + 1][y]=0;
                    }
               }
            }
        }
    }
}
