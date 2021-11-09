# Using the minimal agent in this repo

Build the agent and app jars, and then build the native image:

```
native-image -J-javaagent:agent/target/native-image-agent-1.0-SNAPSHOT.jar \
             -jar app/target/native-image-app-1.0-SNAPSHOT.jar
```

# Using Glowroot

To build the native image with glowroot, first build glowroot ([trask/native-image](https://github.com/trask/glowroot/tree/native-image) branch), and unzip it into this directory.

```
unzip -o -d ../glowroot/agent/dist/target/glowroot-agent-0.14.0-beta.3-SNAPSHOT-dist.zip
```

and then build the native image:

```
native-image --initialize-at-build-time=org.glowroot.agent,sun.instrument.InstrumentationImpl \
             --initialize-at-run-time=org.glowroot.agent.shaded.io.netty \
             --allow-incomplete-classpath \
             -J-javaagent:glowroot/glowroot.jar \
             -jar app/target/native-image-app-1.0-SNAPSHOT.jar
```

it should build, but running the image currently fails with

```
java.lang.ExceptionInInitializerError
        at com.oracle.svm.core.classinitialization.ClassInitializationInfo.initialize(ClassInitializationInfo.java:315)
        at java.lang.Class.ensureInitialized(DynamicHub.java:548)
        at com.oracle.svm.core.classinitialization.ClassInitializationInfo.initialize(ClassInitializationInfo.java:260)
        at org.glowroot.agent.init.NettyInit.run(NettyInit.java:63)
        at org.glowroot.agent.embedded.init.EmbeddedGlowrootAgentInit$1.run(EmbeddedGlowrootAgentInit.java:75)
        at org.glowroot.agent.impl.BytecodeServiceImpl.enteringMainMethod(BytecodeServiceImpl.java:255)
        at org.glowroot.agent.impl.BytecodeServiceImpl.enteringMainMethod(BytecodeServiceImpl.java:77)
        at org.glowroot.agent.bytecode.api.Bytecode.enteringMainMethod(Bytecode.java:37)
        at com.github.trask.app.Main.main(Main.java)
Caused by: java.lang.IllegalArgumentException: Can't find '[toLeakAwareBuffer]' in org.glowroot.agent.shaded.io.netty.buffer.AbstractByteBufAllocator
        at org.glowroot.agent.shaded.io.netty.util.ResourceLeakDetector.addExclusions(ResourceLeakDetector.java:577)
        at org.glowroot.agent.shaded.io.netty.buffer.AbstractByteBufAllocator.<clinit>(AbstractByteBufAllocator.java:36)
        at com.oracle.svm.core.classinitialization.ClassInitializationInfo.invokeClassInitializer(ClassInitializationInfo.java:375)
        at com.oracle.svm.core.classinitialization.ClassInitializationInfo.initialize(ClassInitializationInfo.java:295)
        ... 8 more
```
