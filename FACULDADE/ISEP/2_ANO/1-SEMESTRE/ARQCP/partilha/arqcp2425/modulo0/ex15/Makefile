ex15: count_words.o main.o
		gcc count_words.o main.o -o ex15

count_words.o: count_words.c count_words.h
		gcc -Wall -Wextra -fanalyzer -c count_words.c -o count_words.o
		
main.o: main.c count_words.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex15

run: ex15
		./ex15
