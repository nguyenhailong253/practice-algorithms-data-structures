import java.util.List;

/**
 * Given two strings, one is a subsequence if all of the elements of the first
 * string occur in the same order within the second string. They do not have to
 * be continguous in the second string, but order must be maintained. For
 * example, given the string 'I like cheese', the words ("I", "cheese") are one
 * possible subsequence of that string. Words are space delimited
 * 
 * Given two strings, s and t, where t is a subsequence of s, report the words
 * of s, missing in t (case sensitive), in the order they are missing.
 * 
 * Example s = "I like cheese", t = "like". Then "like" is the subsequence and
 * ["I", "cheese"] is the list of missing words, in order.
 * 
 * Sample Input:
 * 
 * s = I am using HackerRank to improve programming
 * 
 * t = am HackerRank to improve
 * 
 * Sample Output: I using programming
 * 
 */

class Solution {
    public List<String> missingWords(String s, String t) {
        String[] original = s.split("\\s");
        String[] subsequence = t.split("\\s");
        List<String> missingWords = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < original.length && j < subsequence.length) {
            if (original[i].equals(subsequence[j])) {
                i++;
                j++;
            } else {
                missingWords.add(original[i]);
                i++;
            }
        }

        // in case the subsequence length is shorter than original, anything
        // left in original after subsequence finishes will be in result list
        for (int idx = i; idx < original.length; idx++) {
            missingWords.add(original[idx]);
        }
        return missingWords;
    }
}