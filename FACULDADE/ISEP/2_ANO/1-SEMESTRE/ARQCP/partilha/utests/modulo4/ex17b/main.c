#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


unsigned int call_func ( unsigned int (*f)( unsigned int x, unsigned  int y),  unsigned int x,  unsigned int y );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}


unsigned int build_date( int year , int month , int day ) {

	return  (year << 16)  | (day <<8 ) |  month ;
}



void run_test(int x, int y, int expected )
{
    int  res; 

	res = call_func(greater_date,x,y);
        TEST_ASSERT_EQUAL_INT(expected,res); 

    
}


void test_Zero()
{ 
    run_test(build_date(2017,11,11),build_date(2017,11,11),build_date(2017,11,11)); 
}
void test_One()
{ 
    run_test(build_date(2017,11,10),build_date(2017,11,11),build_date(2017,11,11)); 
}
void test_Two()
{ 
    run_test(build_date(2017,11,11),build_date(2017,11,10),build_date(2017,11,11)); 
}
void test_Three()
{ 
    run_test(build_date(2017,10,11),build_date(2017,11,11),build_date(2017,11,11)); 
}
void test_Four()
{ 
    run_test(build_date(2017,11,11),build_date(2017,10,11),build_date(2017,11,11)); 
}
void test_Five()
{ 
    run_test(build_date(2017,11,11),build_date(2014,11,11),build_date(2017,11,11)); 
}
void test_Six()
{ 
    run_test(build_date(2014,11,11),build_date(2017,11,11),build_date(2017,11,11)); 
}

void test_Seven()
{ 
    run_test(build_date(2017,11,11),build_date(2,1,1),build_date(2017,11,11)); 
}
void test_Eight()
{ 
    run_test(build_date(20,1,1),build_date(2017,11,11),build_date(2017,11,11)); 
}
void test_Nine()
{ 
    run_test(build_date(2017,11,11),build_date(12,12,31),build_date(2017,11,11)); 
}

void test_Ten()
{ 
    run_test(build_date(10,11,11),build_date(2,12,31),build_date(10,11,11)); 
}
void test_Eleven()
{ 
    run_test(build_date(256,1,1),build_date(255,12,31),build_date(256,1,1)); 
}

int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    RUN_TEST(test_Four);
    RUN_TEST(test_Five);
    RUN_TEST(test_Six);
    RUN_TEST(test_Seven);
    RUN_TEST(test_Eight);
    RUN_TEST(test_Nine);
    RUN_TEST(test_Ten);
    RUN_TEST(test_Eleven);
    return UNITY_END();  

  } 






