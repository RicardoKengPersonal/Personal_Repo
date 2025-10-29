void swap_nums(int* x,int* y)
{
	int tmp;
	tmp = *x;
	*x = *y;
	*y = tmp;
}

void swap_pointers(char** x,char** y) // added '*' to x and y 
{
	char *tmp;
	tmp = *x; //Added * to x
	*x = *y; //Added '*' to x and y
 	*y = tmp; // Added '*' to y
}
