import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;
        int sLen = s.length();

        if (sLen < totalLen) {
            return result;
        }

        // Count expected frequency of each word
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        // Run sliding window for each offset up to wordLen
        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> currentCounts = new HashMap<>();
            int left = i;
            int count = 0; // Tracks total valid words in the current window

            for (int right = i; right <= sLen - wordLen; right += wordLen) {
                String sub = s.substring(right, right + wordLen);

                if (wordCounts.containsKey(sub)) {
                    currentCounts.put(sub, currentCounts.getOrDefault(sub, 0) + 1);
                    count++;

                    // If a word exceeds its allowed frequency, shrink window from left
                    while (currentCounts.get(sub) > wordCounts.get(sub)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentCounts.put(leftWord, currentCounts.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // Valid window found
                    if (count == numWords) {
                        result.add(left);
                    }
                } else {
                    // Invalid word encountered, reset window
                    currentCounts.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }
}
