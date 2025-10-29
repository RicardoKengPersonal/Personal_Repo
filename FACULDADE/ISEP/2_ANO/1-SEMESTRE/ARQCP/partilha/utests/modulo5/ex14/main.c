#include <string.h> 
#include <stdlib.h> 
#include "../../unity.h"
#include "asm.h" 
#include "dmt2.h" 


short ** call_func(short ** (*f)(short**, short**, int, int),short ** p1 , short ** p2, int p3,int p4);



void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int rows, int columns )
{
    // setup 
	short **m;

	m=new_matrix(rows,columns);
    short **result;
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

	   result=call_func(add_matrixes,m,m,rows,columns);
	   for (i=0;i<rows;i++)
		for (j=0;j<columns;j++)
              {
		TEST_ASSERT_EQUAL_INT((2*m[i][j]),(result[i][j]));
	      }

	   for(j = 0; j < rows ; j++)
		   free(m[j]);
	   free(m);

	   for(j = 0; j < rows ; j++)
		   free(result[j]);
	   free(result);



}


void test_One()
{ 
    run_test(1,1); 
}
void test_Two()
{ 
    run_test(2,2); 
}
void test_Three()
{ 
    run_test(3,35); 
}
void test_Five()
{ 
    run_test(5,100); 
}
void test_Ten()
{ 
    run_test(10,500); 
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






