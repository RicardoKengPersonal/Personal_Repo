ex06: sum_even.o main.o
		gcc sum_even.o main.o -o ex06

sum_even.o: sum_even.c sum_even.h
		gcc -Wall -Wextra -fanalyzer -c sum_even.c -o sum_even.o
		
main.o: main.c sum_even.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex06

run: ex06
		./ex06
