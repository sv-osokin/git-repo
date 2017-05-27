/**
 * Java. Level 1. Lesson 4. Home Work.
 * @author Sergey Osokin
 * @version dated May 19, 2017
 */
import java.util.*;
 
class TicTacToe {
	final int SIZE = 3;
	final int F_SIZE = 13;//playing field
	//playing field array, for turt - input number in  the cell
	char[][] field = {
		{'+','-','-','-','+','-','-','-','+','-','-','-','+'},
		{'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
		{'|',' ','1',' ','|',' ','2',' ','|',' ','3',' ','|'},
		{'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
		{'+','-','-','-','+','-','-','-','+','-','-','-','+'},
		{'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
		{'|',' ','4',' ','|',' ','5',' ','|',' ','6',' ','|'},
		{'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
		{'+','-','-','-','+','-','-','-','+','-','-','-','+'},
		{'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
		{'|',' ','7',' ','|',' ','8',' ','|',' ','9',' ','|'},
		{'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
		{'+','-','-','-','+','-','-','-','+','-','-','-','+'}
	};
	final char DOT_X = 'x';
	final char DOT_O = '.';
	int counter = 0;
	Scanner sc = new Scanner(System.in);
	int[] turn = new int [SIZE * SIZE];
	int[][] aiTurn = {
		{1, 3, 7, 9},
		{2, 4, 6, 8}
	};
	int[] x = new int[5];
	int[] o = new int[4];
	Random rand = new Random();
    public static void main(String[] args) {
		new TicTacToe().go();
	}
	void go() {
		initTurns();
		printField();
		while (true) {
			humanTurn();
			drawCell();
			printField();
			if (checkWin(-11)) {
			System.out.println("You win!");
			break;
			}
			if (isMapFull()) {
			System.out.println("Sorry, drow.");
			break;
			}
			aiTurn(counter);
			drawCell();
			printField();
			if (checkWin(-10)) {
			System.out.println("AI win!");
			break;
			}
			if (isMapFull()) {
			System.out.println("Sorry, drow.");
			break;
			}
			counter++;
			System.out.println(Arrays.toString(turn));
			System.out.println(Arrays.toString(turn));
		}
		System.out.println("Game over!");
	}
	void initTurns() {
		for (int i = 0; i < SIZE * SIZE; i++) {
			turn[i] = i + 1;
		}
		System.out.println(Arrays.toString(x));
		System.out.println(Arrays.toString(o));
	}

	void printField() {
		for (int i = 0; i < F_SIZE; i++) {
			for (int j = 0; j < F_SIZE; j++) {
				if (field[i][j] == DOT_O) {
					field[i-1][j] = '-';
					field[i+1][j] = '-';
					field[i][j-1] = '(';
					field[i][j+1] = ')';
					field[i][j] = ' ';
				}
				if (field[i][j] == DOT_X) {
					field[i-1][j-1] = 'X';
					field[i-1][j+1] = 'X';
					field[i+1][j-1] = 'X';
					field[i+1][j+1] = 'X';
					field[i][j] = 'X';
				}
				System.out.print(field[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	void humanTurn() {
		int ht = 0;
		do {
			try {
				System.out.print("\nPlease enter number (1 - 9) for your turn: ");
				ht = sc.nextInt();
			} catch (InputMismatchException ex) {
                    System.out.println("Input Mismatch Exception!");
                    sc.next();
			}
		}
		while (!isCellValid(ht));//do until finding right input
		x[counter] = ht;
		turn[ht - 1] = - 11;//-11 is equal 'X'
		System.out.println(Arrays.toString(turn));
		System.out.println();
		make_Cell(ht, DOT_X);
	}
	boolean isCellValid(int n) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			if (turn[i] == n) return true;
		}
		return false;
	}
	void make_Cell(int c, char ch) {
		switch (c) {
			case 1:
				field[2][2] = ch;
				break;
			case 2:
				field[2][6] = ch;
				break;
			case 3:
				field[2][10] = ch;
				break;
			case 4:
				field[6][2] = ch;
				break;
			case 5:
				field[6][6] = ch;
				break;
			case 6:
				field[6][10] = ch;
				break;
			case 7:
				field[10][2] = ch;
				break;
			case 8:
				field[10][6] = ch;
				break;
			case 9:
				field[10][10] = ch;
				break;
		}
	}
	void drawCell() {
		for (int i = 0; i < F_SIZE; i++) {
			for (int j = 0; j < F_SIZE; j++) {
				if (field[i][j] == DOT_O) {
					field[i-1][j] = '-';
					field[i+1][j] = '-';
					field[i][j-1] = '(';
					field[i][j+1] = ')';
					field[i][j] = ' ';
				}
				if (field[i][j] == DOT_X) {
					field[i-1][j-1] = 'X';
					field[i-1][j+1] = 'X';
					field[i+1][j-1] = 'X';
					field[i+1][j+1] = 'X';
					field[i][j] = 'X';
				}
			}
		}
	}
	void aiTurn(int counter) {
		int at = 0;
		int r = 0;
		if (turn[4] == 5) at = 5;
		else {
			for (int i = 0; i < 3 && x[i] != 0; i++) {
			System.out.println(Arrays.toString(x));
			if (x[i + 1] == 0) break;
				switch (x[i]) {
					case 1:
						switch (x[i+1]) {
							case 2:
							at = 3;
							break;
						}
					break;
				}
		
			}
			do {
				try {
					
					at = aiTurn[1][rand.nextInt(3)];
				} catch (InputMismatchException ex) {
						System.out.println("Input Mismatch Exception!");
				}
			}
			while (!isCellValid(at));//do until finding right input
		}

		turn[at - 1] = - 10;//-10 is equal 'O'
		o[counter] = at;
		System.out.println(Arrays.toString(turn));
		System.out.println(at);
		make_Cell(at, DOT_O);
		}
	boolean checkWin(int mean) {
		if(turn[0] ==  mean && turn[1] == mean && turn[2] == mean) return true;
		if(turn[0] ==  mean && turn[4] == mean && turn[8] == mean) return true;
		if(turn[0] ==  mean && turn[3] == mean && turn[5] == mean) return true;
		
		if(turn[6] ==  mean && turn[7] == mean && turn[8] == mean) return true;
		if(turn[2] ==  mean && turn[4] == mean && turn[6] == mean) return true;
		if(turn[2] ==  mean && turn[5] == mean && turn[8] == mean) return true;
		
		if(turn[3] ==  mean && turn[4] == mean && turn[5] == mean) return true;
		if(turn[1] ==  mean && turn[4] == mean && turn[7] == mean) return true;
		return false;
	}
	boolean isMapFull() {
		for (int i = 0; i < SIZE * SIZE; i++) {
			if (turn[i] >= 0) return false;
		}
		return true;
	}
}


