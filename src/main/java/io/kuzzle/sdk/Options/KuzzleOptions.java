package io.kuzzle.sdk.Options;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import static io.kuzzle.sdk.Helpers.Default.defaultValue;

public class KuzzleOptions {

  /**
   * The maximum amount of elements that the queue can contains. If set to -1,
   * the size is unlimited.
   */
  private int maxQueueSize = -1;

  /**
   * The minimum duration of a Token before being automatically refreshed. If
   * set to -1 the SDK does not refresh the token automatically.
   */
  private int minTokenDuration = 3_600_000;

  /**
   * The minimum duration of a Token after refresh. If set to -1 the SDK does
   * not refresh the token automatically.
   */
  private int refreshedTokenDuration = 3_600_000;

  /**
   * The maximum delay between two requests to be replayed.
   */
  private int maxRequestDelay = 1000;

  private Predicate<ConcurrentHashMap<String, Object>> queueFilter = (
      ConcurrentHashMap<String, Object> obj) -> true;

  private boolean autoResubscribe = true;

  /**
   * Initialize a new KuzzleOptions instance.
   */
  public KuzzleOptions() {
  }

  /**
   * Initialize a new KuzzleOptions instance and copy other KuzzleOptions fields
   * 
   * @param options
   */
  public KuzzleOptions(KuzzleOptions options) {
    this.maxQueueSize = options.maxQueueSize;
    this.minTokenDuration = options.minTokenDuration;
    this.refreshedTokenDuration = options.refreshedTokenDuration;
    this.autoResubscribe = options.autoResubscribe;

    this.maxRequestDelay = options.maxRequestDelay;

    this.queueFilter = options.queueFilter;
  }

  /**
   * @return The maximum amount of elements that the queue can contains. If set
   *         to -1, the size is unlimited.
   */
  public int getMaxQueueSize() {
    return maxQueueSize;
  }

  /**
   * Set the maximum amount of elements that the queue can contains. If set to
   * -1, the size is unlimited.
   * 
   * @param maxQueueSize
   * @return This KuzzleOptions instance
   */
  public KuzzleOptions setMaxQueueSize(int maxQueueSize) {
    this.maxQueueSize = maxQueueSize < 0 ? -1 : maxQueueSize;

    return this;
  }

  /**
   * @return The minimum duration of a Token before being automatically
   *         refreshed. If set to -1 the SDK does not refresh the token
   *         automatically.
   */
  public int getMinTokenDuration() {
    return minTokenDuration;
  }

  /**
   * Set the minimum duration of a Token before being automatically refreshed.
   * If set to -1 the SDK does not refresh the token automatically.
   * 
   * @param minTokenDuration
   * @return This KuzzleOptions instance
   */
  public KuzzleOptions setMinTokenDuration(int minTokenDuration) {
    this.minTokenDuration = minTokenDuration < 0 ? -1 : minTokenDuration;

    return this;
  }

  /**
   * @return The minimum duration of a Token after refresh. If set to -1 the SDK
   *         does not refresh the token automatically.
   */
  public int getRefreshedTokenDuration() {
    return refreshedTokenDuration;
  }

  /**
   * Set the minimum duration of a Token after refresh. If set to -1 the SDK
   * does not refresh the token automatically.
   * 
   * @param refreshedTokenDuration
   * @return This KuzzleOptions instance
   */
  public KuzzleOptions setRefreshedTokenDuration(int refreshedTokenDuration) {
    this.refreshedTokenDuration = refreshedTokenDuration < 0 ? -1
        : refreshedTokenDuration;

    return this;
  }

  /**
   * @return The maximum delay between two requests to be replayed.
   */
  public int getMaxRequestDelay() {
    return maxRequestDelay;
  }

  /**
   * Set the maximum delay between two requests to be replayed.
   * 
   * @param maxRequestDelay
   * @return This KuzzleOptions instance
   */
  public KuzzleOptions setMaxRequestDelay(int maxRequestDelay) {
    this.maxRequestDelay = maxRequestDelay;
    return this;
  }

  public Predicate<ConcurrentHashMap<String, Object>> getQueueFilter() {
    return queueFilter;
  }

  public KuzzleOptions setQueueFilter(
      Predicate<ConcurrentHashMap<String, Object>> filter) {
    this.queueFilter = defaultValue(filter,
        (ConcurrentHashMap<String, Object> obj) -> true);
    return this;
  }

  public boolean isAutoResubscribe() {
    return autoResubscribe;
  }

  public KuzzleOptions setAutoResubscribe(boolean autoResubscribe) {
    this.autoResubscribe = autoResubscribe;
    return this;
  }
}
