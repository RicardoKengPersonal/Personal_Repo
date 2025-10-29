#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(int x, int y), int x, int y );  

void print_result(char op, int o1, int o2, int res)
   {
     printf("%d %c %d = %d\n", o1, op, o2, res);
   }


void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int x, int y, int expected )
{
    int  res; 

    

    // setup 
	res = call_func(calculate,x,y);
    
    TEST_ASSERT_EQUAL_INT(expected,res); 
    
}


void test_Zero()
{ 
    run_test(0,0,0); 
}
void test_One()
{ 
    run_test(1,-1,3); 
}
void test_OneB()
{ 
    run_test(-1,1,-1); 
}
void test_ThreeFive()
{ 
    run_test(3,5,-17); 
}
void test_FiveThree()
{ 
    run_test(5,3,-13); 
}
void test_Three()
{ 
    run_test(3,3,-9); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_OneB);
    RUN_TEST(test_ThreeFive);
    RUN_TEST(test_FiveThree);
    RUN_TEST(test_Three);
    return UNITY_END();  

  } 






