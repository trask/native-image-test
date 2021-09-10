After building the agent and app jars, to build the native image:

```
native-image -J-javaagent:agent/target/native-image-agent-1.0-SNAPSHOT.jar \
             -jar app/target/native-image-app-1.0-SNAPSHOT.jar
```
