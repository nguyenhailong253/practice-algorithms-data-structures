
/**
 * O(n) complexity O(1) space complexity
 */

class Solution {
    public int characterReplacement(String s, int k) {
        // result
        int maxSubstringLength = 0;

        // setting up windows data
        int[] charFrequency = new int[26]; // A-Z

        int mostCommonCharCount = 0;

        int startWindowIdx = 0;
        int endWindowIdx = 0;

        while (endWindowIdx < s.length()) {
            char lastCharInWindow = s.charAt(endWindowIdx);

            charFrequency[lastCharInWindow - 'A']++;
            mostCommonCharCount = Math.max(mostCommonCharCount, charFrequency[lastCharInWindow - 'A']);

            // condition when to shrink window
            while (endWindowIdx - startWindowIdx + 1 - mostCommonCharCount > k) {

                charFrequency[s.charAt(startWindowIdx) - 'A']--;
                startWindowIdx++;
            }

            // can always update this because the while before makes sure we are in a valid
            // window
            maxSubstringLength = Math.max(maxSubstringLength, endWindowIdx - startWindowIdx + 1);
            endWindowIdx++;
        }

        return maxSubstringLength;
    }
}