
package chessv2;

import java.util.ArrayList;


public class King extends Piece{
    public int[]startingPosition;
    
    public King(int row, int column, char colour) {
        this.row=row;
        this.column=column;
        this.colour =colour;
        this.name="King";
        this.movementPattern="adjacent";
        this.value=1000;
        if(this.colour=='w'){
            int[]sP={7, 4};
            startingPosition=sP;
            this.symbol="k";
            if(this.column!=4 || this.row!=7){
                this.hasMoved=true;
            }
        }
        else{
            int[]sP={0, 4};
            startingPosition=sP;
            this.symbol="K";
            if(this.column!=4 || this.row!=0){
                this.hasMoved=true;
            }
        }
        
        
    }
    public King(char colour){
        this.name="King";
        this.movementPattern="adjacent";
        this.value=1000;
        this.colour=colour;
        if(this.colour=='w'){
            this.symbol="k";
            int[]sP={7, 4};
            startingPosition=sP;
        }
        else{
            this.symbol="K";
            int[]sP={0, 4};
            startingPosition=sP;
        }
    }
    
    public ArrayList<int[]> legalMovesKing(){
        

        ArrayList<int[]>legalMoves= new ArrayList<>();
        
        int[] top ={this.row-1, this.column};
        int[] bottom ={this.row+1, this.column};
        int[] right ={this.row, this.column+1};
        int[] left ={this.row, this.column-1};
        int[] topRight={this.row-1, this.column+1};
        int[] topLeft={this.row-1, this.column-1};
        int[] bottomRight={this.row+1, this.column+1};
        int[] bottomLeft={this.row+1, this.column-1};
        if(this.row>0){
            char topColour= Board.allPieces.get(Board.findPositionUsingLocation(top)).colour;
            if(topColour!=this.colour){
                legalMoves.add(top);
            }
            if(this.column>0){
                char leftColour= Board.allPieces.get(Board.findPositionUsingLocation(left)).colour;
                char topLeftColour= Board.allPieces.get(Board.findPositionUsingLocation(topLeft)).colour;
                if(leftColour!=this.colour){
                    legalMoves.add(left);
                }
                if(topLeftColour!=this.colour){
                    legalMoves.add(topLeft);
                }
            }if(this.column<7){
                char rightColour= Board.allPieces.get(Board.findPositionUsingLocation(right)).colour;
                char topRightColour= Board.allPieces.get(Board.findPositionUsingLocation(topRight)).colour;
                if(rightColour!=this.colour){
                   legalMoves.add(right); 
                }
                if(topRightColour!=this.colour){
                    legalMoves.add(topRight);
                }
            }
        }
        if(this.row<7){
            char bottomColour= Board.allPieces.get(Board.findPositionUsingLocation(bottom)).colour;
            if(bottomColour!=this.colour){
                legalMoves.add(bottom);
            }
            if(this.column>0){
                char leftColour= Board.allPieces.get(Board.findPositionUsingLocation(left)).colour;
                char bottomLeftColour= Board.allPieces.get(Board.findPositionUsingLocation(bottomLeft)).colour;
                if(leftColour!=this.colour){
                    legalMoves.add(left);
                }
                if(bottomLeftColour!=this.colour){
                    legalMoves.add(bottomLeft);
                }
                
            }if(this.column<7){
                char rightColour= Board.allPieces.get(Board.findPositionUsingLocation(right)).colour;
                char bottomRightColour= Board.allPieces.get(Board.findPositionUsingLocation(bottomRight)).colour;
                if(rightColour!=this.colour){
                    legalMoves.add(right);
                }
                if(bottomRightColour!=this.colour){
                    legalMoves.add(bottomRight);
                }
                
            }
        }
        
        
        return legalMoves;
    }
    
}
