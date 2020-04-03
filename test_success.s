// Bunch of tests that were obtained from the morpho workbench
// Note : since this is nanoMorpho some things had to be modified to work here

// TESTS :
// - variable assignment (in different orders)
// - printing out string variables
// - changing the value of an already assigned variable
// - String concatenation of strings and string variables
stringTests()
{
	var x,y;
	y = " Hi Back at you";
	x = "Hello world";
	writeln(x);
	x = "Hello again";
	writeln(x);

	writeln("ABC"++"DEF");
	writeln(x++y);
	newLine();
}

// TESTS :
// - variable assignment
// - Adding integers and variables
// - concatenation of string and integers
// - floating point math
mathTests()
{
	var a,b,c;
	a = 1;
	b = 2;
	c = 3;
	writeln("Adding integers " ++ (1+2+3));
	writeln("Adding variables " ++ (a+b+c));
	writeln(1.0+2.0+3.0);
	writeln(1.0+2.0+3.0+1.0/3.0);
	newLine();
}

// TESTS :
// - variable assignment
// - creating list with integers and null
// - creating list with integers
// - creating list with strings
// - creating list from from variable values
// - creating list from functions that return values
listTests()
{
	var x,y,z,m;
	y = 1;
	x = 2;
	writeln(1:2+3*(4+5):null);
	writeln(1:null);
	writeln(1:2:3);
	writeln(y:2);
	writeln(y:x);
	writeln(head(y:x));
	writeln(tail(y:x));
	writeln(head(y:x):tail(y:x));
	z = "a";
	m = "b";
	writeln("a":"b");
	writeln(z:m);
	writeln(head(z:m));
	writeln(tail(z:m));
	writeln(head(z:m):tail(z:m));
	newLine();
}

// TESTS :
// - reciving and using arguments
// - condional flows
// - body expressions inside IF statements
conditionalTests(first,second)
{
	writeln("conditionalTests called with first="++first++" second="++ second);
	if(first)
	{
		if(second)
		{
			writeln("result: first="++first++" second="++ second);
		}
		else
		{
			writeln("result: first="++first++" second="++ second);
		};
	}
	else{
		if(second)
		{
			writeln("result: first="++first++" second="++ second);
		}
		else
		{
			writeln("result: first="++first++" second="++ second);
		};
	};

	// Tests IF(expressions) and elsif(expressions)
	if(first == true && second == false){
		writeln("reached when first is true and second is false");
	}
	elsif(first == false && second == true)
	{
		writeln("reached when first is false and second is true");
	}
	else{
		writeln("reached correct.");
	};
	
	newLine();
}

// TESTS :
// - only having if no else
// - having if and elsif's but no else
edgecaseconditionalTests(first)
{
	if(first)
	{
		writeln("if condition with no else is reached");
	};

	if(first)
	{
		writeln("if condition with elsif but no else is reached");
	}
	elsif(!first)
	{
		writeln("elseif condition with no else is reached");
	};

}

// fibo and f are from test.nm from Parser.zip in ugla 
fibo(n)
{
	var i,f1,f2,tmp;
	f1 = 1;
	f2 = 1;
	i = 0;
	while( i!=n )
	{
		tmp = f1+f2;
		f1 = f2;
		f2 = tmp;
		i = i+1;
	};
	newLine();
	f1;
}

f(n)
{
	if( n<2 )
	{
		1;
	}
	else
	{
		f(n-1) + f(n-2);
	};
}

newLine()
{
	writeln("");
}

main()
{
	
	stringTests();
	mathTests();
	listTests();
	conditionalTests(true,true);
	conditionalTests(true,false);
	conditionalTests(false,true);
	conditionalTests(false,false);
	edgecaseconditionalTests(true);
	edgecaseconditionalTests(false);
	writeln("non-recursive fibo(35)="++fibo(35));
	writeln("recursive fibo(35)="++f(35));
}
