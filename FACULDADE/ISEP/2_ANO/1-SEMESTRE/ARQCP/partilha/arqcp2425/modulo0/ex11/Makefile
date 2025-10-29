ex11: string_to_int.o main.o
		gcc string_to_int.o main.o -o ex11

string_to_int.o: string_to_int.c string_to_int.h
		gcc -Wall -Wextra -fanalyzer -c string_to_int.c -o string_to_int.o
		
main.o: main.c string_to_int.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex11

run: ex11
		./ex11
