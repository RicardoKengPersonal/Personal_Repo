#include <stdio.h>

int main()
{
	int x = 5;
	int* ptr_x = &x;
	float y = *ptr_x +3;
	
	printf("X value: %d\n",x); //Shows the value stored in x
	printf("Y value: %.2f\n",y); // Shows the value stored in y, it's 8 because y is the value pointed by the ptr_x (it's pointing to the x address) + 3 defined in the declaration
	printf("X address: %p\n",&x); // Shows the hexadecimal value of the address of x because of the usage of '&'
	printf("Y address: %p\n",&y); // Shows the hexadecimal value of the address of y because of the usage of '&'
	printf("ptr_x address: %p\n",&ptr_x); // Shows the hexadecimal value of the address of ptr_x because of the usage of '&'
	printf("ptr_x Value: %p\n",ptr_x); //Shows the value of ptr_x which is the same value as the memory address of x as it is pointing to the memory address of x
	printf("Value pointed by ptr_x: %d\n",*ptr_x); //Shows the value of which it is pointing to, in this case the value of x (shows this value because of '*')
	
	int vec[] = {10,20,30,40};
	int* ptr_vec = vec;
	int z = *ptr_vec;
	int h = *(ptr_vec+3);
	
	printf("\n");
	printf("Value of z: %d\n",z);
	printf("Value of h: %d\n",h);
	printf("Adress of vec: %p\n",&vec);
	printf("Address of ptr_vec: %p\n",&ptr_vec);
	printf("Value of ptr_vec: %ls\n",ptr_vec);
	printf("Value of vec: %ls\n",vec);
	printf("Value of ptr_vec: %d\n",*ptr_vec);
	
	int i;
	for(i = 0; i < 4; i++)
	{
		printf("1: %p,%d\t",&vec[i],vec[i]); // this for cycle will print the address of each array value and value in itself
	}
	printf("\n");
	for(ptr_vec = vec; ptr_vec < vec + 4 ; ptr_vec++) // ptr_vec++ will increment ptr_vec by 1 unit each iteration of the for cycle
	{
		printf("2: %p,%d\t",ptr_vec,*ptr_vec); // this for cycle will print the exact same as the for cycle above as the ptr_vec is pointing to the vec array, ptr_vec will point to the memory address and *ptr_vec will point to the value of each element
	}
	printf("\n");
	for(ptr_vec = vec + 3; ptr_vec >= vec; ptr_vec--) // ptr_vec-- will decrement ptr_vec by 1 unit each iteration of the for cycle
	{
		printf("3: %p,%d\t",ptr_vec,*ptr_vec); // this for cycle will print the same as the for cycle above , the only difference being the order in which it will print (backwards) due to the instructions defined between the brackets of the for cycle
	}
	
	printf("\n");
	ptr_vec = vec;
	printf("4: %p;%d\n",ptr_vec,*ptr_vec);
	a = *ptr_vec++;
	printf("5: %p,%d,%d\n",ptr_vec,*ptr_vec,a);
	ptr_vec = vec;
	a = (*ptr_vec)++;
	printf("6: %p,%d,%\n",ptr_vec,*ptr_vec,a);
	ptr_vec = vec;
	a = *++ptr_vec;
	printf("7: %p,%d,%d\n",ptr_vec,*ptr_vec,a);
	ptr_vec = vec;
	a = ++*ptr_vec;
	printf("8: %p,%d,%d\n",ptr_vec,*ptr_vec,a);
	
	printf("\n");
	for(ptr_vec = vec; ptr_vec < vec + 4; ptr_vec++)
	{
		printf("9: %p,%d\t",ptr_vec,*ptr_vec);
	}
	
	
	printf("\n");
	unsigned int d = 0xAABBCCDD;
	printf("10: %p,%x\t",&d,d);
	printf("\n");
	unsigned char* ptr_d = (unsigned char*)&d;
	unsigned char* p;
	for(p = ptr_d ; p < ptr_d + sizeof(unsigned int);p++)
	{
		printf("11: %p,%x\t",p,(unsigned char)*p);
	}
	return 0;
}
