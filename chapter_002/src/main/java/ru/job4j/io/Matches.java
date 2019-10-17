package ru.job4j.io;

import java.util.Scanner;

public class Matches {

    public static void main(String[] args) {
        System.out.println("Игра \"Спички\". 2 игрока выбирают спички 1 - 3. Побеждает тот, кто вытянет последнюю.");
        int matches = 11;

        while (matches > 0) {
            matches = game(matches, "Игрок 1: ");
            if (matches <= 0) {
                break;
            }
            matches = game(matches, "Игрок 2: ");
        }
    }
    private static int game(int matches, String playerName) {
        Scanner input = new Scanner(System.in);
        System.out.print(playerName);
        int player = input.nextInt();
        while (player > 3) {
            System.out.println("Вы выбрали больше 3, пожалуйста повторите ход.");
            System.out.print(player);
            player = input.nextInt();
        }
        System.out.println("Спички: " + matches + " " + playerName + player + " Итог: " + (matches - player));
        return matches - player;
    }


}
