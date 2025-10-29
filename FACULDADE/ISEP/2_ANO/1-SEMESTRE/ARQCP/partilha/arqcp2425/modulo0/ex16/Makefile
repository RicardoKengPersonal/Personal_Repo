ex16: fake_hash.o main.o
		gcc fake_hash.o main.o -o ex16

fake_hash.o: fake_hash.c fake_hash.h
		gcc -Wall -Wextra -fanalyzer -c fake_hash.c -o fake_hash.o
		
main.o: main.c fake_hash.h
		gcc -Wall -Wextra -fanalyzer -c main.c -o main.o
		
clean:
		rm -f *.o ex16

run: ex16
		./ex16
