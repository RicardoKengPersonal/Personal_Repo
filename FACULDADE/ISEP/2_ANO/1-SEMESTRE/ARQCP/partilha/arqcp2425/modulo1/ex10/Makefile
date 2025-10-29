ex10: where_is.o main.o
		gcc where_is.o main.o -o ex10

where_is.o: where_is.c where_is.h
		gcc -Wall -Wextra -fanalyzer -c where_is.c -o where_is.o
		
main.o: main.c where_is.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex10

run: ex10
		./ex10
