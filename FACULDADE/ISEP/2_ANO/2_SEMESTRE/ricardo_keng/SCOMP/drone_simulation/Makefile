CC = gcc
CFLAGS = -Wall -Wextra -pthread -O2
TARGETS = main drone
OBJS = common.h

all: $(TARGETS)

main: main.c $(OBJS)
	$(CC) $(CFLAGS) main.c -o main

drone: drone.c $(OBJS)
	$(CC) $(CFLAGS) drone.c -o drone

clean:
	rm -f $(TARGETS)
