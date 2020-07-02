package com.aide.trainer.myapp;

import android.app.*;
import android.os.*;

import android.app.Activity;




import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.graphics.Color;



public class MainActivity extends Activity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];



    private int roundCount=0;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
	
		private boolean human1=true;
		private boolean ai=true;

    private Random rand=new Random();
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
				getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FFFFFF"));

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
										resetGame();
                    ai=true;
									
										getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FFFFFF"));
								}
						});
				Button buttonAI = findViewById(R.id.button_AI);
        buttonAI.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
										resetGame();
										getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FFCCFF"));
                    
										ai=true;
										
										
										random();
								}
						});
		
		Button buttonHuman = findViewById(R.id.button_two);
		buttonHuman.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
				resetGame();
				getWindow().getDecorView().setBackgroundColor(Color.parseColor("#00FFFF"));
		

			
				ai=false;
		}
		});
		 


    }
		public void random(){
				int X=rand.nextInt(3);
				int Y=rand.nextInt(3);
				Board.box[X][Y]="O";
				String pos="button_"+X+""+Y;
				int resID = getResources().getIdentifier(pos, "id", getPackageName());
				Button b=findViewById(resID);
				b.setText("⭕");


				roundCount++;
		}

    @Override
    public void onClick(View v) {

				if (!((Button) v).getText().toString().equals("")) {
            return;
        }


				switch(v.getId()){
						case R.id.button_00:
								if(human1==true || ai){
								Board.box[0][0]="X";
								
								((Button) v).setText("❌");
								}else{
										Board.box[0][0]="O";

										((Button) v).setText("⭕");
								}


								roundCount++;

								break;
						case R.id.button_01:
								if(human1==true || ai){
										Board.box[0][1]="X";

										((Button) v).setText("❌");
								}else{
										Board.box[0][1]="O";

										((Button) v).setText("⭕");
								}
								


						    roundCount++;

								break;	
						case R.id.button_02:
								if(human1==true || ai){
										Board.box[0][2]="X";

										((Button) v).setText("❌");
								}else{
										Board.box[0][2]="O";

										((Button) v).setText("⭕");
								}

								roundCount++;

								break;

						case R.id.button_10:
								if(human1==true || ai){
										Board.box[1][0]="X";

										((Button) v).setText("❌");
								}else{
										Board.box[1][0]="O";

										((Button) v).setText("⭕");
								}


								roundCount++;

								break;
						case R.id.button_11:
								if(human1==true || ai){
										Board.box[1][1]="X";

										((Button) v).setText("❌");
								}else{
										Board.box[1][1]="O";

										((Button) v).setText("⭕");
								}


								roundCount++;

							  break;
						case R.id.button_12:
								if(human1==true || ai){
										Board.box[1][2]="X";

										((Button) v).setText("❌");
								}else{
										Board.box[1][2]="O";

										((Button) v).setText("⭕");
								}


							  roundCount++;

						    break;
						case R.id.button_20:
								if(human1==true || ai){
										Board.box[2][0]="X";

										((Button) v).setText("❌");
								}else{
										Board.box[2][0]="O";

										((Button) v).setText("⭕");
								}


								roundCount++;

								break;
						case R.id.button_21:
								if(human1==true || ai){
										Board.box[2][1]="X";

										((Button) v).setText("❌");
								}else{
										Board.box[2][1]="O";

										((Button) v).setText("⭕");
								}


								roundCount++;

							  break;
						case R.id.button_22:
								if(human1==true || ai){
										Board.box[2][2]="X";

										((Button) v).setText("❌");
								}else{
										Board.box[2][2]="O";

										((Button) v).setText("⭕");
								}


					   		roundCount++;

								break;

				}
				
				if (checkForWin()) {
						if(human1==true || ai){

						player1Wins();
						}else{
								player2Wins();
						}

				} else if (roundCount == 9) {
						draw();

				}
				else if(ai==false){
						human1=!human1;
						return;
						}
				else{
						AI();
				}

    }


		private void AI(){
				Button b;
				Point p=Board.findBestMove();
				String pos="button_"+p.x+""+p.y;
				int ID=getResources().getIdentifier(pos,"id",getPackageName());
				b=findViewById(ID);
				Board.box[p.x][p.y]="O";

				b.setText("⭕");

				roundCount++;




				if (checkForWin()) {

						player2Wins();

				} else if (roundCount == 9) {
						draw();
				} 


		}
    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
								&& field[i][0].equals(field[i][2])
								&& !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
								&& field[0][i].equals(field[2][i])
								&& !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
						&& field[0][0].equals(field[2][2])
						&& !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
						&& field[0][2].equals(field[2][0])
						&& !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
				human1=true;
				Board.resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
			  human1=false;
				Board.resetBoard();
    }

    private void draw() {


        Toast.makeText(this, "🅳🆁🅰🆆! 😬", Toast.LENGTH_SHORT).show();
        resetBoard();
				Board.resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("❌ :" + player1Points);
        textViewPlayer2.setText("⭕ :" + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;

    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
				Board.resetBoard();
			
				
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
				//  outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
				// player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
class Point{
		int x,y;
		public  Point(int x,int y){
				this.x=x;
				this.y=y;

		}
}
class Board{
		static List <Point> availableBoxes;
		static String box[][]=new String[3][3];



		public static  void placeElement(Point p,String s){
				box[p.x][p.y]=s;
		}
		public static void remove(Point p){
				box[p.x][p.y]=null;
		}
		public static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box[i][j]=null;
            }
        }
		}
	  public static List<Point> getAvailableBoxes(){

				availableBoxes=new ArrayList<Point>();
				for(int i=0;i<box.length;i++){
						for(int j=0;j<box[i].length;j++){
								if(box[i][j]==null){
										availableBoxes.add(new Point(i,j));
								}
						}
				}
				return availableBoxes;
		}
		public static boolean HasWon(String s){	   
				if((box[0][0]==box[1][1]&&box[1][1]==box[2][2]&&box[2][2]==s)||(box[0][2]==box[1][1]&&box[1][1]==box[2][0]&&box[2][0]==s))
				{ return true;}  
				for(int i=0;i<box.length;i++){
						if(box[i][0]==box[i][1]&&box[i][1]==box[i][2]&&box[i][2]==s||box[0][i]==box[1][i]&&box[1][i]==box[2][i]&&box[2][i]==s){  

								return true; }
				}		    

				return false;

		}

		public static boolean isGameOver(){

				return(HasWon("X")||HasWon("O")||getAvailableBoxes().isEmpty());
		}



		public static int minimax(int depth,boolean isMax){
				List <Point> boxAvailable=getAvailableBoxes();

				if(HasWon("O")){return 1;}
				if(HasWon("X")){return -1;}
				if(boxAvailable.isEmpty()){ return 0;}

				int min=Integer.MAX_VALUE; int max=Integer.MIN_VALUE;	


				if(isMax){  

						for(int i=0;i<boxAvailable.size();i++){
								placeElement(boxAvailable.get(i),"O");

								max=Math.max(minimax(depth+1,!isMax),max);
								remove(boxAvailable.get(i));
						}
				}
				else {
						for(int i=0;i<boxAvailable.size();i++){
								if(min==-1 )break;

								placeElement(boxAvailable.get(i),"X");
								min=Math.min(minimax(depth+1,!isMax),min);	
								remove(boxAvailable.get(i));
						}
				}


				return isMax?max+depth:min;
		}



		public static Point findBestMove(){
				Point A=new Point(-1,-1);
				List<Point> leftBoxes=getAvailableBoxes();
				int bestVal=Integer.MIN_VALUE;

				for(int i=0;i<leftBoxes.size();i++){

						placeElement(leftBoxes.get(i),"O");

						int moveVal=minimax(0,false);
						if(moveVal>bestVal){A=leftBoxes.get(i); }
						bestVal=Math.max(moveVal,bestVal);
						remove(leftBoxes.get(i));
				}
				return A;
		}	


}
