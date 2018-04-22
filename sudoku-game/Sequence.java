package homework3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a group of elements whose number cannot be the same
 *
 */
public abstract class Sequence implements Serializable {

	private static final long serialVersionUID = 4911806490982002779L;
	Element[] nine=new Element[9];
	//constructor
	public Sequence()
	{
		nine=null;
	}
	//methods
	/**
	 * Add an element to the sequence (for initialization only)
	 * @param element the element to be added
	 */
	public abstract void associate(Element element);
	
	public Element findElement(Character val) 
	{
		for(int i=0;i<9;i++) {
			if(Arrays.asList(nine).contains(val)) {
				return nine[i];
			}
		}
		return null;
	}
	/**
	 * Set an element to the sequence
	 * @param pos the position of the element (0 ~ DIMENSION^2-1)
	 * @param element the element to be set
	 */
	protected void setElement(int pos, Element element)
	{
		
	}
	/**
	 * Retrieve a list of all entered numbers in the sequence
	 * @return a list of all entered numbers in the sequence
	 */
	public ArrayList<Character> getEnteredVals()
	{
		ArrayList<Character> entered=new ArrayList();
		
		return null;
	}
}
