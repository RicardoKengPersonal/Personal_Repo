ex12: array_sort.o main.o
		gcc array_sort.o main.o -o ex12

array_sort.o: array_sort.c array_sort.h
		gcc -Wall -Wextra -fanalyzer -c array_sort.c -o array_sort.o
		
main.o: main.c array_sort.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex12

run: ex12
		./ex12
