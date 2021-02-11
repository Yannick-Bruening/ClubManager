package de.toxicpointer.clubmanager.gamepairs;

import de.toxicpointer.clubmanager.ClubDataManager;
import de.toxicpointer.clubmanager.club.Club;

import java.util.List;

public class GamePairManager {
  private final ClubDataManager clubDataManager;

  public GamePairManager(final ClubDataManager clubDataManager) {
    this.clubDataManager = clubDataManager;
  }

  public void generateGamePairs(final boolean grouped) {
    if (!grouped) {
      generateGamePairs();
      return;
    }

    generateGroupedGamePairs();
  }

  private void generateGamePairs() {
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

  // This method has been proven to be faster
  private void generateGroupedGamePairs() {
    clubDataManager.clearGamePairs();
    for (int i = 0; i < clubDataManager.getClubs().size(); i++) {
      for (int j = i + 1; j < clubDataManager.getClubs().size(); j++) {
        final Club home = clubDataManager.getClubs().get(i);
        final Club guest = clubDataManager.getClubs().get(j);

        final GamePair gamePair = new GamePair(home, guest);
        final GamePair gamePair2 = new GamePair(guest, home);

        clubDataManager.addGamePair(gamePair);
        clubDataManager.addGamePair(gamePair2);
      }
    }
    clubDataManager.saveGamePairs();
  }

  public List<GamePair> getGamePairs() {
    return clubDataManager.getGamePairs();
  }
}
