package ru.job4j.exam;

import ru.job4j.exam.io.Output;
import ru.job4j.exam.players.BasePlayer;

import java.util.List;

/**
 * Game - Main Entity in Game.
 */
public class Game {
    private Field field;
    private List<BasePlayer> players;

    public Game(Field field, List<BasePlayer> players) {
        this.field = field;
        this.players = players;
    }

    /**
     * Main game loop.
     */
    public void start() {
        var gameState = -1;

        while (gameState == -1) {
            for (var player : players) {
                field.showField(player.getOutput());
                playerTurn(player, field);

                gameState = isItEnd(player.getSign());
                if (gameState != -1) {
                    finish(player.getName(), gameState);
                    break;
                }
            }
        }

    }

    /**
     * Return game state.
     * This method must use after player finish turn.
     * <p>
     * gameState value:
     * -1 >> Continue.
     * 0 >> Draw.
     * 1 >> Player win.
     *
     * @param sign - sign to check: is it win?
     * @return - game state. (-1, 0, 1)
     */
    private int isItEnd(char sign) {
        var gameState = -1;
        if (IsWin.isWin(field, sign)) {
            gameState = 1;
        } else if (field.getFreeCells() == 0) {
            gameState = 0;
        }
        return gameState;
    }

    /**
     * Show to all players tha it is the end of game.
     *
     * @param playerName - Player's name that made last turn.
     * @param gameState  - for understand is it Draw of Win?
     */
    private void finish(String playerName, int gameState) {
        if (gameState == 1) {
            players.forEach(player1 -> player1.getOutput().accept(
                    "Finish! Winner is:" + playerName + System.lineSeparator()));
        } else if (gameState == 0) {
            players.forEach(player1 -> player1.getOutput().accept(
                    "Finish! It is Draw!" + System.lineSeparator()));
        }
    }


    /**
     * Player make an actions:
     * - enter turn params from Player.
     * - change field.
     *
     * @param player - player.
     * @param field  - gameField.
     */
    private void playerTurn(BasePlayer player, Field field) {
        int[] turnParam = enterParamsFromPlayer(player);
        field.writeOnField(turnParam[0], turnParam[1], player.getSign());
    }

    /**
     * Enter turn params from Player in game logic through validate.
     *
     * @return - turn params through validate.
     */
    private int[] enterParamsFromPlayer(BasePlayer player) {
        int[] result = new int[2];
        var invalidParam = true;

        while (invalidParam) {
            result = player.makeMove();
            if (validateParams(result, player.getOutput())) {
                invalidParam = false;
            }
        }
        return result;
    }

    /**
     * Validate Player's turn params for:
     * Is it in a range of field?
     * Is this cell free?
     *
     * @param params       - Player's turn params for validate.
     * @param playerOutput - show to player that wrong with turn params.
     * @return - true -> if params went through validate.
     */
    private boolean validateParams(int[] params, Output playerOutput) {
        var rsl = false;
        var fieldLength = field.getField().length;
        do {
            if (params[0] >= fieldLength || params[1] >= fieldLength) {
                playerOutput.accept("Turn params is out of field range." + System.lineSeparator());
                break;
            }
            if (!field.isCellFree(params[0], params[1])) {
                playerOutput.accept("This cell isn't free." + System.lineSeparator());
                break;
            }
            rsl = true;
        } while (!rsl);
        return rsl;
    }


}