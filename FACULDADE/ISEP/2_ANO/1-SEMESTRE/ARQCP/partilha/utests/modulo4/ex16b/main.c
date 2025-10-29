#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


long call_func ( long (*f)(long x, long y, char z), long x, long y, char z);  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(long x, long y, char z, long expected )
{
    long  res; 

    

    // setup 
	res = call_func(join_bits,x,y,z);
    
    TEST_ASSERT_EQUAL_INT64(expected,res); 
    
}


void test_One()
{ 
    run_test(0,-1,8,0xfffffffffffffe00); 
}
void test_Two()
{ 
    run_test(-1,0,8,0x1ff); 
}
void test_Three()
{ 
    run_test(0,-1,0,0xfffffffffffffffe); 
}
void test_Four()
{ 
    run_test(-1,0,0,1); 
}

void test_Five()
{ 
    run_test(0,-1,63,0); 
}

void test_Six()
{ 
    run_test(-1,0,63,0xffffffffffffffff); 
}

void test_Seven()
{ 
    run_test(0,-1,15,0xffffffffffff0000); 
}

void test_Eight()
{ 
    run_test(-1,0,15,0xffff); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    RUN_TEST(test_Four);
    RUN_TEST(test_Five);
    RUN_TEST(test_Six);
    RUN_TEST(test_Seven);
    RUN_TEST(test_Eight);
    return UNITY_END();  

  } 






