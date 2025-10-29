#include <string.h>  
#include "../../unity.h"
#include "asm.h" 



void call_func(void (*f)(Student *,  char * ), Student*  s, char * address );

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test( char t_address[])
{
   
    Student s; 	
    // setup 
      s.age=0x55;
      s.number=0x5555;
      s.grades[0]=0x55555555;
      memset(s.name,0xaa,60);
      memset(s.address,0xaa,100);

      call_func(update_address,&s,t_address); 
        TEST_ASSERT_EQUAL_INT8(0x55,s.age);
	TEST_ASSERT_EQUAL_INT16(0x5555,s.number); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.name[0]); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.name[59]); 
	TEST_ASSERT_EQUAL_STRING(t_address,s.address);
	TEST_ASSERT_EQUAL_INT(0x55555555,s.grades[0]); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.address[strlen(t_address)+1]);  
}


void test_Zero()
{ 
    run_test((char*)""); 
}
void test_One()
{ 
    run_test((char*)"Nowhere Street"); 
}
void test_Two()
{ 
    run_test((char*)"Third  Street"); 
}
void test_Three()
{ 
    run_test((char*)"Third  Avenue,178"); 
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






