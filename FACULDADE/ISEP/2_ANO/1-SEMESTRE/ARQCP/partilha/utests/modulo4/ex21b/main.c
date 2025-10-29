#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


int call_func ( int (*f)(char *p1, char *p2), char *p1, char *p2);  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(char *p1, char*p2, int expected)
{
    int  res; 

	res = call_func(distance,p1,p2);
    
    TEST_ASSERT_EQUAL_INT(expected,res); 
    
}


void test_Zero()
{ 
    run_test("","",0); 
}
void test_One()
{ 
    run_test("1","1",0); 
}
void test_OneA()
{ 
    run_test("1","",-1); 
}
void test_OneB()
{ 
    run_test("","1",-1); 
}
void test_Two()
{ 
    run_test("ab","ab",0); 
}
void test_TwoA()
{ 
    run_test("a","ab",-1); 
}
void test_TwoB()
{ 
    run_test("ab","a",-1); 
}
void test_Five()
{ 
    run_test("abcde","abcde",0); 
}
void test_FiveA()
{ 
    run_test("abcdz","abcde",1); 
}
void test_FiveB()
{ 
    run_test("abcde","abxdz",2); 
}
void test_FiveC()
{ 
    run_test("abcde","yaxbz",5); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_OneA);
    RUN_TEST(test_OneB);
    RUN_TEST(test_Two);
    RUN_TEST(test_TwoA);
    RUN_TEST(test_TwoB);
    RUN_TEST(test_Five);
    RUN_TEST(test_FiveA);
    RUN_TEST(test_FiveB);
    return UNITY_END();  

  } 






