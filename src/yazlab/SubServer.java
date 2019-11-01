
package yazlab;

import static java.lang.Thread.sleep;
import java.util.Random;


public class SubServer {
    SubStorage subDepo;
    public SubServer(MainStorage depo,SubStorage subDepo) {  
        this.subDepo=subDepo;
        Cevapla cevapla = new Cevapla(subDepo);
        Istek  istek =new Istek(depo,subDepo);
        Thread t1 = new Thread(istek);
        Thread t2 = new Thread(cevapla);
        t1.start();
        t2.start();
    }
    
}


class Cevapla implements Runnable{
    SubStorage depo;
    public Cevapla(SubStorage depo) {
        this.depo=depo;       
    }    

    @Override
    public void run() {
        while (true) {            
            depo.stop++;
            try {
                sleep(depo.RESPONSE_MS);
                Random rValue = new Random();
                int value = rValue.nextInt(50)+1;
                if(depo.storage>value){
                    depo.storage-=value;                
                }else{
                    depo.storage=0;               
                }
              // System.out.println(depo.stop+" sub verilen cevap= " +value+" depo= "+depo.storage);
            } catch (InterruptedException ex) {
                    System.out.println(ex.toString());
            }
        }
    }

}
class Istek implements Runnable{

    
    MainStorage depo;
    SubStorage subDepo;
    public Istek(MainStorage depo, SubStorage subDepo) {
        this.depo = depo;
        this.subDepo = subDepo;
    }    
    @Override
    public void run() {
        while (true) {            
            subDepo.stop++;
             try {
                sleep(depo.REQUEST_MS);
                Random rValue = new Random();
                int value = rValue.nextInt(50)+1;
                if(depo.storage>value){
                    depo.storage-=value; 
                    if(subDepo.storage<subDepo.CAPACITY)
                         subDepo.storage+=value;
                    else {
                        // kapasite dolmuş
                    }
                }else{
                   // ana serverde yeterli alınacak istek yok
                }              
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }             
        }        
    }    
}