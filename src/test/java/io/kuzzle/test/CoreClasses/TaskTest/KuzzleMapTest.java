package io.kuzzle.test.CoreClasses.TaskTest;

import io.kuzzle.sdk.CoreClasses.Maps.KuzzleMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class KuzzleMapTest {
  private KuzzleMap kuzzleMap;

  @Before
  public void setup() throws URISyntaxException {
    kuzzleMap = new KuzzleMap();
    kuzzleMap.put("String", "String");
    kuzzleMap.put("Number", 0);
    kuzzleMap.put("Boolean", false);
    kuzzleMap.put("ArrayList", new ArrayList<>());
    kuzzleMap.put("Map", new ConcurrentHashMap<>());
    kuzzleMap.put("KuzzleMap", new KuzzleMap());
    kuzzleMap.put("Null", null);
  }

  @Test
  public void isString() {
    Assert.assertTrue(kuzzleMap.isString("String"));
    Assert.assertFalse(kuzzleMap.isString("Number"));
    Assert.assertFalse(kuzzleMap.isString("Boolean"));
    Assert.assertFalse(kuzzleMap.isString("ArrayList"));
    Assert.assertFalse(kuzzleMap.isString("Map"));
    Assert.assertFalse(kuzzleMap.isString("KuzzleMap"));
    Assert.assertFalse(kuzzleMap.isString("Null"));
    Assert.assertFalse(kuzzleMap.isString("UnknownKey"));
  }

  @Test
  public void isNumber() {
    Assert.assertFalse(kuzzleMap.isNumber("String"));
    Assert.assertTrue(kuzzleMap.isNumber("Number"));
    Assert.assertFalse(kuzzleMap.isNumber("Boolean"));
    Assert.assertFalse(kuzzleMap.isNumber("ArrayList"));
    Assert.assertFalse(kuzzleMap.isNumber("Map"));
    Assert.assertFalse(kuzzleMap.isNumber("KuzzleMap"));
    Assert.assertFalse(kuzzleMap.isNumber("Null"));
    Assert.assertFalse(kuzzleMap.isNumber("UnknownKey"));
  }

  @Test
  public void isBoolean() throws Exception {
    Assert.assertFalse(kuzzleMap.isBoolean("String"));
    Assert.assertFalse(kuzzleMap.isBoolean("Number"));
    Assert.assertTrue(kuzzleMap.isBoolean("Boolean"));
    Assert.assertFalse(kuzzleMap.isBoolean("ArrayList"));
    Assert.assertFalse(kuzzleMap.isBoolean("Map"));
    Assert.assertFalse(kuzzleMap.isBoolean("KuzzleMap"));
    Assert.assertFalse(kuzzleMap.isBoolean("Null"));
    Assert.assertFalse(kuzzleMap.isBoolean("UnknownKey"));
  }

  @Test
  public void isArrayList() {
    Assert.assertFalse(kuzzleMap.isArrayList("String"));
    Assert.assertFalse(kuzzleMap.isArrayList("Number"));
    Assert.assertFalse(kuzzleMap.isArrayList("Boolean"));
    Assert.assertTrue(kuzzleMap.isArrayList("ArrayList"));
    Assert.assertFalse(kuzzleMap.isArrayList("Map"));
    Assert.assertFalse(kuzzleMap.isArrayList("KuzzleMap"));
    Assert.assertFalse(kuzzleMap.isArrayList("Null"));
    Assert.assertFalse(kuzzleMap.isArrayList("UnknownKey"));
  }

  @Test
  public void isMap() {
    Assert.assertFalse(kuzzleMap.isMap("String"));
    Assert.assertFalse(kuzzleMap.isMap("Number"));
    Assert.assertFalse(kuzzleMap.isMap("Boolean"));
    Assert.assertFalse(kuzzleMap.isMap("ArrayList"));
    Assert.assertTrue(kuzzleMap.isMap("Map"));
    Assert.assertTrue(kuzzleMap.isMap("KuzzleMap"));
    Assert.assertFalse(kuzzleMap.isMap("Null"));
    Assert.assertFalse(kuzzleMap.isMap("UnknownKey"));
  }

  @Test
  public void isNull() {
    Assert.assertFalse(kuzzleMap.isNull("String"));
    Assert.assertFalse(kuzzleMap.isNull("Number"));
    Assert.assertFalse(kuzzleMap.isNull("Boolean"));
    Assert.assertFalse(kuzzleMap.isNull("ArrayList"));
    Assert.assertFalse(kuzzleMap.isNull("Map"));
    Assert.assertFalse(kuzzleMap.isNull("KuzzleMap"));
    Assert.assertTrue(kuzzleMap.isNull("Null"));
    Assert.assertFalse(kuzzleMap.isNull("UnknownKey"));
  }

  @Test
  public void getString() {
    Assert.assertNotNull(kuzzleMap.getString("String"));
    Assert.assertNull(kuzzleMap.getString("Number"));
    Assert.assertNull(kuzzleMap.getString("Boolean"));
    Assert.assertNull(kuzzleMap.getString("ArrayList"));
    Assert.assertNull(kuzzleMap.getString("Map"));
    Assert.assertNull(kuzzleMap.getString("KuzzleMap"));
    Assert.assertNull(kuzzleMap.getString("Null"));
    Assert.assertNull(kuzzleMap.getString("UnknownKey"));
  }

  @Test
  public void getNumber() {
    Assert.assertNull(kuzzleMap.getNumber("String"));
    Assert.assertNotNull(kuzzleMap.getNumber("Number"));
    Assert.assertNull(kuzzleMap.getNumber("Boolean"));
    Assert.assertNull(kuzzleMap.getNumber("ArrayList"));
    Assert.assertNull(kuzzleMap.getNumber("Map"));
    Assert.assertNull(kuzzleMap.getNumber("KuzzleMap"));
    Assert.assertNull(kuzzleMap.getNumber("Null"));
    Assert.assertNull(kuzzleMap.getNumber("UnknownKey"));
  }

  @Test
  public void getBoolean() {
    Assert.assertNull(kuzzleMap.getBoolean("String"));
    Assert.assertNull(kuzzleMap.getBoolean("Number"));
    Assert.assertNotNull(kuzzleMap.getBoolean("Boolean"));
    Assert.assertNull(kuzzleMap.getBoolean("ArrayList"));
    Assert.assertNull(kuzzleMap.getBoolean("Map"));
    Assert.assertNull(kuzzleMap.getBoolean("KuzzleMap"));
    Assert.assertNull(kuzzleMap.getBoolean("Null"));
    Assert.assertNull(kuzzleMap.getBoolean("UnknownKey"));
  }

  @Test
  public void getArrayList() {
    Assert.assertNull(kuzzleMap.getArrayList("String"));
    Assert.assertNull(kuzzleMap.getArrayList("Number"));
    Assert.assertNull(kuzzleMap.getArrayList("Boolean"));
    Assert.assertNotNull(kuzzleMap.getArrayList("ArrayList"));
    Assert.assertNull(kuzzleMap.getArrayList("Map"));
    Assert.assertNull(kuzzleMap.getArrayList("KuzzleMap"));
    Assert.assertNull(kuzzleMap.getArrayList("Null"));
    Assert.assertNull(kuzzleMap.getArrayList("UnknownKey"));
  }

  @Test
  public void getMap() {
    Assert.assertNull(kuzzleMap.getMap("String"));
    Assert.assertNull(kuzzleMap.getMap("Number"));
    Assert.assertNull(kuzzleMap.getMap("Boolean"));
    Assert.assertNull(kuzzleMap.getMap("ArrayList"));
    Assert.assertNotNull(kuzzleMap.getMap("Map"));
    Assert.assertNotNull(kuzzleMap.getMap("KuzzleMap"));
    Assert.assertNull(kuzzleMap.getMap("Null"));
    Assert.assertNull(kuzzleMap.getMap("UnknownKey"));
  }

  @Test
  public void get() {
    Assert.assertTrue(kuzzleMap.get("String") instanceof String);
    Assert.assertTrue(kuzzleMap.get("Number") instanceof Number);
    Assert.assertTrue(kuzzleMap.get("Boolean") instanceof Boolean);
    Assert.assertTrue(kuzzleMap.get("ArrayList") instanceof ArrayList);
    Assert.assertTrue(kuzzleMap.get("Map") instanceof ConcurrentHashMap);
    Assert.assertTrue(kuzzleMap.get("KuzzleMap") instanceof ConcurrentHashMap);
    Assert.assertTrue(kuzzleMap.get("Null") == null);
    Assert.assertTrue(kuzzleMap.get("UnknownKey") == null);
  }

  @Test
  public void optString() {
    Assert.assertTrue(kuzzleMap.optString("String", "foo").equals("String"));
    Assert.assertTrue(kuzzleMap.optString("Number", "foo").equals("foo"));
    Assert.assertTrue(kuzzleMap.optString("Boolean", "foo").equals("foo"));
    Assert.assertTrue(kuzzleMap.optString("ArrayList", "foo").equals("foo"));
    Assert.assertTrue(kuzzleMap.optString("Map", "foo").equals("foo"));
    Assert.assertTrue(kuzzleMap.optString("KuzzleMap", "foo").equals("foo"));
    Assert.assertTrue(kuzzleMap.optString("Null", "foo").equals("foo"));
    Assert.assertTrue(kuzzleMap.optString("UnknownKey", "foo").equals("foo"));
  }

  @Test
  public void optNumber() {
    Assert.assertTrue(kuzzleMap.optNumber("String", 42).equals(42));
    Assert.assertTrue(kuzzleMap.optNumber("Number", 42).equals(0));
    Assert.assertTrue(kuzzleMap.optNumber("Boolean", 42).equals(42));
    Assert.assertTrue(kuzzleMap.optNumber("ArrayList", 42).equals(42));
    Assert.assertTrue(kuzzleMap.optNumber("Map", 42).equals(42));
    Assert.assertTrue(kuzzleMap.optNumber("KuzzleMap", 42).equals(42));
    Assert.assertTrue(kuzzleMap.optNumber("Null", 42).equals(42));
    Assert.assertTrue(kuzzleMap.optNumber("UnknownKey", 42).equals(42));
  }

  @Test
  public void optBoolean() {
    Assert.assertTrue(kuzzleMap.optBoolean("String", true).equals(true));
    Assert.assertTrue(kuzzleMap.optBoolean("Number", true).equals(true));
    Assert.assertTrue(kuzzleMap.optBoolean("Boolean", true).equals(false));
    Assert.assertTrue(kuzzleMap.optBoolean("ArrayList", true).equals(true));
    Assert.assertTrue(kuzzleMap.optBoolean("Map", true).equals(true));
    Assert.assertTrue(kuzzleMap.optBoolean("KuzzleMap", true).equals(true));
    Assert.assertTrue(kuzzleMap.optBoolean("Null", true).equals(true));
    Assert.assertTrue(kuzzleMap.optBoolean("UnknownKey", true).equals(true));
  }

  @Test
  public void optArrayList() {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);

    Assert.assertEquals(kuzzleMap.optArrayList("String", list).hashCode(), list.hashCode());
    Assert.assertEquals(kuzzleMap.optArrayList("Number", list).hashCode(), list.hashCode());
    Assert.assertEquals(kuzzleMap.optArrayList("Boolean", list).hashCode(), list.hashCode());
    Assert.assertNotEquals(kuzzleMap.optArrayList("ArrayList", list).hashCode(), list.hashCode());
    Assert.assertEquals(kuzzleMap.optArrayList("Map", list).hashCode(), list.hashCode());
    Assert.assertEquals(kuzzleMap.optArrayList("KuzzleMap", list).hashCode(), list.hashCode());
    Assert.assertEquals(kuzzleMap.optArrayList("Null", list).hashCode(), list.hashCode());
    Assert.assertEquals(kuzzleMap.optArrayList("UnknownKey", list).hashCode(), list.hashCode());
  }

  @Test
  public void optMap() {
    KuzzleMap map = new KuzzleMap();
    map.put("Data", 1);

    Assert.assertEquals(kuzzleMap.optMap("String", map).hashCode(), map.hashCode());
    Assert.assertEquals(kuzzleMap.optMap("Number", map).hashCode(), map.hashCode());
    Assert.assertEquals(kuzzleMap.optMap("Boolean", map).hashCode(), map.hashCode());
    Assert.assertEquals(kuzzleMap.optMap("ArrayList", map).hashCode(), map.hashCode());
    Assert.assertNotEquals(kuzzleMap.optMap("Map", map).hashCode(), map.hashCode());
    Assert.assertNotEquals(kuzzleMap.optMap("KuzzleMap", map).hashCode(), map.hashCode());
    Assert.assertEquals(kuzzleMap.optMap("Null", map).hashCode(), map.hashCode());
    Assert.assertEquals(kuzzleMap.optMap("UnknownKey", map).hashCode(), map.hashCode());
  }
}
