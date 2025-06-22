# Jerry Yakala

**Çocuklar için eğlenceli ve renkli bir refleks oyunu!**

## 📌 Proje Açıklaması

**Jerry Yakala**, çocukların el-göz koordinasyonunu ve reflekslerini geliştirmeyi amaçlayan, renkli ve eğlenceli bir Android oyunudur. Oyunda amaç; ekranda rastgele beliren Jerry karakterine hızlıca dokunarak puan toplamak ve seviyeleri geçmektir. Oyun, çocuklara uygun arayüz ve animasyonlarla tasarlanmıştır.

## 🎯 Hedef Kitle

* 5-12 yaş arası çocuklar
* Eğlenceli ve güvenli mobil oyun arayan ebeveynler

## 🛠️ Kullanılan Teknolojiler

* Android (Java)
* Android Studio
* XML ile özel arayüz tasarımı

---

### 📂 Gerekli Yazılımlar

* Android Studio (2022.1 ve üzeri önerilir)
* JDK 8 veya üzeri
* Android SDK

### ⚙️ Kısa Kurulum Adımları

```bash
# Repository'yi klonlayın
git clone https://github.com/kullaniciadi/JerryYakala.git

# Android Studio ile açın
```

1. Gerekli bağımlılıkları yükleyin.
2. Cihazınızı bağlayın ve çalıştırın veya APK dosyasını yükleyin.

### 📦 Bağımlılıklar

* `androidx.appcompat:appcompat`
* `androidx.constraintlayout:constraintlayout`

---

## 📁 Proje Dizini Yapısı

```
JerryYakala/
├── app/
│   ├── build.gradle
│   ├── libs/
│   ├── proguard-rules.pro
│   └── src/
│       ├── androidTest/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/
│       │   │   └── com/serhatsgr/jerryyakala/
│       │   │       ├── MainActivity.java
│       │   │       ├── Level2.java
│       │   │       ├── Level3.java
│       │   │       ├── Level4.java
│       │   │       ├── Level5.java
│       │   │       └── PauseableCountDownTimer.java
│       │   └── res/
│       │       ├── anim/         (bounce.xml ve fade_in.xml silindi)
│       │       ├── drawable/     (Çoğu özel arkaplan ve icon dosyası silindi)
│       │       ├── layout/
│       │       │   ├── activity_main.xml
│       │       │   ├── activity_level2.xml
│       │       │   ├── activity_level3.xml
│       │       │   ├── activity_level4.xml
│       │       │   └── activity_level5.xml
│       │       │   (dialog_game_over.xml ve dialog_level_success.xml silindi)
│       │       ├── mipmap-hdpi/  (ve diğer mipmap klasörleri)
│       │       ├── raw/
│       │       │   ├── clickjerry.mp3
│       │       │   └── fail.mp3
│       │       ├── values/
│       │       │   ├── colors.xml
│       │       │   ├── strings.xml
│       │       │   └── themes.xml
│       │       └── xml/
│       └── test/
├── build.gradle
├── gradle/
│   └── wrapper/
├── gradle.properties
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle
```

---

## 📱 Kullanım Talimatları

1. Uygulamayı başlatın.
2. Ekranda beliren **Jerry**'ye dokunarak puan toplayın.
3. Zaman bitmeden hedef skora ulaşmaya çalışın.
4. Seviye sonunda başarı veya başarısızlık kartı ile karşılaşacaksınız.

### ⭐ Temel Özellikler

* 🎨 Renkli ve çocuk dostu arayüz
* 🔓 Seviye sistemi
* 🏆 Skor ve zaman kutuları
* ✨ Özel animasyonlar ve buton efektleri
* 🎉 Başarı/başarısızlık kartları

---

## 🖼️ Ekran Görüntüleri

| Ana Menü                                                                              | Oyun Ekranı                                                                           | Oyun İçi                                                                              |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| ![1](https://github.com/user-attachments/assets/508c0e41-cec3-4086-9eae-760e4527149a) | ![2](https://github.com/user-attachments/assets/ae098b7e-66ca-4c3c-bfcf-8bdbe4bdc805) | ![3](https://github.com/user-attachments/assets/f4cd6c8f-257c-421f-9cd3-09ffb952adb5) |
| ![4](https://github.com/user-attachments/assets/84d9bb7f-5a97-4063-a52b-8c902449e3d8) | ![5](https://github.com/user-attachments/assets/7937e84d-fedf-45e4-8f79-7154390db198) |                                                                                       |

---

## 📦 APK Dosyası

* [APK İndir](https://drive.google.com/file/d/11z6zTnLtmoOWcAm8rzb27MtYUK2SqgZD/view?usp=sharing)

---

## 🎥 Demo Video

* [Demo İzle](https://youtube.com/shorts/Rtel6iTs_7I?feature=share)

---

## 👥 Katkıda Bulunanlar

| İsim          | GitHub                                             |
| ------------- | -------------------------------------------------- |
| Serhat Sağır  | [@serhatsgr](https://github.com/serhatsgr)         |
| Mert Sarıkaya | [@Mert-sarikaya](https://github.com/Mert-sarikaya) |

---

## 📄 Lisans

Bu proje **MIT Lisansı** ile lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakabilirsiniz.

---

