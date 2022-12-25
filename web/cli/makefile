JC = javac

all: EntryFormatter.class DBConfig.class DBSetup.class DBEntryManager.class Prompter.class Entry.class Star.class Circle.class Plus.class

DBConfig.class: DBConfig.java
	$(JC) -d classes DBConfig.java

DBSetup.class: DBSetup.java
	$(JC) -d classes DBSetup.java

EntryFormatter.class: EntryFormatter.java
	$(JC) -d classes EntryFormatter.java

DBEntryManager.class: DBEntryManager.java
	$(JC) -d classes DBEntryManager.java

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
	java -cp ./mysql-connector-j-8.0.31.jar:./classes DBSetup

clean:
	rm -r classes/*

rebuild:
	make clean
	make

