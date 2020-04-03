package test1;


import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * author:zhaolei
 * Date:2018/7/5
 */
public class test {
    public static <E> List<E> getDuplicateElements(List<E> list) {
        return list.stream() // list 对应的 Stream
                .collect(Collectors.groupingBy(l -> l, Collectors.counting())) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream() // 所有 entry 对应的 Stream
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(Map.Entry::getKey).collect(Collectors.toList());  // 转化为 List
    }

    public static void main(String[] args) {

        Integer[] integers = {1,2,3,0,4,5,6};
        Arrays.asList(integers).forEach(integer -> System.out.println(1/integer));
        /*List<Cat> cats =new ArrayList();
        cats.add(new Cat(1,"192.168.20.0",21));
        cats.add(new Cat(2,"192.168.20.255",20));
        //cats.add(new Cat(3,21));
        cats.add(new Cat("192.168.20.30",23));*/

        //cats.stream().filter(cat -> !cat.getName().equals("192.168.20.0")).collect(Collectors.toList());
/*
        List<Integer> catsName = cats.stream().map(cat -> {
            if (cat.getNum() != 0)
                return cat.getNum();
            else
                return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());*/



/*
        List<String> list = Arrays.asList("a", "b", "c", "d", "a", "a", "d", "c");
        *//*List<String> duplicateElements = getDuplicateElements(list);
         *//*
        List<String> l = new ArrayList<>();
        l.add("a");
        l.add("b");
        List<String> aa = list.stream().filter(s -> l.contains(s)).collect(Collectors.toList());
        System.out.println(aa);*/





		//cats.forEach(System.out::println);

        //cats.stream().filter((info) -> info.getName().compareTo("192.168.20.3") < 0).forEach(System.out::print);



/*
		*//*
		删除
		 *//*
        cats.removeIf(g -> g.getName() == null);
	//	cats.removeIf(cat -> cat.getName() == null);
		System.out.println("-----------------");
        cats.forEach(System.out::println);


		*//**
         * Map 的使用统计个数
         *//*
		Map<Integer, Long> map = cats.stream().collect(Collectors.groupingBy(Cat::getAge,Collectors.counting()));
		//K键，V值（K的个数）
		map.forEach((k, v) -> System.out.println(k + ":" + v));

		*//**
         * 过滤去的使用
         *//*
		cats.stream().filter((info) -> info.getAge()>20).map((info) -> info.getAge()+10).forEach(System.out::print);

		//根据**排序(reversed反序)
		cats.sort(Comparator.comparingInt(Cat::getAge));
		cats.forEach(System.out::print);*/


        /**
         * Predicate的使用
         */
		/*List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> result = conditionFilter(list, integer -> integer > 5);
		result.forEach(System.out::println);*/

	/*	String a = "gh";
		System.out.println(isBlank(a));*/


    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * List过滤
     *
     * @param list
     * @param predicate
     * @return
     */
    //高度抽象的方法定义，复用性高
    public static List<Integer> conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    //条件取反
    public List<Integer> conditionFilterNegate(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(predicate.negate()).collect(Collectors.toList());
    }

    //多条件并
    public List<Integer> conditionFilterAnd(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate2) {
        return list.stream().filter(predicate.and(predicate2)).collect(Collectors.toList());
    }

    //多条件或
    public List<Integer> conditionFilterOr(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate2) {
        return list.stream().filter(predicate.or(predicate2)).collect(Collectors.toList());
    }
}
