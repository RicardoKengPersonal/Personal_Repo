ex07: capitalize2.o main.o
		gcc capitalize2.o main.o -o ex07

capitalize2.o: capitalize2.c capitalize2.h
		gcc -Wall -Wextra -fanalyzer -c capitalize2.c -o capitalize2.o
		
main.o: main.c capitalize2.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex07

run: ex07
		./ex07
