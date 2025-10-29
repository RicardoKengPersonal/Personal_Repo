#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


short call_func ( short (*f)(short x, short y, short z), short x, short y, short z);  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short x, short y, short z, short expected )
{
    short  res; 

    

    // setup 
	res = call_func(greatest,x,y,z);
    
    TEST_ASSERT_EQUAL_INT(expected,res); 
    
}


void test_Zero()
{ 
    run_test(0,0,0,0); 
}
void test_One()
{ 
    run_test(0,1,-1,1); 
}
void test_MinusOne()
{ 
    run_test(-1,-2,-4,-1); 
}
void test_MinusOneB()
{ 
    run_test(-3,-2,-1,-1); 
}
void test_Two()
{ 
    run_test(-3,2,1,2); 
}

void test_Three()
{ 
    run_test(-500,2,3,3); 
}
void test_Five()
{ 
    run_test(-200,3000,5,3000); 
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






