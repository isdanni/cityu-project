package homework3;

/**
 * The box type of sequence
 *
 */
public class Box extends Sequence {

	private static final long serialVersionUID = 3381374557853843834L;
	public int row;
	public int column;
	
	//constructor
	public Box(int i, int j) {
		this.row=i;
		this.column=j;
	}
	
	//methods
	/*
	 * (non-Javadoc)
	 */
	@Override
	public void associate(Element element) 
	{
		setElement(element.getX()%Board.DIMENSION*Board.DIMENSION+element.getY()%Board.DIMENSION, element);
	}
	public int getX() {
	   return row;
    }
	public int getY() {
        return column;
	}
	@Override
	public String toString() {
		return "x: "+row+", y: "+column+", value: "+super.getEnteredVals().toString();
	}
}
