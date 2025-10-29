program: ui.o machManager.o format_command.o get_number_binary.o extract_data.o median.o sort_array.o
	gcc ui.o machManager.o format_command.o get_number_binary.o extract_data.o median.o sort_array.o -o program

ui.o: ui.c machManager.h file.h
	gcc -g -Wall -Wextra -fanalyzer -c ui.c -o ui.o

machManager.o: machManager.c machManager.h file.h
	gcc -g -Wall -Wextra -fanalyzer -c machManager.c -o machManager.o

format_command.o: format_command.s
	as -g format_command.s -o format_command.o

get_number_binary.o: get_number_binary.s
	as -g get_number_binary.s -o get_number_binary.o

extract_data.o: extract_data.s
	as -g extract_data.s -o extract_data.o

median.o: median.s
	as -g median.s -o median.o
	
sort_array.o: sort_array.s
	as -g sort_array.s -o sort_array.o
	
clean:
	rm -f *.o program

run: program
	./program
