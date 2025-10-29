ex02: fn2.o main.o
		gcc fn2.o main.o -o ex02

fn2.o: fn2.c fn2.h
		gcc -Wall -Wextra -fanalyzer -c fn2.c -o fn2.o
		
main.o: main.c fn2.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex02

run: ex02
		./ex02
