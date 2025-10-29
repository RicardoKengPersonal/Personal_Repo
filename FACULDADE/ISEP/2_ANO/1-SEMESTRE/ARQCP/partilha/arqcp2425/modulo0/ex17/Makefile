ex17: check_string.o main.o
		gcc check_string.o main.o -o ex17

check_string.o: check_string.c check_string.h
		gcc -Wall -Wextra -fanalyzer -c check_string.c -o check_string.o
		
main.o: main.c check_string.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex17

run: ex17
		./ex17
