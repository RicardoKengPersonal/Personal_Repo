main: main.o fillStudent.o updateAddress.o
	gcc -Wall -g main.o fillStudent.o updateAddress.o -o main
main.o: main.c fillStudent.h updateAddress.h student.h
	gcc -Wall -g -c main.c -o main.o
updateAddress.o: updateAddress.s student.h
	gcc -Wall -g -c updateAddress.s -o updateAddress.o
fillStudent.o: fillStudent.c student.h
	gcc -Wall -g -c fillStudent.c -o fillStudent.o	
run: main
	./main
clean :
	rm ∗.o main
