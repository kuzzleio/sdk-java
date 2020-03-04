---
code: true
type: page
title: DocumentOptions
description: DocumentOptions class documentation
order: 110
---

# DocumentOptions

This class represents the options usable with the [DocumentController](/sdk/java/3/controllers/document).  

## Namespace

You must include the following package: 

```java
import io.kuzzle.sdk.Options.DocumentOptions;
```

## Properties

| Property           | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `id`               | <pre>String</pre> (optional)                 | Document identifier. Auto-generated if not specified              |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing |
| `retryOnConflict`  | <pre>Number</pre>                            | The number of times the database layer should retry in case of version conflict |
| `source`           | <pre>Boolean</pre>                           | If true, returns the updated document inside the response |
