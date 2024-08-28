package com.ericsson.de.models;

/**
 * A class for representing options in our application.
 */
public final class Options {
    private final String name;
    private final String number;

    public Options(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Process a shortcut for the option to be it's first character
     * @return the option short cut
     */
    public final String getCharacter() {
    	return String.valueOf(name.toLowerCase().charAt(0));
    }

    /**
     * The options' name
     * @return The options' name
     */
    public final String name() {
    	return name;
    }

    /**
     * The options' number, as a string.
     * @return the options' number, as a string.
     */
    public final String getNumber() {
    	return number;
    }

    /**
     * The options' number as an int
     * @return an int representing the option
     */
    public final int getValue() {
        return Integer.parseInt(number);
    }
}
