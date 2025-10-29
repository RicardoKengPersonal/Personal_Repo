ex05: copy_vec.o main.o
		gcc copy_vec.o main.o -o ex05

copy_vec.o: copy_vec.c copy_vec.h
		gcc -Wall -Wextra -fanalyzer -c copy_vec.c -o copy_vec.o
		
main.o: main.c copy_vec.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex05

run: ex05
		./ex05
