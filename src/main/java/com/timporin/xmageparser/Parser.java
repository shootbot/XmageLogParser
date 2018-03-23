package com.timporin.xmageparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Parser {
    private String infile;
    private List<Record> records;
    private MillCounter mc;
    private List<Element> elems = new ArrayList<>();

    public Parser(String infile) {
        this.infile = infile;
        parseLog();
        Machine m = new Machine(elems);
        records = m.getRecords();
        mc = new MillCounter(records);
    }

    public MillCounter getMilled() {
        return mc;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (Record r : records) {
            if (r.time != null) {
                sb.append(r.time);
                sb.append("\t");
            }
            if (r.turn != null) {
                sb.append(r.turn);
                sb.append("\t");
            }
            if (r.player != null) {
                sb.append(r.player);
                sb.append("\t");
            }
            if (r.move != null) {
                sb.append(r.move);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private void parseEl(Element e) {
        String nodeName = e.nodeName();

        if (nodeName.equals("br")
                || nodeName.equals("b")
                || nodeName.equals("i")
                || nodeName.equals("img")
                || nodeName.equals("a")) {
            return;
        }

        if (nodeName.equals("font")) {
            elems.add(e);
        }

        for (Element c : e.children()) {
            parseEl(c);
        }
    }

    private void parseLog() {
        try {
            File input = new File(infile);
            Document doc = Jsoup.parse(input, "UTF-8");
            parseEl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
