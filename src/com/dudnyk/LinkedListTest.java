package com.dudnyk;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;


public class LinkedListTest {
  private LinkedList list;

  @BeforeEach
  void setUp() {
    list = new LinkedList();
  }

  @Test
  void WhenAppended__append__KeepsOrder() {
    list.append("1");
    list.append("2");
    list.append("3");

    Assert.assertEquals("1", list.get(0));
    Assert.assertEquals("2", list.get(1));
    Assert.assertEquals("3", list.get(2));
  }

  @Test
  void WhenAppended__append__ChangesSize() {
    list.append("1");
    list.append("2");
    list.append("3");

    Assert.assertEquals(3, list.getSize());
  }

  @Test
  void WhenInserted__insert__AssignsCorrectIndex() {
    list.append("1");
    list.append("2");
    list.append("3");
    list.insert(0, "inserted");
    list.insert(1, "inserted");
    list.insert(2, "inserted");


    Assert.assertEquals("inserted", list.get(0));
    Assert.assertEquals("inserted", list.get(1));
    Assert.assertEquals("inserted", list.get(2));
    Assert.assertEquals("1", list.get(3));
    Assert.assertEquals("2", list.get(4));
    Assert.assertEquals("3", list.get(5));
  }

  @Test
  void WhenIndexIsOutOfRange__insert__throwsException(){
    list.append("1");
    Exception exception =  Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> {
      list.insert(3, "inserted");
    });
    String expectedMessage = "Wrong index";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }


  @Test
  void WhenIndexIsOutOfRange__replace__throwsException(){
    list.append("1");
    Exception exception =  Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> {
      list.replace(3, "replace");
    });
    String expectedMessage = "Wrong index";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void WhenReplaced__replace__ChangesValue()  {
    list.append("1");
    list.append("2");
    list.append("3");

    list.replace(0, "replaced1");
    list.replace(1, "replaced2");
    list.replace(2, "replaced3");

    Assert.assertEquals("replaced1", list.get(0));
    Assert.assertEquals("replaced2", list.get(1));
    Assert.assertEquals("replaced3", list.get(2));
  }

  @Test
  void WhenRemoved__remove__ChangesSize()  {
    list.append("1");
    list.append("2");
    list.append("3");

    list.remove(0);
    list.remove(1);

    Assert.assertEquals(1, list.getSize());
  }

  @ParameterizedTest(name = "{0} = [{1}, {2}]")
  @MethodSource()
  void WhenRemoved__remove__ChangesOrder(int i, String expected1, String expected2)  {
    list.append("1");
    list.append("2");
    list.append("3");

    list.remove(i);

    Assert.assertEquals(expected1, list.get(0));
    Assert.assertEquals(expected2, list.get(1));
  }

  private static Stream<Arguments> WhenRemoved__remove__ChangesOrder() {
    return Stream.of(
            Arguments.of(0, "2", "3"),
            Arguments.of(1, "1", "3"),
            Arguments.of(2, "1", "2")
    );
  }

  @Test
  void WhenIndexIsOutOfRange__remove__throwsException(){
    list.append("1");
    Exception exception =  Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> {
      list.remove(2);
    });
    String expectedMessage = "Wrong index";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }


  @Test
  void foreach() {
    LinkedList list = new LinkedList();
    list.append("first");
    list.append("second");

    StringBuffer result = new StringBuffer();
    list.forEach(item -> result.append(item + " "));

    Assert.assertEquals("first second ", result.toString());
  }


  @Test
  void WhenEmpty__getSize__returns_0() {
    Assert.assertEquals(0, list.getSize());
  }

  @Test
  void WhenInvoked__get__returnsValue() {
    list.append("1");
    list.append("2");
    Assert.assertEquals("1", list.get(0));
    Assert.assertEquals("2", list.get(1));
  }

  @Test
  void WhenIndexIsOutOfRange__get__throwsError() {
    Exception exception =  Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> {
      list.get(3);
    });
    String expectedMessage = "Wrong index";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @AfterEach
  void tearDown() {
    list = null;
  }
}