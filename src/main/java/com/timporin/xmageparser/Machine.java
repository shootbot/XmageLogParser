package com.timporin.xmageparser;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private List<Record> records = new ArrayList<>();
    private Record curRecord;

    private final String TIME = "#CCCC33";
    private final String PLAYER = "#20B2AA";
    private final String SERVICE_MSG = "Orange";
    private final String REGULAR = "White";

    private final String BLUE_COLOR = "#87CEFA";
    private final String WHITE_COLOR = "White";
    private final String BLACK_COLOR = "White";
    private final String RED_COLOR = "#FF6347";
    private final String GREEN_COLOR = "#90EE90";
    private final String COLORLESS_COLOR = "#B0C4DE";
    private final String MULTICOLOR_COLOR = "#DAA520";

    public Machine(List<Element> list) {
        for (Element e : list) {
            String text = e.text();
            String color = e.attr("color");
            switch (color) {
                case TIME:
                    if (curRecord != null) {
                        records.add(curRecord);
                    }
                    curRecord = new Record();
                    curRecord.time = text;
                    break;
                case PLAYER:
                    if (curRecord.move == null) {
                        curRecord.player = text;
                    } else {
                        curRecord.move += " " + text;
                    }
                    break;
                case SERVICE_MSG:
                    if (curRecord.turn == null) {
                        curRecord.turn = text;
                    } else {
                        curRecord.turn += " " + text;
                    }
                    break;
                case REGULAR:
                default:
                    if (curRecord.move == null) {
                        curRecord.move = text;
                    } else {
                        curRecord.move += " " + text;
                    }
            }
        }
        records.add(curRecord);
    }

    public List<Record> getRecords() {
        return records;
    }
}
