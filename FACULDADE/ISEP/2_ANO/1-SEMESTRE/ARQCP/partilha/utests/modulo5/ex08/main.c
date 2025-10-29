#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


void call_func(void (*f)(s2 *, short *, int, char *), s2* s, short* w, int j, char * b); 

// void fill_s2(s2 *s, short vw[3], int vj, char vc[3]); 




void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test( short * vw , int vj,  char * vc  )
{
     s2 s; 	 
    // setup 
        memset(&s,0xaa,sizeof(s)); 	 
	call_func(fill_s2,&s,vw,vj,vc);
    TEST_ASSERT_EQUAL_INT(vj,s.j);
	TEST_ASSERT_EQUAL_INT8_ARRAY(vc,s.c,3); 
	TEST_ASSERT_EQUAL_INT16_ARRAY(vw,s.w,3); 
	TEST_ASSERT_EQUAL_INT16(0xaaaa,*(short*)(&(s.w[2])+1)); 
	TEST_ASSERT_EQUAL_INT8(0xaa,*(char*)(&(s.c[2])+1)); 
}


void test_One()
{ 
    run_test((short[]){1,2,3}, 4,(char[]){5,6,7}); 
}
void test_Two()
{ 
    run_test((short[]) {1000,20000,30000}, -1,(char[]){50,60,70}); 
}
void test_Three()
{ 
    run_test((short[]){-1,-2,-3}, -4,(char[]){-5,-6,-7}); 
}


int main()
  { 
    UNITY_BEGIN();
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    return UNITY_END();  

  } 






