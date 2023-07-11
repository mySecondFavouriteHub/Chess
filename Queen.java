
package chessv2;

import java.util.ArrayList;


public class Queen extends Piece {
    
    public Queen(int row, int column, char colour) {
        this.colour =colour;
        this.name="Queen";
        this.movementPattern="queen";
        this.value=9;
        if(this.colour=='w'){
            this.symbol="q";
        }
        else{
            this.symbol="Q";
        }
        
        this.row=row;
        this.column=column;
        this.hasMoved=false;
    }
    public Queen(char colour){
        this.name="Queen";
        this.movementPattern="queen";
        this.value=9;
        this.colour=colour;
        if(this.colour=='w'){
            this.symbol="q";
        }
        else{
            this.symbol="Q";
        }
        this.hasMoved=false;
    }
    public ArrayList<int[]> legalMovesQueen(){
        
        ArrayList<int[]> legalMoves= new ArrayList<>();

        char oppositeColour='n';
        if(this.colour=='w'){ 
            oppositeColour='b';
        }else if(this.colour=='b'){
            oppositeColour='w';  
        }
        
        if(this.row!= 0 && this.column!= 0){
            int j = this.column;
            for(int i=this.row-1; i>=0; i--){
                if(j-1>= 0){
                    j--;
                    int[] coordinates = {i,j};
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
        }
        if(this.row!= 0 && this.column< 7){
            int j = this.column;
            for(int i=this.row-1; i>=0; i--){
                if(j+1<8){
                    j++;
                    int[] coordinates = {i,j};
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
        }
        if(this.row<7&& this.column< 7){
            int j = this.column;
            for(int i=this.row+1; i<=7; i++){
                if(j+1<8){
                    j++;
                    int[] coordinates = {i,j};
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
        }
        if(this.row<7 && this.column!=0){
            int j = this.column;
            for(int i=this.row+1; i<=7; i++){
                if(j-1>=0){
                    j--;
                    int[] coordinates = {i,j};
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
        }
        if(this.row!= 0){
            //UP MOVES
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
