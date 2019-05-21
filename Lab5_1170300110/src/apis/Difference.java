package apis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Difference<E> {
  public int differentTracks;
  public Map<Integer, E> left = new HashMap<Integer, E>();
  public Map<Integer, E> right = new HashMap<Integer, E>();

  public int maxTracks;

  public Map<Integer, Integer> differentNum = new HashMap<Integer, Integer>();

  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
    s.append(String.valueOf(differentTracks) + "\n");
    for (int i = 0; i < maxTracks; i++) {
      Iterator<Entry<Integer, Integer>> entries1 = differentNum.entrySet().iterator();
      while (entries1.hasNext()) {
        Entry<Integer, Integer> entry = entries1.next();
        if (entry.getKey() == i + 1) {
          s.append(entry.getValue().toString() + " ");
        }
      }

      Iterator<Entry<Integer, E>> entries2 = left.entrySet().iterator();
      while (entries2.hasNext()) {
        Entry<Integer, E> entry = entries2.next();
        if (entry.getKey() == i + 1) {
          if (entry.getValue() != null) {
            s.append(entry.getValue().toString() + " ");
          } else {
            s.append("нч" + " ");
          }
        }
      }
      s.append(" - ");
      Iterator<Entry<Integer, E>> entries3 = right.entrySet().iterator();
      while (entries3.hasNext()) {
        Entry<Integer, E> entry = entries3.next();
        if (entry.getKey() == i + 1) {
          if (entry.getValue() != null) {
            s.append(entry.getValue().toString() + " ");
          } else {
            s.append("нч" + " ");
          }
        }
      }
      s.append("\n");
    }
    return s.toString();
  }
}
