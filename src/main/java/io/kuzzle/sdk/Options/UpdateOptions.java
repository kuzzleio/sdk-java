package io.kuzzle.sdk.Options;

import java.util.concurrent.ConcurrentHashMap;

public class UpdateOptions {

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
    this.waitForRefresh = options.getWaitForRefresh();
    this.retryOnConflict = options.getRetryOnConflict();
    this.source = options.getSource();
  }

  public Boolean getWaitForRefresh() {
    return waitForRefresh;
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

  public void setRetryOnConflict(int retryOnConflict) {
    this.retryOnConflict = retryOnConflict;
  }

  public void setSource(Boolean source) {
    this.source = source;
  }

  public ConcurrentHashMap<String, Object> toHashMap() {
    ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();

    options.put("source", this.source);
    options.put("waitForRefresh", this.waitForRefresh);
    options.put("retryOnConflict", this.retryOnConflict);

    return options;
  }
}
