CC = gcc
CFLAGS = -Wall -Wextra -O2

all: ex06

ex05: ex05.c
	$(CC) $(CFLAGS) -o ex06 ex06.c -lrt

run: ex06
	./ex06

clean:
	rm -f ex06

.PHONY: all run clean