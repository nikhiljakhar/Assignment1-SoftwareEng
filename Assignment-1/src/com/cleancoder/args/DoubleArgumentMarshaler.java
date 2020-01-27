package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import static java.util.*;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
  private double doubleValue = 0;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      doubleValue = Double.parseDouble(parameter);
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_DOUBLE);
    } catch (NumberFormatException e) {
      throw new ArgsException(INVALID_DOUBLE, parameter);
    }
  }

  private static boolean isValid(ArgumentMarshaler am){
    if (am != null && am instanceof DoubleArgumentMarshaler){
      return true;
    }
    return false;
  }

  public static double getValue(ArgumentMarshaler am) {
    if (isValid(am)){
      return ((DoubleArgumentMarshaler) am).doubleValue;
    }
    else{
      return 0.0;
    }
  }
}
