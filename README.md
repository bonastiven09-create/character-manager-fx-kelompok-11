# Character Manager FX v0.7 — Kelompok AP21

Migrasi Character Manager dari Swing (v0.6) ke JavaFX + FXML (v0.7).
Mata Kuliah Java Visual (SIH-6406) · UNPRI Prodi Sistem Informasi.

## Anggota dan Peran

| Nama | NIM | Peran | Username GitHub | Branch |
|---|---|---|---|---|
| Jesslyn | `253303621224` | Model Manager | `[Belum diisi]` | `feature/model` |
| Bona Stiven | `253303621216` | View Manager | `[Belum diisi]` | `feature/view` |
| Slavia | `253303621221` | Controller Manager | `[Belum diisi]` | `feature/controller` |

## Teknologi

Java 25 · JavaFX 25 · Maven · Scene Builder · VS Code

## Cara Menjalankan

```bash
git clone [URL-REPOSITORY-GITHUB]
cd character-manager-fx-[kode-kelompok]
mvn javafx:run
```

## Struktur Project

```text
src/main/java/com/unpri/charactermanager/
  CharacterManagerApp.java
  model/
    Character.java
    CharacterType.java
    CharacterManager.java
  view/
    CharacterController.java
src/main/resources/com/unpri/charactermanager/view/
  CharacterView.fxml
src/test/java/com/unpri/charactermanager/model/
  ModelCheck.java
```

## Alur MVC

```text
View (klik Simpan)
  -> Controller.handleSave()
  -> CharacterManager.isNameTaken()
  -> new Character(...)
  -> CharacterManager.addCharacter()
  -> Controller.refreshList()
  -> kartu dan bar status diperbarui
```

## Screenshot

Screenshot berikut harus dibuat ulang dari aplikasi yang berjalan dan Scene Builder.

![Aplikasi kosong](docs/01-kosong.png)
![Aplikasi berisi data](docs/02-data.png)
![Pesan error validasi](docs/03-error.png)
![Scene Builder](docs/04-scene-builder.png)

## Bukti Model Bersih

```bash
grep -rn "javafx\|javax.swing" src/main/java/com/unpri/charactermanager/model
```

Hasil: tidak ada hasil.

## Uji Model

```bash
mvn -q compile test-compile
mvn -q exec:java -Dexec.mainClass=com.unpri.charactermanager.model.ModelCheck
```

`ModelCheck` memeriksa karakter valid, nama kosong, level invalid, nama duplikat,
salinan daftar, rata-rata kosong, rata-rata bernilai, dan batas level Mage.

## Tabel Uji

| No | Input | Hasil diharapkan | Hasil nyata | Lapisan yang menangkap |
|---|---|---|---|---|
| 1 | Nama kosong | Pesan "Nama wajib diisi" | Pesan ditampilkan | Controller |
| 2 | Level 0 | Pesan "Level harus 1-99" | Pesan ditampilkan | Controller/Model |
| 3 | Level 100 | Pesan "Level harus 1-99" | Pesan ditampilkan | Model |
| 4 | Tipe kosong | Pesan "Role wajib dipilih" | Pesan ditampilkan | Controller |
| 5 | Mage level 61 | Pesan Mage tidak boleh melebihi level 60 | Pesan ditampilkan | Model |
| 6 | Nama valid dan tipe valid | Karakter tersimpan dan kartu tampil | Kartu bertambah | Model + Controller + View |
| 7 | Nama duplikat dengan kapital berbeda | Pesan "Nama sudah digunakan" | Pesan ditampilkan | Model + Controller |
| 8 | Daftar kosong | Jumlah 0 dan rata-rata 0.00 | Status kosong benar | Model + Controller |
| 9 | Dua karakter level 10 dan 20 | Rata-rata 15.00 | Rata-rata benar | Model + Controller |
| 10 | Tombol Reset | Input dikosongkan dan pesan dikosongkan | Form berhasil direset | Controller |

## Kolaborasi Git

- Branch: `feature/model`, `feature/view`, dan `feature/controller`
- Merge strategy: diputuskan oleh tim; gunakan satu strategi secara konsisten:
  `Squash and merge` atau `Create a merge commit`
- Pull Request skeleton: satu PR per anggota sebelum TP16
- Pull Request final: satu PR per anggota setelah integrasi
- Minimal review: satu komentar bermakna dan satu approval untuk setiap PR
- Rotasi review:
  - PR Model direview oleh Controller Manager
  - PR View direview oleh Model Manager
  - PR Controller direview oleh View Manager
- Tag rilis: `v0.7`
- Dosen collaborator: `evtaindra`
- Repository: `[Belum diisi]`
- Pull Request yang sudah merged: `[Belum diisi]`

## Pembagian Kerja

- Jesslyn bertanggung jawab atas Model dan `ModelCheck`.
- Bona Stiven bertanggung jawab atas FXML dan tampilan Scene Builder.
- Slavia bertanggung jawab atas Maven, aplikasi, dan Controller.

## Analisis Individu

- Jesslyn: `[Belum diisi — Google Docs dengan akses Anyone with the link]`
- Bona Stiven: `[Belum diisi — Google Docs dengan akses Anyone with the link]`
- Slavia: `[Belum diisi — Google Docs dengan akses Anyone with the link]`
