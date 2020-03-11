package io.kuzzle.sdk.Options;

import java.util.concurrent.ConcurrentHashMap;

public class UpdateOptions {

  private String id;
  private Integer retryOnConflict;
  private Boolean source;
  private Boolean waitForRefresh;
  /**
   * Constructor
   */
  public UpdateOptions() {}

  /**
   * Copy constructor
   *
   * @param options
   */
  public UpdateOptions(UpdateOptions options) {
    this.id = options.getId();
    this.waitForRefresh = options.getWaitForRefresh();
    this.retryOnConflict = options.getRetryOnConflict();
    this.source = options.getSource();
  }

  public Boolean getWaitForRefresh() {
    return waitForRefresh;
  }

  public String getId() {
    return id;
  }

  public int getRetryOnConflict() {
    return retryOnConflict;
  }

  public Boolean getSource() {
    return source;
  }

  public void setWaitForRefresh(Boolean waitForRefresh) {
    this.waitForRefresh = waitForRefresh;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setRetryOnConflict(int retryOnConflict) {
    this.retryOnConflict = retryOnConflict;
  }

  public void setSource(Boolean source) {
    this.source = source;
  }

  public ConcurrentHashMap<String, Object> toHashMap() {
    ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();

    options.put("_id", this.id);
    options.put("source", this.source);
    options.put("waitForRefresh", this.waitForRefresh);
    options.put("retryOnConflict", this.retryOnConflict);

    return options;
  }
}
