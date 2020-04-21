# Þýðandi fyrir NanoMorpho

## Högni Freyr Gunnarsson, Son Van Nguyen, Viktor Þór Freysson


#### Notkun og uppsetning
* Til að keyra og þýða forrit skrifuð í NanoMorpho er mælt með að sækja allar skrár hér að ofan. Í möppunni er að finna skrá "test.s" og inniheldur skráin keyrsluhæft prufuforrit skrifað í NanoMorpho. Sömuleiðis er dæmi um kóðann á lokablaðsíðu handbókarinnar. Hægt er að þýða og keyra skrána á tvennskonar hátt.
    * java -jar jflex-full-1.7.0.jar nanoMorpho.jflex
    * javac Lexer.java Parser.java ParserVal.java
    * java Parser test.s | java -jar morpho.jar -c
    * java -jar morpho.jar test

* Einnig er hægt að nýta sér makefile skrána til að þýða og/eða keyra "test.s" skrána með eftirfarandi skipun.
    * make runsimpletest

### Um NanoMorpho
Nanomorpho er einfalt forritunarmál sem tekur eftir Morpho málinu.  Það er bálkmótað hvað föll varðarog styður bara staðværar breytur.  Þar sem Nanomorpho er byggt á Morpho og Morpho er byggt ofan áJava virkar Nanomorpho sem minni og léttari útgáfa af Morpho. Ekki eru allir sömu möguleikar til staðar íNanomorpho miðað við Morpho. Nanomorpho keyrir á Morpho sýndarvélinni.

### Um verkefnið
Þetta verkefni var gert yfir vorönn 2020 við Háskóla Íslands í námskeiðinu "Þýðendur" þar sem Prófessor Snorri Agnarsson, höfundur Morpho, kennir námskeiðið.


### Höfundar :
Högni Freyr Gunnarsson, Son Van Nguyen, Viktor Þór Freysson
hfg7@hi.is               svg5@hi.is     vthf1@hi.is
