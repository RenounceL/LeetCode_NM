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

import java.util.*;

public class No_269_Alien_Dictionary {
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = constructGraph(words);
        // 如果数值不合理，graph为空，返回空字符串
        if (graph == null) {
            return "";
        }
        return topologicalSorting(graph);
    }

    private static Map<Character, Set<Character>> constructGraph(String[] words) {
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
                    break;
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

    private static String topologicalSorting(Map<Character, Set<Character>> graph) {
        // 1. 统计每个点的入度
        Map<Character, Integer> indegree = getIndegree(graph);

        // 要求：这里可能有多个有效的字母顺序，返回正常字典顺序看来最小的
        // 所以这里要选PriorityQueue，从所有可以出对的元素中，先出队字典序比较小的元素
        Queue<Character> queue = new PriorityQueue<>();

        // 2. 将每个入度为0的点放入Queue中为起始点
        for(Character c: indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        // 记录拓扑顺序（外星人字典排序）
        StringBuilder sb = new StringBuilder();

        // 3. 不断从队列中拿出一个点，去掉这个点的所有连边（指向其他点的边，其他点的响应入度-1
        while (!queue.isEmpty()) {
            Character head = queue.poll();
            sb.append(head);
            for (Character neighbor : graph.get(head)) {
                // 当前点的邻居的入度-1
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                // 4. 一但发现新的入度为0的点，丢回队列中
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        // 如果有些字母没有出现在字典排序中，那么没有答案
        return (sb.length() == indegree.size()) ? sb.toString() : "";
    }

    // 统计每个点的入度，如果一个点的入度为0，那么这个点依然存在dict中，对应入度为0
    private static Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();

        //初始化所有点的入度为0
        for (Character u : graph.keySet()) {
            indegree.put(u, 0);
        }

        for (Character u : graph.keySet()) {
            // 所有令居的入度加1
            for (Character v : graph.get(u)) {
                indegree.put(v, indegree.get(v) + 1);
            }
        }
        return indegree;
    }
}
