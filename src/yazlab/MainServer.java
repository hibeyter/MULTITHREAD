
package yazlab;


import static java.lang.Thread.sleep;
import java.util.Random;


public class MainServer {     
    MainStorage  depo;
    public MainServer(MainStorage  depo) {  
         this.depo=depo;
         Cevaplar cevaplar= new Cevaplar(depo);
         Istekler istekler = new Istekler(depo);
         Thread t1 = new Thread(cevaplar);
         Thread t2 = new Thread(istekler);
         t2.start();
         t1.start();
    }
}

class Cevaplar implements Runnable{
    MainStorage depo;
    public Cevaplar(MainStorage depo){
        this.depo=depo;        
    }    
     public synchronized void syncResponse(int value){
        if(depo.storage>value) depo.storage-=value;                
        else  depo.storage=0;              
    }
    @Override
    public void run() {
       while(true){           
            try {
            sleep(depo.RESPONSE_MS);
            Random rValue = new Random();
            int value = rValue.nextInt(50)+1;
             syncResponse(value);
           //System.out.println(depo.stop+" MAİNNNN verilen cevap= " +value+" depo= "+depo.storage);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }
       
           
    }  
}
class Istekler implements Runnable{
    MainStorage depo;
    public Istekler(MainStorage depo){
        this.depo=depo;        
    }    
    
      synchronized void syncRequest(int value){
       if(depo.storage<depo.CAPACITY){                     
            if(depo.storage+value<depo.CAPACITY){
                depo.storage+=value;
            }else depo.storage=depo.CAPACITY;
       }
    }
    @Override
    public void run() {
        while(true){
            depo.stop++;
            try {
                sleep(depo.REQUEST_MS);
                Random rValue = new Random();
                int value = rValue.nextInt(100)+1;
                syncRequest(value);                         
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }         
    }  
}
