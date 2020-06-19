package ru.job4j.exam2;

import ru.job4j.exam2.io.Input;

import java.util.List;

public class Game {

    public void run(Field field, List<Input> inputList) {
        var gameState = GameState.CONTINUE;
        GameTurn gameTurn = null;
        int turnNum = 0;

        while (gameState == GameState.CONTINUE) {
            bigLoop: for (Input input : inputList) {
                while (input.hasNextPlayer()) {
//                    /* - debug - */
//                    System.out.println(turnNum++);
//                    /* - debug - */
                    input.setNextPlayer();

                    input.showMessageToPlayer(turnNum);
                    input.acceptField(field);
                    gameTurn = input.nextPlayerTurnParam();

                    var validate = new ValidateGameParam();
                    while (!validate.checkParams(gameTurn.turnParams, field)) {
                        gameTurn = input.askPlayerEnterParamAgain(validate.waringMessage);
                    }

                    field.writeOnField(gameTurn.turnParams, gameTurn.playerSign);
                    gameState = field.isWin(gameTurn.playerSign);
                    if (gameState != GameState.CONTINUE) {
                        input.acceptField(field);
                        break bigLoop;
                    }
                }
                input.newPlayerRound();
            }
        }
        finish(gameTurn.playerName, gameState, inputList);
    }

    /**
     * Show to all players that it is the end of game.
     * * Here {@param gameState} - can be: WIN || DRAW.
     *
     * @param playerName Player's name that made last turn.
     * @param gameState  for understand is it Draw of Win?
     */
    private void finish(String playerName, GameState gameState, List<Input> inputList) {
        String temp = gameState == GameState.WIN
                ? "Finish! The Winner is: " + playerName + System.lineSeparator()
                : "Finish! It is a Draw!" + System.lineSeparator();
        for (var input : inputList) {
            input.showMessageForEachPlayer(temp);
        }
    }
}
