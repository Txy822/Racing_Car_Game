package Server;

import java.util.Arrays;

public class NetworkHelper {

  /**
   * Checks to see if a given object implements a certain interface
   *
   * @return true if it does, otherwise false
   */
  public static boolean implementsInterface(Object object, Class _interface) {
    return Arrays.stream(object.getClass().getInterfaces()).anyMatch(_interface::equals);
  }
}
