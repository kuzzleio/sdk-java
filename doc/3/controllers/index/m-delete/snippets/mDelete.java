ArrayList<String> indexes = new ArrayList<>();
    indexes.add("nyc-open-data");
ArrayList<String> result = kuzzle.getIndexController().mDelete(indexes).get();
