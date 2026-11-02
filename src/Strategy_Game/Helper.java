package Strategy_Game;

import java.util.ArrayList;

/**
 * Small text-file helper based on the tokenizer shown in Lecture6.
 * Lecture6 itself is not modified.
 */
public final class Helper {

    private Helper() {
    }

    public static String[] Tokenizer(String line, char separator) {
        if (line == null) {
            return new String[0];
        }

        ArrayList<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char currentCharacter = line.charAt(i);

            if (currentCharacter == separator) {
                words.add(currentWord.toString());
                currentWord.setLength(0);
            } else {
                currentWord.append(currentCharacter);
            }
        }

        // The last word must be added after the loop.
        words.add(currentWord.toString());
        return words.toArray(new String[0]);
    }
}
