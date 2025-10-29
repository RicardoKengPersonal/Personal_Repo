ex15: trim_string.o main.o
		gcc trim_string.o main.o -o ex15

trim_string.o: trim_string.c trim_string.h
		gcc -Wall -Wextra -fanalyzer -c trim_string.c -o trim_string.o
		
main.o: main.c trim_string.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex15

run: ex15
		./ex15
