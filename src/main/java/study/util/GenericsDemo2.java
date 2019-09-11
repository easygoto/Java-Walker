package study.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericsDemo2 {

  public static void main(String[] args) {

    List<String> list1 = new ArrayList<>();
    list1.add("hehe");
    list1.add("haha");
    list1.add("xixi");
    list1.add("heihei");
    List<Integer> list2 = new ArrayList<>();
    list2.add(1);
    list2.add(2);
    list2.add(3);
    list2.add(4);
    
//    showList(list1);
//    showList(list2);
    printList(list1);
    printList(list2);
  }
  
  public static <T> void showList(List<T> t){
    for (Iterator<T> it = t.iterator(); it.hasNext();){
      System.out.println(it.next());
    }
  }
  
  public static void printList(List<?> t){
    for (Iterator<?> it = t.iterator(); it.hasNext();){
      System.out.println(it.next().toString());
    }
  }

}
