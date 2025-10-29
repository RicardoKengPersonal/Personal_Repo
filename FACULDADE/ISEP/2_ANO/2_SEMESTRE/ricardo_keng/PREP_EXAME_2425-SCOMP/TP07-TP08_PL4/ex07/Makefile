CC = gcc
CFLAGS = -Wall -Wextra -O2

all: ex07

ex07: ex07.c
	$(CC) $(CFLAGS) -o ex07 ex07.c -lrt

run: ex07
	./ex07

clean:
	rm -f ex07

.PHONY: all run clean