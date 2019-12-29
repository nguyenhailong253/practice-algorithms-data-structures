import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * O(2n) = O(n) complexity
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
            if (!set.contains(s.charAt(endWindowIdx))) {
                set.add(s.charAt(endWindowIdx));
                endWindowIdx++;
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
                startWindowIdx = Math.max(startWindowIdx, map.get(s.charAt(endWindowIdx)));
            }

            maxSubstringLength = Math.max(endWindowIdx - startWindowIdx + 1, maxSubstringLength);
            map.put(s.charAt(endWindowIdx), endWindowIdx + 1);
        }

        return maxSubstringLength;
    }
}

/**
 * O(n) time complexity Assume ASCII characters => array size is 128 O(1) space
 * complexity
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

            startWindowIdx = Math.max(startWindowIdx, characterIndex[s.charAt(endWindowIdx)]);

            maxSubstringLength = Math.max(endWindowIdx - startWindowIdx + 1, maxSubstringLength);
            characterIndex[s.charAt(endWindowIdx)] = endWindowIdx + 1;
        }

        return maxSubstringLength;
    }
}