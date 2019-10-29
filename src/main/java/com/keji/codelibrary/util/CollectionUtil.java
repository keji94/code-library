package com.keji.codelibrary.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Copyright (c) 2019 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2019-10-25
 */
public class CollectionUtil {




  /**
   * 获取两个ArrayList的差集
   *
   * @param firstArrayList 第一个ArrayList
   * @param secondArrayList 第二个ArrayList
   * @return resultList 差集ArrayList
   */
  public static List<String> receiveDefectList(List<String> firstArrayList, List<String> secondArrayList) {
    List<String> resultList = new ArrayList<String>();
    LinkedList<String> result = new LinkedList<String>(firstArrayList);// 大集合用linkedlist
    HashSet<String> othHash = new HashSet<String>(secondArrayList);// 小集合用hashset
    Iterator<String> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
    while(iter.hasNext()){
      if(othHash.contains(iter.next())){
        iter.remove();
      }
    }
    resultList = new ArrayList<String>(result);
    return resultList;
  }

  /**
   * 获取两个ArrayList的交集
   *
   * @param firstArrayList 第一个ArrayList
   * @param secondArrayList 第二个ArrayList
   * @return resultList 交集ArrayList
   */
  public static List<String> receiveCollectionList(List<String> firstArrayList, List<String> secondArrayList) {
    List<String> resultList = new ArrayList<String>();
    LinkedList<String> result = new LinkedList<String>(firstArrayList);// 大集合用linkedlist
    HashSet<String> othHash = new HashSet<String>(secondArrayList);// 小集合用hashset
    Iterator<String> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
    while(iter.hasNext()) {
      if(!othHash.contains(iter.next())) {
        iter.remove();
      }
    }
    resultList = new ArrayList<String>(result);
    return resultList;
  }

  /**
   * @方法描述：获取两个ArrayList的去重并集
   * @param firstArrayList 第一个ArrayList
   * @param secondArrayList 第二个ArrayList
   * @return resultList 去重并集ArrayList
   */
  public static List<String> receiveUnionList(List<String> firstArrayList, List<String> secondArrayList) {
    List<String> resultList = new ArrayList<String>();
    Set<String> firstSet = new TreeSet<String>(firstArrayList);
    for(String id : secondArrayList) {
      // 当添加不成功的时候 说明firstSet中已经存在该对象
      firstSet.add(id);
    }
    resultList = new ArrayList<String>();
    return resultList;
  }

}
