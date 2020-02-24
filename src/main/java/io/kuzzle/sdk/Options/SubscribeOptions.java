package io.kuzzle.sdk.Options;

import java.util.concurrent.ConcurrentHashMap;

public class SubscribeOptions {
  public enum Scope {
    ALL("all"),
    IN("in"),
    OUT("out"),
    NONE("none");

    private final String label;
    private Scope(final String label) {
      this.label = label;
    }

    @Override
    public String toString() {
      return this.label;
    }
  }
  public enum Users {
    ALL("all"),
    IN("in"),
    OUT("out"),
    NONE("none");

    private final String label;
    private Users(final String label) {
      this.label = label;
    }

    @Override
    public String toString() {
      return this.label;
    }
  }
  private Scope scope = Scope.ALL;
  private Users users = Users.NONE;
  private boolean subscribeToSelf = true;
  private ConcurrentHashMap<String, Object> volatiles = new ConcurrentHashMap<>();

  /**
   * Constructor
   */
  public SubscribeOptions() {}

  /**
   * Copy constructor
   *
   * @param options
   */
  public SubscribeOptions(SubscribeOptions options) {
    this.scope = options.getScope();
    this.users = options.getUsers();
    this.subscribeToSelf = options.isSubscribeToSelf();
    this.volatiles = options.getVolatiles();
  }

  public Scope getScope() {
    return scope;
  }

  public void setScope(Scope scope) {
    this.scope = scope;
  }

  public Users getUsers() {
    return users;
  }

  public void setUsers(Users users) {
    this.users = users;
  }

  public boolean isSubscribeToSelf() {
    return subscribeToSelf;
  }

  public void setSubscribeToSelf(boolean subscribeToSelf) {
    this.subscribeToSelf = subscribeToSelf;
  }

  public ConcurrentHashMap<String, Object> getVolatiles() {
    return volatiles;
  }

  public void setVolatiles(ConcurrentHashMap<String, Object> volatiles) {
    this.volatiles = volatiles;
  }

  public ConcurrentHashMap<String, Object> toHashMap() {
    ConcurrentHashMap<String, Object> options = new ConcurrentHashMap<>();

    options.put("scope", this.scope.toString());
    options.put("users", this.users.toString());
    options.put("subscribeToSelf", this.subscribeToSelf);
    options.put("volatile", this.volatiles);

    return options;
  }
}
