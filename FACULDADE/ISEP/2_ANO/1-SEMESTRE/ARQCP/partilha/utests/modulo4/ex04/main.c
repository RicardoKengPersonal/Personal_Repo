#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(int x, int y, int *p), int x, int y , int *p  );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int x, int y, int expected, int exp_smaller)
{
    int  res; 
    int  v[3]={0x55555555,0x55555555,0x55555555}; 

    

    // setup 
	res = call_func(sum_sub,x,y,&v[1]);
    
    TEST_ASSERT_EQUAL_INT(expected,res); 
    TEST_ASSERT_EQUAL_INT(exp_smaller,v[1]); 
    TEST_ASSERT_EQUAL_INT(0x55555555,v[0]); 
    TEST_ASSERT_EQUAL_INT(0x55555555,v[2]); 
    
}


void test_Zero()
{ 
    run_test(0,0,0,0); 
}
void test_One()
{ 
    run_test(0,1,1,-1); 
}
void test_MinusOne()
{ 
    run_test(-1,-2,-3,1); 
}
void test_MinusOneB()
{ 
    run_test(-2,-1,-3,-1); 
}
void test_Two()
{ 
    run_test(4,2,6,2); 
}

void test_Three()
{ 
    run_test(6,3,9,3); 
}
void test_Five()
{ 
    run_test(105,5,110,100); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_MinusOne);
    RUN_TEST(test_MinusOneB);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    RUN_TEST(test_Five);
    return UNITY_END();  

  } 






