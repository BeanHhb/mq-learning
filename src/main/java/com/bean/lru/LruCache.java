package com.bean.lru;

/**
 * LRU缓存。你需要继承这个抽象类来实现LRU缓存
 *
 * @author huanghebin
 * @date 2023/2/22 10:51
 */
public abstract class LruCache<K,V> implements Storage<K,V> {

	// 缓存容量
	protected final int capacity;

	// 低速存储，所有的数据都可以从这里读到
	protected final Storage lowSpeedStorage;

	public LruCache(int capacity, Storage lowSpeedStorage) {
		this.capacity = capacity;
		this.lowSpeedStorage = lowSpeedStorage;
	}
}
