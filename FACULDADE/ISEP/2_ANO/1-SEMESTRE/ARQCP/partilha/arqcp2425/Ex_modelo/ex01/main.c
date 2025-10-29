#include <stdio.h>
void fn(int *x, int *y) {
	int tmp;
	printf("0x%x, 0x%x\n", *x,*y);
	printf("%p, %p\n",x,y);
	tmp = *x;
	*x = *y;
	*y = tmp;
	printf("0x%x, 0x%x\n", *x, *y);
	printf("%p, %p\n",x,y);
}
int main(void) {
	int a = 10, b = 20;
	printf("%p, %p\n",&a,&b);
	printf("0x%x, 0x%x\n",a,b);
	fn(&a,&b);
	printf("0x%x, 0x%x\n",a,b);
	printf("%p, %p\n",&a,&b);
	return 0;
}
