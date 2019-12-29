/**
 * 3 Solutions that I attempted, the last one is the most optimal
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * O(n*k^2) solution where n is the number of characters in s k is the number of
 * characters in p.
 * 
 * Moving static size window: while loop takes O(n), java substring takes O(k)
 * and the loop inside isAnagram() is O(k) => O(k^2)
 * 
 * O(1) space an array of constant length (128) is used
 */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        // If p's length is more than s, then there's no anagram in s. Return empty list
        if (s.length() < p.length())
            return result;

        // Create the window, with starting point at index 0 and length = p.length()
        int startWindowIndex = 0;
        int endWindowIndex = startWindowIndex + p.length();

        // "<=" because java's substring(start, end) return characters of the string
        // from start index to end index - 1
        while (endWindowIndex <= s.length()) {
            if (isAnagram(p, s.substring(startWindowIndex, endWindowIndex))) {
                result.add(startWindowIndex);
            }
            // Slide the window
            startWindowIndex++;
            endWindowIndex++;
        }
        return result;
    }

    private boolean isAnagram(String p, String substring) {
        int[] counts = new int[128];

        for (int i = 0; i < p.length(); i++) {
            counts[p.charAt(i) - 'a']++;
            counts[substring.charAt(i) - 'a']--;
        }

        // If substring is anagram of p then count for each character should be
        // even out from previous loop.
        for (int count : counts) {
            if (count != 0)
                return false;
        }
        return true;
    }
}

/**
 * O(n * k) solution where n is the number of characters in s k is the number of
 * characters in p
 * 
 * Outer while loop takes O(n) time - inner while loop takes O(k) time
 * 
 * O(k) space
 */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length())
            return result;

        // Hash map storing character as key, and its frequency as value
        Map<Character, Integer> targetCharactersFreq = new HashMap<>();

        // Populate hash map with characters and frequency in p
        for (char targetChar : p.toCharArray()) {
            int currentCharFrequency = targetCharactersFreq.getOrDefault(targetChar, 0);
            targetCharactersFreq.put(targetChar, currentCharFrequency + 1);
        }

        int remainingCharToBeAnagram = targetCharactersFreq.size();
        int startWindowIndex = 0;
        int endWindowIndex = 0;

        while (endWindowIndex < s.length()) {
            char currentChar = s.charAt(endWindowIndex);

            // As window extends on the end pointer to cover the current character,
            // if hash map contains current char from s, decrease its frequency
            // to mark that the character is within the frame.
            if (targetCharactersFreq.containsKey(currentChar)) {
                int currentCharFrequency = targetCharactersFreq.get(currentChar);
                targetCharactersFreq.put(currentChar, currentCharFrequency - 1);

                // If all appearances of the current character have been found,
                // the number of characters left in anagram decreases.
                currentCharFrequency = targetCharactersFreq.get(currentChar);
                if (currentCharFrequency == 0) {
                    remainingCharToBeAnagram--;
                }
            }
            endWindowIndex++;

            // Condition to move the start index forward - found ONE of
            // many anagrams
            while (remainingCharToBeAnagram == 0) {
                // Get characters at START index
                char startCharOfAnagram = s.charAt(startWindowIndex);

                // Since we will be moving the start index forward, if the character
                // exists in hash map, we add its frequency back
                if (targetCharactersFreq.containsKey(startCharOfAnagram)) {
                    int startCharFreq = targetCharactersFreq.get(startCharOfAnagram);
                    targetCharactersFreq.put(startCharOfAnagram, startCharFreq + 1);

                    // Add the char count to be anagram back, in order to
                    // find another potential anagram
                    startCharFreq = targetCharactersFreq.get(startCharOfAnagram);
                    if (startCharFreq > 0) {
                        remainingCharToBeAnagram++;
                    }
                }

                // If the window length equals the anagram, record its start index
                if (endWindowIndex - startWindowIndex == p.length()) {
                    result.add(startWindowIndex);
                }
                startWindowIndex++;
            }
        }
        return result;
    }
}

/**
 * O(n) solution where n is the number of characters in string s
 * 
 * While loop takes O(n) time
 * 
 * O(1) space - constant (128), given p of any length
 */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length())
            return result;

        // Use array instead of hash map, use char itself as index
        int[] targetCharactersFreq = new int[128];

        // Populate the array above with char and its frequency in p (not s)
        for (char targetChar : p.toCharArray()) {
            targetCharactersFreq[targetChar]++;
        }

        int remainingCharToBeAnagram = p.length();
        int startWindowIndex = 0;
        int endWindowIndex = 0;

        while (endWindowIndex < s.length()) {
            char currentChar = s.charAt(endWindowIndex);

            // If frequency of this char, which we grab from s, still have more
            // occurence left (>=1) based on the array populated with p,
            // Then as we slide the window, we cover that char. Decrement the
            // number of remaining char to be anagram
            if (targetCharactersFreq[currentChar] >= 1) {
                remainingCharToBeAnagram--;
            }

            // As the char is covered by window, decrement its count
            // If char does not exist in p, it will reach -1, to distinguish from
            // char that does.
            targetCharactersFreq[currentChar]--;
            endWindowIndex++;

            if (remainingCharToBeAnagram == 0) {
                result.add(startWindowIndex);
            }

            int windowLength = endWindowIndex - startWindowIndex;

            // If the window size matches p
            if (windowLength == p.length()) {
                char currentStartWindowChar = s.charAt(startWindowIndex);

                // If the freq of the char at start index is >= 0, or not -1,
                // meaning initially it exists in p, hence as we move the start
                // pointer forward, that will not be in the window anymore.
                // We are adding its freq back
                if (targetCharactersFreq[currentStartWindowChar] >= 0) {
                    remainingCharToBeAnagram++;
                }
                targetCharactersFreq[currentStartWindowChar]++;
                startWindowIndex++;
            }
        }
        return result;
    }
}