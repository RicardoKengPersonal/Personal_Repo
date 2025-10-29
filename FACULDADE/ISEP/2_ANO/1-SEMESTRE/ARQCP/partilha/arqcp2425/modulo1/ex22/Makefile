ex22: swap_pointers.o main.o
		gcc swap_pointers.o main.o -o ex22

swap_pointers.o: swap_pointers.c swap_pointers.h
		gcc -Wall -Wextra -fanalyzer -c swap_pointers.c -o swap_pointers.o
		
main.o: main.c swap_pointers.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex22

run: ex22
		./ex22
