#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 short call_func(short (*f)()); 
 
 extern short s1; 
 extern short s2; 
 extern short wall1; 
 extern short wall2; 
 extern short wall3; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x7878;  
      wall2=0x7878;  
      wall3=0x7878; 
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short x, short y, short expected)
{

    short result; 
    s1=x; 
    s2=y; 
    result=call_func(crossSubBytes); 

    
    TEST_ASSERT_EQUAL_INT16(expected,result); 
    TEST_ASSERT_EQUAL_INT16(x,s1); 
    TEST_ASSERT_EQUAL_INT16(y,s2); 
    TEST_ASSERT_EQUAL_INT16(0x7878,wall1); 
    TEST_ASSERT_EQUAL_INT16(0x7878,wall2); 
    TEST_ASSERT_EQUAL_INT16(0x7878,wall3); 
    
}

void test_Zero()
{ 
    run_test(0, 0, 0); 

}

void test_OneA()
{ 
    run_test(0, 1, -256); 

}

void test_OneB()
{ 
    run_test(1, 1, -255); 

}

void test_Minus()
{ 
    run_test( 0xff, 0xff, 511); 

}

void test_Big()
{ 
   run_test(0x2233, 0x3322, 0); 

}

void test_Big2()
{ 
   run_test(0x4455, 0x6677, -12817); 

}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_OneA);
    RUN_TEST(test_OneB);
    RUN_TEST(test_Minus);
    RUN_TEST(test_Big);
    RUN_TEST(test_Big2);
    return UNITY_END();  

  } 






