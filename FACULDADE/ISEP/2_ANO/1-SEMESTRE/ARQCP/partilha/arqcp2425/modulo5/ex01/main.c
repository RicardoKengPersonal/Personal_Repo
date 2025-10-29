#include <stdio.h>


union union_u1{
	char vec[32];
	float a;
	int b;
	
	
}u;

struct struct_s1{
	char vec[32];
	float a;
	int b;
	
}s;


int main(){
	printf("Size of union_u1: %lu bytes\n", sizeof(u));	//The size will be 32 which correspondes to the largest element in u (char[32])
	printf("Size of struct_s1: %lu bytes\n", sizeof(s));	//The size will be 40, because it correspondes to the sum of all elements (32+4+4=40)
	
	
	return 0;
}
