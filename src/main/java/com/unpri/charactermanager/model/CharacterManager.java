package com.unpri.charactermanager.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/**
 * Mengelola koleksi Character: tambah, cari, hapus, dan statistik dasar.
 * Menerapkan Composition: CharacterManager "has-a" ArrayList<Character>.
 */
public class CharacterManager {

    /** Batas jumlah karakter dalam daftar. Ini aturan koleksi, jadi milik Model. */
    public static final int MAX_CHARACTERS = 20;

    private ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();
    }

    /**
     * Menambahkan karakter baru ke dalam list.
     * Menolak nama duplikat (cek via findByName) dan menolak jika daftar sudah penuh.
     * @return true jika berhasil ditambahkan, false jika ditolak karena nama duplikat
     * @throws IllegalArgumentException jika daftar sudah berisi MAX_CHARACTERS karakter
     */
    public boolean addCharacter(Character character) {
        if (characterList.size() >= MAX_CHARACTERS) {
            throw new IllegalArgumentException("Daftar penuh (maksimal " + MAX_CHARACTERS + " karakter)");
        }
        if (isNameTaken(character.getName())) {
            System.out.println("❌ Gagal: karakter dengan nama '" + character.getName() + "' sudah ada!");
            return false;
        }
        characterList.add(character);
        System.out.println("✅ Karakter '" + character.getName() + "' berhasil ditambahkan.");
        return true;
    }

    /**
     * Memeriksa apakah nama sudah dipakai, tanpa membedakan huruf besar/kecil.
     * Hanya manager yang mengetahui seluruh daftar, sehingga pemeriksaan ini milik Model.
     */
    public boolean isNameTaken(String name) {
        for (Character c : getAllCharacters()) {
            if (c.getName().equalsIgnoreCase(name)) {    // "arjuna" dianggap sama dengan "Arjuna"
                return true;
            }
        }
        return false;
    }

    /**
     * Mencari karakter berdasarkan nama.
     * @return object Character jika ditemukan, null jika tidak ada
     */
    public Character findByName(String name) {
        for (Character c : characterList) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Menghapus karakter dari list berdasarkan nama.
     * @return true jika berhasil dihapus, false jika tidak ditemukan
     */
    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target == null) {
            System.out.println("❌ Gagal hapus: karakter '" + name + "' tidak ditemukan.");
            return false;
        }
        characterList.remove(target);
        System.out.println("🗑️ Karakter '" + name + "' berhasil dihapus.");
        return true;
    }

    public int getTotalCharacters() {
        return characterList.size();
    }

    /**
     * Mengembalikan SALINAN list, bukan referensi asli,
     * agar pemanggil tidak bisa mengubah data internal manager secara langsung.
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }

    /**
     * Menghitung rata-rata level seluruh karakter.
     * @return rata-rata level, atau 0 jika list kosong (menghindari pembagian dengan nol)
     */
    public double getAverageLevel() {
        if (characterList.isEmpty()) {
            return 0;
        }
        int totalLevel = 0;
        for (Character c : characterList) {
            totalLevel += c.getLevel();
        }
        return (double) totalLevel / characterList.size();
    }

    /**
     * Mengembalikan semua karakter dengan tipe tertentu.
     * @return list baru berisi karakter yang cocok (list kosong jika tidak ada)
     */
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> hasil = new ArrayList<>();
        for (Character c : characterList) {
            if (c.getType() == type) {
                hasil.add(c);
            }
        }
        return hasil;
    }

    /**
     * Menghitung jumlah karakter untuk SETIAP jenis (CharacterType), termasuk yang jumlahnya 0.
     * Hasilnya Map BARU, sehingga mengubah Map ini tidak memengaruhi data di dalam manager.
     *
     * @return Map berisi pasangan jenis karakter dan jumlahnya; tidak pernah null
     */
    public Map<CharacterType, Integer> getTypeDistribution() {
        Map<CharacterType, Integer> distribution = new EnumMap<>(CharacterType.class);

        for (CharacterType t : CharacterType.values()) {
            distribution.put(t, 0);
        }
        for (Character c : characterList) {
            distribution.put(c.getType(), distribution.get(c.getType()) + 1);
        }
        return distribution;
    }

}