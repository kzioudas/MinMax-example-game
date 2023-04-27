//+Konstantinos Zioudas..3225 
//+Georgios Giatsos......3202 
//+Dimitrios Vampiris....3186

import java.util.InputMismatchException;
import java.util.Scanner;

public class minmaxex { // ------------------
						// empty -> 0
						// black -> 1
						// playerA ->5
						// playerB ->7
						// static logo error
	public static int p_Max = 1;
	public static int p_Min = -1;
	public static int M;
	public static int N;
	public static int playerA = 5;
	public static int playerB = 7;

	class MinMax {
		public MinMax[] moves;
		public MinMax next; // only for the last move
		public int[][] table; // table of every state
		public int[] a_pos = { 0, 0 }; // x,y position of a
		public int[] b_pos = { 0, 0 };// x,y position of b

		public MinMax() {
			moves = new MinMax[16];// max 16 kiniseis eos 2 thesis se kathe kate
		}

		public MinMax(int M, int N) {
			moves = new MinMax[16];// max 16 kiniseis eos 2 thesis se kathe kate
			table = new int[M][N];
		}
	}

	public static void move1step(MinMax tree, int position, int ax, int ay, int bx, int by) {
		// System.out.printf("<============move1step============>\n");
		// System.out.printf("position= "+position+" ax="+ax+" ay="+ay+" bx="+bx+"
		// by="+by+"\n");
		int x_a = tree.a_pos[0];
		int y_a = tree.a_pos[1];
		int x_b = tree.b_pos[0];
		int y_b = tree.b_pos[1];
		minmaxex minmaxex = new minmaxex();
		tree.moves[position] = minmaxex.new MinMax(M, N);
		for (int i = 0; i < M; i++) {// antigrafi pinaka{
			for (int j = 0; j < N; j++) {
				tree.moves[position].table[i][j] = tree.table[i][j];
			}
		}
		if ((x_a + ax) >= M || (y_a + ay) >= N || (y_a + ay) < 0 || (x_a + ax) < 0) {// elegxos ksana giati pernouse gia
																						// kapoion logo apo ton
																						// proigoume Bug Fixed!{
			return;
		}
		tree.moves[position].table[x_a + ax][y_a + ay] = playerA;
		tree.moves[position].table[x_a][y_a] = 1;
		tree.moves[position].a_pos[0] = x_a + ax;
		tree.moves[position].a_pos[1] = y_a + ay;
		tree.moves[position].b_pos[0] = x_b + bx;
		tree.moves[position].b_pos[1] = y_b + by;

		// System.out.printf("<============move1step============>\n");
		// System.out.printf("x_a+ax="+(x_a+ax)+"y_a+ay="+(y_a+ay)+"\n");
		// System.out.printf("pos_a="+tree.moves[position].a_pos[0]+"
		// pos_b="+tree.moves[position].a_pos[1]+"\n");

	}

	public static void move2steps(MinMax tree, int position, int ax, int ay, int bx, int by) {
		int x_a = tree.a_pos[0];
		int y_a = tree.a_pos[1];
		int x_b = tree.b_pos[0];
		int y_b = tree.b_pos[1];
		minmaxex minmaxex = new minmaxex();
		tree.moves[position] = minmaxex.new MinMax(M, N);
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				tree.moves[position].table[i][j] = tree.table[i][j];
			}
		}
		if ((x_a + ax) >= M || (y_a + ay) >= N || (y_a + ay) < 0 || (x_a + ax) < 0) {// elegxos ksana giati pernouse gia
																						// kapoion logo apo ton
																						// proigoume Bug Fixed!{
			return;
		}
		tree.moves[position].table[x_a + ax][y_a + ay] = playerA;
		tree.moves[position].table[x_a][y_a] = 1;
		tree.moves[position].table[x_a + (ax / 2)][y_a + (ay / 2)] = 1;

		tree.moves[position].a_pos[0] = x_a + ax;
		tree.moves[position].a_pos[1] = y_a + ay;
		tree.moves[position].b_pos[0] = x_b + bx;
		tree.moves[position].b_pos[1] = y_b + by;

		// System.out.printf("<============move2step============>\n");
		// System.out.printf("x_a+ax="+(x_a+ax)+"y_a+ay="+(y_a+ay)+"\n");

	}

	static boolean game_state_check(int[][] arr, int[] pos) {
		int x = pos[0];
		int y = pos[1];
		if (x + 1 < M) {
			if (arr[x + 1][y] == 0) {
				return true; // game continue
			} else if (y + 1 < N && arr[x + 1][y + 1] == 0) {
				return true; // game continue
			} else if (y - 1 >= 0 && arr[x + 1][y - 1] == 0) {
				return true; // game continue
			}

		}
		if (x - 1 >= 0) {
			if (arr[x - 1][y] == 0) {
				return true; // game continue
			} else if (y - 1 >= 0 && arr[x - 1][y - 1] == 0) {
				return true; // game continue
			} else if (y + 1 < N && arr[x - 1][y + 1] == 0) {
				return true; // game continue
			}

		}
		if (y + 1 < N && arr[x][y + 1] == 0) // an mporw na paw 1 thesi dexia
		{
			return true;
		}
		if (y - 1 >= 0 && arr[x][y - 1] == 0) // an mporw na paw 1 thesi aristera
		{
			return true;
		} else if (arr == null)// mallon lathos
		{
			return false; // 100 = end game
		} else {
			return false;
		}
	}

	public static void print_table(int[][] A) // Emfanizei stin othoni ton pinaka paihnidiou
	{
		int i;
		int j;

		System.out.println("<==========|GAME BOARD|==========>");
		for (int k = 0; k < (N); k++) {
			System.out.printf("  " + k + " ");
		}

		System.out.printf(" \n");
		for (int k = 0; k < (N); k++) {
			System.out.printf("+---");
		}
		System.out.printf("+");
		System.out.printf("\n");
		for (i = 0; i < M; i++) {
			for (j = 0; j < N; j++) {
				System.out.printf("| ");
				if (A[i][j] == 0) {
					System.out.printf("  ");

				} else if (A[i][j] == 1) {
					System.out.printf("# ");

				} else if (A[i][j] == 5)// pioni a =5
				{
					System.out.printf("A ");

				} else {
					System.out.printf("B ");

				}

			}
			System.out.printf("|");
			System.out.print(i);
			System.out.printf("\n");
			for (int k = 0; k < (N); k++) {
				System.out.printf("+---");
			}
			System.out.printf("+");
			System.out.printf("\n");
		}

	}

	public static int expand_tree(MinMax tree, int turn, int nextTurn, int[] test, MinMax[] p_moves) {
		int x;
		int y;

		if (turn == p_Max) {
			x = tree.a_pos[0];
			y = tree.a_pos[1];
		} else {
			x = tree.b_pos[0];
			y = tree.b_pos[1];
		}
		int[][] board = tree.table;
		int position = 0;
		// <========================= Gia Metakinisi mias Thesis
		// ====================================>
		if (x + 1 < M - 1 && board[x + 1][y] == 0)// mia thesi kato
		{
			move1step(tree, position, 1, 0, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}
		if (x - 1 > 0 && board[x - 1][y] == 0)// mia thesi pano
		{
			move1step(tree, position, -1, 0, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}
		if (y + 1 < N - 1 && board[x][y + 1] == 0)// mia thesi dexia
		{
			move1step(tree, position, 0, 1, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		} else if (y - 1 > 0 && board[x][y - 1] == 0) {
			move1step(tree, position, 0, -1, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		} else if (x + 1 < M - 1 && y + 1 < N - 1 && board[x + 1][y + 1] == 0) {
			move1step(tree, position, 1, 1, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		} else if (x + 1 < M - 1 && y - 1 > 0 && board[x + 1][y - 1] == 0) {
			move1step(tree, position, 1, -1, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}
		// panw-dexia 1 thesi
		else if (x - 1 > 0 && y + 1 < N - 1 && board[x - 1][y + 1] == 0) {
			move1step(tree, position, -1, 1, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}
		// panw-aristera 1 thesi
		else if (x - 1 > 0 && y - 1 > 0 && board[x - 1][y - 1] == 0) {
			move1step(tree, position, -1, -1, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}
		// <========================= Gia Metakinisi 2 Theseon
		// ====================================>
		else if (x + 2 < M - 1 && board[x + 1][y] == 0 && board[x + 2][y] == 0) {
			move2steps(tree, position, 2, 0, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}

		// panw 2 theseis
		else if (x - 2 > 0 && board[x - 1][y] == 0 && board[x - 2][y] == 0) {
			move2steps(tree, position, -2, 0, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}

		// dexia 2 theseis
		else if (y + 2 < N - 1 && board[x][y + 1] == 0 && board[x][y + 2] == 0) {
			move2steps(tree, position, 0, 2, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}

		// aristera 2 theseis
		else if (y - 2 > 0 && board[x][y - 1] == 0 && board[x][y - 2] == 0) {
			move2steps(tree, position, 0, -2, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}

		// katw-dexia 2 theseis
		else if (x + 2 < M - 1 && y + 2 < N - 1 && board[x + 1][y + 1] == 0 && board[x + 2][y + 2] == 0) {
			move2steps(tree, position, 2, 2, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}

		// katw-aristera 2 theseis
		else if (x + 2 < M - 1 && y - 2 > 0 && board[x + 1][y - 1] == 0 && board[x + 2][y - 2] == 0) {
			move2steps(tree, position, 2, -2, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}

		// panw-dexia 2 theseis
		else if (x - 2 > 0 && y + 2 < N - 1 && board[x - 1][y + 1] == 0 && board[x - 2][y + 2] == 0) {
			move2steps(tree, position, -2, 2, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}

		// panw-aristera 2 theseis
		else if (x - 2 > 0 && y - 2 > 0 && board[x - 1][y - 1] == 0 && board[x - 2][y - 2] == 0) {
			move2steps(tree, position, -2, -2, 0, 0);
			test[position] = minmax_tree(tree.moves[position], nextTurn);
			p_moves[position] = tree.moves[position];
			position++;
		}
		return position;
	}

	public static int minmax_tree(MinMax tree, int turn) // dokimazei kiniseis
	{ // THELEI ALLAGKES
		int nextTurn = p_Max;
		int position = 0;
		int[] test = new int[16];
		MinMax[] p_moves = new MinMax[16];
		int best_val;
		MinMax best;
		int i;

		if (turn == p_Max) {
			if (!game_state_check(tree.table, tree.a_pos)) {
				return (p_Min); // telos paixnidiot eno paizei o max pou simainei oti egklovistike ara kerdise o
								// min
			} else {
				nextTurn = p_Min;
			}
		} else if (turn == p_Min) {

			if (!game_state_check(tree.table, tree.b_pos)) {
				return (p_Max); // telos paixnidiot eno paizei o min pou simainei oti egklovistike ara kerdise o
								// max
			} else {
				nextTurn = p_Max;
			}
		}

		position = expand_tree(tree, turn, nextTurn, test, p_moves); // dimiourgia paidion

		if (turn == p_Max) // An einai sto epipedo MAX
		{
			best_val = test[0];
			best = p_moves[0];
			for (i = 0; i < position; i++) {
				if (test[i] > best_val) // krataw ti MAX timi twn paidiwn
				{
					best_val = test[i];
					best = p_moves[i];
				}
			}
		} else // An einai sto epipedo MIN
		{

			best_val = test[0];
			best = p_moves[0];
			for (i = 1; i < position; i++) {
				if (test[i] < best_val) // krataw ti MIN timi twn paidiwn
				{
					best_val = test[i];
					best = p_moves[i];
				}
			}
		}

		if (best != null) {
			tree.next = best; // krataei tin kaliteri kinisi
		} else {
			tree.next = p_moves[0];
		}
		return best_val;
	}

	public static void main(String[] args) {
		minmaxex minmaxex = new minmaxex();
		// MinMax state = minmaxex.new MinMax();
		int player_Turn = p_Max;
		int dx;
		int dy;
		int[] pos_a = new int[2];
		int[] pos_b = new int[2];
		int[] pos_black = new int[2];
		System.out.println("<========|OUR INFO|========>");
		System.out.println("+Konstantinos Zioudas..3225 ");
		System.out.println("+Georgios Giatsos......3202 ");
		System.out.println("+Dimitrios Vampiris....3186 ");
		System.out.println("<======|INSTRUCTIONS|======>");
		System.out.println(
				"When asked for moves or cord give X first and press ENTER key and then give Y and press ENTER key\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("<========|Give M size|========>");
		M = scan.nextInt();
		System.out.println("<========|Give N size|========>");
		N = scan.nextInt();
		MinMax state = minmaxex.new MinMax(M, N); // state einai i ARXIKI katastasi
		// ftiaxno ton pinaka tou paixnidiou kai ton gemizo me 0
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				state.table[i][j] = 0;
			}
		}
		print_table(state.table);
		System.out.println("<=========|GIVE A CORDS (x,y)|=========>"); // start pos a
		pos_a[0] = scan.nextInt();
		pos_a[1] = scan.nextInt();
		state.table[pos_a[0]][pos_a[1]] = 5;
		state.a_pos = pos_a;
		System.out.println("<=========|GIVE B CORDS (x,y)|=========>"); // start pos b
		pos_b[0] = scan.nextInt();
		pos_b[1] = scan.nextInt();
		state.table[pos_b[0]][pos_b[1]] = 7;
		state.b_pos = pos_b;

		// ALLAGI SIGOURA
		while (true) // Na dwsw kapoia maura tetragona arxika [an de thelw pataw -1 kai -1)
		{
			System.out.println("<=========|GIVE BLACK CORDS (x,y)|=========");
			System.out.println("<=|IF YOU DONT WANT TO JUST GIVE LETTER A|=>");
			if (scan.hasNextInt()) {
				pos_black[0] = scan.nextInt(); // x sintetagmeni (apo 0 ews M-1)
				pos_black[1] = scan.nextInt(); // y sintetagmeni (apo 0 ews N-1)
				state.table[pos_black[0]][pos_black[1]] = 1;// black = 1
			} else {
				scan.next();
				break;
			}

		}

		System.out.println("<============|START|============>");
		print_table(state.table);

		while (true) // Epanaliptika PAIZETAI to paihnidi
		{
			// crashed with null state error BUG FIXED!
			if (state == null && player_Turn == p_Max
					|| player_Turn == p_Max && !game_state_check(state.table, state.a_pos)) // an einai i seira tou MAX
																							// kai to paihnidi teleiose
			{
				System.out.println("<==========|PLAYER MIN WON|==========>"); // kerdise o MIN

				// print_table(state.table);
				break;
			} else if (state == null && player_Turn == p_Min
					|| player_Turn == p_Min && !game_state_check(state.table, state.b_pos)) // an einai i seira tou MIN
																							// kai to paihnidi teleiose
			{
				System.out.println("<==========|PLAYER MAX WON|==========>"); // kerdise o MAX

				// print_table(state.table);
				break;
			}

			if (player_Turn == p_Max) {
				System.out.println("<==========|MAX's TURN|==========>");
				minmax_tree(state, p_Max); // dentro minmax peiramaton
				System.out.println("<==========|MAX  MOVED|==========>");
				print_table(state.table);
				state = state.next; // to next state einai to best state

				player_Turn = p_Min;
			} else {
				int[] posprint;
				System.out.println("<==========|YOUR TURN|==========>");
				System.out.println("<=========|VALID MOVES|=========>");
				for (int i = -2; i <= 2; i++) {
					for (int j = -2; j <= 2; j++) {
						if (i == 1 || i == -1) {
							if (j == 1 || j == -1)// periptoseis(1,1)(1,-1)(-1,1)(-1,-1)
							{
								if (state.b_pos[0] + i > 0 && state.b_pos[1] + j > 0 && state.b_pos[0] + i < M
										&& state.b_pos[1] + j < N) {
									if (state.table[state.b_pos[0] + i][state.b_pos[1] + j] == 0) {
										System.out.println("(" + i + "," + j + ")");
									}

								}
							} else if (j == 2 || j == -2)// periptoseis(1,2)(1,-2)(-1,2)(-1,-2)
							{
								continue;
							} else// periptoseis(1,0)(-1,0)
							{
								if (state.b_pos[0] + i > 0 && state.b_pos[1] + j > 0 && state.b_pos[0] + i < M
										&& state.b_pos[1] + j < N) {
									if (state.table[state.b_pos[0] + i][state.b_pos[1] + j] == 0) {
										System.out.println("(" + i + "," + j + ")");
									}

								}
							}

						} else if (i == 2 || i == -2) {
							if (j == 1 || j == -1)// periptoseis(2,1)(2,-1)(-2,1)(-2,-1)
							{
								continue;
							} else if (j == 2 || j == -2)// periptoseis(2,2)(2,-2)(-2,2)(-2,-2)
							{
								if (state.b_pos[0] + i > 0 && state.b_pos[1] + j > 0 && state.b_pos[0] + i < M
										&& state.b_pos[1] + j < N) {
									if (state.table[state.b_pos[0] + i][state.b_pos[1] + j] == 0
											&& state.table[state.b_pos[0] + i / 2][state.b_pos[1] + j / 2] == 0) {
										System.out.println("(" + i + "," + j + ")");
									}

								}
							} else// periptoseis(2,0)(-2,0)
							{
								if (state.b_pos[0] + i > 0 && state.b_pos[1] + j > 0 && state.b_pos[0] + i < M
										&& state.b_pos[1] + j < N) {
									if (state.table[state.b_pos[0] + i][state.b_pos[1] + j] == 0
											&& state.table[state.b_pos[0] + i / 2][state.b_pos[1] + j] == 0) {
										System.out.println("(" + i + "," + j + ")");
									}

								}
							}
						} else {
							if (j == 1 || j == -1)// periptoseis(0,1)(0,-1)
							{
								if (state.b_pos[0] + i > 0 && state.b_pos[1] + j > 0 && state.b_pos[0] + i < M
										&& state.b_pos[1] + j < N) {
									if (state.table[state.b_pos[0] + i][state.b_pos[1] + j] == 0
											&& state.table[state.b_pos[0] + i][state.b_pos[1] + j] == 0) {
										System.out.printf("(" + i + "," + j + ")\n");
									}

								}
							} else if (j == 2 || j == -2)// periptoseis(0,2)(0,-2)
							{
								if (state.b_pos[0] + i > 0 && state.b_pos[1] + j > 0 && state.b_pos[0] + i < M
										&& state.b_pos[1] + j < N) {
									if (state.table[state.b_pos[0] + i][state.b_pos[1] + j] == 0
											&& state.table[state.b_pos[0] + i][state.b_pos[1] + j / 2] == 0) {
										System.out.println("(" + i + "," + j + ")");
									}

								}
							} else// periptoseis(0,0)
							{
								if (state.b_pos[0] + i > 0 && state.b_pos[1] + j > 0 && state.b_pos[0] + i < M
										&& state.b_pos[1] + j < N) {
									if (state.table[state.b_pos[0] + i][state.b_pos[1] + j] == 0
											&& state.table[state.b_pos[0] + i][state.b_pos[1] + j / 2] == 0) {
										System.out.println("(" + i + "," + j + ")");
									}

								}
							}
						}

					}
				}
				System.out.println("<==========|MAKE YOUR MOVE|==========>");

				dx = scan.nextInt(); // kinisi ston aksona x
				dy = scan.nextInt(); // kinisi ston aksona y

				state.table[state.b_pos[0] + dx][state.b_pos[1] + dy] = 7; // pioni tou xristi
				state.table[state.b_pos[0]][state.b_pos[1]] = 1; // mayro sti thesi poy perase

				// mavrisma(#) tetragonou anamesa
				if (dx == 2 && dy == 0) {
					state.table[state.b_pos[0] + 1][state.b_pos[1]] = 1;
				} else if (dx == 0 && dy == 2) {
					state.table[state.b_pos[0]][state.b_pos[1] + 1] = 1;
				} else if (dx == -2 && dy == 0) {
					state.table[state.b_pos[0] - 1][state.b_pos[1]] = 1;
				} else if (dx == 0 && dy == -2) {
					state.table[state.b_pos[0]][state.b_pos[1] - 1] = 1;
				} else if (dx == 2 && dy == 2) {
					state.table[state.b_pos[0] + 1][state.b_pos[1] + 1] = 1;
				} else if (dx == 2 && dy == -2) {
					state.table[state.b_pos[0] + 1][state.b_pos[1] - 1] = 1;
				} else if (dx == -2 && dy == 2) {
					state.table[state.b_pos[0] - 1][state.b_pos[1] + 1] = 1;
				} else if (dx == -2 && dy == -2) {
					state.table[state.b_pos[0] - 1][state.b_pos[1] - 1] = 1;
				}
				state.b_pos[0] = state.b_pos[0] + dx; // nea thesi x tou B
				state.b_pos[1] = state.b_pos[1] + dy;

				// print_table(state.table);
				player_Turn = p_Max;
			}
		}
	}
}
