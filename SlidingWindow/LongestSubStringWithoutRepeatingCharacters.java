
/**
 * The last solution is the most optimal one
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * O(2n) = O(n) time complexity
 * 
 * Using Hash set and use its property of storing unique value to track non
 * repeating character
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        if (s.length() == 1)
            return 1;

        int maxSubstringLength = Integer.MIN_VALUE;

        Set<Character> set = new HashSet<>();

        int startWindowIdx = 0;
        int endWindowIdx = 0;

        while (endWindowIdx < s.length()) {
            // If hash set does not contain char at end window index, add it
            if (!set.contains(s.charAt(endWindowIdx))) {
                set.add(s.charAt(endWindowIdx));
                endWindowIdx++;
                // Update max substring length with current one or window length
                maxSubstringLength = Math.max(endWindowIdx - startWindowIdx, maxSubstringLength);
            } else {
                set.remove(s.charAt(startWindowIdx));
                startWindowIdx++;
            }
        }
        return maxSubstringLength;
    }
}

/**
 * O(n) time complexity O(n) space complexity
 * 
 * Using hash map to store char as key, and its LAST SEEN index as value
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        if (s.length() == 1)
            return 1;

        int maxSubstringLength = Integer.MIN_VALUE;

        Map<Character, Integer> map = new HashMap<>();
        int startWindowIdx = 0;

        for (int endWindowIdx = 0; endWindowIdx < s.length(); endWindowIdx++) {

            if (map.containsKey(s.charAt(endWindowIdx))) {
                // If already contains char, move the start index to the last
                // seen position of this char
                startWindowIdx = Math.max(startWindowIdx, map.get(s.charAt(endWindowIdx)));
            }
            maxSubstringLength = Math.max(endWindowIdx - startWindowIdx + 1, maxSubstringLength);
            // Update current char in map, with last seen position (which is current index)
            map.put(s.charAt(endWindowIdx), endWindowIdx + 1);
        }
        return maxSubstringLength;
    }
}

/**
 * O(n) time complexity O(1) space complexity
 * 
 * Assume ASCII characters => array size is 128
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        if (s.length() == 1)
            return 1;

        int maxSubstringLength = Integer.MIN_VALUE;

        int[] characterIndex = new int[128];
        int startWindowIdx = 0;

        for (int endWindowIdx = 0; endWindowIdx < s.length(); endWindowIdx++) {
            // If the character has not been seen (or reached by the end pointer),
            // its default last seen index will be 0, then start window will stay at 0
            startWindowIdx = Math.max(startWindowIdx, characterIndex[s.charAt(endWindowIdx)]);

            maxSubstringLength = Math.max(endWindowIdx - startWindowIdx + 1, maxSubstringLength);
            characterIndex[s.charAt(endWindowIdx)] = endWindowIdx + 1;
        }

        return maxSubstringLength;
    }
}