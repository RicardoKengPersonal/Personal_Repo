#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 int call_func(int (*f)()); 
 
 extern short length1; 
 extern short length2; 
 extern short height;  
 extern short wall1; 
 extern short wall2; 
 extern short wall3; 
 extern short wall4; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x1234;  
      wall2=0x1234;  
      wall3=0x1234;  
      wall4=0x1234;  
       
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(short l1, short l2, short y, int expected)
{

    int result; 
    length1=l1; 
    length2=l2; 
    height=y; 
    result=call_func(getArea); 

    
    TEST_ASSERT_EQUAL_INT(expected,result); 
    TEST_ASSERT_EQUAL_INT16(l1,length1); 
    TEST_ASSERT_EQUAL_INT16(l2,length2); 
    TEST_ASSERT_EQUAL_INT16(y,height); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall1); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall2); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall3); 
    TEST_ASSERT_EQUAL_INT16(0x1234,wall4); 
    
}

void test_Zero()
{ 
    run_test(0, 0, 0, 0); 
}

void test_OneA()
{ 
    run_test(4, 2, 2, 6); 
}

void test_OneB()
{ 
    run_test(5, 6, 5, 27); 
}

void test_OneC()
{ 
    run_test( 800, 700, 400, 300000); 
}

void test_MinusA()
{ 
    run_test( 3, 5, 7, 28); 
}

void test_MinusB()
{ 
    run_test( 1000, 1500, 1000, 1250000); 
}

void test_Big()
{ 
   run_test(5000, 2000, 50,  175000); 

}

void test_Big2()
{ 
   run_test(5000, 50, 80, 202000); 

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






