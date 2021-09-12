package com.wyt.wytlab.leetcode.hash;


import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class test {
    public static void main(String[] args) {
        MyHashSet obj = new MyHashSet();
        obj.add(1);
        obj.remove(1);
        int[] num1 = {4,9,5};
        int[] num2 = {9,4,9,8,4};
//        int[] num3 = intersection(num1,num2);

//        System.out.println(isIsomorphic("paper", "title"));
        String[] s1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] s2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
//        String[] str = findRestaurant(s1, s2);
//        System.out.println(containsNearbyDuplicate(new int[]{1,0,1,1},1));
//        List<List<String>> list = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});

        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<String,Integer> map = new HashMap<>();
        Integer index = 0,len = 0;
        char[] ch = s.toCharArray();
        for(int i = 0; i < ch.length; i++) {
            map.put(String.valueOf(ch[i]), i);
        }

        for(int i=0; i < ch.length; i++) {
            if(!map.get(String.valueOf(ch[i])).equals(i)
            || i==ch.length - 1) {
                if((len > 0 && i - index + 1 < len) || len == 0) {
                    len = i - index + 1;
                    index = -1;
                }

            }
            if(index == -1 && map.get(String.valueOf(ch[i])).equals(i)) {
                index = i;
            }
        }
        return len;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            String key = String.valueOf(temp);
            if(map.containsKey(key)) {
                map.get(key).add(strs[i]);
            } else {
                List<String> vals = new ArrayList<String>();
                vals.add(strs[i]);
                map.put(key,vals);
            }
        }
        List<List<String>> list = new ArrayList<List<String>>();
        map.forEach((key,val) -> {
            list.add(val);
        });
        return list;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        Map<Integer,Integer> result = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                result.put(nums[i], i - map.get(nums[i]));
            } else {
                map.put(nums[i], i);
            }
        }

        boolean istrue = false;
        for(Map.Entry<Integer,Integer> entry : result.entrySet()) {
//            if(entry.getValue() > k) {
//                return false;
//            }
            if(entry.getValue() == k) {
                istrue = true;
            }
        }
        return istrue;
    }

    public static int firstUniqChar(String s) {
        if(s == "" || s== null) {
            return -1;
        }
        char[] ch = s.toCharArray();
        Map<String,Integer> map = new HashMap<String, Integer>();
        for(char item : ch) {
            String key = String.valueOf(item);
            if(map.containsKey(key)) {
                map.put(key,map.get(key) + 1);
            } else {
                map.put(key,1);
            }
        }
        for(int i = 0; i<ch.length;i++) {
            if(map.get(String.valueOf(ch[i])) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer> map = new HashMap<String, Integer>();
        int index = -1;
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if(list1[i].equals(list2[j])) {
                    if(map.size() == 0) {
                        index = i + j;
                        map.put(list1[i], i);
                    }
                    if(i + j == index) {
                        map.put(list1[i], i);
                    }
                    if(i+j < index) {
                        map.clear();
                        index = i+ j;
                        map.put(list1[i], index);
                    }
                }
            }
        }
        String[] str=new String[map.size()];
        return map.keySet().toArray(str);
    }

    public static boolean isIsomorphic(String s, String t) {
        char[] ch = s.toCharArray();
        char[] th = t.toCharArray();
        Map<String,Integer> map = new HashMap<String, Integer>();
        for(int i=0;i<ch.length;i++) {
            if(map.containsKey(String.valueOf(ch[i]))){
                String str = String.valueOf(th[i]);
                String src = String.valueOf(th[map.get(String.valueOf(ch[i]))]);
            if(!str.equals(src)) {
                    return false;
                }
            } else {
                map.put(String.valueOf(ch[i]),i);
            }
        }
        return true;
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i : nums1) {
            set.add(i);
        }
        Set<Integer> result = new HashSet<Integer>();
        for(int j: nums2) {
            if(set.contains(j)) {
                result.add(j);
            }
        }
        int[] arr = new int[result.size()];
        int index = 0;
        for (Integer k : result) {
            arr[index++] = k;
        }
        return arr;
    }
}
