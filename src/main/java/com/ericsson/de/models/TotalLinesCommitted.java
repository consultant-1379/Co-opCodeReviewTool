package com.ericsson.de.models;

/**
 * Holding class for holding the lined committed, together with a name
 */
public final class TotalLinesCommitted {
    private final String name;
    private final int lines;

    public TotalLinesCommitted(String name, int lines) {
        this.name = name;
        this.lines = lines;
    }

    public final String getName() {
        return name;
    }

    public final int getLines() {
        return lines;
    }

}
