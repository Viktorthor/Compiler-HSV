NanoMorphoParser.class NanoMorphoLexer.class NanoMorphoParserVal.class: NanoMorphoParser.java NanoMorphoParserVal.java
	@echo "-----"
	@echo "Compiling to java"
	javac NanoMorphoParser.java NanoMorphoLexer.java NanoMorphoParserVal.java

clear:
	clear

NanoMorpho.java:
	@echo "Result from JFlex: "
	java -jar jflex-full-1.7.0.jar nanoMorpho.jflex

NanoMorphoParser.java NanoMorphoParserVal.java: NanoMorpho.java
	@echo "-----"
	@echo "Result from BYACC/J"
	./byacc -Jclass=NanoMorphoParser nanoMorpho.byaccj

test: NanoMorphoParser.class NanoMorphoLexer.class NanoMorphoParserVal.class
	@echo "-----"
	@echo "Parser testing initiated"
	java NanoMorphoParser test_success.s

Generate_morpho: NanoMorphoParser.class NanoMorphoLexer.class NanoMorphoParserVal.class
	@echo "-----"
	@echo "Using test file to generate Morpho"
	java NanoMorphoParser test_success.s | java -jar morpho.jar -c

Test_morpho_generated_code: Generate_morpho
	@echo "-----"
	@echo "Executing newly generated Morpho code"
	java -jar morpho.jar test_success

clean:
	rm -rf *.class *~ *.mexe *.bak yacc.* *.java
