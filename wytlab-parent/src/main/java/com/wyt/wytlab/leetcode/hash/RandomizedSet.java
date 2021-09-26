package com.wyt.wytlab.leetcode.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.get(val) != null) {
            return false;
        } else {
            map.put(val, val);
            return true;
        }
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (map.get(val) != null) {
            map.remove(val);
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        Integer[] keys = map.keySet().toArray(new Integer[0]);
        Random random = new Random();
        Integer randomKey = keys[random.nextInt(keys.length)];
        return map.get(randomKey);
    }
}
