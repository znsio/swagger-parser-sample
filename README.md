# swagger-parser-sample

## How to Run

1. Run the [`Main.kt`](src/main/kotlin/Main.kt) file and observe the output:
   ```shell
   ./gradlew run
    ```

2. Execute the [`ReferenceTest.kt`](src/test/kotlin/ReferenceTest.kt) file and observe the assertions:
   ```shell
   ./gradlew test
    ```
   
## Description

When using OpenAPI Specification where the operations of an endpoint are being referenced using another file, 
the examples are parsed as strings rather than objects in the absence of a reference.

![schema_diff](https://github.com/user-attachments/assets/4be8cedb-82a8-4840-b419-a4b212a34379)

As observed in the image above, the only distinction is that the operations under `/products` are referenced in `V1`, 
whereas no such reference exists in `V2`. When flattened out both `V1` and `V2` are identical. However, 
the example value and example type do not match when these specifications are parsed.

## Expected Behavior

The examples in both `V1` and `V2` should be parsed consistently as JSON Object.

## Actual Behavior

In `V1`, where the operations are referenced:
```shell
V1 Example Value: {id=1, name=XYZ Product, inventory=100}
V1 Example Value Type: class java.util.LinkedHashMap
```

In `V2`, where no reference exists:
```shell
V2 Example Value: {"id":"1","name":"XYZ Product","inventory":100}
V2 Example Value Type: class com.fasterxml.jackson.databind.node.ObjectNode
```