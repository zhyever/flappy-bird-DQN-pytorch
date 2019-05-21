package applications;

public class TrackGameContext {

  public TrackGame game = new TrackGameRandom();

  /**test.
   * 
   * @param g game
   */
  public void run(TrackGame g) {
    // 传入游戏类型不同 random sorted
    game = g;

    game.setPlayer();
  }

}
