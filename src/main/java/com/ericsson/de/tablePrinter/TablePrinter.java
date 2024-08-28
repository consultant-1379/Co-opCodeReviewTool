package com.ericsson.de.tablePrinter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The table printer classes takes a matrix of data and prints it.
 */
public final class TablePrinter {

	private final ColumnHeader[] cols;
	private final Collection<Row> rows;

	/**
	 * A class to hold row string information
	 */
	private static class Row {
		private final String[] data;
		Row(String[] v) {
			data = v;
		}
	}




	private static class ColumnHeader {
		private String name;
		private int maxWidth;
	}



	/**
	 * Constructor - pass in columns as an array, or hard coded
	 */
	public TablePrinter(String... names) {
		cols = new ColumnHeader[names.length];
		for (int i = 0; i < cols.length; i++) {
			cols[i] = new ColumnHeader();
			cols[i].name = names[i];
			cols[i].maxWidth = names[i].length();
		}

		rows = new ArrayList<>();
	}

	/**
	 * Adds a row - pass in an array or hard coded
	 */
	public final void addRow(String... values) {
		if (values.length != cols.length) {
			throw new IllegalArgumentException("invalid number of columns in values");
		}

		Row row = new Row(values);
		rows.add(row);
		for (int i = 0; i < values.length; i++) {
			if (values[i].length() > cols[i].maxWidth) {
				cols[i].maxWidth = values[i].length();
			}
		}
	}

	/**
	 * Ugly, poorly documented print method. All pieces of production code
	 * should have some methods that you have to decipher. This fulfils that
	 * requirement.
	 */
	public final void print() {
		int numDashes = cols.length * 3 + 1;
		for (ColumnHeader col : cols)
			numDashes += col.maxWidth;
		System.out.print(generateDashesString(numDashes) + "\n");
		System.out.print("|");
		for (ColumnHeader col : cols) {
			print(col.name, col.maxWidth);
		}
		System.out.print("\n");

		System.out.print(generateDashesString(numDashes) + "\n");
		for (Row row : rows) {
			System.out.print("|");
			int i = 0;
			for (String v : row.data) {
				print(v, cols[i++].maxWidth);
			}
			System.out.print("\n");
		}
		System.out.print(generateDashesString(numDashes) + "\n");
		System.out.print("\n");
		//return table.toString();
	}

	/**
	 * Helper method to make sure column headers and row information are printed
	 * the same
	 */
	private void print(final String columnHeader, final int columnWidth) {
		System.out.print(" ");
		System.out.print(columnHeader);
		System.out.print(generateSpacesString(columnWidth - columnHeader.length()));
		System.out.print(" |");
	}

	/**
	 *
	 * @param numberOfSpaces an integer specifying the number of dashes we want in our string.
	 * @return a string that is numberOfSpaces long
	 */
	private String generateSpacesString(final int numberOfSpaces) {
		StringBuilder sb = new StringBuilder();
		for(int count=0; count < numberOfSpaces; count++){
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 *
	 * @param numberOfDashes an integer specifying the number of dashes we want in our string.
	 * @return a string of dashes, that is numberOfDashes long
	 */
	private String generateDashesString(final int numberOfDashes) {
		StringBuilder sb = new StringBuilder();
		for(int count=0; count < numberOfDashes; count++) {
			sb.append("-");
		}
		return sb.toString();
	}


}