package com.qunar.leetcode;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 */
public class Trie {

    TrieNode root;
    class TrieNode {

        // R links to node children
        private TrieNode[] links;

        private final int R = 26;

        private boolean isEnd;

        public TrieNode() {
            //每个节点都有26个子节点(都是由小写字母 a-z 构成)
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            //如果这个字符的下标位置为空那么 这个位置就是没有这个元素的
            return links[ch -'a'] != null;
        }
        public TrieNode get(char ch) {
            //得到这个位置的node
            return links[ch -'a'];
        }
        public void put(char ch, TrieNode node) {
            //存放node,元素转化成他的下标
            links[ch -'a'] = node;
        }
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        //根节点是一个特殊节点，他也是有26个槽位的
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            //获取第一个字符，看后看第一个位置对应的第一个字符对应的槽位是否为空
            if (!node.containsKey(currentChar)) {
                //不包含说明需要重新存入值
                node.put(currentChar, new TrieNode());
            }
            //得到该字符槽位，如果是上面新存入的，那么这个node26个位置都是空的
            node = node.get(currentChar);
        }
        node.setEnd();
    }
    //找前缀
    private TrieNode searchPrefix(String word) {
        //扎到根节点
        TrieNode node = root;
        //不断迭代
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            //根据当前字符找到槽位，看槽位是否包含Node
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null ? true : false;
    }

}
