import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * O(n*k^2) solution where n is the number of characters in s k is the number of
 * characters in p
 * 
 * Moving static size window - while loop takes O(n) - java substring takes O(k)
 * and the loop inside anagram is O(k) => O(k^2)
 * 
 * O(1) space
 */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length())
            return result;

        int startWindowIndex = 0;
        int endWindowIndex = startWindowIndex + p.length();

        while (endWindowIndex <= s.length()) {
            if (isAnagram(p, s.substring(startWindowIndex, endWindowIndex))) {
                result.add(startWindowIndex);
            }

            startWindowIndex++;
            endWindowIndex++;
        }

        return result;
    }

    private boolean isAnagram(String p, String substring) {
        int[] counts = new int[26];

        for (int i = 0; i < p.length(); i++) {
            counts[p.charAt(i) - 'a']++;
            counts[substring.charAt(i) - 'a']--;
        }

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
 * - outer while loop takes O(n) time - inner while loop takes O(k) time
 * 
 * O(k) space
 */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length())
            return result;

        Map<Character, Integer> targetCharactersFreq = new HashMap<>();

        for (char targetChar : p.toCharArray()) {
            int currentCharFrequency = targetCharactersFreq.getOrDefault(targetChar, 0);
            targetCharactersFreq.put(targetChar, currentCharFrequency + 1);
        }

        int remainingCharToBeAnagram = targetCharactersFreq.size();

        int startWindowIndex = 0;
        int endWindowIndex = 0;

        while (endWindowIndex < s.length()) {

            char currentChar = s.charAt(endWindowIndex);

            if (targetCharactersFreq.containsKey(currentChar)) {
                int currentCharFrequency = targetCharactersFreq.get(currentChar);
                targetCharactersFreq.put(currentChar, currentCharFrequency - 1);

                currentCharFrequency = targetCharactersFreq.get(currentChar);
                if (currentCharFrequency == 0) {
                    remainingCharToBeAnagram--;
                }
            }

            endWindowIndex++;

            while (remainingCharToBeAnagram == 0) {
                char startCharOfAnagram = s.charAt(startWindowIndex);

                if (targetCharactersFreq.containsKey(startCharOfAnagram)) {
                    int startCharFreq = targetCharactersFreq.get(startCharOfAnagram);
                    targetCharactersFreq.put(startCharOfAnagram, startCharFreq + 1);

                    startCharFreq = targetCharactersFreq.get(startCharOfAnagram);
                    if (startCharFreq > 0) {
                        remainingCharToBeAnagram++;
                    }
                }

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
 * - while loop takes O(n) time
 * 
 * O(1) space - constant 256 given p of any length
 */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length())
            return result;

        int[] targetCharactersFreq = new int[256];

        for (char targetChar : p.toCharArray()) {
            targetCharactersFreq[targetChar]++;
        }

        int remainingCharToBeAnagram = p.length();

        int startWindowIndex = 0;
        int endWindowIndex = 0;

        while (endWindowIndex < s.length()) {

            char currentChar = s.charAt(endWindowIndex);

            if (targetCharactersFreq[currentChar] >= 1) {
                remainingCharToBeAnagram--;
            }
            targetCharactersFreq[currentChar]--;
            endWindowIndex++;

            if (remainingCharToBeAnagram == 0) {
                result.add(startWindowIndex);
            }

            int windowLength = endWindowIndex - startWindowIndex;

            if (windowLength == p.length()) {
                char currentStartWindowChar = s.charAt(startWindowIndex);

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