ex04: capitalize.o main.o
		gcc capitalize.o main.o -o ex04

capitalize.o: capitalize.c capitalize.h
		gcc -Wall -Wextra -fanalyzer -c capitalize.c -o capitalize.o
		
main.o: main.c capitalize.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex04

run: ex04
		./ex04
