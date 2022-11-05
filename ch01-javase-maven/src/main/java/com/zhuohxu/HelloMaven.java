package com.zhuohxu;

public class HelloMaven {

    public static void main(String[] args) {
        System.out.println(new HelloMaven().add(10, 20));
    }

    public int add(int n1, int n2) {
        return n1 + n2;
    }
}
