main: asm.o main.o
	gcc -g asm.o main.o -z noexecstack -o main
	
main.o: main.c asm.h
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o

asm.o: asm.s
	gcc -g -Wall -Wextra -fanalyzer -c asm.s -o asm.o

run: main
	./main

clean:
	rm *.o main
