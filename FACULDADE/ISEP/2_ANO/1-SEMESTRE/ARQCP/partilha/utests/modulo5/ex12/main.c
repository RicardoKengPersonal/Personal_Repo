#include <string.h> 
#include <stdlib.h> 
#include "../../unity.h"
#include "asm.h" 
#include "dmt2.h" 


int call_func(int (*f)(short**, int, int,short),  short ** p1 , int p2, int p3, short p4);



void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(  int rows, int columns, short to_search, int expected  )
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

	   result=call_func(find_matrix,m,rows,columns,to_search); 

	   TEST_ASSERT_EQUAL_INT(expected, result); 
	   for(j = 0; j < rows ; j++)
		   free(m[j]);
	   free(m);



}


void test_One()
{ 
    run_test(1,1,1,1); 
}
void test_OneB()
{ 
    run_test(1,1,0,0); 
}
void test_Two()
{ 
    run_test(2,2,4,1); 
}
void test_TwoB()
{ 
    run_test(2,2,-1,0); 
}
void test_Twenty()
{ 
    run_test(20,20,5000,0); 
}
void test_TwoHundred()
{ 
    run_test(4,200,301,1); 
}


int main()
  { 
    UNITY_BEGIN();
    RUN_TEST(test_One);
    RUN_TEST(test_OneB);
    RUN_TEST(test_Two);
    RUN_TEST(test_TwoB);
    RUN_TEST(test_Twenty);
    RUN_TEST(test_TwoHundred);
    return UNITY_END();  

  } 






