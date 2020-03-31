simpletest:
	./byacc -Jclass=Parser nanoMorpho.byaccj
	java -jar jflex-full-1.7.0.jar nanoMorpho.jflex
	javac Lexer.java Parser.java ParserVal.java
	@echo ""
	@echo ""
	java Parser test.s

Parser.class Lexer.class ParserVal.class: Parser.java ParserVal.java
	@echo "-----"
	@echo "Compiling to java"
	javac Parser.java Lexer.java ParserVal.java

clear:
	clear

NanoMorpho.java:
	@echo "Result from JFlex: "
	java -jar jflex-full-1.7.0.jar nanoMorpho.jflex

Parser.java ParserVal.java: NanoMorpho.java
	@echo "-----"
	@echo "Result from BYACC/J"
	./byacc -Jclass=Parser nanoMorpho.byaccj

test: Parser.class Lexer.class ParserVal.class
	@echo "-----"
	@echo "Parser testing initiated"
	java Parser test_success.s

Generate_morpho: Parser.class Lexer.class ParserVal.class
	@echo "-----"
	@echo "Using test file to generate Morpho"
	java Parser test_success.s | java -jar morpho.jar -c

Test_morpho_generated_code: Generate_morpho
	@echo "-----"
	@echo "Executing newly generated Morpho code"
	java -jar morpho.jar test_success

clean:
	rm -rf *.class *~ *.mexe *.bak yacc.* *.java
