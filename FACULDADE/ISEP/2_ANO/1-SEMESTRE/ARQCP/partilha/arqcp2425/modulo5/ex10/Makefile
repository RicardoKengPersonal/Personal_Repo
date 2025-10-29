ex10: main.o new_str.o 

	gcc -g -Wall main.o new_str.o -o ex10

	

main.o: main.c

	gcc -g -Wall -c main.c -o main.o	

	

new_str.o: new_str.c

	gcc -g -Wall -c new_str.c -o new_str.o

	

vg: ex10

	valgrind ./ex10

clean:

	rm -f *.o ex10

	

run: ex10

	./ex10

