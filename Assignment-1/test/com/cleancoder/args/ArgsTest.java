package com.cleancoder.args;

import java.util.Map;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.argument.Argument;
import static com.argument.exception.ArgumentException;

import static com.argument.exception.ArgumentException.ErrorCode.*;

public class ArgumentsTest {

	@Test
	public void testWithNoSchemaAndNoArguments() throws Exception {
		final Argument args = new Argument("", new String[0]);
    assertEquals(0, args.nextArgumentIndex());
  }

  @Test
  public void testErrorArgIdWithNoSchemaAndOneArgument() throws Exception {
    try {
      new Argument("", new String[] { "-x" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals('x', e.getErrorArgumentId());
    }
  }

  @Test
  public void testExceptionTypeForNoSchemaWithMultipleArguments() throws Exception {
    try {
      new Argument("", new String[] { "-x", "-y" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals(UNEXPECTED_ARGUMENT, e.getErrorCode());
    }
  }

  @Test
  public void testErrorArgIdForNoSchemaWithMultipleArguments() throws Exception {
    try {
      new Argument("", new String[] { "-x", "-y" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals('x', e.getErrorArgumentId());
    }
  }

  @Test
  public void testExceptionTypeWithNonLetterSchemaAndNoArguments() throws Exception {
    try {
      new Argument("*", new String[] {});
      fail("Args constructor should have thrown exception");
    } catch (final ArgumentException e) {
      assertEquals(INVALID_ARGUMENT_NAME, e.getErrorCode());
    }
  }

  @Test
  public void testErrorArgIdWithNonLetterSchemaAndNoArguments() throws Exception {
    try {
      new Argument("*", new String[] {});
      fail("Args constructor should have thrown exception");
    } catch (final ArgumentException e) {
      assertEquals('*', e.getErrorArgumentId());
    }
  }

  @Test
  public void testExceptionFormatWithInvalidArguments() throws Exception {
    try {
      new Argument("f~", new String[] {});
      fail("Args constructor should have throws exception");
    } catch (final ArgumentException e) {
      assertEquals(INVALID_ARGUMENT_FORMAT, e.getErrorCode());
    }
  }

  @Test
  public void testErrorArgIdWithInvalidArguments() throws Exception {
    try {
      new Argument("f~", new String[] {});
      fail("Args constructor should have throws exception");
    } catch (final ArgumentException e) {
      assertEquals('f', e.getErrorArgumentId());
    }
  }

  @Test
  public void testIfSimpleBooleanArgumentIsPresent() throws Exception {
    final Argument args = new Argument("x", new String[] { "-x" });
    assertEquals(true, args.getBoolean('x'));
  }

  @Test
  public void testIfSingleBooleanArgumentIsPresent() throws Exception {
    final Argument args = new Argument("x", new String[] { "-x" });
    assertEquals(1, args.nextArgumentIndex());
  }

  @Test
  public void testIfSimpleStringArgumentPresent() throws Exception {
    final Argument args = new Argument("x*", new String[] { "-x", "param" });
    assertTrue(args.hasArg('x'));
  }

  public void testSimpleStringArgumentValue() throws Exception {
    final Argument args = new Argument("x*", new String[] { "-x", "param" });
    assertEquals("param", args.getString('x'));
  }

  public void testSimpleStringArgumentHasTwoItems() throws Exception {
    final Argument args = new Argument("x*", new String[] { "-x", "param" });
    assertEquals(2, args.nextArgumentIndex());
  }

  @Test
  public void testExceptionTypeForStringArgumentMissing() throws Exception {
    try {
      new Argument("x*", new String[] { "-x" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals(MISSING_STRING, e.getErrorCode());
    }
  }

  @Test
  public void testErrorArgumentIdForStringArgumentMissing() throws Exception {
    try {
      new Argument("x*", new String[] { "-x" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals('x', e.getErrorArgumentId());
    }
  }

  @Test
  public void testArgsInFormatFor1stArg() throws Exception {
    final Argument args = new Argument("x, y", new String[] { "-xy" });
    assertTrue(args.hasArg('x'));
  }

  @Test
  public void testArgsInFormatFor2ndArg() throws Exception {
    final Argument args = new Argument("x, y", new String[] { "-xy" });
    assertTrue(args.hasArg('y'));
  }

  @Test
  public void testNextArgument() throws Exception {
    final Argument args = new Argument("x, y", new String[] { "-xy" });
    assertEquals(1, args.nextArgumentIndex());
  }

  @Test
  public void testIfSimpleIntArgumentIsPresent() throws Exception {
    final Argument args = new Argument("x#", new String[] { "-x", "42" });
    assertTrue(args.hasArg('x'));
    assertEquals(42, args.getInt('x'));
    assertEquals(2, args.nextArgumentIndex());
  }

  @Test
  public void testValueForSimpleIntArgument() throws Exception {
    final Argument args = new Argument("x#", new String[] { "-x", "42" });
    assertTrue(args.hasArg('x'));
    assertEquals(42, args.getInt('x'));
  }

  @Test
  public void testNumberOfArgsInSimpleIntArgument() throws Exception {
    final Argument args = new Argument("x#", new String[] { "-x", "42" });
    assertTrue(args.hasArg('x'));
    assertEquals(2, args.nextArgumentIndex());
  }

  @Test
  public void testExceptionTypeForInvalidIntegerArgument() throws Exception {
    try {
      new Argument("x#", new String[] { "-x", "Forty two" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals(INVALID_INTEGER, e.getErrorCode());
    }

  }

  @Test
  public void testErrorArgIdForInvalidIntegerArgument() throws Exception {
    try {
      new Argument("x#", new String[] { "-x", "Forty two" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals('x', e.getErrorArgumentId());
    }

  }

  @Test
  public void testErrorParameterIdForInvalidIntegerArgument() throws Exception {
    try {
      new Argument("x#", new String[] { "-x", "Forty two" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals("Forty two", e.getErrorParameter());
    }

  }

  @Test
  public void testErrorParameterForInvalidIntegerArgument() throws Exception {
    try {
      new Argument("x#", new String[] { "-x", "Forty two" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals("Forty two", e.getErrorParameter());
    }

  }

  @Test
  public void testExceptionTypeForMissingIntegerArgument() throws Exception {
    try {
      new Argument("x#", new String[] { "-x" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals(MISSING_INTEGER, e.getErrorCode());
    }
  }

  @Test
  public void testErrorArgIdForMissingIntegerArgument() throws Exception {
    try {
      new Argument("x#", new String[] { "-x" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals('x', e.getErrorArgumentId());
    }
  }

  @Test
  public void testIfSimpleDoubleArgumentIsPresent() throws Exception {
    final Argument args = new Argument("x##", new String[] { "-x", "42.3" });
    assertTrue(args.hasArg('x'));
  }

  @Test
  public void testValueForSimpleDoubleArgument() throws Exception {
    final Argument args = new Argument("x##", new String[] { "-x", "42.3" });
    assertEquals(42.3, args.getDouble('x'), .001);
  }

  @Test
  public void testExceptionTypeForInvalidDoubleArgumet() throws Exception {
    try {
      new Argument("x##", new String[] { "-x", "Forty two" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals(INVALID_DOUBLE, e.getErrorCode());
    }
  }

  @Test
  public void testErrorArgIdForInvalidDoubleArgumet() throws Exception {
    try {
      new Argument("x##", new String[] { "-x", "Forty two" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals('x', e.getErrorArgumentId());
    }
  }

  @Test
  public void testErrorParameterForInvalidDoubleArgumet() throws Exception {
    try {
      new Argument("x##", new String[] { "-x", "Forty two" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals("Forty two", e.getErrorParameter());
    }
  }

  @Test
  public void testExceptionTypeForMissingDoubleArgument() throws Exception {
    try {
      new Argument("x##", new String[] { "-x" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals(MISSING_DOUBLE, e.getErrorCode());
    }
  }

  @Test
  public void testErrorArgIdForMissingDoubleArgument() throws Exception {
    try {
      new Argument("x##", new String[] { "-x" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals('x', e.getErrorArgumentId());
    }
  }

  @Test
  public void testStringArrayArgument() throws Exception {
    final Argument args = new Argument("x[*]", new String[] { "-x", "alpha" });
    assertTrue(args.hasArg('x'));
  }

  @Test
  public void testValueArrayLengthForStringArrayArgument() throws Exception {
    final Argument args = new Argument("x[*]", new String[] { "-x", "alpha" });
    final String[] result = args.getStringArray('x');
    assertEquals(1, result.length);
  }

  @Test
  public void testArraylengthForStringArrayArgument() throws Exception {
    final Argument args = new Argument("x[*]", new String[] { "-x", "alpha" });
    final String[] result = args.getStringArray('x');
    assertEquals(1, result.length);
  }

  @Test
  public void testArrayValueForStringArrayArgument() throws Exception {
    final Argument args = new Argument("x[*]", new String[] { "-x", "alpha" });
    final String[] result = args.getStringArray('x');
    assertEquals("alpha", result[0]);
  }

  @Test
  public void testIfStringArrayElementIsMissing() throws Exception {
    try {
      new Argument("x[*]", new String[] { "-x" });
      fail();
    } catch (final ArgumentException e) {
      assertEquals(MISSING_STRING, e.getErrorCode());
    }
  }

  @Test
  public void testManyStringArrayElements() throws Exception {
    final Argument args = new Argument("x[*]", new String[] { "-x", "alpha", "-x", "beta", "-x", "gamma" });
    assertTrue(args.hasArg('x'));
  }

  @Test
  public void testArrayLengthForStringArrayElements() throws Exception {
    final Argument args = new Argument("x[*]", new String[] { "-x", "alpha", "-x", "beta", "-x", "gamma" });
    final String[] result = args.getStringArray('x');
    assertEquals(3, result.length);
  }

  @Test
  public void testMapArgument() throws Exception {
    final Argument args = new Argument("f&", new String[] { "-f", "key1:val1,key2:val2" });
    assertTrue(args.hasArg('f'));
  }

  @Test
  public void testKeysAndValuesForMapArgument() throws Exception {
    final Argument args = new Argument("f&", new String[] { "-f", "key1:val1,key2:val2" });
    final Map<String, String> map = args.getMap('f');
    assertEquals("val1", map.get("key1"));
    assertEquals("val2", map.get("key2"));
  }

  @Test(expected = ArgumentException.class)
  public void testExceptionTypeForMalormedMapArgument() throws Exception {
    new Argument("f&", new String[] { "-f", "key1:val1,key2" });
  }

  @Test
  public void testSingleMapArgument() throws Exception {
    final Argument args = new Argument("f&", new String[] { "-f", "key1:val1" });
    assertTrue(args.hasArg('f'));
  }

  @Test
  public void testSingleKeyValuePairForMapArgument() throws Exception {
    final Argument args = new Argument("f&", new String[] { "-f", "key1:val1" });
    final Map<String, String> map = args.getMap('f');
    assertEquals("val1", map.get("key1"));
  }

  @Test
  public void testExtraArgumentsForBooleanArg() throws Exception {
    final Argument args = new Argument("x,y*", new String[] { "-x", "-y", "alpha", "beta" });
    assertTrue(args.getBoolean('x'));
  }

  @Test
  public void testExtraArgumentsForStringArgs() throws Exception {
    final Argument args = new Argument("x,y*", new String[] { "-x", "-y", "alpha", "beta" });
    assertEquals("alpha", args.getString('y'));
  }

  @Test
  public void testIf4thArgExists() throws Exception {
    final Argument args = new Argument("x,y*", new String[] { "-x", "-y", "alpha", "beta" });
    assertEquals(3, args.nextArgumentIndex());
  }

  @Test
  public void testExtraArgumentsThatLookLikeFlagsFor1stArg() throws Exception {
    final Argument args = new Argument("x,y", new String[] { "-x", "alpha", "-y", "beta" });
    assertTrue(args.hasArg('x'));
  }

  @Test
  public void testExtraArgumentsThatLookLikeFlagsFor2ndrg() throws Exception {
    final Argument args = new Argument("x,y", new String[] { "-x", "alpha", "-y", "beta" });
    assertFalse(args.hasArg('y'));
  }

  @Test
  public void testBooleanValueFor1stArg() throws Exception {
    final Argument args = new Argument("x,y", new String[] { "-x", "alpha", "-y", "beta" });
    assertTrue(args.getBoolean('x'));
  }

  @Test
  public void testBooleanValueFor2ndArg() throws Exception {
    final Argument args = new Argument("x,y", new String[] { "-x", "alpha", "-y", "beta" });
    assertFalse(args.getBoolean('y'));
    assertEquals(1, args.nextArgumentIndex());
  }

  @Test
  public void testIf2ndArgExists() throws Exception {
    final Argument args = new Argument("x,y", new String[] { "-x", "alpha", "-y", "beta" });
		assertEquals(1, args.nextArgumentIndex());
	}
}
