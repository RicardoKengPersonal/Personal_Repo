#include <string.h>  
#include "../../unity.h"
#include "asm.h" 


void call_func ( void (*f)(char* ptr1, char* ptr2), char* ptr1, char* ptr2);  

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(char * str, char * expected )
{
    char vec1[100];
    char vec2[100];

    

    // setup 
        memset(vec1, 0xaa, sizeof vec1);
        memset(vec2, 0xaa, sizeof vec2);
     
	strcpy(vec1+1,str);  // bad practice  
	call_func(str_copy_roman2,vec1+1,vec2+1);
    
    TEST_ASSERT_EQUAL_CHAR(0xaa, vec1[strlen(str)+2]);    // check sentinel 
    TEST_ASSERT_EQUAL_CHAR(0xaa, vec1[0]);    // check sentinel 
    TEST_ASSERT_EQUAL_CHAR(0xaa, vec2[strlen(str)+2]);    // check sentinel 
    TEST_ASSERT_EQUAL_CHAR(0xaa, vec2[0]);    // check sentinel 
    TEST_ASSERT_EQUAL_STRING(str,vec1+1); 
    TEST_ASSERT_EQUAL_STRING(expected,vec2+1); 
    
}


void test_hey()
{ 
    run_test("hey","hey"); 
}
void test_hello()
{ 
    run_test("Hello world!\n","Hello world!\n"); 
}
void test_One_u()
{ 
    run_test("One uU","One vV"); 
}
void test_Two_u()
{ 
    run_test("Two uuUU","Two vvVV"); 
}
void test_Two_U()
{ 
    run_test("Twu UBU","Twv VBV"); 
}
void test_Two_uU()
{ 
    run_test("Twu uU","Twv vV"); 
}
void test_NullString()
{ 
    run_test("",""); 
}



int main()
  { 

    UNITY_BEGIN();
    RUN_TEST(test_hey);
    RUN_TEST(test_hello);
    RUN_TEST(test_One_u);
    RUN_TEST(test_Two_U);
    RUN_TEST(test_Two_uU);
    RUN_TEST(test_Two_u);
    RUN_TEST(test_NullString);
    return UNITY_END();  

  } 






