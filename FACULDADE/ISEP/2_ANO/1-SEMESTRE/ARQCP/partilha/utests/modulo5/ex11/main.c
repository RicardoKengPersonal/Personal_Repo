#include <string.h> 
#include <stdlib.h> 
#include "../../unity.h"
#include "asm.h" 
#include "dmt2.h" 


short ** call_func(short**  (*f)(int , int ), int  p1 , int p2); 



void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(  int rows , int columns  )
{
    // setup 
	short **m;

	m=call_func(new_matrix,rows,columns);
	int pok=dmt_has(m);
	int psize=dmt_size(m);
	TEST_ASSERT_MESSAGE(pok,"Pointer not in Dynamic Memory");
        TEST_ASSERT_EQUAL_INT(rows*sizeof(short*), psize);

	   int i,j;
	   for (i=0;i<rows;i++)
		for (j=0;j<columns;j++)
			m[i][j]=1+i*columns+j;
	   int sum=0;
	   for (i=0;i<rows;i++)
		for (j=0;j<columns;j++)
			sum+=m[i][j];
	   TEST_ASSERT_EQUAL_INT((rows*columns+1)*(rows*columns)/2, sum);
	   for(j = 0; j < rows ; j++)
		   free(m[j]);
	   free(m);



}


void test_One()
{ 
    run_test(1,1); 
}
void test_Two()
{ 
    run_test(2,2); 
}
void test_Twenty()
{ 
    run_test(20,20); 
}
void test_TwoHundred()
{ 
    run_test(4,200); 
}


int main()
  { 
    UNITY_BEGIN();
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Twenty);
    RUN_TEST(test_TwoHundred);
    return UNITY_END();  

  } 






