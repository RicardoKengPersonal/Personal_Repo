#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


long call_func ( long (*f)(int num), int num );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(long x, long expected )
{
    long  res; 

    

    // setup 
	res = call_func(sum2_n,x);
    
    TEST_ASSERT_EQUAL_INT64(expected,res); 
    
}


void test_Zero()
{ 
    run_test(0,0); 
}
void test_One()
{ 
    run_test(1,1); 
}
void test_MinusOne()
{ 
    run_test(-1,0); 
}
void test_Two()
{ 
    run_test(2,9); 
}

void test_Three()
{ 
    run_test(3,36); 
}
void test_Five()
{ 
    run_test(5,225); 
}
void test_Six()
{ 
    run_test(6666,493777774074321); 
}
void test_Seven()
{ 
    run_test(100000,6553755928790448384); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_MinusOne);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    RUN_TEST(test_Five);
    RUN_TEST(test_Six);
    RUN_TEST(test_Seven);
    return UNITY_END();  

  } 






