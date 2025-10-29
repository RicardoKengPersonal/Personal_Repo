ex14: count_value.o main.o
		gcc count_value.o main.o -o ex14

count_value.o: count_value.c count_value.h
		gcc -Wall -Wextra -fanalyzer -c count_value.c -o count_value.o
		
main.o: main.c count_value.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex14

run: ex14
		./ex14
