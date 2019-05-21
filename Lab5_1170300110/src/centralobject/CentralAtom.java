package centralobject;

public class CentralAtom {
  private String elementName;

  // RI:
  // 合法的ElementName
  // AF:
  // 轨道系统中的中心物体类 for app 3
  // 含有元素名属性
  // 防止REP暴露:
  // 使用private属性
  // 不存在set方法

  public CentralAtom(String str) {
    this.elementName = str;
  }

  public String getElementName() {
    return elementName;
  }
}
