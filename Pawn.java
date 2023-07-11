
package chessv2;

//import static chess.Board.findPositionUsingLocation;
import java.util.ArrayList;
import java.util.Scanner;

public class Pawn extends Piece{
    
    public static ChessGUI gui;
    public static int promotion=-1;
    
    public Pawn(int row, int column, char colour) {
        this.colour =colour;
        this.name="Pawn";
        this.movementPattern="forward";
        this.value=1;
        if(this.colour=='w'){
            this.symbol="p";
        }
        else{
            this.symbol="P";}
        
        this.row=row;
        this.column=column;
        
        
    }
    public Pawn(char colour){
        this.name="Pawn";
        this.movementPattern="forward";
        this.value=1;
        this.colour=colour;
        if(this.colour=='w'){
            this.symbol="p";
        }
        else{
            this.symbol="P";}
    }
    
    public void promotion() throws InterruptedException{
        //ChessGUI gui= Chess.gui;
        
        int[] coords= {this.row, this.column};
        int pos=Board.findPositionUsingLocation(coords);
        
        int end=0;
        if(this.colour=='w'){
            end=0;
        }else if(this.colour=='b'){
            end=7;
        }
        
        if(this.row==end){
            gui.showPromotionOptions();
            //System.out.println("Promote to: 'h', 'b', 'r', 'q'");
            //Scanner sc=new Scanner(System.in);
            //char promotion=sc.nextLine().charAt(0);
            
            int makeOverPiece=promotion;
            
            while(makeOverPiece==-1){
                Thread.sleep(30);
                makeOverPiece=promotion;
            }

            switch (makeOverPiece) {
                case 0 -> Board.allPieces.set(pos, new Queen(this.row, this. column,this.colour));
                case 1 -> Board.allPieces.set(pos, new Rook(this.row, this. column,this.colour));
                case 2 -> Board.allPieces.set(pos, new Knight(this.row, this. column,this.colour));
                case 3 -> Board.allPieces.set(pos, new Bishop(this.row, this. column,this.colour));
                default -> Board.allPieces.set(pos, new Queen(this.row, this. column,this.colour));
            }
            
            promotion=-1;
            gui.hidePromotionOptions();
            
        }
        
    }
    
    public ArrayList<int[]> attacks(){
        ArrayList<int[]> attacks= new ArrayList<>();
        int moveDirection=0;
        char oppositeColour='n';
        int[] currentCoordinates= {row,column};
        int currentPosition= Board.findPositionUsingLocation(currentCoordinates);
        if(colour=='w'){
            oppositeColour='b';
            moveDirection =-1;
        }else if(colour=='b'){
            oppositeColour='w';
            moveDirection=1;
        }
        if(currentPosition+7*moveDirection< 64 && currentPosition+7*moveDirection>-1){
            Piece diagonalLeft= Board.allPieces.get(currentPosition+7*moveDirection);
            if(Math.abs(diagonalLeft.row-row)==1){
            if(diagonalLeft.colour==oppositeColour){
                int[]legalMove={diagonalLeft.row, diagonalLeft.column};

                attacks.add(legalMove);
            }
        }
        }
        
        
        if(currentPosition+9*moveDirection<64 && currentPosition+9*moveDirection>-1){
            
            Piece diagonalRight= Board.allPieces.get(currentPosition+9*moveDirection);
            if(Math.abs(diagonalRight.row-row)==1){
            
            if(diagonalRight.colour==oppositeColour){
                int[]legalMove={diagonalRight.row, diagonalRight.column};
                attacks.add(legalMove);
            }
        }
        }
        
        
        
        
        return attacks;
    }
    
    public ArrayList<int[]> legalMovesPawn(){
        
        ArrayList<int[]> legalMoves= new ArrayList<>();
        ArrayList<Piece> oppositeColourPieces= null;
        
        
        char colour= this.colour;
        int row= this.row;
        int column= this.column;
        
        int[] currentCoordinates= {row,column};
        int currentPosition= Board.findPositionUsingLocation(currentCoordinates);
        
        int[] coords = {this.row, this.column};
        int pieceLocation =Board.findPositionUsingLocation(coords);
        int moveDirection=0;
        int startingRow=0;
        char oppositeColour=0;
        if(colour=='w'){
            startingRow=6;
            oppositeColourPieces= Board.blackPieces();
            oppositeColour='b';
            moveDirection =-1;
        }else if(colour=='b'){
            startingRow=1;
            oppositeColourPieces= Board.whitePieces();
            oppositeColour='w';
            moveDirection=1;
        }

        if(row== startingRow){
            
            Piece pieceInFront = Board.allPieces.get(pieceLocation+8*moveDirection);
            Piece pieceTwoAhead = Board.allPieces.get(pieceLocation+16*moveDirection);
            if(pieceInFront.symbol.equals(" ")){
                int r = row+moveDirection;
                int c = column;

                if(pieceTwoAhead.symbol.equals(" ")){
                    int r2= row+2*moveDirection;//- white +black
                    int[] legalMove2 ={r2, c};
                    legalMoves.add(legalMove2);
                }


                int[] legalMove ={r, c};

                legalMoves.add(legalMove);

            }
            
        }
        else if(row!= startingRow){
            
            Piece pieceInFront = Board.allPieces.get(pieceLocation+8*moveDirection);

            if(pieceInFront.symbol.equals(" ")){
                int r = row+moveDirection;
                int c = column;
                int[] legalMove ={r, c};

                legalMoves.add(legalMove);
            }
        }
        
        if(currentPosition+7*moveDirection>-1 && currentPosition+7*moveDirection<64){
            Piece diagonalLeft= Board.allPieces.get(currentPosition+7*moveDirection);
            if(Math.abs(diagonalLeft.row-row)==1){
                if(diagonalLeft.colour==oppositeColour){
                    int[]legalMove={diagonalLeft.row, diagonalLeft.column};

                    legalMoves.add(legalMove);
                }
            }
        }
        if(currentPosition+9*moveDirection>-1 && currentPosition+9*moveDirection<64){
           
            Piece diagonalRight= Board.allPieces.get(currentPosition+9*moveDirection);
            if(Math.abs(diagonalRight.row-row)==1){

                if(diagonalRight.colour==oppositeColour){
                    int[]legalMove={diagonalRight.row, diagonalRight.column};
                    legalMoves.add(legalMove);
                }
            } 
        }
        
        if(Chess.moves.isEmpty()){
            return legalMoves;
        }
        
        
        if(column!=7){
            Piece right= Board.allPieces.get(currentPosition+1);
            if(right.name.equals("Pawn")){
                int[] last =Chess.moves.get(Chess.moves.size()-1);
                if(last[1]==currentPosition+1 && Math.abs(last[1]-last[0])==16){
                    int[]legalMove={right.row+moveDirection,right.column};
                    legalMoves.add(legalMove);
                    
//                    EmptySquare temp = new EmptySquare(right.row,right.column);
//        
//                    Board.allPieces.set(currentPosition+1, temp);
                }
            }
        }

        if(column!=0){
            Piece left= Board.allPieces.get(currentPosition-1);
            if(left.name.equals("Pawn")){
                int[] last =Chess.moves.get(Chess.moves.size()-1);
                if(last[1]==currentPosition-1 && Math.abs(last[1]-last[0])==16){
                    int[]legalMove={left.row+moveDirection,left.column};
                    legalMoves.add(legalMove);
                    
//                    EmptySquare temp = new EmptySquare(left.row,left.column);
//        
//                    Board.allPieces.set(currentPosition-1, temp);
                }
            }
        }
        
      
        return legalMoves;
    }
    
            
            
                
                
            
        
    
    
    
    
}
