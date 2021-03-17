package de.toxicpointer.clubmanager;

import de.toxicpointer.clubmanager.club.Club;
import de.toxicpointer.clubmanager.gamepairs.GamePair;
import de.toxicpointer.clubmanager.utils.serialization.TypedDeserializer;
import de.toxicpointer.clubmanager.utils.serialization.TypedSerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClubDataManager {
  private final File clubFile;
  private final File gamePairFile;
  private final List<Club> clubs = new ArrayList<>();
  private final List<GamePair> gamePairs = new ArrayList<>();

  public ClubDataManager(final File clubFile, final File gamePairFile) {
    this.clubFile = clubFile;
    this.gamePairFile = gamePairFile;

    init();
  }

  /*
   * General methods
   */

  private void init() {
    if (clubFile.exists()) {
      final TypedDeserializer<Club> clubTypedDeserializer = new TypedDeserializer<>();
      clubs.addAll(clubTypedDeserializer.deserializeMany(clubFile));
    }

    if (gamePairFile.exists()) {
      final TypedDeserializer<GamePair> gamePairTypedDeserializer = new TypedDeserializer<>();
      gamePairs.addAll(gamePairTypedDeserializer.deserializeMany(gamePairFile));
    }
  }

  public void forceReload() {
    clubs.clear();
    gamePairs.clear();

    init();
  }

  /*
   * Club methods
   */

  public File getClubFile() {
    return clubFile;
  }

  public List<Club> getClubs() {
    return clubs;
  }

  public List<Club> getSortedClubs() {
    final List<Club> sorted = new ArrayList<>(clubs);
    // Abra kadabra Sort ordinata *Woosh*
    return sorted.stream()
        .sorted(Comparator
            .comparingInt(Club::getPoints)
            .thenComparingInt(Club::getGoalDiff)
            .reversed())
        .collect(Collectors.toList());
    // Now the returned list is sorted
  }

  public void saveClubs() {
    final TypedSerializer<Club> clubTypedSerializer = new TypedSerializer<>();
    clubTypedSerializer.serializeMany(clubs, clubFile, false);
  }

  public void addClub(final Club club) {
    addClub(club, true);
  }

  public void addClub(final Club club, final boolean save) {
    clubs.add(club);

    if (save) {
      club.save(clubFile);
    }
  }

  public void removeClub(final Club club) {
    removeClub(club, true);
  }

  public void removeClub(final Club club, final boolean save) {
    clubs.remove(club);

    if (save) {
      saveClubs();
    }
  }

  public Club findClub(final UUID uuid) {
    return getClubs().stream()
        .filter(club -> club.getClubUuid().equals(uuid))
        .limit(1)
        .findFirst()
        .orElse(null);
  }

  public Club findClub(final String name) {
    return getClubs().stream()
        .filter(club -> club.getClubName().equalsIgnoreCase(name))
        .limit(1)
        .findFirst()
        .orElse(null);
  }

  public void clearClubs() {
    clubs.clear();
  }

  /*
   * Gamepair methods
   */

  public File getGamePairFile() {
    return gamePairFile;
  }

  public List<GamePair> getGamePairs() {
    return gamePairs;
  }

  public void saveGamePairs() {
    final TypedSerializer<GamePair> gamePairTypedSerializer = new TypedSerializer<>();
    gamePairTypedSerializer.serializeMany(gamePairs, gamePairFile, false);
  }

  public void addGamePair(final GamePair gamePair) {
    addGamePair(gamePair, true);
  }

  public void addGamePair(final GamePair gamePair, final boolean save) {
    gamePairs.add(gamePair);

    if (save) {
      gamePair.save(gamePairFile);
    }
  }

  public void removeGamePair(final GamePair gamePair) {
    removeGamePair(gamePair, true);
  }

  public void removeGamePair(final GamePair gamePair, final boolean save) {
    gamePairs.remove(gamePair);

    if (save) {
      saveGamePairs();
    }
  }

  public GamePair findGamePair(final Club home, final Club guest) {
    return getGamePairs().stream()
        .filter(gamePair -> gamePair.getHomeClub(this) == home
            && gamePair.getGuestClub(this) == guest)
        .limit(1)
        .findFirst()
        .orElse(null);
  }

  public void clearGamePairs() {
    gamePairs.clear();
  }
}
