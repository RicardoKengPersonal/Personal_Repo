#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define DATA_LENGTH 5

void init_data(int *data,int n);
void print_data(int *data, int n);
void init_data_ptrs(int *data, int n, int ptrs);
void print_data_ptrs(int **ptrs, int n);
int * find_max_data_ptrs(int ** ptrs, int n);
void sort_data_ptrs(int** ptrs, int n);

int main(void)
{
	int data[DATA_LENGTH];
	int *data_ptrs[DATA_LENGTH];
	int *res;
	
	init_data(data,DATA_LENGTH);
	print_data(data, DATA_LENGTH);
	init_data_ptrs(data, DATA_LENGTH,data_ptrs);
	print_data_ptrs(data_ptrs, DATA_LENGTH);
	res = find_max_data_ptrs(data_ptrs, DATA_LENGTH);
	printf("0x%x\n",*res);
	sort_data_ptrs(data_ptrs, DATA_LENGTH);
	print_data(data, DATA_LENGTH);
	
	return 0;
}
