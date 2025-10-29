ex18: populate.o check.o inc_nsets.o main.o
		gcc populate.o check.o inc_nsets.o main.o -o ex18

populate.o: populate.c populate.h
		gcc -Wall -Wextra -fanalyzer -c populate.c -o populate.o
		
check.o: check.c check.h
		gcc -Wall -Wextra -fanalyzer -c check.c -o check.o
		
inc_nsets.o: inc_nsets.c inc_nsets.h
		gcc -Wall -Wextra -fanalyzer -c inc_nsets.c -o inc_nets.o

main.o: main.c populate.h check.h inc_nsets.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex18

run: ex18
		./ex18
