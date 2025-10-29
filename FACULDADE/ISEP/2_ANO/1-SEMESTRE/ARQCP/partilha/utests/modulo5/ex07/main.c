#include <string.h>  
#include "../../unity.h"
#include "asm.h" 



void call_func(void (*f)(s1 *, int,char, int , char ), s1*  s,
				 int vi, char vc , int vj , char vd); 

// void fill_s1(s1 *s,int vi, char vc, int vj, char vd); 


void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test( int vi, char vc , int vj,  char vd   )
{
     s1 s; 	 
    // setup 
        memset(&s,0xaa,sizeof(s)); 	 
      call_func(fill_s1,&s,vi,vc,vj,vd); 
        TEST_ASSERT_EQUAL_INT(vi,s.i);
	TEST_ASSERT_EQUAL_INT8(vc,s.c); 
	TEST_ASSERT_EQUAL_INT(vj,s.j); 
	TEST_ASSERT_EQUAL_INT8(vd,s.d); 
	TEST_ASSERT_EQUAL_INT8(0xaa,*(char*)(&(s.d)+1)); 
	TEST_ASSERT_EQUAL_INT8(0xaa,*(char*)(&(s.d)+2)); 
}


void test_One()
{ 
    run_test(1,2,3,4); 
}
void test_Two()
{ 
    run_test(-1,-2,-3,-4); 
}
void test_Three()
{ 
    run_test(100000,70,300000,70); 
}


int main()
  { 
    UNITY_BEGIN();
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    return UNITY_END();  

  } 

