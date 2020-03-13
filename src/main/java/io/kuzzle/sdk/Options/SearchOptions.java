package io.kuzzle.sdk.Options;

import java.util.concurrent.ConcurrentHashMap;

public class SearchOptions {

  private Integer from = 0;
  private String scroll;
  private Integer size = 10;
  /**
   * Constructor
   */
  public SearchOptions() {}

  /**
   * Copy constructor
   *
   * @param options
   */
  public SearchOptions(SearchOptions options) {
    this.from = options.getFrom();
    this.scroll = options.getScroll();
    this.size = options.getSize();
  }

  public Integer getFrom() {
    return from;
  }

  public Integer getSize() {
    return size;
  }

  public String getScroll() {
    return scroll;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public void setScroll(String scroll) {
    this.scroll = scroll;
  }

  public ConcurrentHashMap<String, Object> toHashMap() {
    ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();

    options.put("from", this.from);
    options.put("scroll", this.scroll);
    options.put("size", this.size);

    return options;
  }
}
