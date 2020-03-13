ConcurrentHashMap<String, Object> document = new ConcurrentHashMap<>();
document.put("name", "nina-vkote");

kuzzle.getRealtimeController().publish("my-index", "my-collection", document);