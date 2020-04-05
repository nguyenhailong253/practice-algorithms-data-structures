import java.util.HashMap;
import java.util.Map;

/**
 * Google online test - intern 2019
 * 
 * Leetcode easy
 * 
 * https://leetcode.com/discuss/interview-question/356477
 */

class Solution {
    public int calculateTime(String keyboard, String word) {
        int totalTime = 0;

        // Build look up table with key = letter, value = index
        Map<Character, Integer> indexLookUpTable = buildIndexLookUpTable(keyboard);

        int currentIndex = 0;

        // Loop every letter in word
        for (char letter : word.toCharArray()) {
            // Look up the index of that letter
            int letterIndexOnKeyboard = indexLookUpTable.get(letter);
            // Calculate the absolute difference between indices of current position with
            // position of target letter
            int timeToTypeTargetLetter = Math.abs(letterIndexOnKeyboard - currentIndex);

            // Add to total time
            totalTime += timeToTypeTargetLetter;

            // Move current index to newly typed letter
            currentIndex = letterIndexOnKeyboard;
        }

        return totalTime;
    }

    private Map<Character, Integer> buildIndexLookUpTable(String keyboard) {
        Map<Character, Integer> lookUpTable = new HashMap<>();

        char[] keyArray = keyboard.toCharArray();
        for (int index = 0; index < keyArray.length; index++) {
            lookUpTable.put(keyArray[index], index);
        }

        return lookUpTable;
    }
}