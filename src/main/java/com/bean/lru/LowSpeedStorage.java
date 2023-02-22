package com.bean.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Bean
 * @Date 2023/2/22 20:21
 */
public class LowSpeedStorage<K,V> implements Storage<K,V> {

    Map<K,V> map = new HashMap<K,V>();

    @Override
    public V get(K key) {
        return map.get(key);
    }

    public void set(K key, V value) {
        map.put(key, value);
    }
}
