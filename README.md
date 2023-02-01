# Android Kart Oyunu (Harry Potter: Memory Master)

Bu proje ile Android uygulama ve bulut bilişim teknolojilerinin kullanılması amaçlanmaktadır.
Projede belirtilen süre içinde zorluk seviyesine göre kartları doğru eşleştirmek gerekmektedir.

Projenin yapımında Kotlin, Jetpack Compose teknolojileri, veritabanında Firebase ve Android Studio IDE’si kullanılmıs ̧tır.


### Android uygulamasında bulunması beklenen isterler
- Giriş ekranı: Oyun ilk açıldığında ekranda açılacak sayfa giriş ekranı olmalıdır. Kullanıcı bu ekranda, kullanıcı adı ve şifresi ile giriş yapabilmeli, şifre değiştirebilmeli ve kaydolabilmelidir.
- Oyun ekranı: Kullanıcı giriş yaptıktan sonra karşısına gelecek ekran oyun ekranı olmalıdır. Burada Tek Oyuncu ve Çoklu Oyuncu Olarak iki farklı seçenek bulunmalıdır. Oyun ekranı ilk açıldığında “BAŞLA” butonu bulunmalıdır. Oyuncu BAŞLA butonuna tıkladığında oyun ve süre başlatılır.
- Oyun başlatıldığında kartlar kapalı şekilde dağıtılmalıdır. Oyundaki kartların her birinden birer çift bulunmaktadır. Buradaki amaç açılan kartın diğer çiftini bulabilmektir. Oyunda kartlar ilk olarak rastgele dağıtılır.
- Oyun zorluk seviyesi: Oyunda 2*2, 4*4 ve 6*6 olmak üzere 3 farklı zorluk seviyesi vardır.
- Kullanıcı bilgileri telefonda tutulmayacak bulut üzerinden doğrulama (log-in) yapılacaktır.
- Kart bilgileri telefonda tutulmayacak bulut üzerinde bir veri tabanında saklanacak ve kullanıcı oradan erişecektir.

### Tek Oyuncu:
- Kartlar oyunun başında rastgele arka yüzleri kapalı olacak şekilde dağıtılır. Oyuncu bir kartın üzerine tıklar ve kart açılır. Daha sonra oyuncu farklı bir karta tıklayarak kartın eşini bulmaya çalışır.
- Oyun skoru: Oyun süresi 45 saniyedir. Oyunda her kartın bir puanı ve ait olduğu bir ev bulunmaktadır. Oyun skoru her hamle sonrasında ekranda anlık olarak gösterilecektir.
  - Örn- Harry Potter (Puan :10 , Ev: Gryffindor)
  - Oyuncu doğru bir eşleştirme yaparsa [(2*kartın puanı * evin katsayısı) * (kalan
süre / 10) ] kadar puan kazanır.
  - Yanlış bir eşleştirme durumunda iki kart aynı evden ise [(kartların toplam puanı / evin katsayısı) * (geçen süre / 10)] kadar puan kaybeder.
  - Yanlış bir eşleştirme durumunda iki kart farklı evden ise [(kartların puan ortalaması * Ev_1_katsayı * Ev_2_katsayı ) * (geçen süre / 10)] kadar puan kaybeder
- Ev katsayıları
  - Gryffindor : 2
  - Slytherin : 2
  -Hufflepuff : 1 
  - Ravenclaw : 1

### Çoklu Oyuncu:
- Kartlar oyunun başında rastgele arka yüzleri kapalı olacak şekilde dağıtılır. 1. Oyuncu oyuna başlar ve bir kartı seçer. Daha sonrasında kartın eşini bulmaya çalışır. Eğer kartın eşini bulursa aynı oyuncu oyuna devam eder. Eğer kartın eşini bulamazsa sıra rakip oyuncuya geçer.
- Oyun skoru: Oyun süresi 60 saniyedir. Oyunda her kartın bir puanı ve ait olduğu bir ev bulunmaktadır. Her oyuncu sırayla seçim yapar. Doğru bir eşleştirme yapan oyuncu tekrar oynama hakkına sahiptir. Oyun skoru her hamle sonrasında ekranda anlık olarak gösterilecektir.
  - Örn - Harry Potter (Puan :10 , Ev: Gryffindor)
  - Oyuncu doğru bir eşleştirme yaparsa (2*kartın puanı * evin katsayısı) kadar
puan kazanır.
  - Yanlış bir eşleştirme durumunda iki kart aynı evden ise (kartların toplam puanı / evin katsayısı) kadar puan kaybeder.
  - Yanlış bir eşleştirme durumunda iki kart farklı evden ise (kartların puan ortalaması * Ev_1_katsayı * Ev_2_katsayı ) kadar puan kaybeder.
- Ev katsayıları
  - Gryffindor : 2
  - Slytherin : 2
  - Hufflepuff : 1
  - Ravenclaw : 1

### Bulut Platformu:
- Veri tabanı tutulacak. Veritabanında kullanıcı adı, şifresi, ID bilgisi, e-posta hesabı ve kart bilgileri (adı, evi, puanı, kartı resmi) bilgileri tutulmalıdır.
- Resimler veri tabında Base64 tipinde tutulmalıdır.
