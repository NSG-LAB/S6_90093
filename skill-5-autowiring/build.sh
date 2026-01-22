#!/bin/bash
# Spring Autowiring Demo - Build and Run Script
# This script compiles the project and runs both XML and Annotation configuration demos

echo ""
echo "======================================"
echo "Spring Autowiring Demo"
echo "Build and Run Script"
echo "======================================"
echo ""

# Check for Spring JARs in lib folder
LIB_DIR="$(dirname "$0")/lib"
JAR_COUNT=0

if [ -d "$LIB_DIR" ]; then
    JAR_COUNT=$(find "$LIB_DIR" -maxdepth 1 -name "*.jar" | wc -l)
fi

if [ $JAR_COUNT -eq 0 ]; then
    echo "Checking for Spring JARs..."
    echo "Warning: No JAR files found in lib folder"
    echo ""
    echo "Please download the following JARs and place them in lib folder:"
    echo "- spring-core-6.1.0.jar"
    echo "- spring-beans-6.1.0.jar"
    echo "- spring-context-6.1.0.jar"
    echo "- spring-aop-6.1.0.jar"
    echo "- spring-jcl-6.1.0.jar"
    echo "- commons-logging-1.2.jar"
    echo ""
    echo "Or run: mvn clean compile"
    echo ""
    read -p "Press any key to continue . . ."
    exit 1
fi

echo "Found $JAR_COUNT JAR files"
echo ""

# Build classpath from lib folder
CLASSPATH="target/classes"
for jar in "$LIB_DIR"/*.jar; do
    CLASSPATH="$CLASSPATH:$jar"
done

# Compile Java source files
echo "======================================"
echo "COMPILING PROJECT"
echo "======================================"
echo ""

mkdir -p target/classes

# Compile all Java files
javac -d target/classes -cp "$CLASSPATH" $(find src/main/java -name "*.java") 2>&1

if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi

echo "Compilation successful"
echo ""

# Copy resources
echo "Copying resources..."
if [ -d "src/main/resources" ]; then
    cp -r src/main/resources/* target/classes/ 2>/dev/null
fi
echo ""

# Run XML Configuration Demo
echo "======================================"
echo "RUNNING XML CONFIGURATION DEMO"
echo "======================================"
echo ""

java -cp "$CLASSPATH" com.example.XMLConfigMain
if [ $? -ne 0 ]; then
    echo "XML Configuration demo failed"
fi

echo ""
echo "XML demo completed"
echo ""

# Run Annotation Configuration Demo
echo "======================================"
echo "RUNNING ANNOTATION CONFIGURATION DEMO"
echo "======================================"
echo ""

java -cp "$CLASSPATH" com.example.AnnotationConfigMain
if [ $? -ne 0 ]; then
    echo "Annotation Configuration demo failed"
fi

echo ""
echo "Annotation demo completed"
echo ""

echo "All done"
read -p "Press any key to continue . . ."
