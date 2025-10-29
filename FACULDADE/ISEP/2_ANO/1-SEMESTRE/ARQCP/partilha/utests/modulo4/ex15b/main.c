#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


long call_func ( long (*f)(long x, char y, char z), long x, char y , char z  );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(long x, char y, char z, long expected )
{
    long  res; 

    

    // setup 
	res = call_func(reset_bits,x,y,z);
    
    TEST_ASSERT_EQUAL_INT64(expected,res); 
    
}


void test_Zero()
{ 
    run_test(-1L,31,0,0xffffffff); 
}
void test_One()
{ 
    run_test(-1L,51,8,0xfffffffffff00); 
}
void test_Two()
{ 
    run_test(-1L,30,1,0x7ffffffe); 
}
void test_Three()
{ 
    run_test(-1L,29,2,0x3ffffffc); 
}
void test_Four()
{ 
    run_test(-1L,7,24,0); 
}

void test_Five()
{ 
    run_test(-1,31,0,0xffffffff); 
}

void test_Six()
{ 
    run_test(-1L,63,0,-1L); 
}

void test_Seven()
{ 
    run_test(-1L,59,4,0x0ffffffffffffff0); 
}

void test_Eight()
{ 
    run_test(0x5555555555555555,24,7,0x1555500); 
}
void test_Nine()
{ 
    run_test(0x5555555555555555,29,2,0x15555554); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    RUN_TEST(test_Four);
    RUN_TEST(test_Five);
    RUN_TEST(test_Six);
    RUN_TEST(test_Seven);
    RUN_TEST(test_Eight);
    RUN_TEST(test_Nine);
    return UNITY_END();  

  } 






