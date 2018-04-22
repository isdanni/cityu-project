package homework3;

import java.util.ArrayList;
import java.util.List;

/**
 *a grid on board that can be modified, stores all related sequences
 */
public class ModifiableElement extends Element {

	private static final long serialVersionUID = 8025282256821727988L;
	public int val;//used to store value
	private Box box;
	private Row row;
	private Column col;
	
	//constructors
	public ModifiableElement(int i, int j, Box box, Row row, Column col, Alphabet alphabet) 
	{
		super(i,j,null,box,row,col,alphabet);
		val=-1;
		this.box = box;
		this.row = row;
		this.col = col;
	}
	
	//methods
	/**
	 * Set value to the element. The value will not be set if any of the
	 * following exceptions happen
	 * @param val the value to be set. Null if you want to empty the 
	 * element.
	 * @throws OutOfBoundaryException The entered value is out-of-boundary
	 * @throws DuplicateException The entered value conflicts with some of
	 * the related sequences. The information of the conflicts is included
	 * in the DuplicateException object.
	 */
	public void setVal(Character val) throws OutOfBoundaryException, DuplicateException
	{
		//set the value
		if(val==null) {
			this.val = -1;
		}else {
		
		//error handling
		//check if alphabet include Character val
		boolean cont=false;
		if(!super.getAlphabet().isValidChar(val))
			throw new OutOfBoundaryException();
	}
	
	public ArrayList<Character> getCandidates()
	{	
		ArrayList<Character> out = new ArrayList<Character>();
		
		for(int i = 0;i<(Board.DIMENSION*Board.DIMENSION);i++) {
			out.add(getAlphabet().get(i));
		}
		out.removeAll(row.getEnteredVals());
		out.removeAll(col.getEnteredVals());
		out.removeAll(box.getEnteredVals());
		
		return out;
	}
	
	public Character getVal() {
		if(val!='\0') {
			return val;
		}
		return null;
	}
	}
}
