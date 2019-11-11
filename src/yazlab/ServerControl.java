
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
    public  List<Float> information(){
        List<Float> list = new ArrayList<>();
        float ort=(float) (100-(((float)(mainServer.depo.CAPACITY-mainServer.depo.storage)/mainServer.depo.CAPACITY)*100.0));
        list.add(ort);
        for (SubServer subServer : subServers) {
            ort=(float) (100-(((float)(subServer.subDepo.CAPACITY-subServer.subDepo.storage)/subServer.subDepo.CAPACITY)*100.0));
            list.add(ort);            
        }        
        return list;
    }
    
 
    
}
