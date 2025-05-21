package cs191;

import javax.swing.JButton;

public class PianoKeys extends JButton {

	private int row;
	private int column;

	public PianoKeys(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Getter that can get the row of the button
	 * 
	 * @return row, the row the button is in
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Setter that can set which row the button will be in
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Getter that can get the column of the button
	 * 
	 * @return column, the column the button is in
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Setter that can set which column the button will be in
	 */
	public void setColumn(int column) {
		this.column = column;
	}
}

