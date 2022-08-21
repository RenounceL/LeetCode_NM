/*
Description
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You
receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this
new language. Derive the order of letters in this language.

Example 1:
Input：["wrt","wrf","er","ett","rftt"]
Output："wertf"
Explanation：
from "wrt"and"wrf" ,we can get 't'<'f'
from "wrt"and"er" ,we can get 'w'<'e'
from "er"and"ett" ,we can get 'r'<'t'
from "ett"and"rftt" ,we can get 'e'<'r'
So return "wertf"

Example 2:
Input：["z","x"]
Output："zx"
Explanation：
from "z" and "x"，we can get 'z' < 'x'
So return "zx"
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class No_269_Alien_Dictionary {
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = constructGraph(words);
        // 如果数值不合理，graph为空，返回空字符串
        if (graph == null) {
            return "";
        }
        return topologicalSorting(graph);
    }

    private Map<Character, Set<Character>> constructGraph(String[] words) {
        // 存放（字母 -> 右面的多个字母）的映射关系
        Map<Character, Set<Character>> graph = new HashMap<>();

        // 生成所有的点，每个点都后续点暂时为空
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<Character>());
                }
            }
        }

        // 生成所有的边，找到一个点之后的点，并简历连接
        for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                }
                index++;
            }
            // 如果输入为["abc", "ab"], "abc"出现在“ab"前面不合法,graph为null
            if (index == Math.min(words[i].length(), words[i + 1].length())) {
                if (words[i].length() > words[i + 1].length()) {
                    return null;
                }
            }
        }
        return graph;
    }

    private String topologicalSorting(Map<Character, Set<Character>> graph) {
    }

}
