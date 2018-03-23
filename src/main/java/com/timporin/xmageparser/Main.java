package com.timporin.xmageparser;

public class Main {

    public static void main(String[] args) {
        Parser p = new Parser("in (10).html");
        p.print();
        System.out.println(p.getMilled());
    }
}
