/**
 * Pramp solution 1 - O(n^2 * m) time, m = length of a word, n = number of words
 * in array. O(n) space
 */

class Solution {

    static int shortestWordEditPath(String source, String target, String[] words) {
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair(source, 0));

        Set<String> seen = new HashSet<>();
        seen.add(source);

        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.remove();
            String word = node.getKey();
            int pathLength = node.getValue();

            if (word.equals(target))
                return pathLength;

            // Loop each word in the given array - for each node in queue, we loop through
            // array
            // again to check which one is 1 diff char
            // This leads to O(n^2 * m) - bottelneck, could be improved by using adjacency
            // list
            for (String comparedWord : words) {

                // Check if 2 words have the same length
                if (word.length() == comparedWord.length()) {

                    boolean isOneCharDifferent = isOneCharDifferent(word, comparedWord);
                    if (isOneCharDifferent && !seen.contains(comparedWord)) {
                        queue.add(new Pair(comparedWord, pathLength + 1));
                        seen.add(comparedWord);
                    }
                }
            }
        }
        return -1;
    }

    static boolean isOneCharDifferent(String word, String comparedWord) { // O(m)
        int diffCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != comparedWord.charAt(i)) {
                diffCount++;
                if (diffCount == 2)
                    break;
            }
        }
        return diffCount == 1;
    }
}

/**
 * Pramp solution 2 - O(n*m) time, m = length of a word, n = number of words in
 * array. O(n) space
 */

class Solution {

    static int shortestWordEditPath(String source, String target, String[] words) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Set<String> wordSet = convertArrayToSet(words);
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair(source, 0));

        Set<String> seen = new HashSet<>();
        seen.add(source);

        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.remove();
            String word = node.getKey();
            int pathLength = node.getValue();

            if (word.equals(target))
                return pathLength;

            for (int i = 0; i < word.length(); i++) {

                // Loop 26 times to replace each character from alphabet with character at i
                // index
                for (char character : alphabet.toCharArray()) {
                    String newWord = word.substring(0, i) + character + word.substring(i + 1, word.length());

                    // If the word with replaced char exists in the given array (using wordSet for
                    // O(1) time checking) & not visited
                    if (wordSet.contains(newWord) && !seen.contains(newWord)) {
                        queue.add(new Pair(newWord, pathLength + 1));
                        seen.add(newWord);
                    }
                }
            }
        }
        return -1;
    }

    static Set<String> convertArrayToSet(String[] arr) {
        Set<String> set = new HashSet<>();
        for (String s : arr)
            set.add(s);
        return set;
    }
}

/**
 * Leetcode O(m * n) time & space where m = length of a word, n = number of
 * words in wordList
 * 
 * Source: https://leetcode.com/articles/word-ladder/
 */
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1. Pre-process each word in wordList - build a map with key = intermediate
        // word, val = [words that share same intermediate word]
        Map<String, List<String>> intermediateWords = buildIntermediateWordsMap(wordList);

        // 2. Push tuple containing beginWord and 1 in a queue - 1 represents pathLength
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        int pathLength = 1;
        queue.add(new Pair(beginWord, pathLength));

        // 3. Create a visited map - prevent cycles
        Map<String, Boolean> visitedNodes = new HashMap<>();
        visitedNodes.put(beginWord, true);

        // 4. While queue not empty, get front element
        while (!queue.isEmpty()) {
            Pair<String, Integer> curPair = queue.remove();
            String curWord = curPair.getKey();
            pathLength = curPair.getValue();

            // 5. Find all intermediate forms of current word, use each of them to look up
            // in the map in step 1 to find neighbouring words/nodes

            for (int i = 0; i < curWord.length(); i++) {
                String intermediateWord = curWord.substring(0, i) + '*' + curWord.substring(i + 1, curWord.length());
                List<String> neighbours = intermediateWords.getOrDefault(intermediateWord, new ArrayList<>());

                // 6. For each neighbour, add to queue, with the pathLength = current word's
                // pathLength + 1 - representing next potential node, which would mean
                // increasing the path
                for (String neighbour : neighbours) {
                    // 8. If reach endWord, return its pathLength
                    if (neighbour.equals(endWord))
                        return pathLength + 1;

                    // 9. Check if visited, if not, add to queue and mark visited
                    if (!visitedNodes.containsKey(neighbour)) {
                        queue.add(new Pair(neighbour, pathLength + 1));
                        visitedNodes.put(neighbour, true);
                    }
                }
            }
        }
        return 0;
    }

    private Map<String, List<String>> buildIntermediateWordsMap(List<String> wordList) {
        Map<String, List<String>> intermediateWords = new HashMap<>();

        for (String word : wordList) {
            // for each word in wordList, what are the intermediate forms do I have
            for (int i = 0; i < wordList.get(0).length(); i++) {
                // for each form, check with map, if exist, add myself. Otherwise, create empty
                // list, add myself
                String intermediateWord = word.substring(0, i) + '*' + word.substring(i + 1, word.length());
                List<String> neighbours = intermediateWords.getOrDefault(intermediateWord, new ArrayList<>());

                neighbours.add(word);
                intermediateWords.put(intermediateWord, neighbours);
            }
        }
        return intermediateWords;
    }
}

/**
 * Leetcode O(m * n) time & space wher m = length of a word, n = number of words
 * in wordList
 * 
 * Source: https://leetcode.com/articles/word-ladder/
 */

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 0. Check if list contains endWord - this is extra step compared to prev
        // solution since we add another BFS from endWord so have to make sure it exists
        if (!wordList.contains(endWord))
            return 0;

        // 1. Pre-process each word in wordList - build a map with key = intermediate
        // word, val = [words that share same intermediate word]
        Map<String, List<String>> intermediateWords = buildIntermediateWordsMap(wordList);

        // 2. Push tuple containing beginWord and 1 in a queue - 1 represents pathLength
        // Push another tuple containing endWord and 1 in another queue
        Queue<Pair<String, Integer>> startQueue = new LinkedList<>();
        Queue<Pair<String, Integer>> endQueue = new LinkedList<>();

        int pathLength = 1;
        startQueue.add(new Pair(beginWord, pathLength));
        endQueue.add(new Pair(endWord, pathLength));

        // 3. Create 2 visited maps for each BFS - prevent cycles
        Map<String, Integer> visitedFromStart = new HashMap<>();
        Map<String, Integer> visitedFromEnd = new HashMap<>();

        visitedFromStart.put(beginWord, pathLength);
        visitedFromEnd.put(endWord, pathLength);

        // 4. While both queues not empty, get front element
        while (!startQueue.isEmpty() && !endQueue.isEmpty()) {

            // BFS from beginWord
            pathLength = findPathLength(startQueue, visitedFromStart, visitedFromEnd, intermediateWords);
            if (pathLength != -1)
                return pathLength;

            // BFS from endWord
            pathLength = findPathLength(endQueue, visitedFromEnd, visitedFromStart, intermediateWords);
            if (pathLength != -1)
                return pathLength;
        }
        return 0;
    }

    private Map<String, List<String>> buildIntermediateWordsMap(List<String> wordList) {
        Map<String, List<String>> intermediateWords = new HashMap<>();

        for (String word : wordList) {
            // for each word in wordList, what are the intermediate forms do I have
            for (int i = 0; i < wordList.get(0).length(); i++) {
                // for each form, check with map, if exist, add myself. Otherwise, create empty
                // list, add myself
                String intermediateWord = word.substring(0, i) + '*' + word.substring(i + 1, word.length());
                List<String> neighbours = intermediateWords.getOrDefault(intermediateWord, new ArrayList<>());

                neighbours.add(word);
                intermediateWords.put(intermediateWord, neighbours);
            }
        }
        return intermediateWords;
    }

    private int findPathLength(Queue<Pair<String, Integer>> queue, Map<String, Integer> curVisited,
            Map<String, Integer> otherVisited, Map<String, List<String>> intermediateWords) {

        Pair<String, Integer> curPair = queue.remove();
        String curWord = curPair.getKey();
        int pathLength = curPair.getValue();

        // 5. Find all intermediate forms of current word, use each of them to look up
        // in the map in step 1 to find neighbouring words/nodes

        for (int i = 0; i < curWord.length(); i++) {
            String intermediateWord = curWord.substring(0, i) + '*' + curWord.substring(i + 1, curWord.length());

            // no guarantee that key exists, use getOrDefault
            List<String> neighbours = intermediateWords.getOrDefault(intermediateWord, new ArrayList<>());

            // 6. For each neighbour, add to queue, with the pathLength = current word's
            // pathLength + 1 - representing next potential node, which would mean
            // increasing the path
            for (String neighbour : neighbours) {
                // 8. If neighbour of curNode is already visited by the other BFS, meaning this
                // is the meeting point,
                // return the total length from one BFS plus the other BFS
                if (otherVisited.containsKey(neighbour))
                    return pathLength + otherVisited.get(neighbour);

                // 9. Check if visited, if not, add to queue and mark visited
                if (!curVisited.containsKey(neighbour)) {
                    queue.add(new Pair(neighbour, pathLength + 1));
                    curVisited.put(neighbour, pathLength + 1);
                }
            }
        }
        // 10. If not found the pathlength, return -1
        return -1;
    }
}

/**
 * Leetcode solution 1 applied in Pramp
 */

class Solution {

    static int shortestWordEditPath(String source, String target, String[] words) {

        // 0. In this case, not guarantee both of them has the same length
        if (source.length() != target.length())
            return -1;

        // 1. Pre-process each word in wordList - build a map with key = intermediate
        // word, val = [words that share same intermediate word]
        Map<String, List<String>> intermediateWords = buildIntermediateWordsMap(words);

        // 2. Push tuple containing beginWord and 1 in a queue - 1 represents pathLength
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        int pathLength = 0;
        queue.add(new Pair(source, pathLength));

        // 3. Create a visited map - prevent cycles
        Map<String, Boolean> visitedNodes = new HashMap<>();
        visitedNodes.put(source, true);

        // 4. While queue not empty, get front element
        while (!queue.isEmpty()) {
            Pair<String, Integer> curPair = queue.remove();
            String curWord = curPair.getKey();
            pathLength = curPair.getValue();

            // 5. Find all intermediate forms of current word, use each of them to look up
            // in the map in step 1 to find neighbouring words/nodes

            for (int i = 0; i < curWord.length(); i++) {
                String intermediateWord = curWord.substring(0, i) + '*' + curWord.substring(i + 1, curWord.length());

                // no guarantee that key exists, use getOrDefault
                List<String> neighbours = intermediateWords.getOrDefault(intermediateWord, new ArrayList<>());

                // 6. For each neighbour, add to queue, with the pathLength = current word's
                // pathLength + 1 - representing next potential node, which would mean
                // increasing the path
                for (String neighbour : neighbours) {
                    // 8. If reach endWord, return its pathLength
                    if (neighbour.equals(target))
                        return pathLength + 1;

                    // 9. Check if visited, if not, add to queue and mark visited
                    if (!visitedNodes.containsKey(neighbour)) {
                        queue.add(new Pair(neighbour, pathLength + 1));
                        visitedNodes.put(neighbour, true);
                    }
                }
            }
        }
        return -1;
    }

    static Map<String, List<String>> buildIntermediateWordsMap(String[] wordList) {
        Map<String, List<String>> intermediateWords = new HashMap<>();

        for (String word : wordList) {
            // for each word in wordList, what are the intermediate forms do I have
            for (int i = 0; i < wordList[0].length(); i++) {
                // for each form, check with map, if exist, add myself. Otherwise, create empty
                // list, add myself
                String intermediateWord = word.substring(0, i) + '*' + word.substring(i + 1, word.length());
                List<String> neighbours = intermediateWords.getOrDefault(intermediateWord, new ArrayList<>());

                neighbours.add(word);
                intermediateWords.put(intermediateWord, neighbours);
            }
        }
        return intermediateWords;
    }
}

/**
 * Leetcode solution 2 applied in Pramp
 */

class Solution {

    static int shortestWordEditPath(String source, String target, String[] words) {

        // 0. Check if list contains endWord - this is extra step compared to prev
        // solution since we add another BFS from endWord so have to make sure it exists
        if (!Arrays.asList(words).contains(target))
            return -1;

        // 0.a. In this case, not guarantee both of them has the same length
        if (source.length() != target.length())
            return -1;

        // 1. Pre-process each word in wordList - build a map with key = intermediate
        // word, val = [words that share same intermediate word]
        Map<String, List<String>> intermediateWords = buildIntermediateWordsMap(words);

        // 2. Push tuple containing beginWord and 1 in a queue - 1 represents pathLength
        // Push another tuple containing endWord and 1 in another queue
        Queue<Pair<String, Integer>> startQueue = new LinkedList<>();
        Queue<Pair<String, Integer>> endQueue = new LinkedList<>();

        int pathLength = 0;
        startQueue.add(new Pair(source, pathLength));
        endQueue.add(new Pair(target, pathLength));

        // 3. Create 2 visited maps for each BFS - prevent cycles
        Map<String, Integer> visitedFromStart = new HashMap<>();
        Map<String, Integer> visitedFromEnd = new HashMap<>();

        visitedFromStart.put(source, pathLength);
        visitedFromEnd.put(target, pathLength);

        // 4. While both queues not empty, get front element
        while (!startQueue.isEmpty() && !endQueue.isEmpty()) {

            // BFS from beginWord
            pathLength = findPathLength(startQueue, visitedFromStart, visitedFromEnd, intermediateWords);
            if (pathLength != -1)
                return pathLength;

            // BFS from endWord
            pathLength = findPathLength(endQueue, visitedFromEnd, visitedFromStart, intermediateWords);
            if (pathLength != -1)
                return pathLength;
        }
        return -1;
    }

    static Map<String, List<String>> buildIntermediateWordsMap(String[] wordList) {
        Map<String, List<String>> intermediateWords = new HashMap<>();

        for (String word : wordList) {
            // for each word in wordList, what are the intermediate forms do I have
            for (int i = 0; i < wordList[0].length(); i++) {
                // for each form, check with map, if exist, add myself. Otherwise, create empty
                // list, add myself
                String intermediateWord = word.substring(0, i) + '*' + word.substring(i + 1, word.length());
                List<String> neighbours = intermediateWords.getOrDefault(intermediateWord, new ArrayList<>());

                neighbours.add(word);
                intermediateWords.put(intermediateWord, neighbours);
            }
        }
        return intermediateWords;
    }

    static int findPathLength(Queue<Pair<String, Integer>> queue, Map<String, Integer> curVisited,
            Map<String, Integer> otherVisited, Map<String, List<String>> intermediateWords) {
        Pair<String, Integer> curPair = queue.remove();
        String curWord = curPair.getKey();
        int pathLength = curPair.getValue();

        // 5. Find all intermediate forms of current word, use each of them to look up
        // in the map in step 1 to find neighbouring words/nodes

        for (int i = 0; i < curWord.length(); i++) {
            String intermediateWord = curWord.substring(0, i) + '*' + curWord.substring(i + 1, curWord.length());

            // no guarantee that key exists, use getOrDefault
            List<String> neighbours = intermediateWords.getOrDefault(intermediateWord, new ArrayList<>());

            // 6. For each neighbour, add to queue, with the pathLength = current word's
            // pathLength + 1 - representing next potential node, which would mean
            // increasing the path
            for (String neighbour : neighbours) {
                // 8. If neighbour of curNode is already visited by the other BFS, meaning this
                // is the meeting point,
                // return the total length from one BFS plus the other BFS
                if (otherVisited.containsKey(neighbour))
                    return pathLength + otherVisited.get(neighbour) + 1;

                // 9. Check if visited, if not, add to queue and mark visited
                if (!curVisited.containsKey(neighbour)) {
                    queue.add(new Pair(neighbour, pathLength + 1));
                    curVisited.put(neighbour, pathLength + 1);
                }
            }
        }
        // 10. If not found the pathlength, return -1
        return -1;
    }
}