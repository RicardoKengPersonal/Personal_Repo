ex10: size_string.o main.o
		gcc size_string.o main.o -o ex10

size_string.o: size_string.c size_string.h
		gcc -Wall -Wextra -fanalyzer -c size_string.c -o size_string.o
		
main.o: main.c size_string.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex10

run: ex10
		./ex10
