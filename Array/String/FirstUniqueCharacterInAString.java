/**
 * O(n) time and space
 */

class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty())
            return -1;

        if (s.length() == 1)
            return 0;

        Map<Character, Integer> map = buildLookUpMap(s);

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    private Map<Character, Integer> buildLookUpMap(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }
        return map;
    }
}