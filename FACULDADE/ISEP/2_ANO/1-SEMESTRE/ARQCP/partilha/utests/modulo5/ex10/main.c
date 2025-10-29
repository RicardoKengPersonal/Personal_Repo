#include <string.h>  
#include "../../unity.h"
#include "asm.h" 
#include "dmt2.h" 

char* call_func(char *  (*f)(char * ), char * p1 );




void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test( char * str )
{
    // setup 
	char * result; 
	int slen =strlen(str)+1; 

	result=call_func(new_str,str);
	int pok=dmt_has(result); 
	int plen=dmt_size(result); 

	TEST_ASSERT_EQUAL_INT(slen,plen); 
	TEST_ASSERT_EQUAL_STRING(str,result); 
	TEST_ASSERT_MESSAGE(pok,"Pointer not in Dynamic Memory!");  
}

void test_Zero()
{ 
    run_test(""); 
}

void test_One()
{ 
    run_test("a"); 
}
void test_Two()
{ 
    run_test("ab"); 
}
void test_Three()
{ 
    run_test("abc"); 
}


int main()
  { 
    UNITY_BEGIN();
    RUN_TEST(test_Zero);
    RUN_TEST(test_One);
    RUN_TEST(test_Two);
    RUN_TEST(test_Three);
    return UNITY_END();  

  } 






