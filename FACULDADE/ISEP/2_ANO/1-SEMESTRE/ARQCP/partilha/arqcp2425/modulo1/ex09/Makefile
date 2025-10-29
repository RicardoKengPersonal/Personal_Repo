ex09: get_array_statistics.o main.o
		gcc get_array_statistics.o main.o -o ex09

get_array_statistics.o: get_array_statistics.c get_array_statistics.h
		gcc -Wall -Wextra -fanalyzer -c get_array_statistics.c -o get_array_statistics.o
		
main.o: main.c get_array_statistics.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex09

run: ex09
		./ex09
