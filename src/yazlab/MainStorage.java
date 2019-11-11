
package yazlab;


public class MainStorage {
  public final int CAPACITY=10000; 
  public final long REQUEST_MS =200; // istek alma  süresi
  public final long RESPONSE_MS=200; // cevaplama süresi
  public int storage=3000; // sunucudaki anlık  istek sayısı
  public int stop; // threadlerin sonlandırılması için
}
