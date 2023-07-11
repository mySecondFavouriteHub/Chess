
package chessv2;

import java.util.ArrayList;


public class Bishop extends Piece {
    public Bishop(int row, int column, char colour) {
        this.colour =colour;
        this.name="Bishop";
        this.movementPattern="diagonal";
        this.value=3;
        if(this.colour=='w'){
            this.symbol="b";
        }
        else{this.symbol="B";}
               
        this.row=row;
        this.column=column;
    }
    public Bishop(char colour){
        this.name="Bishop";
        this.movementPattern="diagonal";
        this.value=3;
        this.colour=colour;
        if(this.colour=='w'){
            this.symbol="b";
        }
        else{this.symbol="B";}
    }
    
    public ArrayList<int[]> legalMovesBishop(){
        
        char oppositeColour='n';
        if(this.colour=='w'){
            oppositeColour= 'b';
        }else if(this.colour=='b'){
            oppositeColour='w';
        }
        ArrayList<int[]> legalMoves= new ArrayList<>();
        
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
                
            
            
  
     
        
        
        return legalMoves;   
    }
    
}
