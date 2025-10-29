#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 short call_func(short (*f)()); 
 
 extern char desired; 
 extern char current; 
 extern char wall1; 
 extern char wall2; 
 extern char wall3; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x56;  
      wall2=0x56;  
      wall3=0x56;  
       
       
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(char x, char y, short expected)
{

   short result; 
    current=x; 
    desired=y; 
    result=call_func(needed_time); 

    
    TEST_ASSERT_EQUAL_INT16(expected,result); 
    TEST_ASSERT_EQUAL_INT8(x,current); 
    TEST_ASSERT_EQUAL_INT8(y,desired); 
    TEST_ASSERT_EQUAL_INT16(0x56,wall1); 
    TEST_ASSERT_EQUAL_INT16(0x56,wall2); 
    TEST_ASSERT_EQUAL_INT16(0x56,wall3); 
    
}

void test_Zero()
{ 
    run_test(0, 0, 0); 
}

void test_OneA()
{ 
    run_test(0, 10, 30*60); 
}

void test_OneB()
{ 
    run_test(2, 3, 3*60); 
}

void test_OneC()
{ 
    run_test( -2, -1, 3*60); 
}

void test_MinusA()
{ 
    run_test( 5, 3, 8*60); 
}

void test_MinusB()
{ 
    run_test( 5, -5, 40*60); 
}

void test_Big()
{ 
   run_test(50, 100, 150*60); 

}

void test_Big2()
{ 
   run_test(5, 55, 150*60); 

}


int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_OneA);
    RUN_TEST(test_OneB);
    RUN_TEST(test_OneC);
    RUN_TEST(test_MinusA);
    RUN_TEST(test_MinusB);
    RUN_TEST(test_Big);
    RUN_TEST(test_Big2);
    return UNITY_END();  

  } 






