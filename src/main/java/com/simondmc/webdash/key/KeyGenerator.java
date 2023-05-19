package com.simondmc.webdash.key;

public class KeyGenerator {

    private String key;
    public KeyGenerator(int length) {
        final String[] chars = new String[] {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l","m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v","w", "x", "y", "z", "1", "2", "3", "4",
                "5", "6","7", "8", "9", "0", "A", "B", "C", "D",
                "E", "F","G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P","Q", "R", "S", "T", "U", "V", "W", "X",
                "Y", "Z"
        };

        StringBuilder key = new StringBuilder();
        for (int i = 0; i < length; i++) {
            key.append(chars[(int) Math.floor(Math.random() * chars.length)]);
        }
        this.key = key.toString();
    }

    public String getKey() {
        return key;
    }
}
