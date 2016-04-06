/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakes_and_ladders;

import java.util.*;
import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author Umad Rai
 */
public class Snakes_and_Ladders {

    public static void draw_board() {
        for (int i = 100; i > 0; i--) {
            if (i % 10 == 0 && i != 100) {
                System.out.print("\n");
            }
            System.out.print(i + "\t");
        }
    }

    public static int n_players;        //Number of players
    public static int t_roll;           //Total roll to be moved for one turn
    public static int t_snakes = 7;     //Total Number of snakes on board
    public static int t_ladders = 6;    //Total Number of ladders on board
    public static int avg, max, min;    //For ananlysis.
    public static int[] snake_head_arr = new int[t_snakes]; //Array for storing snake heads positions
    public static int[] ladder_bot_arr = new int[t_ladders];    //Array for storing ladder bottom positions
    public static int[] user_position;      //User's current position on board
    public static int dice_roll = 1;        //The value from rolling dice
    public static int temp = 0;             //For storing value of dice temporarily
    public static int max_roll;             //Used to check Winning position which is 100
    public static int[] stuck;              //Keeps track of players reached snake head and got stuck
    public static int rounds = 0;           //For analysis and counting total rounds
    public static int[] rounds_arr = new int[100];  //For analysis
    static Random rn = new Random();                //For generating random values.

    Snakes_and_Ladders() {
        //Assigning bot of ladder values
        ladder_bot_arr[0] = 3;  //Top at 51
        ladder_bot_arr[1] = 6;  //Top at 27
        ladder_bot_arr[2] = 20; //Top at 70
        ladder_bot_arr[3] = 36; //Top at 55
        ladder_bot_arr[4] = 63; //Top at 95
        ladder_bot_arr[5] = 68; //Top at 98
        //Assigning Snake heads values
        snake_head_arr[0] = 25; //Tail at 5
        snake_head_arr[1] = 34; //Tail at 1
        snake_head_arr[2] = 47; //Tail at 19
        snake_head_arr[3] = 65; //Tail at 52
        snake_head_arr[4] = 87; //Tail at 57
        snake_head_arr[5] = 91; //Tail at 61
        snake_head_arr[6] = 99; //Tail at 69
    }
    //Generating Random value for dice

    public static int dice() {
        int range = (6 - 1) + 1;
        int dice_value = rn.nextInt(range) + 1;
        return dice_value;
    }
    //Generating Random players between 2-4

    public static int players() {
        int range = (4 - 2) + 2;
        int n_players = rn.nextInt(range) + 2;
        return n_players;
    }

    //Function if any of the player gets 6 or finds ladder bot so player will roll dice again.
    public static int dice_again() {
        temp = 0;
        while (true) {

            if (dice_roll == 6) {
                temp = temp + dice_roll;
                dice_roll = dice();
            } else {
                dice_roll = dice_roll + temp;
                break;
            }
        }
        return dice_roll;
    }
    //For checking all the conditions and checking if some player finds ladder or snake head and keep track of it.

    public static int check(int pos, int roll, int snake_head_arr[], int ladder_bot_arr[], int player_num) {
        int newpos = 0;
        if (pos == ladder_bot_arr[0]) {
            System.out.println("Ladder Found " + pos);
            newpos = dice_again();
            pos = 51 + newpos;
        } else if (pos == ladder_bot_arr[1]) {
            System.out.println("Ladder Found " + pos);
            newpos = dice_again();
            pos = 27 + newpos;
        } else if (pos == ladder_bot_arr[2]) {
            System.out.println("Ladder Found " + pos);
            newpos = dice_again();
            pos = 70 + newpos;
        } else if (pos == ladder_bot_arr[3]) {
            System.out.println("Ladder Found " + pos);
            newpos = dice_again();
            pos = 55 + newpos;
        } else if (pos == ladder_bot_arr[4]) {
            System.out.println("Ladder Found " + pos);
            newpos = dice_again();
            pos = 95 + newpos;
        } else if (pos == ladder_bot_arr[5]) {
            System.out.println("Ladder Found " + pos);
            newpos = dice_again();
            pos = 98 + newpos;
        } else if (pos == snake_head_arr[0]) {
            System.out.println("Snake Head " + pos);
            pos = 5;
            stuck[player_num] = 1;
        } else if (pos == snake_head_arr[1]) {
            System.out.println("Snake Head " + pos);
            pos = 1;
            stuck[player_num] = 1;
        } else if (pos == snake_head_arr[2]) {
            System.out.println("Snake Head " + pos);
            pos = 19;
            stuck[player_num] = 1;
        } else if (pos == snake_head_arr[3]) {
            System.out.println("Snake Head " + pos);
            pos = 52;
            stuck[player_num] = 1;
        } else if (pos == snake_head_arr[4]) {
            System.out.println("Snake Head " + pos);
            pos = 57;
            stuck[player_num] = 1;
        } else if (pos == snake_head_arr[5]) {
            System.out.println("Snake Head " + pos);
            pos = 61;
            stuck[player_num] = 1;
        } else if (pos == snake_head_arr[6]) {
            System.out.println("Snake Head " + pos);
            pos = 69;
            stuck[player_num] = 1;
        }
        if (pos > 100) {
            pos = pos - roll;
        } else if (pos == 100) {
            System.out.println("Player " + (player_num + 1) + " Won");
        }
        return pos;
    }
    //Function in which game is being played as it calls other required functions.

    public static void Game() {
        draw_board();
        int sum = 0;
        n_players = players();
        user_position = new int[n_players];
        stuck = new int[n_players];
        for (int k = 0; k < 100; k++) {
            //Assigning initial positions to the players which is 1
            for (int i = 0; i < n_players; i++) {
                user_position[i] = 1;
            }
            rounds = 0;
            max_roll = 0;
            while (max_roll != 100) {
                rounds++;
                for (int i = 0; i < n_players; i++) {
                    System.out.println("Turn of user " + (i + 1));
                    dice_roll = dice();
                    //Checking if some player is stuck and its dice value is 6 or not.
                    if (stuck[i] == 1 && dice_roll != 6) {
                        System.out.println(dice_roll + " You cant move.You need 6 to move forward.");
                        continue;
                    }
                    stuck[i] = 0;
                    t_roll = dice_again();
                    user_position[i] += t_roll;
                    user_position[i] = check(user_position[i], t_roll, snake_head_arr, ladder_bot_arr, (i));
                    if (user_position[i] == 100) {

                        max_roll = 100;
                        break;
                    }
                }
            }
            rounds_arr[k] = rounds;
        }
        //Average, Minimum and Maximum 
        for (int i = 0; i < rounds_arr.length; i++) {
            sum = sum + rounds_arr[i];
        }
        avg = sum / rounds_arr.length;
        Arrays.sort(rounds_arr);
        min = rounds_arr[0];
        max = rounds_arr[rounds_arr.length - 1];
        System.out.println("Average " + avg + "\nMinimum " + min + "\nMaximum " + max);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Snakes_and_Ladders play_Game = new Snakes_and_Ladders();
        play_Game.Game();
    }
}
