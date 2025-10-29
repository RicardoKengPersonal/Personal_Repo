#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


short call_func ( short (*f)(short x, char y), short x, char y );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short x, char y, short exp_l, short exp_r)
{
    short  resl; 
    short  resr; 

	resl = call_func(rotate_left,x,y);
        TEST_ASSERT_EQUAL_INT16(exp_l,resl); 

	resr = call_func(rotate_right,x,y);
        TEST_ASSERT_EQUAL_INT16(exp_r,resr); 
    
}


void test_Zero()
{ 
    run_test(0,0,0,0); 
}
void test_ZeroB()
{ 
    run_test(0,5,0,0); 
}
void test_MinusOne()
{ 
    run_test(-1,10,-1,-1); 
}
void test_One()
{ 
    run_test(1,2,4,0x4000); 
}
void test_Two()
{ 
    run_test(7,4,0x70,0x7000); 
}
void test_Five()
{ 
    run_test(0x5555,1,0xaaaa,0xaaaa); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_ZeroB);
    RUN_TEST(test_MinusOne);
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Five);
    return UNITY_END();  

  } 






