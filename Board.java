
package chessv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Board {

    public static ArrayList<Piece> allPieces = new ArrayList<>();

    public static ArrayList<Piece> whitePieces(){
        ArrayList<Piece> white= new ArrayList<>();
        for(int i=0; i< allPieces.size();i++){
            if(allPieces.get(i).colour=='w'){
                white.add(allPieces.get(i));
            }
        }
        return white;
    }
    
    public static ArrayList<Piece> blackPieces(){
        ArrayList<Piece> black= new ArrayList<>();
        for(int i=0; i< allPieces.size();i++){
            if(allPieces.get(i).colour=='b'){
                black.add(allPieces.get(i));
            }
        }
        return black;
    }
    
    
    public static void setBoard(String fen){
        for(int i=0; i< fen.length();i++){
            char c= fen.charAt(i);
            int n=(int)c;
            //System.out.println("c: "+c+" n: "+n);
            if(c=='r'){
                allPieces.add(new Rook('b'));
            }
            else if(c=='n'){
                allPieces.add(new Knight('b'));
            }
            else if(c=='b'){
                allPieces.add(new Bishop('b'));
            }
            else if(c=='q'){
                allPieces.add(new Queen('b'));
            }
            else if(c=='k'){
                allPieces.add(new King('b'));
            }
            else if(c=='p'){
                allPieces.add(new Pawn('b'));
            }
            else if(c=='R'){
                allPieces.add(new Rook('w'));
            }
            else if(c=='N'){
                allPieces.add(new Knight('w'));
            }
            else if(c=='B'){
                allPieces.add(new Bishop('w'));
            }
            else if(c=='Q'){
                allPieces.add(new Queen('w'));
            }
            else if(c=='K'){
                allPieces.add(new King('w'));
            }
            else if(c=='P'){
                allPieces.add(new Pawn('w'));
            }
            else if(c=='/'){
                
            }
            else if(((int)c< 58)&&((int)c>47) ){
                for(int j=0; j< (int)c-48; j++){
                    allPieces.add(new EmptySquare());
                }
            }
                
        }
        
        for(int i=0; i<allPieces.size();i++){
            Piece p= allPieces.get(i);
            int[] coordinates= findCoordinates(i);
            p.setRow(coordinates[0]);
            p.setColumn(coordinates[1]);
        }
    }
    
    public static void move(ArrayList<Piece> board,int intPiece, int[] finalPosition){
        //board[piece.row][piece.column]=" ";
        Piece piece= board.get(intPiece);
        int intFinal= findPositionUsingLocation(finalPosition);
        EmptySquare es= (EmptySquare)board.get(intFinal);
        Collections.swap(board, intPiece, intFinal);
        int[] initial=findCoordinates(intPiece);
        
        
        es.setRow(initial[0]);
        es.setRow(initial[0]);
        piece.setRow(finalPosition[0]);
        piece.setColumn(finalPosition[1]);
        //board[piece.row][piece.column]=piece.symbol;
    }
    
    public static void capture(ArrayList<Piece> board, int intPiece, int[] finalPosition){
        
        int intFinal = findPositionUsingLocation(finalPosition);
//        if(board.get(intFinal).colour== 'b'){
//            if(Chess.capturedBlack.length()>0){
//                //Chess.capturedBlack+="-";
//            }
//            //Chess.capturedBlack+= board.get(intFinal).symbol;
//        }
//        else if(board.get(intFinal).colour== 'w'){
//            if(Chess.capturedWhite.length()>0){
//               //Chess.capturedWhite+="-"; 
//            }
//            //Chess.capturedWhite+= board.get(intFinal).symbol;
//        }
        EmptySquare temp = new EmptySquare(finalPosition[0],finalPosition[1]);
        board.set(intFinal, temp);
        move(board, intPiece, finalPosition);
    }
    
    public static int[] findCoordinates(int location){
        
        int row =(int)Math.floor(location/8.0);
        int column = location%8;
        int[] coords ={row, column};
        return coords;
    }
    public static int findPositionUsingLocation(int[] coords){
        int location;
        location = 8*coords[0]+ coords[1];
        return location;
    }
    
    public static ArrayList<int[]> findAttacks(Piece p){
        if(p.name.equals("Pawn")){
            Pawn pawn=(Pawn)p;
            return pawn.attacks();
        }
        else{
            return fLM(p);
        }
    }
    
    public static ArrayList<int[]> fLM(Piece p){
        
        ArrayList<int[]>legalMoves;
            
        switch (p.name) {
            case "Pawn" -> {
                Pawn thisPawn =(Pawn)p;
                legalMoves= thisPawn.legalMovesPawn();
            }
            case "Knight" -> {
                Knight thisKnight= (Knight)p;
                legalMoves= thisKnight.legalMovesKnight();
            }
            case "Bishop" -> {
                Bishop thisBishop =(Bishop)p;
                legalMoves= thisBishop.legalMovesBishop();
            }
            case "Rook" -> {
                Rook thisRook =(Rook)p;
                legalMoves= thisRook.legalMovesRook();
            }
            case "Queen" -> {
                Queen thisQueen =(Queen)p;
                legalMoves= thisQueen.legalMovesQueen();
            }
            case "King" -> {
                King thisKing =(King)p;
                legalMoves= thisKing.legalMovesKing();
            }
            default -> {
                return null;
            }
        }

        return legalMoves;
    }
    
    public static ArrayList<int[]> excludeSelfChecks(ArrayList<int[]> legalMoves, int intPiece,double turn){
        ArrayList<Piece> originalPosition=allPieces;
        ArrayList<Piece> possiblePosition;
        ArrayList<int[]> legalForReal= new ArrayList<>();
        
        
        
        if(allPieces.get(intPiece).name.equals("King")){
            int firstRank;
            Piece k= allPieces.get(intPiece);
            char oppositeColour;
            if(k.colour=='w'){
                firstRank=7;
                oppositeColour='b';
            }else{
                firstRank=0;
                oppositeColour='w';
            }
            if(!k.hasMoved){
                int[] kingSideRook={firstRank, 7};
                int[] fFile={firstRank, 5};
                int[] gFile= {firstRank, 6};

                int intKSR= Board.findPositionUsingLocation(kingSideRook);
                int intFFile= Board.findPositionUsingLocation(fFile);
                int intGFile= Board.findPositionUsingLocation(gFile);

                ArrayList<Piece> board= Board.allPieces;
                if(!board.get(intKSR).hasMoved
                && board.get(intFFile).name.equals("Empty")
                && board.get(intGFile).name.equals("Empty")
                && !Board.isAttacked(board.get(intFFile), oppositeColour)){

                    int[] castleK={k.row, k.column+2};
                    legalMoves.add(castleK);
                }

                int[] queenSideRook={firstRank,0};
                int[] dFile={firstRank, 3};
                int[] cFile={firstRank, 2};
                int[] bFile={firstRank, 1};

                int intQSR=Board.findPositionUsingLocation(queenSideRook);
                int intDFile=Board.findPositionUsingLocation(dFile);
                int intCFile=Board.findPositionUsingLocation(cFile);
                int intBFile=Board.findPositionUsingLocation(bFile);
                    
                
//                
//                System.out.println("Queen rook moved: "+board.get(intQSR).hasMoved);
//                System.out.println("D File Empty: "+board.get(intDFile).name.equals("Empty"));
//                System.out.println("C File Empty: "+board.get(intDFile).name.equals("Empty"));
//                System.out.println("B File Empty: "+board.get(intDFile).name.equals("Empty"));
//                System.out.println("D file attacked: "+Board.isAttacked(board.get(intDFile)));
//                System.out.println("C file attacked: "+Board.isAttacked(board.get(intCFile)));
//                
                
                if(!board.get(intQSR).hasMoved
                && board.get(intDFile).name.equals("Empty")
                && board.get(intCFile).name.equals("Empty")
                && board.get(intBFile).name.equals("Empty")
                && !Board.isAttacked(board.get(intDFile), oppositeColour)
                && !Board.isAttacked(board.get(intCFile), oppositeColour)
                        ){
                    
                    
                    int[] castleQ={k.row, k.column-2};
                    legalMoves.add(castleQ);
                    
                }
            }

        }
        
        if(legalMoves.isEmpty()){
            return legalForReal;
        }
        
        for (int[] legalMove : legalMoves) {
            int intLegal= findPositionUsingLocation(legalMove);
            possiblePosition=originalPosition;
            boolean capture=false;
            
            Piece captured=null;
            Piece capturer=originalPosition.get(intPiece);
            
            if(possiblePosition.get(intLegal).name.equals("Empty")){
                move(possiblePosition, intPiece, legalMove);
            }else{
                captured= possiblePosition.get(intLegal);
                capture(possiblePosition, intPiece, legalMove);
                capture=true;
            }
            if(!checkCheck(possiblePosition, turn)){
                legalForReal.add(legalMove);
            }//resetting the board
            if(!capture){
                int[] c=findCoordinates(intPiece);
                move(possiblePosition, intLegal, c);
            }else if(capture){
                move(possiblePosition, intLegal, findCoordinates(intPiece));
                possiblePosition.set(intLegal, captured);
            }
            //possiblePosition=allPieces;
//            for(int i=0; i< possiblePosition.size();i++){
//                int[] coords = Board.findCoordinates(i);
//
//                possiblePosition.get(i).setRow(coords[0]);
//                possiblePosition.get(i).setColumn(coords[1]);
//            }
            
        }
        return legalForReal; 
    }
    
    public static boolean checkCheckmate(ArrayList<Piece> currentPosition, double turn){ 
        ArrayList<Piece> ourPieces;
        if(turn %1== 0){    
            ourPieces=whitePieces();    
        }else{   
            ourPieces=blackPieces();  
        }
        for (Piece p : ourPieces) {
            ArrayList<int[]> pLM= fLM(p);
            int[] location={p.row, p.column};
            int intPiece= findPositionUsingLocation(location);
            pLM=excludeSelfChecks(pLM, intPiece,turn);
            if(!pLM.isEmpty()){
                return false;
            }
        }
        return checkCheck(currentPosition, turn);
    }
    
    public static boolean checkStalemate(ArrayList<Piece> currentPosition, double turn){
        ArrayList<Piece> ourPieces;
        if(turn %1== 0){    
            ourPieces=whitePieces();    
        }else{   
            ourPieces=blackPieces();  
        }
        for (Piece p : ourPieces) {
            ArrayList<int[]> pLM= fLM(p);
            int[] location={p.row, p.column};
            int intPiece= findPositionUsingLocation(location);
            pLM=excludeSelfChecks(pLM, intPiece,turn);
            if(!pLM.isEmpty()){
                return false;
            }
        }
        return !checkCheck(currentPosition, turn);
    }
    
    public static boolean isAttacked(Piece p){
        ArrayList<int[]> opMoves= new ArrayList<>();
        ArrayList<Piece> opPieces;
        
        if(p.colour=='b'){
            opPieces=whitePieces();
        }else{
            opPieces=blackPieces();
        }
        
        for(int i=0; i< opPieces.size();i++){
            ArrayList<int[]> attacks= findAttacks(opPieces.get(i));
            opMoves.addAll(attacks);
        }
        for(int i=0; i< opMoves.size();i++){
            if(opMoves.get(i)[0]==p.row && opMoves.get(i)[1]==p.column){
                
                return true;
            }
        }
        return false;
    }
    
    public static boolean isAttacked(Piece p, char attacker){
        ArrayList<int[]> opMoves= new ArrayList<>();
        ArrayList<Piece> opPieces;
        
        switch (attacker) {
            case 'b' -> opPieces=blackPieces();
            case 'w' -> opPieces=whitePieces();
            default -> {
                return false;
            }
        }
        for(int i=0; i< opPieces.size();i++){
            ArrayList<int[]> attacks= findAttacks(opPieces.get(i));
            opMoves.addAll(attacks);
        }
        for(int i=0; i< opMoves.size();i++){
            if(opMoves.get(i)[0]==p.row && opMoves.get(i)[1]==p.column){
                
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkCheck(ArrayList<Piece> currentPosition, double turn){
        
        //checks if we are in check;
        ArrayList<int[]> opMoves= new ArrayList<>();
        ArrayList<Piece> opPieces;
        ArrayList<Piece> friendly;
        Piece ourKing;
        if(turn %1== 0){
            opPieces=blackPieces();
            friendly=whitePieces();
            ourKing= new King('w');
        }else{
            opPieces=whitePieces();
            friendly=blackPieces();
            ourKing= new King('b');
        }

        for(int i=0; i< friendly.size();i++){
            if(friendly.get(i).name.equals("King")){
                ourKing= friendly.get(i);
                break;
            }
        }
        return isAttacked(ourKing);
        
    }
 
}
