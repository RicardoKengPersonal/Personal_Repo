#include <stdio.h>
#include <stdlib>


int checkMathParenthesis(char exp[])

{
	int i;
	char car,par;
	char par_abre[] = "[(";
	char par_fecha[] = ")]";
	
	for (i=0;exp[i] !='\0';i++)
	{
		car = exp[i];
		if (findChar(par_abre,car)!= -1)
		    push(car);
	}	
	
}
