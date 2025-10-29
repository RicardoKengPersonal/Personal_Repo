ex08: main.o fill_s2.o
	gcc -g -Wall main.o fill_s2.o -o ex08
	
main.o: main.c
	gcc -g -Wall -c main.c -o main.o

fill_s2.o: fill_s2.s
	gcc -g -Wall -c fill_s2.s -o fill_s2.o
	
vg: ex08
	valgrind ./ex08
	
clean:
	rm -f *.o ex08
	
run: ex08
	./ex08
