
package yazlab;

import java.util.ArrayList;
import java.util.List;

public class ServerControl {

    MainServer mainServer;
    List<SubServer> subServers;   
    public ServerControl(MainServer mainServer, List<SubServer> subServers) {
        this.mainServer = mainServer;
        this.subServers = subServers;
    }
    public  List<Double> information(){
        List<Double> list = new ArrayList<>();
        double ort=100-(((double)(mainServer.depo.CAPACITY-mainServer.depo.storage)/mainServer.depo.CAPACITY)*100.0);
        list.add(ort);
        for (SubServer subServer : subServers) {
            ort=100-(((double)(subServer.subDepo.CAPACITY-subServer.subDepo.storage)/subServer.subDepo.CAPACITY)*100.0);
            list.add(ort);            
        }        
        return list;
    }
    
 
    
}
