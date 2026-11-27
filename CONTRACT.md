# CONTRACT — Character Manager v0.7

Kesepakatan ini menjadi titik sambung antara Model, View, dan Controller.

## 1. Model
Package: `com.unpri.charactermanager.model`
Pemilik: Jesslyn — Model Manager

| Class | Anggota yang dipakai Controller |
|---|---|
| `Character` | `new Character(name, type, level)` melempar `IllegalArgumentException` bila nama kosong, tipe null, level di luar 1-99, atau Mage melebihi level 60. Menyediakan `getName()`, `getType()`, `getLevel()`, `getHitPoints()`, dan `getAttackPower()`. |
| `CharacterType` | Enum `WARRIOR`, `MAGE`, `ARCHER`, `HEALER`. |
| `CharacterManager` | `boolean addCharacter(Character)`, `ArrayList<Character> getAllCharacters()`, `int getTotalCharacters()`, `boolean isNameTaken(String)`, dan `double getAverageLevel()` (0 bila kosong). |

Kebutuhan Controller -> method Model:

| Kebutuhan | Method |
|---|---|
| Tolak nama ganda | `isNameTaken(name)` |
| Buat karakter | `new Character(name, type, level)` |
| Simpan | `addCharacter(character)` |
| Gambar kartu | `getAllCharacters()` dan getter Character |
| Jumlah | `getTotalCharacters()` |
| Rata-rata level | `getAverageLevel()` |

Model tidak mengimpor `javafx.*` maupun `javax.swing.*`. Aturan nama, tipe, level, dan koleksi tetap berada di Model.

## 2. View / FXML
Berkas: `src/main/resources/com/unpri/charactermanager/view/CharacterView.fxml`
Pemilik: Bona Stiven — View Manager

| fx:id | Tipe node | Fungsi |
|---|---|---|
| `nameField` | `TextField` | Input nama (`onAction="#handleSave"`) |
| `typeCombo` | `ComboBox` | Pilihan `CharacterType` |
| `levelSpinner` | `Spinner` | Input level |
| `messageLabel` | `Label` | Pesan error/sukses |
| `cardsBox` | `VBox` | Wadah kartu karakter |
| `countLabel` | `Label` | Jumlah karakter |
| `avgLevelLabel` | `Label` | Rata-rata level |
| `statusLabel` | `Label` | Ringkasan status bar bawah |

| Tombol | onAction |
|---|---|
| Simpan | `#handleSave` |
| Reset | `#handleReset` |

Nilai `fx:controller` adalah `com.unpri.charactermanager.view.CharacterController`. FXML tidak boleh berisi logika atau perhitungan.

## 3. Controller
Class: `com.unpri.charactermanager.view.CharacterController`
Pemilik: Slavia — Controller Manager

| Method | Keterangan |
|---|---|
| `initialize()` | Mengisi `typeCombo` dan mengatur `levelSpinner` |
| `setManager(CharacterManager)` | Dipanggil oleh `CharacterManagerApp` |
| `handleSave()` | Membaca input, memanggil Model, lalu `refreshList()` |
| `handleReset()` | Mengosongkan input dan pesan |
| `refreshList()` | Membangun kartu, jumlah, rata-rata level, dan status |

Controller bukan tempat aturan bisnis. Validasi domain dilakukan oleh `Character` dan `CharacterManager`.

## 4. Aturan Perubahan
- Perubahan nama `fx:id`, nama handler, atau tanda tangan method wajib lewat Pull Request disetujui ketiga anggota.
- PR yang mengubah kontrak diberi label `contract`.
