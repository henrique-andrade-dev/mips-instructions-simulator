INPUT ?= assets/input.txt

build:
	./gradlew jar
clean:
	./gradlew clean
run:
	$(MAKE) build
	java -jar build/libs/mips-instructions-simulator-1.0-SNAPSHOT.jar $(INPUT)

