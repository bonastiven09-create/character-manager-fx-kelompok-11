# Character Manager FX v0.7 — Kelompok AP21

Migrasi Character Manager dari Swing (v0.6) ke JavaFX + FXML (v0.7). Mata Kuliah Java Visual (SIH-6406) · UNPRI Prodi Sistem Informasi.

## Anggota dan Peran

Pembagian peran awal berikut mengikuti urutan anggota yang diberikan tim. Peran dan undian resmi dapat dicocokkan kembali saat TP15.

| Nama | NIM | Peran | Username GitHub | Branch |
|---|---|---|---|---|
| Jesslyn | `[NIM]` | Model Manager | `[username]` | `feature/model` |
| Bona Stiven | `[NIM]` | View Manager | `[username]` | `feature/view` |
| Slavia | `[NIM]` | Controller Manager | `[username]` | `feature/controller` |

## Teknologi

Java 17 · JavaFX 21 · Maven · Scene Builder · VS Code

## Cara Menjalankan

```bash
git clone [URL-REPOSITORY]
cd [NAMA-REPOSITORY]
mvn javafx:run
```

Alternatif dari Maven: `mvn clean javafx:run`.

## Struktur Project

```text
src/main/java/com/unpri/charactermanager/
  CharacterManagerApp.java
  model/
  view/CharacterController.java
src/main/resources/com/unpri/charactermanager/view/
  CharacterView.fxml
src/test/java/com/unpri/charactermanager/model/
  ModelCheck.java
```

## Alur MVC

View (klik Simpan) → Controller.handleSave() → Model.addCharacter() → Controller.refreshList() → kartu, jumlah, dan rata-rata level diperbarui.

## Screenshot

![Aplikasi kosong](docs/01-kosong.png)
![Aplikasi berisi data](docs/02-data.png)
![Pesan error validasi](docs/03-error.png)
![Scene Builder](docs/04-scene-builder.png)

## Bukti Model Bersih

Perintah:

```bash
grep -rn "javafx\|javax.swing" src/main/java/com/unpri/charactermanager/model
```

Hasil: kosong.

## Uji Model

Jalankan pemeriksaan tanpa framework:

```bash
mvn -q compile exec:java -Dexec.mainClass=com.unpri.charactermanager.model.ModelCheck
```

Pemeriksaan mencakup karakter valid, nama kosong, level invalid, nama duplikat, salinan daftar, rata-rata kosong/bernilai, dan batas Mage.

## Tabel Uji

| No | Input | Hasil diharapkan | Hasil nyata | Lapisan yang menangkap |
|---|---|---|---|---|
| 1 | Nama kosong | Pesan "Nama wajib diisi" | Pesan ditampilkan | Controller |
| 2 | Level 0 | Pesan "Level harus 1-99" | Pesan ditampilkan | Model/Controller |
| 3 | Level 100 | Pesan "Level harus 1-99" | Pesan ditampilkan | Model |
| 4 | Tipe kosong | Pesan "Role wajib dipilih" | Pesan ditampilkan | Controller |
| 5 | Mage level 61 | Pesan batas Mage ditolak | Pesan ditampilkan | Model |
| 6 | Nama valid dan tipe valid | Karakter tersimpan dan kartu tampil | Kartu bertambah | Model + Controller + View |
| 7 | Nama yang sudah ada | Pesan "Nama sudah digunakan" | Pesan ditampilkan | Model + Controller |
| 8 | Daftar kosong | Jumlah 0 dan rata-rata 0.00 | Status kosong benar | Controller |
| 9 | Dua karakter level 10 dan 20 | Rata-rata 15.00 | Rata-rata benar | Model + Controller |
| 10 | Tombol Reset | Form dan pemilihan tipe dikosongkan | Form berhasil direset | Controller |

## Kolaborasi Git

- Merge strategy: **Tentukan saat rapat:** Squash and merge atau Merge commit.
- Pull Request yang sudah merged: `Tambahkan tautan semua PR skeleton dan final setelah proses Git.`
- Dosen collaborator: `evtaindra`

## Analisis Individu

- Jesslyn: `[link Google Docs — akses Anyone with the link]`
- Bona Stiven: `[link Google Docs — akses Anyone with the link]`
- Slavia: `[link Google Docs — akses Anyone with the link]`
