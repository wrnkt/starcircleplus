JC = javac

all: DBEntryFormatter.class DBConfig.class DBSetup.class DBTableSetup.class DBTagManager.class DBEntryManager.class Prompter.class Entry.class

PrompterEntryFormatter.class: PrompterEntryFormatter.java
	$(JC) -d classes PrompterEntryFormatter.java

DBTagManager.class: DBTagManager.java
	$(JC) -d classes DBTagManager.java

DBConfig.class: DBConfig.java
	$(JC) -d classes DBConfig.java

DBSetup.class: DBSetup.java
	$(JC) -d classes DBSetup.java

DBTableSetup.class: DBTableSetup.java
	$(JC) -d classes DBTableSetup.java

DBEntryFormatter.class: DBEntryFormatter.java
	$(JC) -d classes DBEntryFormatter.java

DBEntryManager.class: DBEntryManager.java
	$(JC) -d classes DBEntryManager.java

Prompter.class: Prompter.java
	$(JC) -d classes Prompter.java

Entry.class: Entry.java
	$(JC) -d classes Entry.java

run:
	java -cp ./mysql-connector-j-8.0.31.jar:./classes Prompter

clean:
	rm -r classes/*

rebuild:
	make clean
	make

