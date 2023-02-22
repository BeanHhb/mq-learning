package com.bean.lru;

/**
 * @Author Bean
 * @Date 2023/2/22 20:24
 */
public class Main {

    public static void main(String[] args) {

        // 构建低速存储
        LowSpeedStorage<String, String> lss = new LowSpeedStorage<>();
        for (int i = 0; i < 10000; i++) {
            lss.set(String.valueOf(i), "a" + i);
        }

        // 构建LRU缓存
        LruCacheImpl<String, String> lruc = new LruCacheImpl<>(10, lss);

        for (int i = 0; i < 20; i++) {
            String s = lruc.get(String.valueOf(i * 2));
            if (i == 16) {
                String s1 = lruc.get(String.valueOf(i - 2));
            }
            LruCacheImpl<String, String>.Node node = lruc.head;
            do {
                System.out.println(node.key + ":" + node.value);
                node = node.next;
            } while (node != null);
            System.out.println("-------------");
        }
    }
}
