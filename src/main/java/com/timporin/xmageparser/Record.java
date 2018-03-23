package com.timporin.xmageparser;

public class Record {
    String time;
    String turn;
    String player;
    String move;

    Record() {}

    Record(String time, String turn, String player, String move) {
        this.time = time;
        this.turn = turn;
        this.player = player;
        this.move = move;
    }
}
