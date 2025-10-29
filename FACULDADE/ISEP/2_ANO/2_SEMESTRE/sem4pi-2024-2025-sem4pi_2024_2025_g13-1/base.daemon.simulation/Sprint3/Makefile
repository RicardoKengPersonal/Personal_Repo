prog_avg: geradorDrones.o collision.o report.o simulação_drone.o environment.o main.o 
	gcc main.o geradorDrones.o collision.o report.o simulação_drone.o environment.o -z noexecstack -o prog_avg 
	
geradorDrones.o: geradorDrones.c
	gcc -g -Wall -Wextra -fanalyzer -c geradorDrones.c -o geradorDrones.o

collision.o: collision.c
	gcc -g -Wall -Wextra -fanalyzer -c collision.c -o collision.o
	
report.o: report.c
	gcc -g -Wall -Wextra -fanalyzer -c report.c -o report.o

simulação_drone.o: simulação_drone.c 
	gcc -g -Wall -Wextra -fanalyzer -c simulação_drone.c -o simulação_drone.o

environment.o: environment.c
	gcc -g -Wall -Wextra -fanalyzer -c environment.c -o environment.o
	
main.o: main.c asm.h
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o

clean:
	rm -f *.o prog_avg

run: prog_avg
	./prog_avg
