import java.util.*;
public class Maze {
  private final Scanner keyboard;
  private final boolean[][] easy;
  private final boolean[][] medium;
  private final boolean[][] rage;
  private int[] currentPosition;
  private boolean[][] selectedMap;

  public static void main(String[] args) {
    Maze maze = new Maze();
    maze.start();
  }

  public Maze() {
    keyboard = new Scanner(System.in);
    easy = new boolean[][] {
      {true, false, true, true, true},
      {true, true, false, false, false},
      {true, false, true, true, true},
      {true, false, true, false, true},
      {true, true, true, false, true}
    };
    medium = new boolean[10][10];
    rage = new boolean[20][20];
  }

  private void reset() {
    currentPosition = new int[]{0, 0};
  }

  public void start() {
    reset();
    System.out.println("---------------------");
    System.out.println("1. No cheating and hacking");
    System.out.println("2. You can move N(North), W(west), E(East), S(South)");
    System.out.println("3. Watch out for a trap");
    System.out.println("4. O is your position, X is the point you cannot move to, D is the destination");
    System.out.println("---------------------");
    System.out.println("Choose a difficulty");
    System.out.println("1. Easy, 2. Medium, 3. Rage");
    int difficulty = keyboard.nextInt();
    while (difficulty != 1 && difficulty != 2 && difficulty != 3) {
      System.out.println("We don't support the difficulty");
      difficulty = keyboard.nextInt();
    }
    if (difficulty == 1) {
      selectedMap = easy;
    } else if(difficulty == 2) {
      selectedMap = medium;
    } else {
      selectedMap = rage;
    }
    while(currentPosition[0] != selectedMap.length - 1 && currentPosition[1] != selectedMap[0].length) {
      printMap();
      System.out.println("What is your move?");
      String move = keyboard.next();
      while (!isValidMove(move)) {
        System.out.println("What is your move?");
        move = keyboard.next();
      }
    }
  }

  private void printMap() {
    int x = currentPosition[0];
    int y = currentPosition[1];
    for (int i = 0; i < selectedMap.length; i++) {
      for (int j = 0; j < selectedMap[0].length; j++) {
        if (x == i && y == j) {
          System.out.print(" O ");
        } else {
          if (selectedMap[i][j] == false) {
            System.out.print(" X ");
          } else {
            System.out.print ("   ");
          }
        }
      }
      if (i == selectedMap.length - 1) {
        System.out.println("D");
      }
      System.out.println();
    }
  }

  private boolean isValidMove(String move) {
    if (!move.equals("N")
      && !move.equals("W")
      && !move.equals("S")
      && !move.equals("E")) {
      return false;
    }
    int x = currentPosition[0];
    int y = currentPosition[1];
    if (move.equals("N")) {
      x = x - 1;
    } else if(move.equals("S")) {
      x = x + 1;
    } else if(move.equals("W")) {
      y = y - 1;
    } else {
      y = y + 1;
    }
    if (!selectedMap[x][y]) {
      return false;
    }
    if (x < 0 || x >= selectedMap.length) {
      return false;
    }
    if (y >= selectedMap[0].length) {
      return x == selectedMap.length - 1;
    }
    currentPosition = new int[]{x, y};
    return true;
  }
}
