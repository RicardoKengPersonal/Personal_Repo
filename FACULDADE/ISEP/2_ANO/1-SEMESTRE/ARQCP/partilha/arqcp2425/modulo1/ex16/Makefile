ex16: format_word.o main.o
		gcc format_word.o main.o -o ex16

format_word.o: format_word.c format_word.h
		gcc -Wall -Wextra -fanalyzer -c format_word.c -o format_word.o
		
main.o: main.c format_word.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex16

run: ex16
		./ex16
