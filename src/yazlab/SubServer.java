
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

    synchronized void syncResponse(int value){
        if(depo.storage>value) depo.storage-=value;                
        else  depo.storage=0;              
    }
    @Override
    public void run() {       
            while (depo.stop) {           
                try {
                    sleep(depo.RESPONSE_MS);
                    Random rValue = new Random();
                    int value = rValue.nextInt(50)+1;
                    syncResponse(value);
                  //System.out.println(" sub verilen cevap= " +value+" depo= "+depo.storage);
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
    synchronized void syncRequest(int value){
        if(depo.storage>value){
            depo.storage-=value;             
            subDepo.storage+=value;
        }else{
           // main storage'daki bütün istekler alınır
           subDepo.storage+=depo.storage;
           depo.storage-=depo.storage;
        } 
    }
    @Override
    public void run() {        
             while (subDepo.stop) {           
                try {
                   sleep(subDepo.REQUEST_MS);
                   Random rValue = new Random();
                   int value = rValue.nextInt(50)+1;
                   syncRequest(value);          
               } catch (InterruptedException ex) {
                   System.out.println(ex.toString());
               }             
             }
             if(!subDepo.stop) System.out.println("Bir Alt Sunucu Kapatıldı");
    }  
   
}