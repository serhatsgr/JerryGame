# Kurulum Rehberi - Jerry Yakala

Bu rehber, "Jerry Yakala" projesini kendi bilgisayarınıza kurup çalıştırmanız için gerekli adımları ve bilgileri içerir.

## 1. Sistem Gereksinimleri 

Projeyi sorunsuz bir şekilde derlemek ve çalıştırmak için aşağıdaki yazılımların ve donanımların sisteminizde kurulu olması gerekmektedir:

- *İşletim Sistemi*: Windows, macOS veya Linux
- *Android Studio*: 2022.1 (Electric Eel) veya daha yeni bir sürüm.
- *Java Development Kit (JDK)*: JDK 8 veya üzeri. Android Studio genellikle kendi JDK'sını kullanır, bu yüzden ek bir kuruluma gerek kalmayabilir.
- *Android SDK*:
  - *SDK Platforms*: Android 7.0 (Nougat) (API 24) veya üzeri.
  - *SDK Tools*: Android SDK Build-Tools, Android Emulator, Android SDK Platform-Tools.
- *Donanım*:
  - En az 8 GB RAM (Android Studio ve emülatör için önerilir).
  - Android 7.0 veya üzeri bir fiziksel cihaz ya da Android Studio Emülatör'ü.

---

## 2. Kurulum Adımları

Kurulumu iki farklı yöntemle yapabilirsiniz: Kaynak koddan derleyerek veya doğrudan hazır APK dosyasını yükleyerek.

### Yöntem 1: Kaynak Koddan Kurulum (Geliştiriciler için)

Bu yöntem, projeyi geliştirmek veya kodunu incelemek isteyenler içindir.

1.  *Repository'yi Klonlayın:*
    Projeyi bilgisayarınıza indirmek için bir terminal veya komut istemcisi açın ve aşağıdaki komutu çalıştırın:
    bash
    git clone https://github.com/serhatsgr/JerryGame.git
    
    

2.  *Projeyi Android Studio'da Açın:*
    - Android Studio'yu başlatın.
    - Open an Existing Project (Mevcut Projeyi Aç) seçeneğine tıklayın.
    - Bilgisayarınıza klonladığınız JerryYakala klasörünü seçin ve OK'a basın.

3.  *Gradle Senkronizasyonunu Bekleyin:*
    Android Studio, projeyi açtıktan sonra gerekli bağımlılıkları (dependencies) otomatik olarak indirmeye ve projeyi senkronize etmeye başlayacaktır. Bu işlem internet hızınıza bağlı olarak birkaç dakika sürebilir. Sağ alttaki durum çubuğundan ilerlemeyi takip edebilirsiniz.

4.  *Projeyi Çalıştırın:*
    - Bir Android cihazı USB ile bilgisayarınıza bağlayın ve *USB Hata Ayıklama (USB Debugging)* modunu aktif edin.
    - Veya Android Studio'da *AVD Manager* (Sanal Cihaz Yöneticisi) üzerinden bir emülatör başlatın.
    - Üst menüde cihazınızın veya emülatörünüzün göründüğünden emin olun.
    - Yeşil 'Run' (Çalıştır) butonuna (▶️) basın veya Shift + F10 kısayolunu kullanın.

Uygulama derlenip cihazınıza veya emülatörünüze yüklenecektir.

### Yöntem 2: APK Dosyasını Yüklemek (Kullanıcılar için)

1.  *APK Dosyasını İndirin:*
    Linkte yer alan APK dosyasını indirin. 
    
    https://drive.google.com/file/d/11z6zTnLtmoOWcAm8rzb27MtYUK2SqgZD/view?usp=sharing

2.  *Cihazınıza Aktarın:*
    APK dosyasını USB kablosuyla veya başka bir yöntemle Android cihazınıza kopyalayın.

3.  *Yüklemeyi Etkinleştirin:*
    Android cihazınızda Ayarlar > Güvenlik menüsüne gidin ve *"Bilinmeyen Kaynaklar" (Unknown Sources)* seçeneğini aktif hale getirin. Bu, Google Play Store dışından uygulama yüklemenize izin verir.

4.  *APK'yı Yükleyin:*
    Cihazınızdaki dosya yöneticisini kullanarak APK dosyasını bulun ve üzerine dokunarak yüklemeyi başlatın.

---

## 3. Troubleshooting (Sorun Giderme)

- *Hata: Build failed (Derleme Başarısız Oldu)*
  - *Çözüm:* Build > Clean Project ve ardından Build > Rebuild Project seçeneklerini kullanarak projeyi temizleyip yeniden derleyin. Ayrıca Gradle ve SDK sürümlerinizin güncel olduğundan emin olun.

- *Hata: APK not installed (APK Yüklenmedi)*
  - *Çözüm:* Cihazınızda uygulamanın eski bir sürümü varsa kaldırın. Cihazınızda yeterli depolama alanı olduğundan ve "Bilinmeyen Kaynaklar" seçeneğinin aktif olduğundan emin olun.

- *Hata: App keeps stopping (Uygulama Sürekli Duruyor)*
  - *Çözüm:* Android Studio'daki *Logcat* panelini kontrol ederek hatanın kaynağını tespit edebilirsiniz. Genellikle bu, kod içerisindeki bir mantık hatasından veya eksik bir kaynaktan (resource) kaynaklanır.
