package com.unpri.charactermanager.model;

public class ModelCheck {
    private static int passed;

    public static void main(String[] args) {
        check("Karakter valid dapat dibuat", () -> {
            Character c = new Character("Arjuna", CharacterType.ARCHER, 20);
            return c.getName().equals("Arjuna") && c.getLevel() == 20;
        });

        check("Nama kosong ditolak", () -> {
            try { new Character("  ", CharacterType.WARRIOR, 10); return false; }
            catch (IllegalArgumentException ex) { return ex.getMessage().equals("Nama tidak boleh kosong"); }
        });

        check("Level di luar rentang ditolak", () -> {
            try { new Character("Bima", CharacterType.HEALER, 100); return false; }
            catch (IllegalArgumentException ex) { return ex.getMessage().equals("Level harus 1-99"); }
        });

        check("Manager menolak nama duplikat", () -> {
            CharacterManager manager = new CharacterManager();
            manager.addCharacter(new Character("Arjuna", CharacterType.ARCHER, 20));
            return !manager.addCharacter(new Character("arjuna", CharacterType.MAGE, 30));
        });

        check("getAllCharacters mengembalikan salinan", () -> {
            CharacterManager manager = new CharacterManager();
            manager.addCharacter(new Character("Satria", CharacterType.WARRIOR, 10));
            manager.getAllCharacters().clear();
            return manager.getTotalCharacters() == 1;
        });

        check("Rata-rata level kosong adalah nol", () -> {
            return new CharacterManager().getAverageLevel() == 0.0;
        });

        check("Rata-rata level dihitung dengan benar", () -> {
            CharacterManager manager = new CharacterManager();
            manager.addCharacter(new Character("A", CharacterType.HEALER, 10));
            manager.addCharacter(new Character("B", CharacterType.WARRIOR, 20));
            return manager.getAverageLevel() == 15.0;
        });

        check("Mage tidak boleh melebihi level 60", () -> {
            try { new Character("Merlin", CharacterType.MAGE, 61); return false; }
            catch (IllegalArgumentException ex) { return ex.getMessage().equals("Mage tidak boleh melebihi level 60"); }
        });

        if (passed != 8) throw new AssertionError(passed + " dari 8 pemeriksaan berhasil");
        System.out.println("Semua " + passed + " pemeriksaan Model berhasil.");
    }

    private static void check(String name, java.util.function.BooleanSupplier check) {
        if (!check.getAsBoolean()) throw new AssertionError("Gagal: " + name);
        passed++;
        System.out.println("[OK] " + name);
    }
}
