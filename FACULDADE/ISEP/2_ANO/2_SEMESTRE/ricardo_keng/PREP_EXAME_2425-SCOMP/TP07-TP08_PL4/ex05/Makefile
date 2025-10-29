CC = gcc
CFLAGS = -Wall -Wextra -O2

all: ex05

ex05: ex05.c
	$(CC) $(CFLAGS) -o ex05 ex05.c -lrt

run: ex05
	./ex05

clean:
	rm -f ex05

.PHONY: all run clean