package com.cleancoder.args;

import static java.util.HashMap;
import static java.util.Iterator;
import static java.util.Map;
import static java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class MapArgumentMarshaler implements ArgumentMarshaler {
  private Map<String, String> map = new HashMap<>();

  private static boolean isMapEntry(String[] entryComponents){
    if (entryComponents.length != 2){
      return false;
    }
    return true;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      String[] mapEntries = currentArgument.next().split(",");
      for (String entry : mapEntries) {
        String[] entryComponents = entry.split(":");
        if (!isMapEntry(entryComponents)){
          throw new ArgsException(MALFORMED_MAP);
        }
        map.put(entryComponents[0], entryComponents[1]);
      }
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_MAP);
    }
  }

  private static boolean isValid(ArgumentMarshaler am){
    if (am != null && am instanceof MapArgumentMarshaler){
      return true;
    }
    return false;
  }

  public static Map<String, String> getValue(ArgumentMarshaler am) {
    if (isValid(am)){
      return ((MapArgumentMarshaler) am).map;
    }
    else{
      return new HashMap<>();
    }
  }
}
