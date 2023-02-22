package com.bean.lru;

import java.util.HashMap;
import java.util.Objects;

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
	Node tail=null;

	public LruCacheImpl(int capacity, Storage lowSpeedStorage) {
		super(capacity, lowSpeedStorage);
	}

	/**
	 * 取值
	 *
	 * 判断map：存在key，取值，并将对应Node移动到链表头；不存在key，从lowSpeedStorage中取值，并set到map
	 *
	 * @param key 数据Key
	 * @return
	 */
	@Override
	public V get(K key) {
		if (map.containsKey(key)) {
			// 覆盖value并将Node移到链表头
			Node node = map.get(key);
			remove(node);
			moveHead(node);
			return node.value;
		} else {
			// lowSpeedStorage中取值
			V v = (V) lowSpeedStorage.get(key);
			if (v != null) {
				set(key, v);
			}
			return v;
		}
	}

	/**
	 * 存值
	 *
	 * 判断map：存在key，覆盖value并将对应的Node移动到链表头；不存在key，判断是否超出容量，是则移除链表尾，然后将Node已到链表头
	 *
	 * @param key
	 * @param value
	 */
	public void set(K key, V value) {
		if (map.containsKey(key)) {
			// 覆盖value并将Node移到链表头
			Node node = map.get(key);
			node.value = value;
			remove(node);
			moveHead(node);
		} else {
			// 构建新Node，移到链表头
			Node node = new Node(key, value);
			moveHead(node);
			// 容量超出
			if (map.size() > capacity) {
				remove(tail);
			}
		}
	}

	/**
	 * 移除
	 */
	private void remove(Node node) {
		if(node.prev != null){
			node.prev.next = node.next;
		}else{
			head = node.next;
		}

		if (node.next != null) {
			node.next.prev = node.prev;
		} else {
			tail = node.prev;
		}
		map.remove(node.key);
	}

	/**
	 * 将Node移动到头部
	 *
	 * @param node
	 */
	private void moveHead(Node node) {
		if(Objects.isNull(head)) {
			tail = node;
		} else {
			head.prev = node;
			node.next = head;
		}
		head = node;
		map.put(node.key, node);
	}


	/**
	 * 节点类
	 */
	class Node {
		K key;
		V value;
		Node prev;
		Node next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
