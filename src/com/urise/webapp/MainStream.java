package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int result1 = minValue(new int[] {1, 2, 3, 3, 2, 3});
        System.out.println(result1);

        int result2 = minValue(new int[] {9, 8});
        System.out.println(result2);

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        System.out.println(oddOrEven(list1));
    }

    static int minValue(int[] values) {
        Set<Integer> set = Arrays.stream(values).boxed().collect(Collectors.toSet());
        double result = 0;
        int power = set.size();
        for (Integer val : set) {
            power--;
            result += val * (Math.pow(10, power));
        }
        return (int) result;
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream().reduce(Integer::sum).get() % 2 == 1) {
            return integers.stream().filter(integer -> integer%2 != 1).collect(Collectors.toList());
        } else {
            return integers.stream().filter(integer -> integer%2 == 1).collect(Collectors.toList());
        }
    }
}
