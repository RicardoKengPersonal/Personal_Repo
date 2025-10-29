#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 short call_func(short (*f)()); 
 
 extern short s1; 
 extern short wall1; 
 extern short wall2; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x5678;  
      wall2=0x5678;  
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short x, short expected)
{

    short result; 
    s1=x; 
    result=call_func(swapBytes); 

    
    TEST_ASSERT_EQUAL_INT16(expected,result); 
    TEST_ASSERT_EQUAL_INT16(x,s1); 
    TEST_ASSERT_EQUAL_INT8(0x5678,wall1); 
    TEST_ASSERT_EQUAL_INT8(0x5678,wall2); 
    
}

void test_Zero()
{ 
    run_test(0, 0); 

}

void test_OneA()
{ 
    run_test( 1,0x100); 

}

void test_OneB()
{ 
    run_test(0x1234,  0x3412); 

}

void test_Minus()
{ 
    run_test( -1, -1); 

}

void test_Big()
{ 
   run_test(0x9876,0x7698 ); 

}

void test_Big2()
{ 
   run_test( 0xaa55,0x55aa); 

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






