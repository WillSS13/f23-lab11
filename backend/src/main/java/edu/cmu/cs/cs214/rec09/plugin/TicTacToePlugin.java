package edu.cmu.cs.cs214.rec09.plugin;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;

public class TicTacToePlugin implements GamePlugin<String> {
  private static final int SIZE = 3;

  private static final String GAME_NAME = "Tic Tac Toe";
  private static final String SELECT_FIRST_MSG = "Select a square.";
  private static final String GAME_OVER_MSG = "Player %s won!";
  private static final String GAME_TIED_MSG = "The game ended in a tie.";

  private GameFramework framework;
  private TicTacToe game;
  private String currentPlayer;
  private String lastPlayer;

  @Override
  public String getGameName() {
    return GAME_NAME;
  }

  @Override
  public int getGridWidth() {
    return SIZE;
  }

  @Override
  public int getGridHeight() {
    return SIZE;
  }

  @Override
  public void onRegister(GameFramework f) {
    framework = f;
  }

  @Override
  public void onNewGame() {
    game = new TicTacToe();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        framework.setSquare(i, j, "");
      }
    }
    framework.setFooterText(SELECT_FIRST_MSG);
    currentPlayer = "X";
  }

  @Override
  public void onNewMove() {
  }

  @Override
  public boolean isMoveValid(int x, int y) {
    return game.isValidPlay(x, y);
  }

  @Override
  public boolean isMoveOver() {
    return !currentPlayer.equals(lastPlayer);
  }

  @Override
  public void onMovePlayed(int x, int y) {
    game.play(x, y);
    framework.setSquare(x, y, currentPlayer);
    lastPlayer = currentPlayer;
    currentPlayer = game.currentPlayer().toString();
  }

  @Override
  public boolean isGameOver() {
    return game.isOver();
  }

  @Override
  public String getGameOverMessage() {
    if (game.winner() == null) {
      return GAME_TIED_MSG;
    } else {
      return String.format(GAME_OVER_MSG, game.winner());
    }
  }

  @Override
  public void onGameClosed() {
  }

  @Override
  public String currentPlayer() {
    return game.currentPlayer().toString();
  }
  
}
