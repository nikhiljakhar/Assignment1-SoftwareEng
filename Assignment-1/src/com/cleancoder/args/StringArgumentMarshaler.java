package com.cleancoder.args;

import static java.util.Iterator;
import static java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_STRING;

public class StringArgumentMarshaler implements ArgumentMarshaler {
  private String stringValue = "";

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      stringValue = currentArgument.next();
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }

  private static boolean isValid(ArgumentMarshaler am){
    if (am != null && am instanceof StringArgumentMarshaler){
      return true;
    }
    return false;
  }

  public static String getValue(ArgumentMarshaler am) {
    if (isValid(am)){
      return ((StringArgumentMarshaler) am).stringValue;
    }
    else{
      return "";
    }
  }
}
