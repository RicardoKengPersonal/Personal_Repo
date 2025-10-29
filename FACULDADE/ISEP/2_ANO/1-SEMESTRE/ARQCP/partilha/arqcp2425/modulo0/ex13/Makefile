ex13: count_char.o main.o
		gcc count_char.o main.o -o ex13

count_char.o: count_char.c count_char.h
		gcc -Wall -Wextra -fanalyzer -c count_char.c -o count_char.o
		
main.o: main.c count_char.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex13

run: ex13
		./ex13
