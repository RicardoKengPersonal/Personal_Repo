main: main.o mach_manager.o extract_data.o get_number_binary.o get_number.o format_command.o enqueue_value.o dequeue_value.o get_n_element.o move_n_to_array.o sort_array.o median.o
	gcc main.o mach_manager.o extract_data.o get_number_binary.o get_number.o format_command.o enqueue_value.o dequeue_value.o get_n_element.o move_n_to_array.o sort_array.o median.o -z noexecstack -o main

main.o: main.c asm.h mach_manager.h
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o

mach_manager.o: mach_manager.c mach_manager.h
	gcc -g -Wall -Wextra -fanalyzer -c mach_manager.c -o mach_manager.o

extract_data.o: extract_data.s
	gcc -g -Wall -Wextra -fanalyzer -c extract_data.s -o extract_data.o

get_number_binary.o: get_number_binary.s
	gcc -g -Wall -Wextra -fanalyzer -c get_number_binary.s -o get_number_binary.o

get_number.o: get_number.s
	gcc -g -Wall -Wextra -fanalyzer -c get_number.s -o get_number.o

format_command.o: format_command.s
	gcc -g -Wall -Wextra -fanalyzer -c format_command.s -o format_command.o

enqueue_value.o: enqueue_value.s
	gcc -g -Wall -Wextra -fanalyzer -c enqueue_value.s -o enqueue_value.o

dequeue_value.o: dequeue_value.s
	gcc -g -Wall -Wextra -fanalyzer -c dequeue_value.s -o dequeue_value.o

get_n_element.o: get_n_element.s
	gcc -g -Wall -Wextra -fanalyzer -c get_n_element.s -o get_n_element.o

move_n_to_array.o: move_n_to_array.s
	gcc -g -Wall -Wextra -fanalyzer -c move_n_to_array.s -o move_n_to_array.o

sort_array.o: sort_array.s
	gcc -g -Wall -Wextra -fanalyzer -c sort_array.s -o sort_array.o

median.o: median.s
	gcc -g -Wall -Wextra -fanalyzer -c median.s -o median.o

run: main
	./main

clean:
	rm -f *.o main