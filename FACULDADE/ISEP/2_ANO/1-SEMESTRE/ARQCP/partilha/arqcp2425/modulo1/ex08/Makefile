ex08: sum_integer_bytes.o main.o
		gcc sum_integer_bytes.o main.o -o ex08

sum_integer_bytes.o: sum_integer_bytes.c sum_integer_bytes.h
		gcc -Wall -Wextra -fanalyzer -c sum_integer_bytes.c -o sum_integer_bytes.o
		
main.o: main.c sum_integer_bytes.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex08

run: ex08
		./ex08
