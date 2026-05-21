#!/bin/sh
GRADLE_HOME="$(dirname "$0")"
exec "${JAVA_HOME}/bin/java" -cp "${GRADLE_HOME}/gradle/wrapper/gradle-wrapper.jar" \
  org.gradle.wrapper.GradleWrapperMain "$@"
