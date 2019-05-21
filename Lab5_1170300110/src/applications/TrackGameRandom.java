package applications;

import java.util.List;
import java.util.Random;

import physicalobject.Athlete;

public class TrackGameRandom extends TrackGame {

  private void shuffle(List<Athlete> ath, int front, int rear) {
    Random random = new Random();
    for (int i = front; i <= rear; i++) {
      int randomPos = random.nextInt(rear + 1 - front) + front;

      Athlete temp = ath.get(i);
      ath.set(i, ath.get(randomPos));
      ath.set(randomPos, temp);
    }
  }

  @Override
  public void setPlayer() {

    Random random = new Random();
    int randomPos = 0;
    boolean[] flag = new boolean[numOfTracks];

    for (int i = 0; i < flag.length; i++) {
      flag[i] = false;
    }

    if (objects.size() <= numOfTracks) {
      shuffle(objects, 0, objects.size() - 1);
      if (objects.size() < numOfTracks) {
        for (int i = 0; i < objects.size(); i++) {
          randomPos = random.nextInt(numOfTracks);
          if (flag[randomPos] == false) {
            addObjectToTrack(tracks.get(randomPos), objects.get(i));
            rel2.put(objects.get(i), tracks.get(randomPos));
            //objects.get(i).setTrack(tracks.get(randomPos));
            flag[randomPos] = true;
          } else {
            // ÔÙ´ÎËæ»ú
            i--;
          }
        }
      }
    } else {
      shuffle(objects, 0, objects.size() - 1);

      for (int i = 0; i < flag.length; i++) {
        flag[i] = false;
      }

      int k = 0; /// counter
      for (int i = 0; i < objects.size(); i++) {
        k = i % numOfTracks;

        if (k == 0) {
          for (int index = 0; index < flag.length; index++) {
            flag[index] = false;
          }
        }

        randomPos = random.nextInt(numOfTracks);

        if (flag[randomPos] == false) {
          addObjectToTrack(tracks.get(randomPos), objects.get(i));
          rel2.put(objects.get(i), tracks.get(randomPos));
          //objects.get(i).setTrack(tracks.get(randomPos));
          flag[randomPos] = true;
        } else {
          i--;
          k--;
        }

        k++;
      }
    }
  }
}
