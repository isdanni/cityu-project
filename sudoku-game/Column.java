package homework3;

/**
 * The column type of sequence
 *
 */
public class Column extends Sequence {

	public int colNum;
	private static final long serialVersionUID = -9172599321818777827L;
	/**
	 * The ID of the row
	 */

	//constructor
	public Column(int i) {
		this.colNum=i;
	}
	
	//methods
	@Override
	public void associate(Element readOnlyElement) 
	{
		super.setElement(colNum, readOnlyElement);
	}

	public int getColId() {
		return colNum; //get column id
	}
	@Override
	public String toString() {
		return null;//TO DO
	}

}
