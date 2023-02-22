package com.bean.lru;

/**
 * KV存储抽象
 *
 * @author huanghebin
 * @date 2023/2/22 10:50
 */
public interface Storage<K,V> {

	/**
	 * 根据提供的key来访问数据
	 *
	 * @param key 数据Key
	 * @return 数据值
	 * */
	V get(K key);
}
