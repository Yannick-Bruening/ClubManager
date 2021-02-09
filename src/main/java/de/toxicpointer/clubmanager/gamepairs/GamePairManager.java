package de.toxicpointer.clubmanager.gamepairs;

import de.toxicpointer.clubmanager.ClubDataManager;
import de.toxicpointer.clubmanager.club.Club;

import java.util.List;

public class GamePairManager {
  private final ClubDataManager clubDataManager;

  public GamePairManager(final ClubDataManager clubDataManager) {
    this.clubDataManager = clubDataManager;
  }

  public void generateGamePairs() {
    clubDataManager.clearGamePairs();
    for (final Club home : clubDataManager.getClubs()) {
      for (final Club guest : clubDataManager.getClubs()) {
        if (home != guest) {
          final GamePair gamePair = new GamePair(home, guest);
          clubDataManager.addGamePair(gamePair);
        }
      }
    }
    clubDataManager.saveGamePairs();
  }

  public List<GamePair> getGamePairs() {
    return clubDataManager.getGamePairs();
  }
}
