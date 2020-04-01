package io.kuzzle.sdk.Options;

import java.util.concurrent.ConcurrentHashMap;

public class ListOptions {
  private Integer from;
  private Integer size;
  /**
   * Constructor
   */
  public ListOptions() {}

  /**
   * Copy constructor
   *
   * @param options
   */
  public ListOptions(ListOptions options) {
    this.from = options.getFrom();
    this.size = options.getSize();
  }

  public Integer getFrom() {
    return from;
  }

  public Integer getSize() {
    return size;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public ConcurrentHashMap<String, Object> toHashMap() {
    ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();

    options.put("from", this.from);
    options.put("size", this.size);

    return options;
  }
}
