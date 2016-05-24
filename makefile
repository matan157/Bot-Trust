JAVAFILES = Main.java Frame.java Panel.java Bot.java InstructionPair.java
CLASSES = Main.class Frame.class Panel.class Bot.class InstructionPair.class

Main.class: $(JAVAFILES)
	javac Main.java

Frame.class: Frame.java Panel.class
	javac Frame.java

Panel.class: Panel.java
	javac Panel.java

Bot.class: Bot.java InstructionPair.class
	javac Bot.java

InstructionPair.class: InstructionPair.java
	javac InstructionPair.java

clean:
	rm $(CLASSES)

run:
	java Main < A-small-practice.in
