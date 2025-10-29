ex12: main.o find_matrix.o new_matrix.o
	gcc -g -Wall main.o find_matrix.o new_matrix.o -o ex12
	
main.o: main.c
	gcc -g -Wall -c main.c -o main.o

find_matrix.o: find_matrix.c
	gcc -g -Wall -c find_matrix.c -o find_matrix.o
	
new_matrix.o: new_matrix.c
	gcc -g -Wall -c new_matrix.c -o new_matrix.o

vg: ex12
	valgrind ./ex12
	
clean:
	rm -f *.o ex12
	
run: ex12
	./ex12
