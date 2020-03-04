package io.kuzzle.sdk.Options;

import java.util.concurrent.ConcurrentHashMap;

public class DocumentOptions {

  private String id;
  private Boolean waitForRefresh;
  /**
   * Constructor
   */
  public DocumentOptions() {}

  /**
   * Copy constructor
   *
   * @param options
   */
  public DocumentOptions(DocumentOptions options) {
    this.id = options.getId();
    this.waitForRefresh = options.getWaitForRefresh();
  }

  public Boolean getWaitForRefresh() {
    return waitForRefresh;
  }

  public String getId() {
    return id;
  }

  public void setWaitForRefresh(Boolean waitForRefresh) {
    this.waitForRefresh = waitForRefresh;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ConcurrentHashMap<String, Object> toHashMap() {
    ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();

    options.put("scope", this.id);
    options.put("users", this.waitForRefresh);

    return options;
  }
}
