import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {


    public static void main(String[] args) {


        long sta = System.currentTimeMillis();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add("哈哈"+i);
        }
        long end = System.currentTimeMillis();
        System.out.println("时间：" + (end - sta));


        long sta2 = System.currentTimeMillis();
        ArrayList<String> list2 = new ArrayList<>(100000);
        for (int i = 0; i < 100002; i++) {
            list2.add("哈哈"+i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("时间：" + (end2 - sta2));

//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add("哈哈"+i);
//        }
//
//
//        Iterator<String> it = list.iterator();
//        while (it.hasNext()) {
//            String str = it.next();
//            if("哈哈2".equals(str)){
//                it.remove();
//            }
//        }
//
//        System.out.println(list);
//
//        int i = list.indexOf("哈哈1");
//        System.out.println(i);
//
//        list.clear();

//        ArrayList<Object> list2 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list2.add("你妹"+i);
//        }
//        list2.addAll(0,list);
//
//
//        System.out.println(list);
//        System.out.println(list2);




    }
}
