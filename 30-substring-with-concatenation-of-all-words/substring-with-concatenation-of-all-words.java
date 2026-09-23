import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) {
            return result;
        }

        // Frequency of each word in words
        Map<String, Integer> wordMap = new HashMap<>();

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // Try each possible starting offset
        for (int offset = 0; offset < wordLen; offset++) {

            int left = offset;
            int right = offset;
            int count = 0;

            Map<String, Integer> currentMap = new HashMap<>();

            while (right + wordLen <= s.length()) {

                String word = s.substring(right, right + wordLen);
                right += wordLen;

                // Word is not present in words
                if (!wordMap.containsKey(word)) {
                    currentMap.clear();
                    count = 0;
                    left = right;
                    continue;
                }

                currentMap.put(word, currentMap.getOrDefault(word, 0) + 1);
                count++;

                // Too many occurrences of this word
                while (currentMap.get(word) > wordMap.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);

                    currentMap.put(
                        leftWord,
                        currentMap.get(leftWord) - 1
                    );

                    left += wordLen;
                    count--;
                }

                // Found all words
                if (count == wordCount) {
                    result.add(left);

                    // Move window forward
                    String leftWord = s.substring(left, left + wordLen);

                    currentMap.put(
                        leftWord,
                        currentMap.get(leftWord) - 1
                    );

                    left += wordLen;
                    count--;
                }
            }
        }

        return result;
    }
}