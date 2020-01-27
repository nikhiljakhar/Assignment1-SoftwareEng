package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import static java.util.*;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
  private List<String> strings = new ArrayList<String>();

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      strings.add(currentArgument.next());
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }

  private static boolean isValid(ArgumentMarshaler am){
    if (am != null && am instanceof StringArrayArgumentMarshaler){
      return true;
    }
    return false;
  }

  public static String[] getValue(ArgumentMarshaler am) {
    if (isValid(am)){
      return ((StringArrayArgumentMarshaler) am).strings.toArray(new String[0]);
    }
    else{
      return new String[0];
    }
  }
}
