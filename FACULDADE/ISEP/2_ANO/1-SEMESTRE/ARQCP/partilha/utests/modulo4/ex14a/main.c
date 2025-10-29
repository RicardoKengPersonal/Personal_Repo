#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(long *p, char x), long *p, char x );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(long x, char bitpos, long expected, int exp_res)
{
    int  res; 
    long  v[3]={0x5555555555555555,0x5555555555555555,0x5555555555555555}; 

    v[1]=x;     

	res = call_func(set_bit,&v[1],bitpos);
    
    TEST_ASSERT_EQUAL_INT(exp_res,res); 
    TEST_ASSERT_EQUAL_INT64(expected,v[1]); 
    TEST_ASSERT_EQUAL_INT64(0x5555555555555555,v[0]); 
    TEST_ASSERT_EQUAL_INT64(0x5555555555555555,v[2]); 
    
}


void test_Zero()
{ 
    run_test(0,0,1,1); 
}
void test_One()
{ 
    run_test(1,0,1,0); 
}
void test_Four()
{ 
    run_test(0x7fff,15,0xffff,1); 
}
void test_FourA()
{ 
    run_test(0xffff,15,0xffff,0); 
}
void test_Thirty()
{ 
    run_test(0x3fffffff,30,0x7fffffff,1); 
}
void test_ThirtyA()
{ 
    run_test(0xfffffffeffffffff,32,0xffffffffffffffff,1); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Four);
    RUN_TEST(test_FourA);
    RUN_TEST(test_Thirty);
    RUN_TEST(test_ThirtyA);
    return UNITY_END();  

  } 






