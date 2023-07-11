
package chessv2;

import java.util.ArrayList;


public class Knight extends Piece{

    public Knight(int row, int column, char colour) {
        this.colour =colour;
        this.name="Knight";
        this.movementPattern="L";
        this.value=3;
        if(this.colour=='w'){
            this.symbol="h";
        }
        else{
            this.symbol="H";
        }
        this.row=row;
        this.column=column;
    }
    public Knight(char colour){
        this.name="Knight";
        this.movementPattern="L";
        this.value=3;
        this.colour=colour;
        if(this.colour=='w'){
            this.symbol="h";
        }
        else{
            this.symbol="H";
        }
    }
    public ArrayList<int[]> legalMovesKnight(){
            
        ArrayList<int[]> legalMoves= new ArrayList<>();
        
        char colour= this.colour;
        int row= this.row;
        int column= this.column;
        
        int[] currentCoordinates= {row,column};
        int currentPosition= Board.findPositionUsingLocation(currentCoordinates);
        
        
        char oppositeColour='n';
        if(colour=='w'){
            oppositeColour='b';
        }else if(colour=='b'){
            oppositeColour='w';
        }  
        
        ArrayList<Integer> finalPositions=new ArrayList<>();
        for(int i=-1; i<=1; i+=2){
            int finalPosition_1= currentPosition+i*6;
            int finalPosition_2= currentPosition+i*10;
            int finalPosition_3= currentPosition+i*15;
            int finalPosition_4= currentPosition+i*17; 
            finalPositions.add(finalPosition_1);
            finalPositions.add(finalPosition_2);
            finalPositions.add(finalPosition_3);
            finalPositions.add(finalPosition_4);
        }
        
        for(int i=0; i< finalPositions.size();i++){
            if(finalPositions.get(i)<64 && finalPositions.get(i)>-1){
                Piece atFinal= Board.allPieces.get(finalPositions.get(i));
            
                if(atFinal.colour!=colour){
                    int[]legalMove=Board.findCoordinates(finalPositions.get(i));
                    if(Math.abs(legalMove[0]-row) <=2 && Math.abs(column-legalMove[1]) <= 2){
                        legalMoves.add(legalMove);
                    } 
                }
            }
            
        }
        
        return legalMoves;

    }
    
    
}
