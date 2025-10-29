#include <string.h> 
#include <stdlib.h> 
#include "../../unity.h"
#include "asm.h" 
#include "dmt2.h" 


int call_func(int (*f)(short**, int, int), short ** p1 , int p2, int p3); 


void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(  int rows, int columns, int expected  )
{
    // setup 
	short **m;
	int result;  

	m=new_matrix(rows,columns);
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

	   result=call_func(count_odd_matrix,m,rows,columns);

	   TEST_ASSERT_EQUAL_INT(expected, result); 
	   for(j = 0; j < rows ; j++)
		   free(m[j]);
	   free(m);



}


void test_One()
{ 
    run_test(1,1,1); 
}
void test_Two()
{ 
    run_test(2,2,2); 
}
void test_Three()
{ 
    run_test(3,3,5); 
}
void test_Five()
{ 
    run_test(5,100,250); 
}
void test_Ten()
{ 
    run_test(10,10,50); 
}


int main()
  { 
    UNITY_BEGIN();
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    RUN_TEST(test_Five);
    RUN_TEST(test_Ten);
    return UNITY_END();  

  } 






