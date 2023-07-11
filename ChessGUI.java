
package chessv2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public final class ChessGUI implements ActionListener{
    
    //int count = 0;
    JFrame frame;
    JPanel panel;
    JFrame promotionSelector;
    JPanel promotionPanel;
    JFrame gameOver;
    JPanel gameOverPanel;
    JLabel gameOverLabel;
    
    ArrayList<JButton> board;
    ArrayList<JButton> promotionList;
    ArrayList<JButton> gameOverList;
    
    static Font calibri= new Font("Calibri", 2, 30);
    static Font halvetica= new Font("Halvetica", 1,30);
    static final Color LEGAL_MOVE_COLOUR= new Color(10,35,66);
    static final Color TILE_COLOUR = new Color(44, 165,141);
    static final Color HIGHLIGHT_COLOUR= new Color(50,75,116);
    
    static final String PATH="YOUR PATH HERE";
    
    static boolean promotionIsVisible;
    static boolean gameOverIsVisible;
    static final ImageIcon promotionIcon= new ImageIcon(PATH+"kingIcon.png");
    static final ImageIcon programIcon= new ImageIcon(PATH+"blackPawnWhiteBg.png");
    
    
    static final ImageIcon blackKing= new ImageIcon(PATH+"blackKing.png");
    static final ImageIcon blackQueen= new ImageIcon(PATH+"blackQueen.png");
    static final ImageIcon blackRook= new ImageIcon(PATH+"blackRook.png");
    static final ImageIcon blackKnight= new ImageIcon(PATH+"blackKnight.png");
    static final ImageIcon blackBishop= new ImageIcon(PATH+"blackBishop.png");
    static final ImageIcon blackPawn= new ImageIcon(PATH+"blackPawn.png");
            
    
    static final ImageIcon whiteKing= new ImageIcon(PATH+"whiteKing.png");
    static final ImageIcon whiteQueen= new ImageIcon(PATH+"whiteQueen.png");
    static final ImageIcon whiteRook= new ImageIcon(PATH+"whiteRook.png");
    static final ImageIcon whiteKnight= new ImageIcon(PATH+"whiteKnight.png");
    static final ImageIcon whiteBishop= new ImageIcon(PATH+"whiteBishop.png");
    static final ImageIcon whitePawn= new ImageIcon(PATH+"whitePawn.png");
    
    static final ImageIcon grayQueen= new ImageIcon(PATH+"grayQueen.png");
    static final ImageIcon grayRook= new ImageIcon(PATH+"grayRook.png");
    static final ImageIcon grayKnight= new ImageIcon(PATH+"grayKnight.png");
    static final ImageIcon grayBishop= new ImageIcon(PATH+"grayBishop.png");
    
    //static JPanel inscr;
    //static ImageIcon blackPawnWhiteBg;
    public static final int BOARD_WIDTH= 8;
    public ChessGUI(){
 
        frame = new JFrame();
        promotionSelector=new JFrame();
        promotionPanel= new JPanel();
        panel = new JPanel();
        
        gameOverIsVisible=false;
        promotionSelector.setIconImage(promotionIcon.getImage());
        promotionPanel.setLayout(new GridLayout(1, 4, 0,0));
        promotionSelector.setSize(800, 300);
        promotionSelector.setVisible(true);
//        fuckYou=new JButton();
//        fuckYou.getSize(new Dimension(50, 50));
//        fuckYou.setText("fuck you java");
          fuckYou.setIcon(redSquare);
        
        frame.setIconImage(programIcon.getImage());
        //frame.add(fuckYou);
        
        //inscr= new JPanel();
        //inscr.setLayout(new GridLayout(1, BOARD_WIDTH, 0, 0));
        panel.setLayout(new GridLayout(BOARD_WIDTH,BOARD_WIDTH,0,0));
        //panel.setLayout(null);
        frame.setSize(730, 730);
        frame.add(panel, BorderLayout.CENTER);
        //frame.add(inscr, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chess");
        frame.setVisible(true);
        setBoard(TILE_COLOUR);
        setPromotion();
        setGameOver();
        
//        promotionSelector=new JPanel();
//        promotionSelector.setLayout(new GridLayout(1,4,0,0));
//        promotionSelector.setBounds(new Rectangle(800, 40));
        //promotionSelector.setVisible(true);
        //promotionSelector.setVisible(true);
        //frame.add(promotionSelector, BorderLayout.NORTH);
        
    }
  
    public void setGameOverLabel(String text){
        gameOverLabel.setText(text);
    }
    
    public void setGameOver(){
        gameOver= new JFrame();
        gameOver.setIconImage(promotionIcon.getImage());
        gameOverList= new ArrayList<>();
        gameOverPanel= new JPanel();
        EmptyBorder border= new EmptyBorder(20,20,20,20);
        gameOverPanel.setBorder(border);
        gameOverLabel= new JLabel("GAME OVER");
        gameOverLabel.setForeground(Color.WHITE);
        //label.setAlignmentX(400);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setFont(new Font("Calibri", 1, 50));
        gameOverPanel.setLayout(new GridLayout(3,1,20,10));
        gameOverPanel.setBackground(TILE_COLOUR);
        //gameOverPanel.setForeground(Color.WHITE);
        gameOverPanel.add(gameOverLabel, BorderLayout.NORTH);
        //gameOverPanel.setBounds(new Rectangle(800, 100));
        gameOver.setTitle("Game Over");
        gameOver.setResizable(true);
        gameOver.setSize(800, 500);
        gameOver.add(gameOverPanel);
        //gameOver.add(label, BorderLayout.NORTH);
        gameOver.setVisible(false);
        
        JButton quit= new JButton("QUIT");
        quit.setFont(halvetica);
        quit.addActionListener(this);
        quit.setFocusable(false);
        //quit.setIcon(grayBishop);
        quit.setBackground(Color.WHITE);
        quit.setForeground(Color.BLACK);
        //quit.setBackground(Color.white);
        gameOverList.add(quit);
        gameOverPanel.add(quit);
        
        JButton newGame= new JButton("NEW GAME");
        newGame.setFont(halvetica);
        newGame.addActionListener(this);
        newGame.setFocusable(false);
        newGame.setBackground(Color.WHITE);
        newGame.setForeground(Color.BLACK);
        //quit.setIcon(grayBishop);
        //newGame.setBackground(Color.white);
        gameOverList.add(newGame);
        gameOverPanel.add(newGame);
    }
    
    public void setPromotion(){
        promotionList= new ArrayList<>();
        
        promotionSelector.setTitle("Select Promotion");
        promotionSelector.setResizable(true);
        promotionSelector.setSize(800, 300);
        promotionSelector.add(promotionPanel);
        promotionSelector.setVisible(false);
        
        //promotionPanel.add(promotionList);
        
        
        JButton queen= new JButton();
        queen.addActionListener(this);
        queen.setFocusable(false);
        queen.setIcon(grayQueen);
        queen.setBackground(TILE_COLOUR);
        promotionPanel.add(queen);
        
        
        JButton rook= new JButton();
        rook.addActionListener(this);
        rook.setFocusable(false);
        rook.setIcon(grayRook);
        rook.setBackground(Color.white);
        promotionPanel.add(rook);
        
        JButton knight= new JButton();
        knight.addActionListener(this);
        knight.setFocusable(false);
        knight.setIcon(grayKnight);
        knight.setBackground(TILE_COLOUR);
        promotionPanel.add(knight);
        
        
        JButton bishop= new JButton();
        bishop.addActionListener(this);
        bishop.setFocusable(false);
        bishop.setIcon(grayBishop);
        bishop.setBackground(Color.white);
        promotionPanel.add(bishop);
        
        promotionList.add(queen);    
        promotionList.add(rook);    
        promotionList.add(knight);    
        promotionList.add(bishop);    
        
        
        
        
    }
    
    public void setBoard(Color bg){
        
        board= new ArrayList<>();

        
        for(int i=0; i< BOARD_WIDTH;i++){
            for(int j=0; j< BOARD_WIDTH;j++){
                JButton jb= new JButton();
                jb.addActionListener(this);
                //ImageIcon blackPawnWhiteBg= new ImageIcon("blackPawnWhiteBg.png");
                //jb.setIcon(whiteKing);
                jb.setFocusable(false);
                jb.setFont(halvetica);
                board.add(jb);
                
            }
        }
        
        for(int i=0; i< board.size();i++){
            JButton current= board.get(i);
            
            
            current.setBackground(bg);
            int column= (int)i%BOARD_WIDTH;
            if(column %2==0){
                for(int j=0; j< BOARD_WIDTH; j+=2){
                    if((i-j)%(BOARD_WIDTH*2)==0){
                        current.setBackground(Color.white);
                    }
                }       
            }
            else if(column %2 !=0){
                for(int j=BOARD_WIDTH+1; j< BOARD_WIDTH*2; j+=2){
                    if((i-j)%(BOARD_WIDTH*2)==0){
                        current.setBackground(Color.white);
                    }
                }

            } 
            if(current.getText().endsWith("w")){
                current.setFont(new Font("Calibri", 2, 40));
            }
            panel.add(current);
            //current.setText("hi:)");
            //current.wait(50);
        }
        
 
    }
    
    public void updateBoard(){

        for(int i=0; i< board.size();i++){
            
            JButton current= board.get(i);
            String symb= Board.allPieces.get(i).symbol;
            Piece p= Board.allPieces.get(i);
            int[] a={p.row, p.column};
            int position= Board.findPositionUsingLocation(a);
            boolean hasMoved=Board.allPieces.get(i).hasMoved;
            //boolean hasMoved=Board.allPieces.get(i).hasMoved;
            char colour= Board.allPieces.get(i).colour;
            //int[]coordinates= {Board.allPieces.get(i).row, Board.allPieces.get(i).column};
            switch (symb) {
                case " " -> current.setIcon(null);
                case "k" -> current.setIcon(whiteKing);
                case "q" -> current.setIcon(whiteQueen);
                case "r" -> current.setIcon(whiteRook);
                case "b" -> current.setIcon(whiteBishop);
                case "h" -> current.setIcon(whiteKnight);
                case "p" -> current.setIcon(whitePawn);
                
                case "K" -> current.setIcon(blackKing);
                case "Q" -> current.setIcon(blackQueen);
                case "R" -> current.setIcon(blackRook);
                case "B" -> current.setIcon(blackBishop);
                case "H" -> current.setIcon(blackKnight);
                case "P" -> current.setIcon(blackPawn);
                default -> {
                }
            }
            
            current.setText("");
            //Chess.printIntArray(coordinates);
            if(colour=='w'){
                current.setFont(calibri);
            }
            else{
                
                current.setFont(halvetica);
            }
            //JButton current= board.get(i);
            
            
        }
        
    }
    public void recolour(Color bg){
        for(int i=0; i< board.size();i++){
            JButton current= board.get(i);
            current.setForeground(Color.black);
            current.setBackground(bg);
            int column= (int)i%BOARD_WIDTH;
            if(column %2==0){
                for(int j=0; j< BOARD_WIDTH; j+=2){
                    if((i-j)%(BOARD_WIDTH*2)==0){
                        current.setBackground(Color.white);
                    }
                }       
            }
            else if(column %2 !=0){
                for(int j=BOARD_WIDTH+1; j< BOARD_WIDTH*2; j+=2){
                    if((i-j)%(BOARD_WIDTH*2)==0){
                        current.setBackground(Color.white);
                    }
                }

            } 
            
        }
    }
    
    public void highlightSquares(ArrayList<Integer> squares, Color colour) throws InterruptedException{
        for(int i=0; i< board.size();i++){
            for(int j=0; j< squares.size();j++){
                int[] coords=Board.findCoordinates(i);
                int row= 8-coords[0];
                char column=(char)(coords[1]+97);
                if(i==squares.get(j)){
                    board.get(i).setForeground(Color.white);
                    board.get(i).setBackground(colour);
                    board.get(i).setText(column+""+row);
                }
            }
        }
        //Thread.sleep(1000);
        //recolour();
    }
    public void highlightNoText(ArrayList<Integer> squares, Color colour) throws InterruptedException{
        for(int i=0; i< board.size();i++){
            for(int j=0; j< squares.size();j++){
                int[] coords=Board.findCoordinates(i);
                int row= 8-coords[0];
                char column=(char)(coords[1]+97);
                if(i==squares.get(j)){
                    board.get(i).setForeground(Color.white);
                    board.get(i).setBackground(colour);
                    //board.get(i).setText(column+""+row);
                }
            }
        }
        //Thread.sleep(1000);
        //recolour();
    }
    
    public void showGameOver(){
        gameOver.setVisible(true);
        gameOverIsVisible=true;
        
    }
    public void hideGameOver(){
        gameOver.setVisible(false);
        gameOverIsVisible=false;
    }
    
    public void showPromotionOptions(){
        promotionSelector.setVisible(true);
        promotionIsVisible=true;
        
    }
    public void hidePromotionOptions(){
        promotionSelector.setVisible(false);
        promotionIsVisible=false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(promotionIsVisible){
            for(int i=0 ;i< promotionList.size();i++){
                JButton p= promotionList.get(i);
                if(e.getSource()==p){
                    Pawn.promotion=i;
                }
            }
            
        }
        
        if(gameOverIsVisible){
            for(int i=0;i< gameOverList.size(); i++){
                JButton o= gameOverList.get(i);
                if(e.getSource() ==o){
                    Chess.optionListener=i;
                }
            }
        }
        else{
            for(int i=0; i< board.size();i++){
                JButton b= board.get(i);
                if(e.getSource()==b){
                    if(Chess.requestingPiece){
                        Chess.buttonListener=i;
                    }
                    else if(Chess.requestingMove){
                        Chess.moveListener=i;
                    }
                    //System.out.println(i);

                }
            }
        }
        
        //label.setText("Clicks: "+count);
    }
    
}

