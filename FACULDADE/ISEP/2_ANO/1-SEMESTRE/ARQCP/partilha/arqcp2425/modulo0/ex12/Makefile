ex12: integer_part.o main.o
		gcc integer_part.o main.o -o ex12

intger_part.o: integer_part.c integer_part.h
		gcc -Wall -Wextra -fanalyzer -c integer_part.c -o integer_part.o
		
main.o: main.c integer_part.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex12

run: ex12
		./ex12
