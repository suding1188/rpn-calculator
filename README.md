# Reverse Polish Notation Calculator Documentation

### Release Version
- 1.0.0

### Startup
- dependency : 
	1. java 8 or above installed
	2. put startup script together with rpn-calculator.jar
- script
	1. startup.bat for Windows
	1. startup.sh for Linux
	
### Configuration : application.properties
- RPN.NUMBER.DECIMAL.STORAGR: the decimal places of precision for calculation, default to 15
- RPN.NUMBER.DECIMAL.DISPLAY: the decimal places of precision for display, default to 10

# Development Guide

### Build Script
- dependency : Apache Maven 3.5.2 or above
- build script: mvn clean package -DskipTests
