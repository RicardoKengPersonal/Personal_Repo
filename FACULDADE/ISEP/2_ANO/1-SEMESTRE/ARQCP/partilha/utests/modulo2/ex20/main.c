#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

char call_func(char (*f)()); 
 
 extern short num; 
 extern short wall1; 
 extern short wall2; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x1234;  
      wall2=0x1234;  
       
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short x, char expected)
{

    char result; 
    num=x; 
    result=call_func(check_num); 

    
    TEST_ASSERT_EQUAL_INT8(expected,result); 
    TEST_ASSERT_EQUAL_INT16(x,num); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall1); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall2); 
    
}

void test_Zero()
{ 
    run_test(0, 3); 
}

void test_One()
{ 
    run_test(1, 4); 
}

void test_Two()
{ 
    run_test(2, 3); 
}


void test_MinusA()
{ 
    run_test( -1, 2); 
}

void test_MinusB()
{ 
    run_test( -10, 1); 
}

void test_Big()
{ 
   run_test(0xffff,  2); 
}

void test_Big2()
{ 
   run_test(0xfffe,  1); 

}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_MinusA);
    RUN_TEST(test_MinusB);
    RUN_TEST(test_Big);
    RUN_TEST(test_Big2);
    return UNITY_END();  

  } 






