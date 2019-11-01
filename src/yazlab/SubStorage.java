
package yazlab;


public class SubStorage {
  public final int CAPACITY=5000; 
  public final long REQUEST_MS =500; // istek alma  süresi
  public final long RESPONSE_MS=300; // cevaplama süresi
  public int storage; // sunucudaki anlık  istek sayısı
  public int stop;
}
