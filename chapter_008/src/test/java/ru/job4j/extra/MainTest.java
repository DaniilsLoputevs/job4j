package ru.job4j.extra;

import org.junit.Test;
import ru.job4j.extra.fieldaction.FieldAction;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainTest {
    /* game.start(params): */
    private List<Player> playerList = List.of(new PlayerStub("first"), new PlayerStub("second"));
    private Field field = new FieldStub(25);
    /* methods by this var - it's methods use inside class Game */
    private Game game = new GameStub();

    @Test
    public void imitateGameStartMethod() {
        var dice = new DiceStub();
        GameTurn gameTurn;
        Player winner = null;

        var run = true;
        game.setPlayers(playerList);
        while (run) {
            for (var player : playerList) {
                gameTurn = new GameTurnStub();
                gameTurn.acceptInfo("player: " + player.toString());

                var diceNum = dice.getNum();
                System.out.println("dice: " + diceNum);
                var newPosition = game.getPlayerPosition(player) + diceNum;

                var action = field.getActionByPosition(newPosition);
                if (action != null) {
                    gameTurn.acceptInfo(action.info());
                    newPosition = action.execute(game.getPlayerPosition(player));
                }

                gameTurn.acceptInfo("Your position: " + newPosition);
                game.setPlayerPosition(player, newPosition);

                System.out.println(gameTurn.getInfo());
                if (newPosition >= field.getCountOfField()) {
                    run = false;
                    winner = player;
                    break;
                }

            }
        }
        System.out.print("Winner: " + winner.toString());
    }

    /* ----------- Stub Classes ----------- */

    class PlayerStub implements Player {
        protected String name;

        public PlayerStub(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public String showName() {
            return this.name;
        }
    }

    class DiceStub implements Dice {
        @Override
        public int getNum() {
            return new Random().nextInt(6);
        }
    }

    class GameStub implements Game {
        protected HashMap<Player, Integer> playerPositionMap;

        @Override
        public void setPlayers(List<Player> playerList) {
            this.playerPositionMap = new HashMap<>(playerList.size());
            for (Player player : playerList) {
                this.playerPositionMap.put(player, 0);
            }
        }

        @Override
        public int getPlayerPosition(Player player) {
            return this.playerPositionMap.get(player);
        }

        @Override
        public void setPlayerPosition(Player player, int newPosition) {
            this.playerPositionMap.put(player, newPosition);
        }

        @Override
        public void start(List<Player> playerList, Field field) {
        }
    }

    class GameTurnStub implements GameTurn {
        String info = "";

        @Override
        public void acceptInfo(String string) {
            this.info += string + System.lineSeparator();
        }

        @Override
        public String getInfo() {
            return info;
        }
    }

    class FieldStub implements Field {
        protected HashMap<Integer, FieldAction> actionMap;

        public FieldStub(int size) {
            this.actionMap = new HashMap<>(size);
            for (int i = 0; i < size; i++) {
                this.actionMap.put(i, null);
            }
        }

        public FieldStub(HashMap<Integer, FieldAction> actionMap) {
            this.actionMap = actionMap;
        }

        @Override
        public FieldAction getActionByPosition(int position) {
            return this.actionMap.get(position);
        }

        @Override
        public int getCountOfField() {
            return this.actionMap.size();
        }
    }
}