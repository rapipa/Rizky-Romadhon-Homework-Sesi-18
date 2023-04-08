Deskripsi
Cloud function ini digunakan untuk mengambil data cuaca dari API WeatherAPI.com menggunakan OkHttp dan Gson, dan mengembalikan data dalam bentuk JSON. Cloud function ini ditulis dalam bahasa pemrograman Java 11 dan di-deploy di Google Cloud Functions.

Persyaratan
Java 11
Maven
Akun WeatherAPI.com dan API key yang valid
Akun Google Cloud Platform dengan project yang sudah di-set up
CLI Google Cloud SDK (gcloud)
Dependensi
functions-framework-api
okhttp
gson
Cara Mengkompilasi
Clone atau download repository ini ke komputer Anda.
Buka terminal dan arahkan ke direktori tempat Anda menyimpan repository.
Jalankan perintah mvn package untuk mengkompilasi code dan membangun file JAR.
Setelah proses selesai, file JAR berada di direktori target/.
Cara Menerapkan
Pastikan Anda sudah mempunyai akun WeatherAPI.com dan API key yang valid.
Buat project di Google Cloud Platform dan aktifkan layanan Google Cloud Functions.
Buat cloud function baru di Google Cloud Functions.
Setelah membuat cloud function, buka tab "Runtime, build, and connections".
Pilih "Java 11" pada menu dropdown "Runtime".
Upload file JAR yang sudah dikompilasi ke dalam cloud function.
Atur variabel API_KEY dengan API key yang Anda miliki dari WeatherAPI.com pada menu "Environment Variables".
Simpan perubahan pada cloud function.
Cloud function siap digunakan.
Cara Memanggil Target
Buka terminal dan jalankan perintah gcloud functions call NAMA_FUNCTION --data '{"city":"NAMA_KOTA"}'.
Ganti NAMA_FUNCTION dengan nama cloud function yang sudah Anda buat.
Ganti NAMA_KOTA dengan nama kota yang ingin Anda ambil data cuacanya.
Hasil respon dari cloud function akan ditampilkan di terminal.