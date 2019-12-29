
/**
 * O(n) complexity O(1) space complexity
 */

class Solution {
    public int characterReplacement(String s, int k) {
        // result
        int maxSubstringLength = 0;

        // setting up lookup table, using character captured in window as index,
        // and its frequency as value
        int[] charFrequency = new int[26]; // A-Z

        // Counter of the char that appears the most in current window frame
        int mostCommonCharCount = 0;

        int startWindowIdx = 0;
        int endWindowIdx = 0;

        while (endWindowIdx < s.length()) {
            char lastCharInWindow = s.charAt(endWindowIdx);

            // As we extend the window & capture new char, increment the char frequency
            charFrequency[lastCharInWindow - 'A']++;

            // Determine the frequency of the most common char, doesn't matter which char
            mostCommonCharCount = Math.max(mostCommonCharCount, charFrequency[lastCharInWindow - 'A']);

            // Condition when to shrink window: if the number of char that is not
            // the dominant one is bigger than k, we need to shrink it
            while (endWindowIdx - startWindowIdx + 1 - mostCommonCharCount > k) {
                charFrequency[s.charAt(startWindowIdx) - 'A']--;
                startWindowIdx++;
            }
            // Can always update this max length because the while before makes sure we
            // are in a valid window
            maxSubstringLength = Math.max(maxSubstringLength, endWindowIdx - startWindowIdx + 1);
            endWindowIdx++;
        }
        return maxSubstringLength;
    }
}