package physicalobject;

public class ElectronFactory {
  private static Electron e = null;

  public static Electron getElectron() {
    if(e == null) {
      e = new Electron(0);
    }

    return e;
  }
}
