
package chessv2;

//import static chess.Board.blackPieces;
//import static chess.Board.board;
//import static chess.Board.whitePieces;
import java.util.ArrayList;


public class EmptySquare extends Piece {
    public EmptySquare(int row, int column) {
        
        this.name="Empty";
        this.symbol=" ";
        this.row=row;
        this.column=column;
        this.colour=' ';
        
    }
    public EmptySquare(){
        this.name="Empty";
        this.symbol=" ";
        this.colour=' ';
    }
}