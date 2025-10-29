main: main.o sigma.o
		gcc main.o sigma.o -z noexecstack -o main
		
main.o: main.c sigma.h
		gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o
		
sigma.o: sigma.s
		gcc -g -Wall -Wextra -fanalyzer -c sigma.s -o sigma.o
		
run: main
		./main
	
clean:
		rm *.o main
