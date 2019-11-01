## SUNUCU İSTEK YOĞUNLUĞUNUN MULTITHREAD İLE KONTROLÜ
***

![heads](https://github.com/hibeyter/Yazlab/blob/master/imgs/a.jpg)

***
### Proje bileşenlerinin özellikleri:

* **Ana Sunucu (Main Thread):** 10000 istek kapasitesine sahiptir. 500 ms zaman aralıklarıyla [1-100] arasında rastgele sayıda istek kabul etmektedir. İstek olduğu sürece 200 ms zaman aralıklarıyla [1-50] arasında rastgele sayıda isteğe geri dönüş
yapmaktadır.

* **Alt Sunucu (Sub Thread):** 5000 istek kapasitesine sahiptir. 500 ms zaman aralıklarıyla [1-50] arasında rastgele sayıda ana sunucudan istek almaktadır. İstek olduğu sürece 300 ms zaman aralıklarıyla [1-50] arasında rastgele sayıda isteğe geri
dönüş yapmaktadır.

* **Alt sunucu oluşturucu (Sub Thread)**: Mevcut olan alt sunucuları kontrol eder. Eğer herhangi bir alt sunucunun kapasitesi %70 ve üzerinde ise yeni bir alt sunucu oluşturur ve kapasitenin yarısını yeni oluşturduğu alt sunucuya gönderir. Eğer herhangi bir alt sunucunun kapasitesi %0 a ulaşır ise mevcut olan alt sunucu sistemden kaldırılır. En az iki alt sunucu sürekli çalışır durumda kalması gerekmektedir.

* **Sunucu takip (Sub Thread):** Sistemde mevcut olan tüm sunucuların bilgilerini(Sunucu/Thread sayısı, ve kapasiteleri(%)) canlı olarak göstermektedir.
