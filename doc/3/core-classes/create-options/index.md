---
code: true
type: page
title: CreateOptions
description: CreateOptions class documentation
order: 110
---

# CreateOptions

This class represents the options usable with the [`create`](/sdk/java/3/controllers/document/create) method of the [DocumentController](/sdk/java/3/controllers/document).  

## Namespace

You must include the following package: 

```java
import io.kuzzle.sdk.Options.CreateOptions;
```

## Properties

| Property           | Type                                         | Description                       |
| ------------------ | -------------------------------------------- | --------------------------------- |
| `id`               | <pre>String</pre> (optional)                 | Document identifier. Auto-generated if not specified              |
| `waitForRefresh`   | <pre>Boolean</pre> (optional)                | If set to `true`, Kuzzle will wait for the persistence layer to finish indexing|
