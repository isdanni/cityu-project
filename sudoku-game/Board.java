package homework3;

import java.lang.Exception;
import java.awt.Dimension;
import java.io.File;
import java.io.File.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;



/**
 * The controller of the sudoku board.
 *
 */
public class Board implements Serializable{
	
	/**
	 * For serialization
	 */
    private static final long serialVersionUID = 6136897931786320404L;
    
    
    public static final int DIMENSION=3;
	private Element [][]element;  //2d array to store all elements
	private Sequence[][] sequence;  //2d array to store all sequences
	private Alphabet alphabet;
	
	/**
	 * Creates a new game
	 */
	//3 constructors
	//1: empty game
	public Board()
	{
		initSequences();
		alphabet=new Alphabet();
		element=new Element[DIMENSION*DIMENSION][DIMENSION*DIMENSION];//use 3*3 to define
		
		//update value
		for(int i=0;i<DIMENSION*DIMENSION;i++) {
			for(int j=0;j<DIMENSION*DIMENSION;j++) {
				element[i][j]=new ModifiableElement(i,j,(Box)sequence[0][(i/DIMENSION)*DIMENSION+j/DIMENSION],(Row)sequence[1][i],(Column)sequence[2][j],alphabet);
			}
		}
	}
	
	//2: construct game with given characters in "initBoard" and initial alphabet
	public Board(Character [][] initBoard, Alphabet alphabet)
	{
		this.alphabet=alphabet;//update value
		initSequences();
		element=new Element[DIMENSION*DIMENSION][DIMENSION*DIMENSION];//USE 3 DEFINE
		
		//update element value
		for(int i=0;i<initBoard.length;i++) {
			for(int j=0;j<initBoard.length;j++) {
				if(initBoard[i][j]==null) {
					element[i][j] = new Element(i, j, initBoard[i][j], (Box)sequence[0][(i/DIMENSION)*DIMENSION+j/DIMENSION], (Row)sequence[1][i], (Column)sequence[2][j], alphabet);
				}else {
					element[i][j] = new ModifiableElement(i, j, (Box)sequence[0][(i/DIMENSION)*DIMENSION+j/DIMENSION], (Row)sequence[1][i], (Column)sequence[2][j], alphabet);
				}
			}
		}
	}
	
	//3: read game from saved file using path included
	public Board(String path) throws CorruptedSaveFileException, IOException
	{
		FileInputStream fsaved=new FileInputStream(path); 
		ObjectInputStream osaved=new ObjectInputStream(fsaved);
		//use object input stream, easier to check
		
		Character[][] initBoard=new Character[DIMENSION*DIMENSION][DIMENSION*DIMENSION];
		
		//read all characters
		//error handling
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				initBoard[i][j]=osaved.readChar();
				if(!alphabet.isValidChar(initBoard[i][j])) {
					throw new CorruptedSaveFileException();
				}
				if(fsaved.read()==-1) {
					throw new FileNotFoundException();
				}
			}
		}
		
		String ext=path.toString().toLowerCase();
		if(ext!="txt") {
			throw new CorruptedSaveFileException();
		}
		
		//update
		initSequences();
		this.element=new Element[DIMENSION*DIMENSION][DIMENSION*DIMENSION];
		for(int i=0;i<initBoard.length;i++) {
			for(int j=0;j<initBoard[i].length;j++) {
				element[i][j]=new Element(i,j,initBoard[i][j],(Box)sequence[0][(i/DIMENSION)*DIMENSION+j/DIMENSION],(Row)sequence[0][j],(Column)sequence[1][j],alphabet);
			}
		}
		
		//close
		fsaved.close();
		osaved.close();
	} 
	
	/**
	 * Initialize all sequences
	 */
	
	
	//methods
	public void setVal(int x, int y, Character val)throws 
	OutOfBoundaryException, DuplicateException, ReadOnlyException
	{
		if(!(element[x][y] instanceof ModifiableElement)||!alphabet.isValidChar(val)) {
			throw new OutOfBoundaryException( "val is invalid" );
		}
		if(!(element[x][y] instanceof ModifiableElement)) {
			throw new ReadOnlyException("input val already exists");
		}
		if(sequence[1][x].findElement(val)!=null) {
			throw new DuplicateException(null,null);
		}
		((ModifiableElement)element[x][y]).setVal(val);
	}
	
	public int getVal(int i, int j)
	{
		return element[i][j].getVal();//get the value at position i,j
	}
	
	//get list of candidates
	public ArrayList<Character> getCandidates(int x, int y)
	{
		return ((ModifiableElement)element[x][y]).getCandidates();

	}
	
	//Get the alphabet
	public Alphabet getAlphabet() 
	{
		return alphabet;
	}
	
	//Set the character set for the game
	public void setAlphabet(Alphabet alphabet2) 
	{
		this.alphabet.copy(alphabet2);
	}
	
	
	private void initSequences() 
	{
		sequence = new Sequence[3][];
		sequence[0] = new Box[DIMENSION*DIMENSION];
		sequence[1] = new Row[DIMENSION*DIMENSION];
		sequence[2] = new Column[DIMENSION*DIMENSION];
		
		for(int i = 0;i<DIMENSION*DIMENSION;i++)
		{
			sequence[1][i] = new Row(i);
			sequence[2][i] = new Column(i);
		}
		for(int i = 0;i<DIMENSION;i++) {
			for(int j = 0;j<DIMENSION;j++) {
				sequence[0][i*3+j] = new Box(i, j);
			}
		}
	}


	@SuppressWarnings("resource")
	public void save(String path) throws IOException
	{
		FileOutputStream fsaved = new FileOutputStream(path);
		ObjectOutputStream osaved = new ObjectOutputStream(fsaved);
		for(int i = 0;i<DIMENSION*DIMENSION;i++) {
			osaved.writeChar(this.getAlphabet().get(i));
		}
		
		for(int i = 0;i<DIMENSION*DIMENSION;i++)
		{
			for(int j = 0;j<DIMENSION*DIMENSION;j++) {
				if(element[i][j].getVal()!=null) {
					osaved.writeChars(element[i][j].getVal().toString());
				}
			}
		}
		//close
		osaved.close();
		fsaved.close();
		}
	
	//open the game from saved file
	private void open(String path) throws CorruptedSaveFileException, IOException 
	{
		FileInputStream saved=new FileInputStream(path);  
		saved.close();
		//error handling
		String ext=path.toString().toLowerCase();
		if(saved.read()==-1) {
			throw new IOException();
		}
		if(ext=="txt") {
			throw new CorruptedSaveFileException();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String out=new String();
		for(int i=0;i<DIMENSION*DIMENSION;i++) {
			for(int j=0;j<DIMENSION*DIMENSION;j++) {
				if(element[i][j].getVal()!=null) {
					out+=element[i][j].getVal().toString();
				}
			}
		}
		out+='\n';//add ending
		return out;
	}
	
	//Checks if the game has finished
	//@return true if the game has finished
	public boolean isSolved()
	{
		for(int i=0;i<DIMENSION*DIMENSION;i++) {
			for(int j=0;j<DIMENSION*DIMENSION;j++) {
				if(element[i][j].getVal()==null)
					return false;
			}
		}
		return true;
	}
	
	//check if the game can be modified
	public boolean canModify(int r, int c) 
	{
		if(element[r][c] instanceof ModifiableElement) {
			return true;
		}
		return false;
	}

	//clear all the user input
	public void reset() throws OutOfBoundaryException, DuplicateException
	{
		for(int i=0;i<DIMENSION*DIMENSION;i++) {
			for(int j=0;j<DIMENSION*DIMENSION;j++) {
				if(element[i][j] instanceof ModifiableElement) {
					((ModifiableElement)element[i][j]).setVal(null);
					//if the element can be found in modifiable, then it's user input
				}
			}
		}
	}
}
