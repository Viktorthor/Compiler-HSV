test:
	./byacc -Jclass=Parser nanoMorpho.byaccj
	java -jar jflex-full-1.7.0.jar nanoMorpho.jflex
	javac Lexer.java Parser.java ParserVal.java
	@echo ""
	@echo ""
	java Parser test.s
	
	
runtest:
	./byacc -Jclass=Parser nanoMorpho.byaccj
	java -jar jflex-full-1.7.0.jar nanoMorpho.jflex
	javac Lexer.java Parser.java ParserVal.java
	java Parser test.s | java -jar morpho.jar -c
	@echo ""
	@echo ""
	java -jar morpho.jar test


clean:
	rm -rf *.class *~ *.mexe *.bak yacc.* *.java
