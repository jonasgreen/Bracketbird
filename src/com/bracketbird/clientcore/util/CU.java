package com.bracketbird.clientcore.util;

import com.bracketbird.clientcore.model.*;

import java.util.*;

/**
 *
 */
public class CU {

    public static <K> ArrayList<K> toArrayList(Collection<K> col) {
        if (col == null) {
            return new ArrayList<K>();
        }
        ArrayList<K> result = new ArrayList<K>();
        for (K k : col) {
            result.add(k);
        }
        return result;
    }

    public static int[] toIntArray(List<Integer> list){
        if(list == null){
            return null;
        }
        int[] ia = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            ia[i++] = integer;
        }
        return ia;
    }


    public static <K extends Model> K get(List<K> list, K model){
        int i = list.indexOf(model);
        return list.get(i);
    }

    public static <K extends Model> boolean isLast(List<K> list, K model){
        int indexOf = list.indexOf(model);
        return list.size()-1 == indexOf;
    }

    public static <K> int lastIndex(List<K> list) {
        return list.size() - 1;
    }

    public static <K> K lastItem(List<K> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }




    public static <K extends Model>void sortByEventLogNr(List<K> list) {
        Collections.sort(list, new Comparator<K>() {
            public int compare(K m1, K m2) {
                return m1.compareByEventLogNr(m2);
            }
        });

    }


    public static <K extends Model>void sortBySeeding(List<K> list) {
        Collections.sort(list, new Comparator<K>() {
            public int compare(K m1, K m2) {

                return m1.compareByEventLogNr(m2);
            }
        });

    }


    public static <K> List<K> convert(Collection<K> col) {
        List<K> list = new ArrayList<K>();
        for (K k : col) {
            list.add(k);
        }
        return list;
    }

    public static <K> boolean isEmpty(Collection<K> col) {
        return col == null || col.isEmpty();
    }
}
