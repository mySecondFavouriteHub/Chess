
package chessv2;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Chess {
    //public static String capturedWhite="";
    //public static String capturedBlack="";
    public static final String STARTING_POSITION_FEN="rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    public static ArrayList<int[]> moves= new ArrayList<>();
    public static int buttonListener;
    public static int moveListener;
    public static boolean requestingMove;
    public static boolean requestingPiece;
    public static boolean requestingOption;
    public static int optionListener;
    
    public static boolean gameIsOver;
    //public static ChessGUI gui;
    
    
    
    public static void main(String[] args) throws InterruptedException {
        startGame();
  
    }
    public static void startGame() throws InterruptedException{
        gameIsOver = false;
        Board.allPieces=new ArrayList<>();
        Board.setBoard(STARTING_POSITION_FEN);
        ChessGUI gui = new ChessGUI();
        gui.updateBoard();
        double move= 1;
        buttonListener=64;
        moveListener=64;
        optionListener=64;
        //System.out.println(Board.checkCheck());
        for (Piece p : Board.allPieces) {
            if(p.name.equals("King")){
                King k=(King)p;
                int[] position={k.row, k.column};
                if(position[0]!= k.startingPosition[0] || position[1]!= k.startingPosition[1]){
                    k.hasMoved=true;
                }
            }
        }
        
        
        Pawn.gui= gui;
        //gui.setPromotion();
        //gui.setBoard(ChessGUI.tileColour);
        
        while (!gameIsOver){
            boolean success=gameLoop(move, gui);
            if(success){
                move+=0.5;
            }
            
            
        }
        System.exit(0);
    }
    
    /**
     *
     * @param move
     * @param gui
     * @return
     */
    public static boolean gameLoop(double move, ChessGUI gui) throws InterruptedException{
        ArrayList<Piece> pieces= Board.allPieces;
        gui.updateBoard();
        
        System.out.println("\n");
                            //        if(move%1==0){
                            //            System.out.println("White to move");
                            //        }
                            //        else{
                            //            System.out.println("Black to move");
                            //        }
        System.out.println("move : "+move);
        
        for(int i=0; i< Board.allPieces.size();i++){
            if(Board.allPieces.get(i).name.equals("Pawn")){
                Pawn p= (Pawn)Board.allPieces.get(i);
                p.promotion();
                gui.updateBoard();
            }
        }

        System.out.println("Check - "+Board.checkCheck(Board.allPieces, move));
        System.out.println("Stalemate - "+Board.checkStalemate(Board.allPieces, move));
        System.out.println("Checkmate - "+Board.checkCheckmate(Board.allPieces, move));
                            //        if(Board.checkCheckmate(Board.allPieces, move) || Board.checkStalemate(Board.allPieces, move)){
                            //            return 0;
                            //        }
                            
        //int intChosenPiece = requestPiece();
        requestingOption=true;
        int option= optionListener;
        
        boolean mate= Board.checkCheckmate(pieces, move);
        boolean staleMate= Board.checkStalemate(pieces, move);
        if(mate || staleMate){
            
            String winner;
            if(move%1 == 0){
                winner="Black";
            }else{
                winner="White";
            }
            
            if(mate){
                gui.setGameOverLabel(winner+" wins by checkmate");
            }else if(staleMate){
                gui.setGameOverLabel("Draw by stalemate");
            }
            gui.showGameOver();
            while(option==64){
                Thread.sleep(30);
                option=optionListener;
            }
            gui.hideGameOver();
            if(option==0){
                
                //gui.hideGameOver();
                gameIsOver=true;
                return true;
            }else if(option==1){
                //System.exit(0);
                //gui.setBoard(Color.red);
                gui.frame.setVisible(false);
                gui=null;
                
                option=64;
                //Board.setBoard(STARTING_POSITION_FEN);
                //gui.updateBoard();
                startGame();

            }
        }
        requestingOption=false;
        optionListener=64;
        requestingPiece=true;
        int intChosenPiece= buttonListener;
        
        while(buttonListener==64 || !matchesTurn(move, intChosenPiece)){
            //intChosenPiece = requestPiece(); 
            Thread.sleep(30);
            intChosenPiece= buttonListener;
        }
        System.out.println("Chosen Piece: ");
        printIntArray(Board.findCoordinates(intChosenPiece));
        System.out.println("");
        requestingPiece=false;        
        //buttonListener=0;
        ArrayList<int[]>legalMoves= Board.fLM(pieces.get(intChosenPiece));
        //legalMoves=Board.eSC(legalMoves, pieces.get(intChosenPiece), move);
        legalMoves=Board.excludeSelfChecks(legalMoves, intChosenPiece, move);
        
        if(legalMoves.isEmpty() && !Board.checkCheckmate(pieces, move) && !Board.checkStalemate(pieces, move)){
            buttonListener=64;
            return false;
            
        }
        
        //System.out.println("Piece at: "+(char)(pieces.get(intChosenPiece).column+97)+", "+(8-pieces.get(intChosenPiece).row));
        //removeDuplicates(legalMoves);  
        System.out.println("Legal: ");
        printArrayListOFArrays(legalMoves);System.out.print("\n");
        //System.out.println("Impossible: ");
        //printArrayListOFArrays(impossibleMoves);System.out.print("\n");

        ArrayList<Integer> currentSquare= new ArrayList<>();
        currentSquare.add(intChosenPiece);
        gui.highlightNoText(currentSquare, ChessGUI.HIGHLIGHT_COLOUR);

        ArrayList<Integer> emptySquares= new ArrayList<>();
        ArrayList<Integer> occupiedSquares= new ArrayList<>();

        for(int i=0; i< legalMoves.size();i++){
            int sq= Board.findPositionUsingLocation(legalMoves.get(i));
            if(pieces.get(sq).name.equals("Empty")){
                emptySquares.add(sq);
            }else{
                occupiedSquares.add(sq);
            }

            //squares.add(intChosenPiece);
        }
        gui.highlightNoText(emptySquares, ChessGUI.LEGAL_MOVE_COLOUR);
        gui.highlightNoText(occupiedSquares, ChessGUI.LEGAL_MOVE_COLOUR);

        requestingMove=true;
        int intFinalPosition=moveListener;
        
        while(moveListener==64){
            //intChosenPiece = requestPiece(); 
            Thread.sleep(30);
            intFinalPosition= moveListener;
        }
        requestingMove=false;
        moveListener=64;
        int[] finalPosition = Board.findCoordinates(intFinalPosition);
        //System.out.println(finalPosition[0]+", "+finalPosition[1]); 
        System.out.println("Chosen Move: ");
        printIntArray(finalPosition);
        
        if(!checkContainsMove(legalMoves, finalPosition[0], finalPosition[1])){
            buttonListener=64;
            moveListener=64;
            gui.recolour(ChessGUI.TILE_COLOUR);
            return gameLoop(move, gui);
            
        }
        
        if(checkContainsMove(legalMoves,finalPosition[0],finalPosition[1])){
            gui.recolour(ChessGUI.TILE_COLOUR);
            gui.updateBoard();
            int intFinal=Board.findPositionUsingLocation(finalPosition);
            if(pieces.get(intChosenPiece).name.equals("Pawn") &&
                pieces.get(intFinal).name.equals("Empty") &&
                finalPosition[1]!=Board.findCoordinates(intChosenPiece)[1]){
                    int[] captured={Board.findCoordinates(intChosenPiece)[0], finalPosition[1]};
                    Board.move(Board.allPieces, intFinal, finalPosition);

                    int intCaptured= Board.findPositionUsingLocation(captured);
                    Board.allPieces.set(intCaptured, new EmptySquare(captured[0], captured[1]));

                    //System.out.println(Board.allPieces.get(intCaptured).symbol);
                    //Board.move(Board.allPieces, intCaptured, finalPosition);

            }
            if(pieces.get(intChosenPiece).name.equals("King")
            && Board.findPositionUsingLocation(finalPosition)-intChosenPiece==2){

                int kingRook= intChosenPiece+3;
                int[] rookFinalPos= Board.findCoordinates(kingRook-2);

                pieces.get(intChosenPiece).hasMoved=true;
                Board.move(Board.allPieces,intChosenPiece,finalPosition);
                Board.move(Board.allPieces, kingRook, rookFinalPos);
                pieces.get(kingRook-2).hasMoved=true;
            }

            else if(pieces.get(intChosenPiece).name.equals("King")
            && Board.findPositionUsingLocation(finalPosition)-intChosenPiece==-2){
                int queenRook= intChosenPiece-4;
                int[] rookFinalPos= Board.findCoordinates(queenRook+3);

                pieces.get(intChosenPiece).hasMoved=true;
                Board.move(Board.allPieces,intChosenPiece,finalPosition);
                Board.move(Board.allPieces, queenRook, rookFinalPos);
                pieces.get(queenRook+3).hasMoved=true;
            }

            else if(pieces.get(intFinal).colour!=' '){
                pieces.get(intChosenPiece).hasMoved=true;
                Board.capture(Board.allPieces, intChosenPiece, finalPosition);

            }
            else{
                pieces.get(intChosenPiece).hasMoved=true;
                Board.move(Board.allPieces,intChosenPiece,finalPosition);

            }

            //move+= 0.5;
            for(int i=0; i< pieces.size();i++){
                int[] coords = Board.findCoordinates(i);

                pieces.get(i).setRow(coords[0]);
                pieces.get(i).setColumn(coords[1]);
            }
            //
            int[] currentMove= {intChosenPiece, Board.findPositionUsingLocation(finalPosition)};
            moves.add(currentMove);
            //System.out.println(moves);

            //
            //Board.checkCheck(pieces, move);
            return true;
        }

        else{
            System.out.println("Not a legal move!");
            gui.recolour(ChessGUI.TILE_COLOUR);
            return false;
        }
        

    }
    
    
    public static boolean matchesTurn(double turn, int intChosenPiece){
        if(turn %1 == 0 && Board.allPieces.get(intChosenPiece).colour=='w'){
            return true;
        }else if(turn %1 != 0 && Board.allPieces.get(intChosenPiece).colour=='b'){
            return true;
        }
        return false;
    }
    public static void printArrayListOFArrays(ArrayList<int[]> array){
        //System.out.print("Legal moves: ");
        for(int i=0; i< array.size();i++){
            printIntArray(array.get(i));
            if(i!= array.size()-1){
                System.out.print(", ");
            }
            
        }
    }
    public static void printIntArray(int[] array){
        //System.out.print(" ");
        System.out.print((char)(array[1]+97)+"");
        System.out.print(8-(array[0]));
        //System.out.print(", ");
        
    }
    public static  boolean checkContainsMove(ArrayList<int[]>legalMoves, int desiredRow, int desiredColumn){
        for(int i=0; i< legalMoves.size();i++){
            if(legalMoves.get(i)[0]==desiredRow && legalMoves.get(i)[1]==desiredColumn){
                return true;
            }
        }
        return false;
    }
    public static int requestPiece(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Piece:");
        
        
        String coordinates = sc.nextLine();
        
        if(coordinates.length()<2 || coordinates.charAt(0)>'h' || coordinates.charAt(1)>56){
            return requestPiece();
        }
        
        int column= coordinates.charAt(0)-97;
        int row= 8-(coordinates.charAt(1)-48);
        
        int[] coords= {row, column};
        
        int pieceNumber= Board.findPositionUsingLocation(coords);
        
        return pieceNumber;
                
    } 

    public static int[] requestMove(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Your move:");
        String coordinates = sc.nextLine();
        
        int column= coordinates.charAt(0)-97;
        int row= 8-(coordinates.charAt(1)-48);
        
        int[] coords= {row, column};
        
        int pieceNumber= Board.findPositionUsingLocation(coords);
        
        
        int[] finalPosition= {row, column};
        return finalPosition;
    }
    public void printBoard(){
                    //System.out.println(buttonListener);
            
            
            //ACTUAL BOARD
            //System.out.print("\n"+capturedWhite+"\n");
//            System.out.print("\n(A)(B)(C)(D)(E)(F)(G)(H)\n");
//            for(int i=0;i< pieces.size(); i++){
//                if(i%8==0){
//                    if(i/8!=0){
//                        System.out.println("  ("+(9-(i/8))+")");
//                        
//                    }
//                    else{
//                        System.out.println("");
//                    }
//                    
//                }
//                if(pieces.get(i)!= null){
//                    System.out.print("["+pieces.get(i).symbol+"]");
//                    
//                }
//                
//                
//            }
//            System.out.print("  (1)");
            
            //System.out.println("");System.out.print("\n"+capturedBlack);
            
//            if(capturedWhite.contains("K")){
//                System.out.println("\nBlack wins");
//                break;
//                
//                
//            }
//            if(capturedBlack.contains("K")){
//                System.out.println("\nWhite wins");
//                break;
//            }
    }
 
}
