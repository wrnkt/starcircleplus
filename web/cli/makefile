JC = javac

all: TimelineManager.class Entry.class Star.class Circle.class Plus.class

TimelineManager.class: TimelineManager.java
	$(JC) -d classes TimelineManager.java

Entry.class: Entry.java
	$(JC) -d classes Entry.java

Star.class: Star.java
	$(JC) -d classes Star.java

Circle.class: Circle.java
	$(JC) -d classes Circle.java

Plus.class: Plus.java
	$(JC) -d classes Plus.java

run:
	java -cp classes/ TimelineManager

clean:
	rm -r classes/*

rebuild:
	make clean
	make

