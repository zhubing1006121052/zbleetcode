package com.qunar.leetcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ConsistentHash<T> {
    /**
     * Hash计算对象，用于自定义hash算法
     */
    HashFunc hashFunc;
    /**
     * 复制的节点个数
     */
    private final int numberOfReplicas;
    /**
     * 一致性Hash环
     */
    private final TreeMap<Long, T> hashCircle = new TreeMap<>();

    /**
     * 构造，使用Java默认的Hash算法
     *
     * @param numberOfReplicas 复制的节点个数，增加每个节点的复制节点有利于负载均衡
     * @param nodes            节点对象
     */
    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        this.hashFunc = ConsistentHash::md5HashingAlg;
        //初始化节点
        nodes.forEach(node -> add(node));
    }

    /**
     * 增加节点<br>
     * 每增加一个节点，就会在闭环上增加给定复制节点数<br>
     * 例如复制节点数是2，则每调用此方法一次，增加两个虚拟节点，这两个节点指向同一Node
     * 由于hash算法会调用node的toString方法，故按照toString去重
     *
     * @param node 节点对象
     */
    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            hashCircle.put(hashFunc.hash(node.toString() + i), node);
        }
    }

    /**
     * 移除节点的同时移除相应的虚拟节点
     *
     * @param node 节点对象
     */
    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            hashCircle.remove(hashFunc.hash(node.toString() + i));
        }
    }

    /**
     * 获得一个最近的顺时针节点
     *
     * @param key 为给定键取Hash，取得顺时针方向上最近的一个虚拟节点对应的实际节点
     * @return 节点对象
     */
    public T get(Object key) {
        if (hashCircle.isEmpty()) {
            return null;
        }
        long hash = hashFunc.hash(key);
        hash = nextHash(hash);
        //正好命中
        return hashCircle.get(hash);
    }

    public Long nextHash(Long hash) {
        if (!hashCircle.containsKey(hash)) {
            Long next = hashCircle.higherKey(hash + 1);//返回下一个hash值
            hash = next == null ? hashCircle.firstKey() : next;
        }
        return hash;
    }

    /**
     * 使用MD5算法
     *
     * @param key
     * @return
     */
    private static long md5HashingAlg(Object key) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            String str = key.toString();
            md5.update(str.getBytes());
            byte[] bKey = md5.digest();
            long res = ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16) | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
            return res;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * 使用FNV1hash算法
     *
     * @param key
     * @return
     */
    private static long fnv1HashingAlg(Object key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        String str = key.toString();
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }

    /**
     * Hash算法对象，用于自定义hash算法
     */
    public interface HashFunc {
        public Long hash(Object key);
    }

    public static void main(String[] args) {
        List<String> nodes = new ArrayList<>();
        System.out.println("--添加节点 ABC");
        nodes.add("NODE_A");
        nodes.add("NODE_B");
        nodes.add("NODE_C");
        ConsistentHash<String> chash = new ConsistentHash(3, nodes);
        System.out.println("test1数据存储于节点："+chash.get("test1"));
        System.out.println("aest2数据存储于节点："+chash.get("aest2"));
        System.out.println("dest3数据存储于节点："+chash.get("dest3"));
        System.out.println("--添加节点NODE_D");
        chash.add("NODE_D");
        System.out.println("test1数据存储于节点："+chash.get("test1"));
        System.out.println("aest2数据存储于节点："+chash.get("aest2"));
        System.out.println("dest3数据存储于节点："+chash.get("dest3"));

        for (Iterator<Map.Entry<Long, String>> it = chash.hashCircle.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Long, String> entry = it.next();
            Long k = entry.getKey();
            System.out.println(k + ":" + entry.getValue());
        }
    }

}