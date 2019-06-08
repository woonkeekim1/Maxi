/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maxie_project;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class TicTakToe {
    private char[][] board;
    private char turn;
    private int totalMove;
    private static final Scanner keyboard = new Scanner(System.in);
    public TicTakToe() {
        reset();
    }
    
    private void reset() {
        board = new char[3][3];
        turn = 'X';
        totalMove = 0;
    }
    
    private void display() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
    
    public void playGame() {
        int x = 0;
        int y = 0;
        while (!isGameOver(x, y) && totalMove < 9) {
            changeTurn();
            System.out.println("Turn: " + turn);
            System.out.println("Enter your move. (example: 1 1)");
            String[] move = keyboard.nextLine().split(" ");
            x = Integer.parseInt(move[0]);
            y = Integer.parseInt(move[1]);
            while (!isValidMove(x, y)) {
                System.out.println("Invalid move, try again");
                move = keyboard.nextLine().split(" ");
                x = Integer.parseInt(move[0]);
                y = Integer.parseInt(move[1]);
            }
            board[x][y] = turn;
            display();
            totalMove++;
        }
        if (isGameOver(x, y)) {
            System.out.println(turn + " win the battle, Congratulation!");
        } else {
            System.out.println("Draw. :(");
        }
    }
    
    private boolean isGameOver(int x, int y) {
        if (totalMove == 0) {
            return false;
        }
        char c = board[x][y];
        boolean horizontal = true;
        boolean vertical = true;
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        for (int i = 0; i < board.length; i++) {
            horizontal = horizontal && (board[x][i] == c);
            vertical = vertical && (board[i][y] == c);
            if (x + y == board.length - 1) {
                diagonal1 = diagonal1 && (board[i][i] == c);
                diagonal2 = diagonal2 && (board[i][board.length-i -1] == c);
            } else {
                diagonal1 = false;
                diagonal2 = false;
            }
        }
        
        return horizontal || vertical || diagonal1 || diagonal2;
    }
    
    private void changeTurn() {
        if (turn == 'O') {
            turn = 'X';
        } else {
            turn = 'O';
        }
    }
    
    private boolean outOfBound(int x, int y) {
        return x < 0 || y < 0 || x >= board.length || y >= board[0].length;
    }
    
    private boolean isValidMove(int x, int y) {
        return !outOfBound(x, y) && board[x][y] == '\0';
    }
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Quit: press q\nPlay: press any key");
        String nextChar = keyboard.next();
        while (!nextChar.equals("q") && !nextChar.equals("Q") && !nextChar.equals("done")) {
            TicTakToe game = new TicTakToe();
            game.playGame();
            System.out.println("Quit: press q\nPlay: press any key");
            nextChar = keyboard.next();
        }
    }
}
