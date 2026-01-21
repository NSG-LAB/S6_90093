#!/bin/bash
# Spring DI Demo - Build and Run Script for Linux/Mac
# This script compiles and runs the Spring DI project

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Create directories
mkdir -p target/classes lib

# Spring JAR URLs
JAR_URLS=(
    "https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.0/spring-core-6.1.0.jar"
    "https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.0/spring-beans-6.1.0.jar"
    "https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.0/spring-context-6.1.0.jar"
    "https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.0/spring-jcl-6.1.0.jar"
    "https://repo.maven.apache.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar"
)

JAR_FILES=(
    "spring-core-6.1.0.jar"
    "spring-beans-6.1.0.jar"
    "spring-context-6.1.0.jar"
    "spring-jcl-6.1.0.jar"
    "commons-logging-1.2.jar"
)

# Set default action
ACTION="${1:-all}"

echo ""
echo "======================================"
echo "Spring Dependency Injection Demo"
echo "Build & Run Script"
echo "======================================"
echo ""

# Download JARs if needed
echo "Checking for Spring JARs..."
for i in "${!JAR_FILES[@]}"; do
    if [ ! -f "lib/${JAR_FILES[$i]}" ]; then
        echo "Downloading ${JAR_FILES[$i]}..."
        if command -v wget &> /dev/null; then
            wget -q "${JAR_URLS[$i]}" -O "lib/${JAR_FILES[$i]}" || echo "Failed to download with wget"
        elif command -v curl &> /dev/null; then
            curl -s -o "lib/${JAR_FILES[$i]}" "${JAR_URLS[$i]}" || echo "Failed to download with curl"
        else
            echo "Warning: Neither wget nor curl found. Please download manually."
        fi
    fi
done

# Build classpath
CLASSPATH=""
for jar in lib/*.jar; do
    if [ -n "$CLASSPATH" ]; then
        CLASSPATH="$CLASSPATH:$jar"
    else
        CLASSPATH="$jar"
    fi
done

if [ -z "$CLASSPATH" ]; then
    echo -e "${RED}✗ No JAR files found in lib directory${NC}"
    echo "Please download Spring JARs to lib/ folder"
    exit 1
fi

# Functions
compile_project() {
    echo ""
    echo "======================================"
    echo "COMPILING PROJECT"
    echo "======================================"
    echo ""
    
    javac -cp "$CLASSPATH" -d target/classes \
        src/main/java/com/example/Student.java \
        src/main/java/com/example/ApplicationConfig.java \
        src/main/java/com/example/MainXML.java \
        src/main/java/com/example/MainAnnotation.java
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ Compilation successful!${NC}"
        return 0
    else
        echo -e "${RED}✗ Compilation failed!${NC}"
        return 1
    fi
}

run_xml_demo() {
    echo ""
    echo "======================================"
    echo "RUNNING XML CONFIGURATION DEMO"
    echo "======================================"
    echo ""
    
    java -cp "target/classes:$CLASSPATH" com.example.MainXML
    
    if [ $? -eq 0 ]; then
        echo ""
        echo -e "${GREEN}✓ XML demo completed successfully${NC}"
    else
        echo ""
        echo -e "${RED}✗ XML demo failed${NC}"
        return 1
    fi
}

run_annotation_demo() {
    echo ""
    echo "======================================"
    echo "RUNNING ANNOTATION CONFIGURATION DEMO"
    echo "======================================"
    echo ""
    
    java -cp "target/classes:$CLASSPATH" com.example.MainAnnotation
    
    if [ $? -eq 0 ]; then
        echo ""
        echo -e "${GREEN}✓ Annotation demo completed successfully${NC}"
    else
        echo ""
        echo -e "${RED}✗ Annotation demo failed${NC}"
        return 1
    fi
}

# Main execution
case $ACTION in
    build)
        compile_project
        ;;
    run-xml)
        if compile_project; then
            run_xml_demo
        fi
        ;;
    run-annotation)
        if compile_project; then
            run_annotation_demo
        fi
        ;;
    all)
        if compile_project; then
            run_xml_demo
            run_annotation_demo
        fi
        ;;
    *)
        echo "Usage:"
        echo "  ./build.sh build              # Compile only"
        echo "  ./build.sh run-xml            # Compile and run XML demo"
        echo "  ./build.sh run-annotation     # Compile and run Annotation demo"
        echo "  ./build.sh all                # Compile and run both demos"
        exit 0
        ;;
esac

echo ""
echo "Done!"
