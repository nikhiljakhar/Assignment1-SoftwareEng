package com.cleancoder.args;

import static java.util.Iterator;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
  private boolean booleanValue = false;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    booleanValue = true;
  }
  private static boolean isValid(ArgumentMarshaler am){
    if (am != null && am instanceof BooleanArgumentMarshaler){
      return true;
    }
    return false;
  }

  public static boolean getValue(ArgumentMarshaler am) {
    if (isValid(am)){
      return ((BooleanArgumentMarshaler) am).booleanValue;
    }
    else{
      return false;
    }
  }
}
