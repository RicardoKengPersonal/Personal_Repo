#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(int a, int *b, int c), int a, int *b , int c  );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(int x, int y, int z, int expected)
{
    int  res; 
    int  v[3]={0x55555555,0x55555555,0x55555555}; 

    v[1]=y;  

    // setup 
	res = call_func(calc,x,&v[1],z);
    
    TEST_ASSERT_EQUAL_INT(expected,res); 
    TEST_ASSERT_EQUAL_INT(y,v[1]); 
    TEST_ASSERT_EQUAL_INT(0x55555555,v[0]); 
    TEST_ASSERT_EQUAL_INT(0x55555555,v[2]); 
    
}


void test_MinusTwo()
{ 
    run_test(0,0,0,0); 
}
void test_MinusOne()
{ 
    run_test(1,2,1,-30); 
}
void test_MinusOneA()
{ 
    run_test(2,1,-1,-63); 
}
void test_MinusSix()
{ 
    run_test(2,1,4,-18); 
}
void test_Six()
{ 
    run_test(3,1,-4,-130); 
}
void test_Up()
{ 
    run_test(1,2,3,-18); 
}
void test_Down()
{ 
    run_test(3,2,1,-70); 
}
void test_Hundred()
{ 
    run_test(-100,-100,-100,53000); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_MinusTwo);
    RUN_TEST(test_MinusOne);
    RUN_TEST(test_MinusOneA);
    RUN_TEST(test_MinusSix);
    RUN_TEST(test_Six);
    RUN_TEST(test_Up);
    RUN_TEST(test_Down);
    RUN_TEST(test_Hundred);
    return UNITY_END();  

  } 






