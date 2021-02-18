package de.toxicpointer.clubmanager.gamepairs;

import de.toxicpointer.clubmanager.ClubDataManager;
import de.toxicpointer.clubmanager.club.Club;
import de.toxicpointer.clubmanager.utils.serialization.TypedSerializer;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class GamePair implements Serializable {
  public static final String EOF = ".tpgpair";
  @Serial
  private static final long serialVersionUID = 6775952640135254098L;
  private final UUID homeClub;
  private final UUID guestClub;
  private int homeGoals;
  private int guestGoals;

  public GamePair(final Club homeClub, final Club guestClub) {
    this(homeClub, guestClub, 0, 0);
  }

  public GamePair(final Club homeClub, final Club guestClub, final int homeGoals, final int guestGoals) {
    this.homeClub = homeClub.getClubUuid();
    this.guestClub = guestClub.getClubUuid();
    this.homeGoals = homeGoals;
    this.guestGoals = guestGoals;
  }

  public UUID getHomeClubUuid() {
    return homeClub;
  }

  public Club getHomeClub(final ClubDataManager clubDataManager) {
    return clubDataManager.getClubs().stream()
        .filter(club -> club.getClubUuid().equals(getHomeClubUuid()))
        .limit(1)
        .findFirst()
        .orElse(null);
  }

  public UUID getGuestClubUuid() {
    return guestClub;
  }

  public Club getGuestClub(final ClubDataManager clubDataManager) {
    return clubDataManager.getClubs().stream()
        .filter(club -> club.getClubUuid().equals(getGuestClubUuid()))
        .limit(1)
        .findFirst()
        .orElse(null);
  }

  public int getHomeGoals() {
    return homeGoals;
  }

  public void setHomeGoals(final int homeGoals) {
    this.homeGoals = homeGoals;
  }

  public int getGuestGoals() {
    return guestGoals;
  }

  public void setGuestGoals(final int guestGoals) {
    this.guestGoals = guestGoals;
  }

  public void save(final File file) {
    final TypedSerializer<GamePair> gamePairTypedSerializer = new TypedSerializer<>();
    gamePairTypedSerializer.serialize(this, file, true);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final GamePair gamePair = (GamePair) o;
    return getHomeGoals() == gamePair.getHomeGoals()
        && getGuestGoals() == gamePair.getGuestGoals()
        && getHomeClubUuid().equals(gamePair.getHomeClubUuid())
        && getGuestClubUuid().equals(gamePair.getGuestClubUuid());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getHomeClubUuid(), getGuestClubUuid(), getHomeGoals(), getGuestGoals());
  }

  @Override
  public String toString() {
    return "GamePair{"
        + "homeClubUUID=" + homeClub
        + ", guestClubUUID=" + guestClub
        + ", homeGoals=" + homeGoals
        + ", guestGoals=" + guestGoals
        + '}';
  }
}
