#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(int a, int b, short c, char d), int a, int b, short c, char d);  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}

void run_test( int a, int b, short c, char d, int expected)
{
    int  res; 
    // setup 
	res = call_func(call_proc, a, b, c, d);
    
    TEST_ASSERT_EQUAL_INT(expected,res); 
}

void test_Zero()                  /*  LOTS of things to improve !!!! */ 
{ 
    run_test(0,0,0,0,0); 
}

void test_One1()                  /*  LOTS of things to improve !!!! */ 
{ 
    run_test(1,0,0,0,0); 
}

void test_One2()                  /*  LOTS of things to improve !!!! */ 
{ 
    run_test(0,1,0,0,2); 
}

void test_One3()                  /*  LOTS of things to improve !!!! */ 
{ 
    run_test(0,0,1,0,0); 
}

void test_One4()                  /*  LOTS of things to improve !!!! */ 
{ 
    run_test(0,0,0,1,0); 
}

void test_MinusOne1()                 /*  LOTS of things to improve !!!! */ 
{ 
    run_test(-1,0,0,0,0); 
}

void test_MinusOne2()                 /*  LOTS of things to improve !!!! */ 
{ 
    run_test(0,-1,0,0,2); 
}

void test_MinusOne3()                 /*  LOTS of things to improve !!!! */ 
{ 
    run_test(0,0,-1,0,0); 
}

void test_MinusOne4()                 /*  LOTS of things to improve !!!! */ 
{ 
    run_test(0,0,0,-1,0); 
}

void test_One()                  /*  LOTS of things to improve !!!! */ 
{ 
    run_test(1,1,1,1,-2); 
}

void test_MinusOne()                  /*  LOTS of things to improve !!!! */ 
{ 
    run_test(-1,-1,-1,-1,-2); 
}

void test_Big()                  /*  LOTS of things to improve !!!! */ 
{ 
    run_test(33,9998,5311,100,305238940); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One1);
    RUN_TEST(test_One2);
    RUN_TEST(test_One3);
    RUN_TEST(test_One4);
    RUN_TEST(test_MinusOne1);
    RUN_TEST(test_MinusOne2);
    RUN_TEST(test_MinusOne3);
    RUN_TEST(test_MinusOne4);
    RUN_TEST(test_One);
    RUN_TEST(test_MinusOne);
    RUN_TEST(test_Big);
    return UNITY_END();  

  } 






