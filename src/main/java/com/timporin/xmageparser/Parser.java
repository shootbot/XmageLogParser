package com.timporin.xmageparser;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Parser {
    private String infile;

    private final String TIME_COLOR = "#CCCC33";
    private final String PLAYER_COLOR = "#20B2AA";
    private final String TURN_LIFE_COLOR = "Orange";
    private final String REGULAR_COLOR = "White";

    private final String BLUE_COLOR = "#87CEFA";
    private final String WHITE_COLOR = "White";
    private final String BLACK_COLOR = "White";
    private final String RED_COLOR = "#FF6347";
    private final String GREEN_COLOR = "#90EE90";
    private final String COLORLESS_COLOR = "#B0C4DE";
    private final String MULTICOLOR_COLOR = "#DAA520";


    public static void main(String[] args) {
        Parser p = new Parser("in.html");
        p.parseLog();
    }

    public Parser(String infile) {
       this.infile = infile;
    }

    private void parse(Element e, int depth) {
        String nodeName = e.nodeName();
        if (nodeName.equals("br")
                || nodeName.equals("b")
                || nodeName.equals("i")
                || nodeName.equals("img")
                || nodeName.equals("a")) {
            return;
        }

        StringBuilder nodeText = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            nodeText.append("  ");
        }

        nodeText.append(nodeName);
        if (e.hasText()
                && !nodeName.equals("#document")
                && !nodeName.equals("html")
                && !nodeName.equals("body")) {
            nodeText.append('(');
            nodeText.append(e.text());
            nodeText.append(')');
        }

        System.out.println(nodeText);

        for (Element c : e.children()) {
            parse(c, depth + 1);
        }
    }

    private void parseLog() {
        try {
            File input = new File(infile);
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

            parse(doc, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
