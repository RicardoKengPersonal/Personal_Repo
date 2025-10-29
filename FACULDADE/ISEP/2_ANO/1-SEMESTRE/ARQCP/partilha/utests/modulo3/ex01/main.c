#include <string.h>  
#include "../../unity.h"
#include "asm.h" 

int call_func ( int (*f)(char* ptr),char *ptr );  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(char * str, int expected )
{
    char vec[100];
    int  res; 

    

    // setup 
        memset(vec, 0xaa, sizeof vec);
     
	strcpy(vec+1,str);  // bad practice  
	res = call_func(five_count,vec+1);
    
    TEST_ASSERT_EQUAL_CHAR(0xaa, vec[strlen(str)+2]);    // check sentinel 
    TEST_ASSERT_EQUAL_CHAR(0xaa, vec[0]);    // check sentinel 
    TEST_ASSERT_EQUAL_INT(expected,res); 
    
}


void test_None()
{ 
    run_test("hex",0); 
}
void test_Hello()
{ 
    run_test("Hello 5 word!",1); 
}
void test_Three()
{ 
    run_test("555",3); 
}
void test_ThreeL()
{ 
    run_test("A5B5C5D",3); 
}

void test_Spaces()
{ 
    run_test("     ",0); 
}
void test_NullString()
{ 
    run_test("",0); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_None);
    RUN_TEST(test_Hello);
    RUN_TEST(test_Three);
    RUN_TEST(test_ThreeL);
    RUN_TEST(test_NullString);
    return UNITY_END();  

  } 






