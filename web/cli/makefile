JC = javac

all: Prompter.class Entry.class Star.class Circle.class Plus.class

Prompter.class: Prompter.java
	$(JC) -d classes Prompter.java

Entry.class: Entry.java
	$(JC) -d classes Entry.java

Star.class: Star.java
	$(JC) -d classes Star.java

Circle.class: Circle.java
	$(JC) -d classes Circle.java

Plus.class: Plus.java
	$(JC) -d classes Plus.java

run:
	java -cp classes/ Prompter

clean:
	rm -r classes/*

rebuild:
	make clean
	make

