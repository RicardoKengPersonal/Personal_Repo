#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


void call_func ( void (*f)(long *p, char x), long *p, char x );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(long x, char bitpos, long expected )
{
    long  v[3]={0x5555555555555555,0x5555555555555555,0x5555555555555555}; 

    v[1]=x;     

	call_func(set_2bits,&v[1],bitpos);
    
    TEST_ASSERT_EQUAL_INT64(expected,v[1]); 
    TEST_ASSERT_EQUAL_INT64(0x5555555555555555,v[0]); 
    TEST_ASSERT_EQUAL_INT64(0x5555555555555555,v[2]); 
    
}


void test_Zero()
{ 
    run_test(0,0,0x8000000000000001); 
}
void test_One()
{ 
    run_test(0x8000000000000001,0,0x8000000000000001); 
}
void test_Four()
{ 
    run_test(0x8000000000000001,4,0x8800000000000011); 
}
void test_Eight()
{ 
    run_test(0x8000000000000001,12,0x8008000000001001); 
}
void test_Twelve()
{ 
    run_test(0x8008000000001001,12,0x8008000000001001); 
}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Four);
    RUN_TEST(test_Eight);
    RUN_TEST(test_Twelve);
    return UNITY_END();  

  } 






