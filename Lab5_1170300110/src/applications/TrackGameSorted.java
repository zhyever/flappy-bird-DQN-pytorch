package applications;

import java.util.Collections;
import java.util.Comparator;

import physicalobject.Athlete;

public class TrackGameSorted extends TrackGame {

  // 如果 tracks 是偶数 6 1 2 3 4 5 6 六条赛道
  // 6/2 = 3 放置顺序： 3 4 2 5 1 6
  // 如果 tracks 是奇数5 1 2 3 4 5
  // 5/2 = 2 放置顺序 3 2 4 1 5
  @Override
  void setPlayer() {
    Collections.sort(objects, new Comparator<Athlete>() {

      @Override
      public int compare(Athlete o1, Athlete o2) {
        int num1 = (int) (o1.getGrade() * 100);
        int num2 = (int) (o2.getGrade() * 100);

        return num1 - num2;
      }

    });
    // sort(objects, 0, objects.size() - 1);
    // Collections.reverse(objects);

    if (objects.size() <= numOfTracks) {
      if (numOfTracks % 2 == 0) {
        int counter = 0;
        int index = numOfTracks / 2;
        while (index >= 1) {
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
          }
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index + counter - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + counter - 1));
            // objects.get(counter).setTrack(tracks.get(index + counter - 1));
            counter++;
          }
          index--;
        }
      } else {
        int counter = 0;
        int index = numOfTracks / 2 + 1;
        addObjectToTrack(tracks.get(index - 1), objects.get(counter));
        rel2.put(objects.get(counter), tracks.get(index - 1));
        // objects.get(counter).setTrack(tracks.get(index - 1));
        index--;
        counter++;
        while (index >= 1) {
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
          }
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index + counter - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + counter - 1));
            // objects.get(counter).setTrack(tracks.get(index + counter - 1));
            counter++;
          }
          index--;
        }
      }
    } else {
      int numOfGroup;
      if (objects.size() % numOfTracks == 0) {
        numOfGroup = objects.size() / numOfTracks;
      } else {
        numOfGroup = objects.size() / numOfTracks + 1;
      }

      // 先对所有满员组进行操作
      int cont = 1;
      int counter = 0;

      int f = 0; // 索引
      while (cont <= numOfGroup - 1) {
        f = counter % numOfTracks;

        if (numOfTracks % 2 == 0) {

          int index = numOfTracks / 2;
          while (index >= 1) {

            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
            f++;

            addObjectToTrack(tracks.get(index + f - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + f - 1));
            // objects.get(counter).setTrack(tracks.get(index + f - 1));
            counter++;
            f++;

            index--;
          }
        } else {

          int index = numOfTracks / 2 + 1;
          addObjectToTrack(tracks.get(index - 1), objects.get(counter));
          rel2.put(objects.get(counter), tracks.get(index - 1));
          // objects.get(counter).setTrack(tracks.get(index - 1));
          index--;
          counter++;
          f++;
          while (index >= 1) {

            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
            f++;

            addObjectToTrack(tracks.get(index + f - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + f - 1));
            // objects.get(counter).setTrack(tracks.get(index + f - 1));
            counter++;
            f++;

            index--;
          }
        }
        cont++;
      }

      /// 进入最后一组

      if (numOfTracks % 2 == 0) {
        f = counter % numOfTracks;
        int index = numOfTracks / 2;
        while (index >= 1) {
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
            f++;
          }
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index + f - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + f - 1));
            // objects.get(counter).setTrack(tracks.get(index + f - 1));
            counter++;
            f++;
          }
          index--;
        }
      } else {
        f = counter % numOfTracks;
        int index = numOfTracks / 2 + 1;
        addObjectToTrack(tracks.get(index - 1), objects.get(counter));
        rel2.put(objects.get(counter), tracks.get(index - 1));
        // objects.get(counter).setTrack(tracks.get(index - 1));
        index--;
        counter++;
        f++;
        while (index >= 1) {
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
            f++;
          }
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index + f - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + f - 1));
            // objects.get(counter).setTrack(tracks.get(index + f - 1));
            counter++;
            f++;
          }
          index--;
        }
      }

    }
  }

}
