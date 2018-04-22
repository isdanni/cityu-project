package homework3;

/**
 * The row type of sequence
 *
 */
public class Row extends Sequence {

	public int rowNum;
	private static final long serialVersionUID = 8046294874695168279L;
	
	//constructor
	/**
	 * The ID of the row
	 */
	public Row(int i) {
		this.rowNum=i;
	}

	//methods
	@Override
	public void associate(Element element) 
	{
		super.setElement(rowNum, element);
	}
	public int getRowId() 
	{
		return rowNum;
	}
	@Override
	public String toString() 
	{
		return "row: "+rowNum+", value: "+super.getEnteredVals().toString();
	}

}
