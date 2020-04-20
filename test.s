test()
{
	var a,b;
	a = 2+3;
	b = "HelloWorld";
	if(a == 5) {
		writeln(a); 
		writeln(b);
	};

	if(a != 5) {
		writeln("if a is != 5");
	}
	elsif (a < 5) {
		writeln("else if a is < 5");
	}
	else {
		writeln("else a is >= 5");
	};

	return "Return value is " ++ 1;
}
test2(x, y)
{
	var i;
	i = 0;
	while(i != 5){
		i = i + 1;
	};

	if(i == 5) {
		writeln("x equals " ++ x ++ " and y equals " ++ y);
	};
}
main()
{
	var i;
	writeln(test());
	test2(2, 5);
	i = 0;
	while(i <= 3) {
		writeln("while loop " ++ i);
		i = i+1;
	};
}