package com.bean.lru;

import java.util.HashMap;

/**
 * LRU容器实现
 *	hashmap+链表
 * @author huanghebin
 * @date 2023/2/22 16:47
 */
public class LruCacheImpl<K,V> extends LruCache<K,V> {

	HashMap<K, Node> map = new HashMap<K, Node>();
	//先声明一个头结点和一个尾节点
	Node head=null;
	Node end=null;

	public LruCacheImpl(int capacity, Storage lowSpeedStorage) {
		super(capacity, lowSpeedStorage);
	}

	@Override
	public V get(K key) {
		return null;
	}

	/*
	* 存值
	* */




	/**
	 * 节点类
	 */
	class Node {
		Node prev;
		Node next;
	}
}
