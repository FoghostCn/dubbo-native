/Library/Java/JavaVirtualMachines/graalvm-ce-java17-22.3.1/Contents/Home/bin/java -agentlib:native-image-agent=config-output-dir=./src/main/resources/META-INF/native-image -Dfile.encoding=UTF-8 -classpath /Users/foghost/Documents/IdeaProjects/dubbo-native/logging/log4j/target/classes:/Users/foghost/.m2/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar org.apache.dubbo.logging.provider.Application