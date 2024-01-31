SRC_DIR := src/main/java/aoc2015
TEMPLATE_DIR := template
INPUT_DIR := input
JAVA_FILES := $(wildcard $(SRC_DIR)/day/*.java)
NUM_FILES := $(shell echo $(JAVA_FILES) | wc -w)
NUMBER := $(shell expr $(NUM_FILES) + 1)
FORMATTED_NUMBER := $(shell echo $(NUMBER) | awk '{printf "%02d", $$1}')

new:
	@touch $(INPUT_DIR)/Day$(FORMATTED_NUMBER).txt
	@cp $(TEMPLATE_DIR)/Day.java.template $(SRC_DIR)/day/Day$(FORMATTED_NUMBER).java
	@sed -i 's/number/$(FORMATTED_NUMBER)/g' $(SRC_DIR)/day/Day$(FORMATTED_NUMBER).java

build:
	@cd src/main/java/ && javac aoc2015/Main.java

run:
	@cd src/main/java/ && java aoc2015.Main ${day}

clean:
	@find . -type f -name '*.class' -exec rm -f {} +
