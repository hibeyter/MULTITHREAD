
package yazlab;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;


public class ServerCreater {
    
    public List<SubServer> subServers ;
    ServerControl control;
    public ServerCreater() {
        subServers = new ArrayList<>(); 
        MainStorage depo = new MainStorage();
        SubStorage subDepo = new SubStorage();        
        SubStorage subDepo1 = new SubStorage();        
        MainServer mainThread = new MainServer(depo);
        SubServer subThread1 = new SubServer(depo, subDepo);
        SubServer subThread2 = new SubServer(depo, subDepo1);        
        subServers.add(subThread1);
        subServers.add(subThread2);
        control = new ServerControl(mainThread,subServers);  
        Creater creater=new Creater(control, depo);
        Thread thread = new Thread(creater);
        thread.start();
    }
    
}
class Creater implements Runnable{
    ServerControl control;
    MainStorage depo ;

    public Creater(ServerControl control, MainStorage depo) {
        this.control = control;
        this.depo = depo;
    }   

    @Override
    public void run() {
        while (true) {            
            try {                
               sleep(200); 
               List<Double> temp=control.information();
               for (int i = 0; i < temp.size(); i++) {                     
                    double value = temp.get(i);                    
                    if (value>70) {                        
                        SubStorage subDepo = new SubStorage();
                        if(i!=0){
                            subDepo.storage=control.subServers.get(i-1).subDepo.storage/2;
                            control.subServers.get(i-1).subDepo.storage=control.subServers.get(i-1).subDepo.storage/2;
                        }else{
                            subDepo.storage=depo.storage/2;
                            depo.storage=depo.storage/2;
                        }                        
                        SubServer subThread = new SubServer(depo, subDepo);                        
                        control.subServers.add(subThread);
                    }else if (value==0 && control.subServers.size()>2 && i!=0){
                        control.subServers.remove(i);
                    } 
                } 
            } catch (InterruptedException ex) {
                System.err.println(ex.toString());
            }
        }              
    }    
}