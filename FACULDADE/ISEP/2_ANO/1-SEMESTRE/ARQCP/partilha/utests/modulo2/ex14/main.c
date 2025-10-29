#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

 int call_func(int (*f)()); 
 
 extern int length1; 
 extern int length2; 
 extern int height;  
 extern int wall1; 
 extern int wall2; 
 extern int wall3; 
 extern int wall4; 

void setUp(void) {
    // set stuff up here
     
      wall1=0x12345678;  
      wall2=0x12345678;  
      wall3=0x12345678;  
      wall4=0x12345678;  
       
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int l1, int l2, int y, int expected)
{

    int result; 
    length1=l1; 
    length2=l2; 
    height=y; 
    result=call_func(getArea); 

    
    TEST_ASSERT_EQUAL_INT(expected,result); 
    TEST_ASSERT_EQUAL_INT(l1,length1); 
    TEST_ASSERT_EQUAL_INT(l2,length2); 
    TEST_ASSERT_EQUAL_INT(y,height); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall1); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall2); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall3); 
    TEST_ASSERT_EQUAL_INT(0x12345678,wall4); 
    
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






