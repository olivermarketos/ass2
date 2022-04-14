
# makefile for assignment 1
# Oliver Marketos
# 6 September 2021

JAVAC=/usr/bin/javac 	#flag variables
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
JAVADOC= Javadoc
V1 =
V2 =
V3 =

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=WordDictionary.class Score.class WordRecord.class  WordPanel.class WordAppView.class WordAppModel.class WordAppController.class WordApp.class 
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class $(SRCDIR)/*.class

run: $(CLASS_FILES)
	java -cp $(BINDIR) WordApp ${V1} ${V2} ${V3}

doc: 
	javadoc -d $(JAVADOC) $(SRCDIR)/*.java

