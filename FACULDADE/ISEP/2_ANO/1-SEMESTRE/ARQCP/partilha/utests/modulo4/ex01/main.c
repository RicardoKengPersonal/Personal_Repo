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
	res = call_func(d_square,x);
    
    TEST_ASSERT_EQUAL_INT64(expected,res); 
    
}


void test_Zero()
{ 
    run_test(0,0); 
}
void test_One()
{ 
    run_test(1,2); 
}
void test_MinusOne()
{ 
    run_test(-1,2); 
}
void test_Two()
{ 
    run_test(2,8); 
}

void test_MinusThree()
{ 
    run_test(-3,18); 
}
void test_Five()
{ 
    run_test(5,50); 
}

void test_BIG()
{ 
    run_test(0x70000,420906795008); 
}

void test_BIGm()
{ 
    run_test(-0x70000,420906795008); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_MinusOne);
    RUN_TEST(test_Two);
    RUN_TEST(test_MinusThree);
    RUN_TEST(test_Five);
    RUN_TEST(test_BIG);
    RUN_TEST(test_BIGm);
    return UNITY_END();  

  } 






