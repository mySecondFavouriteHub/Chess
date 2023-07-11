

package chessv2;
import java.util.ArrayList;
public class Piece {
    
    public String name;
    public String symbol;
    
    public String movementPattern;
    public int value;
    public int row;
    public int column;
    public char colour;
    public boolean hasMoved;
    //public ArrayList<int[]> legalMoves;
    public void move(char piece, char[] endPosition){
        
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public void setSymbol(String symbol){
        this.symbol= symbol;
    }
    
 
}
