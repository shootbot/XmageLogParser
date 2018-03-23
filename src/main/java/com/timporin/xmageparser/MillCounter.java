package com.timporin.xmageparser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MillCounter {
    private int hedronCrab;
    private int mesmericOrb;
    private int manicScribe;
    private int glimpse; // Glimpse The Unthinkable
    private int mindFuneral;
    private int ashiok; // Ashiok, Nightmare Weaver
    private int sanity; // Fraying Sanity
    private int archiveTrap;
    private int shelldock; // Shelldock Isle

    private String player;
    private boolean mindFuneralFlag = false;
    private boolean sanityFlag = false;

    private final String MILL_PLAYER = "Molli";

    public MillCounter(List<Record> records) {
        Pattern pattern = Pattern.compile("puts .* from library into his or her graveyard");

        for (Record r : records) {
            if (r.turn != null) {
                player = r.player;
            }

            if (r.move == null) continue;

            if (r.move.startsWith("Ability triggers: Hedron Crab") && !r.move.endsWith("targeting " + MILL_PLAYER + " ")) {
                hedronCrab += 3;
            } else if (r.move.startsWith("Ability triggers: Manic Scribe")) {
                manicScribe += 3;
            } else if (r.move.startsWith("casts Glimpse the Unthinkable")) {
                glimpse += 10;
            } else if (r.move.startsWith("casts Archive Trap")) {
                archiveTrap += 13;
            } else if (r.move.startsWith("casts Mind Funeral")) {
                mindFuneralFlag = true;
            } else if (mindFuneralFlag == true) {
                if (r.move.startsWith("reveals ")) continue;

                Matcher m = pattern.matcher(r.move);
                if (m.matches()) {
                    mindFuneral++;
                } else {
                    mindFuneralFlag = false;
                }
            } else if (r.move.startsWith("Ability triggers: Fraying Sanity")) {
                sanityFlag = true;
            } else if (sanityFlag == true) {
                Matcher m = pattern.matcher(r.move);
                if (m.matches()) {
                    sanity++;
                } else {
                    sanityFlag = false;
                }
            } else if (r.move.startsWith("Ability triggers: Mesmeric Orb") && !MILL_PLAYER.equals(player)) {
                mesmericOrb++;
            } else if (r.move.startsWith("activates: Exile the top three cards of target opponent's library")) {
                ashiok += 3;
            }
        }
    }

    public int getHedronCrab() {
        return hedronCrab;
    }

    public int getMesmericOrb() {
        return mesmericOrb;
    }

    public int getManicScribe() {
        return manicScribe;
    }

    public int getGlimpse() {
        return glimpse;
    }

    public int getMindFuneral() {
        return mindFuneral;
    }

    public int getAshiok() {
        return ashiok;
    }

    public int getSanity() {
        return sanity;
    }

    public int getArchiveTrap() {
        return archiveTrap;
    }

    public int getShelldock() {
        return shelldock;
    }

    @Override
    public String toString() {
        String res = "Hedron Crab:\t\t" + hedronCrab + "\n"
                + "Mesmeric Orb:\t\t" + mesmericOrb + "\n"
                + "Manic Scribe:\t\t" + manicScribe + "\n"
                + "Glimpse The Unthinkable:\t" + glimpse + "\n"
                + "Mind Funeral:\t\t" + mindFuneral + "\n"
                + "Ashiok, Nightmare Weaver:\t" + ashiok + "\n"
                + "Fraying Sanity:\t\t" + sanity + "\n"
                + "Archive Trap:\t\t" + archiveTrap + "\n"
                + "Shelldock Isle:\t\t" + shelldock + "\n";

        return res;
    }
}
