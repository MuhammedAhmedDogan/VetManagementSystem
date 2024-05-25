# vet REST API
vet REST API projesi, veteriner kliniğinin kendi işlerini yönetebilmesini sağlayan bir veteriner yönetim sistemi projesidir.
Müşterileri, müşterilere ait hayvanları, hayvanların aşılarını ve tarihlerini, veteriner hekimleri ve uygun günlerini sisteme
kaydetme, silme, güncelleme, görüntüleme işlerini yaparak uygun tarihlere randevu oluşturmayı ve randevuları düzenlemeyi sağlar.

## Teknolojier
Java 21.0.2<br>
PostgreSQL v15.6<br>
Spring Boot v3.2.5<br>
swagger-core v3<br>

## Entity UML
TourismAgencyManagementSystemBackup dosyasını kullanarak database oluşturduktan sonra database.properties içerisine
postgre için db.url, db.user ve db.password bilgilerinizi girerek bağlantıyı sağlayabilirsiniz.<br>
<br>
Sisteme ilk kez girişi yapmak için<br>
Kullanıcı adı : admin<br>
Şifre : admin<br>
## Açıklama
Turizm Acente Sistemi programı, Java Swing kullanılarak hazırlanmış bir masaüstü uygulamasıdır.<br>
Bu programın temel amacı, otel sektöründe faaliyet gösteren işletmenin günlük operasyonlarını daha etkili bir şekilde
yönetmesini sağlamak ve müşteri rezervasyon süreçlerini optimize etmektir.<br>
<br>
Admin yetkisine sahip kişi sisteme login olup hem admin hem acente çalışanı ekleyebilmektedir.<br>
Acente çalışanları sisteme oteller ve bu otellere ait odalar kaydedebilmekte ve müşterilerin taleplerine göre oda araması
yapıp rezervasyon işlemi yapabilmektedir.<br>
<br>