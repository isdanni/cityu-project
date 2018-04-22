package homework3;

import java.io.Serializable;

public class Element implements Serializable{

	private static final long serialVersionUID = 5575699296262255060L;
	private int row;
	private int col;
	private Alphabet alphabet;
	private int val;
	
	//constructor
	public Element(int x, int y, Character val,
			Box box, Row row, Column col, Alphabet alphabet) {
		this.row=x;
		this.col=y;
		this.val = (val==null)?-1:alphabet.getPosition(val);
		box.associate(this);
		row.associate(this);
		col.associate(this);
		this.alphabet=alphabet;
	}

	//methods
	public int getX() 
	{
		return row;
	}
	public int getY() 
	{
		return col;
	}

	/**
	 * Get the value entered in the element. Null if the element is empty.
	 * @return the value entered in the element
	 */
	public Character getVal() 
	{
		if(val!=-1) {
			return alphabet.get(val);
		}
		return null;
	}
	
	protected Alphabet getAlphabet() 
	{
		return alphabet;
	}
}
