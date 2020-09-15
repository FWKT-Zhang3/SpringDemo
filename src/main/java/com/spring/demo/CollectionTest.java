package com.spring.demo;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionTest {
    private String[] arr;
    private List<String> list;
    private Map<String, String> map;
    private Set<String> set;

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "CollectionTest{" +
                "arr=" + Arrays.toString(arr) +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                '}';
    }
}
