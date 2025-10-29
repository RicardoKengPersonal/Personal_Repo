ex16: main.o approved_semester.o
	gcc -g -Wall main.o approved_semester.o -o ex16
	
main.o: main.c
	gcc -g -Wall -c main.c -o main.o

approved_semester.o: approved_semester.s
	gcc -g -Wall -c approved_semester.s -o approved_semester.o
	
vg: ex16
	valgrind ./ex16
	
clean:
	rm -f *.o ex16
	
run: ex16
	./ex16
