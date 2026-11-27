package com.unpri.charactermanager.model;

/**
 * Merepresentasikan satu karakter dalam aplikasi Character Manager.
 * Menerapkan prinsip Encapsulation: semua atribut private,
 * akses hanya melalui getter/setter yang memvalidasi nilai.
 *
 * Catatan: class ini TIDAK boleh mengimpor javax.swing atau java.awt.
 */
public class Character {

    // ===== ATRIBUT STATIC: milik CLASS, dipakai bersama oleh SEMUA object Character =====
    private static int totalCharacterCreated = 0;

    // ===== ATURAN DOMAIN (Sprint #6): batas milik Model, bukan View =====
    private static final int MIN_LEVEL = 1;
    private static final int MAX_LEVEL = 99;
    private static final int MAGE_MAX_LEVEL = 60;

    // ===== ATRIBUT (private) =====
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;                // nilai awal otomatis 0 (default int di Java)

    // ===== CONSTRUCTOR 1: untuk membuat karakter BARU (dipakai form) =====
    public Character(String name, CharacterType type, int level) {
        validate(name, type, level);             // aturan dicek SEBELUM field diisi
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
        totalCharacterCreated++;
    }

    // ===== CONSTRUCTOR 2: untuk MEMUAT karakter dari database / file =====
    // Data dari file tidak melewati form, jadi validasi juga wajib dijalankan di sini.
    public Character(int id, String name, CharacterType type, int level, int hitPoints, int attackPower) {
        validate(name, type, level);
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        totalCharacterCreated++;
    }

    /**
     * Satu-satunya tempat aturan domain untuk nama, role, dan level didefinisikan.
     * Dipanggil oleh kedua konstruktor dan setter, sehingga tidak ada jalur masuk yang terlewat.
     */
    private static void validate(String name, CharacterType type, int level) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (type == null) {
            throw new IllegalArgumentException("Role wajib dipilih");
        }
        if (level < MIN_LEVEL || level > MAX_LEVEL) {
            throw new IllegalArgumentException("Level harus 1-99");
        }
        // Aturan konsistensi antarfield: dua field (type dan level) harus dicek bersama
        if (type == CharacterType.MAGE && level > MAGE_MAX_LEVEL) {
            throw new IllegalArgumentException("Mage tidak boleh melebihi level 60");
        }
    }

    // ===== HELPER PRIVATE: hanya dipakai internal class ini =====
    private int calculateBaseHP() {
        return 100 + (level * 10);   // contoh: level 5 → HP = 150
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);     // contoh: level 5 → Attack = 35
    }

    // ===== GETTER =====
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMana() {
        return mana;
    }

    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    // ===== SETTER: setiap perubahan dicek dengan aturan yang sama =====
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        validate(name, this.type, this.level);
        this.name = name;
    }

    public void setType(CharacterType type) {
        validate(this.name, type, this.level);   // MAGE dengan level > 60 ditolak di sini juga
        this.type = type;
    }

    public void setLevel(int level) {
        validate(this.name, this.type, level);   // rentang 1-99 dan aturan MAGE dicek sekaligus
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    public void setHitPoints(int hitPoints) {
        if (hitPoints < 0) {
            throw new IllegalArgumentException("Hit points tidak boleh negatif");
        }
        this.hitPoints = hitPoints;
    }

    public void setAttackPower(int attackPower) {
        if (attackPower < 0) {
            throw new IllegalArgumentException("Attack power tidak boleh negatif");
        }
        this.attackPower = attackPower;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh negatif");
        }
        this.mana = mana;
    }

    // ===== METHOD PERILAKU =====
    public boolean isAlive() {
        return hitPoints > 0;
    }

    @Override
    public String toString() {
        return "Character{id=" + id
                + ", name='" + name + '\''
                + ", type=" + type
                + ", level=" + level
                + ", hitPoints=" + hitPoints
                + ", attackPower=" + attackPower
                + ", mana=" + mana
                + '}';
    }

    // ===== equals(): membandingkan ISI DATA, bukan alamat memori (nama, case-sensitive) =====
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Character)) return false;
        Character other = (Character) obj;
        return this.name.equals(other.name);
    }
}