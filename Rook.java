
package chessv2;

import java.util.ArrayList;


public class Rook extends Piece{
    public Rook(int row, int column, char colour) {
        this.hasMoved=false;
        this.colour =colour;
        this.name="Rook";
        this.movementPattern="straight";
        this.value=5;
        if(this.colour=='w'){
            this.symbol="r";
        }else if(this.colour=='b'){
            this.symbol="R";
        }
        this.row=row;
        this.column=column;
    }
    public Rook(char colour){
        this.hasMoved=false;
        this.name="Rook";
        this.movementPattern="straight";
        this.value=5;
        this.colour=colour;
        if(this.colour=='w'){
            this.symbol="r";
        }else if(this.colour=='b'){
            this.symbol="R";
        }
    }
    
    public ArrayList<int[]> legalMovesRook(){
        
        ArrayList<int[]> legalMoves= new ArrayList<>();
        //ArrayList<Piece> oppositeColourPieces= null;

        char oppositeColour='n';
        if(this.colour=='w'){ 
            oppositeColour='b';
        }else if(this.colour=='b'){
            oppositeColour='w';  
        }
        
        if(this.row!= 0){

            for(int i=this.row-1;i>=0;i--){

                int[] coordinates = {i, this.column};
                int position= Board.findPositionUsingLocation(coordinates);

                if(Board.allPieces.get(position).colour== this.colour){
                    break;
                }
                else if(Board.allPieces.get(position).colour== oppositeColour){
                    legalMoves.add(coordinates);
                    break;
                }
                else{
                    legalMoves.add(coordinates);
                }
            }
        }
        if(this.row!= 7){
            //DOWN MOVES
            for(int i=this.row+1;i<8;i++){

                int[] coordinates = {i, this.column};
                int position= Board.findPositionUsingLocation(coordinates);

                if(Board.allPieces.get(position).colour== this.colour){
                    break;
                }
                else if(Board.allPieces.get(position).colour== oppositeColour){
                    legalMoves.add(coordinates);
                    break;
                }
                else{
                    legalMoves.add(coordinates);
                }
            }
        }
        if(this.column!= 7){
            //RIGHT MOVES
            for(int i=this.column+1;i<8;i++){

                int[] coordinates = {this.row,i};
                int position= Board.findPositionUsingLocation(coordinates);

                if(Board.allPieces.get(position).colour== this.colour){
                    break;
                }
                else if(Board.allPieces.get(position).colour== oppositeColour){
                    legalMoves.add(coordinates);
                    break;
                }
                else{
                    legalMoves.add(coordinates);
                }
            }
        }
        if(this.column!= 0){
            //LEFT MOVES
            for(int i=this.column-1;i>=0;i--){

                int[] coordinates = {this.row,i};
                int position= Board.findPositionUsingLocation(coordinates);

                if(Board.allPieces.get(position).colour== this.colour){
                    break;
                }
                else if(Board.allPieces.get(position).colour== oppositeColour){
                    legalMoves.add(coordinates);
                    break;
                }
                else{
                    legalMoves.add(coordinates);
                }
            }
        }
       return legalMoves; 
    }
}
