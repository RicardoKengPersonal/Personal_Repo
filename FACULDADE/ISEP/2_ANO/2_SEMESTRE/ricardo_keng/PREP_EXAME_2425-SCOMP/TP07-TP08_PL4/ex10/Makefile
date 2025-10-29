CC = gcc
CFLAGS = -Wall -Wextra -O2

all: ex10

ex07: ex10.c
	$(CC) $(CFLAGS) -o ex10 ex10.c -lrt

run: ex10
	./ex10

clean:
	rm -f ex10

.PHONY: all run clean