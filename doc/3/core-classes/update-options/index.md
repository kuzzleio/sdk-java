---
code: true
type: page
title: UpdateOptions
description: UpdateOptions class documentation
order: 110
---

# UpdateOptions

This class represents the options usable with the [`update`](/sdk/java/3/controllers/document/update) and [mUpdate](/sdk/java/3/controllers/document/m-update) methods of the [DocumentController](/sdk/java/3/controllers/document).  

## Namespace

You must include the following package: 

```java
import io.kuzzle.sdk.Options.UpdateOptions;
```

## Properties

| Property           | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |
| `retryOnConflict`  | <pre>Integer</pre>                           | The number of times the database layer should retry in case of version conflict |
| `source`           | <pre>Boolean</pre>                           | If true, returns the updated document inside the response |
