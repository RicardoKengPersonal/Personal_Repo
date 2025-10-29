#include <string.h>  
#include "../../unity.h"
#include "asm.h" 



void call_func(void (*f)(Student *, char , short , char * , char * ), Student*  s, char age,
			short number, char * name , char * address );

void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test(char t_age, short t_number, char t_name[] , char t_address[])
{
   
    Student s; 	
    // setup 
      s.age=0; 
      s.number=0; 	
      s.grades[0]=0x55555555; 	
      memset(s.name,0xaa,60); 	
      memset(s.address,0xaa,100); 	 

      call_func(fill_student,&s,t_age,t_number,t_name,t_address); 
        TEST_ASSERT_EQUAL_INT8(t_age,s.age);
	TEST_ASSERT_EQUAL_INT16(t_number,s.number); 
	TEST_ASSERT_EQUAL_STRING(t_name,s.name); 
	TEST_ASSERT_EQUAL_STRING(t_address,s.address);
	TEST_ASSERT_EQUAL_INT(0x55555555,s.grades[0]); 
	TEST_ASSERT_EQUAL_INT8(0xaa,s.name[strlen(t_name)+1]);  
	TEST_ASSERT_EQUAL_INT8(0xaa,s.address[strlen(t_address)+1]);  
}


void test_Zero()
{ 
    run_test(10, 200,(char*)"",(char*)""); 
}
void test_One()
{ 
    run_test(50, -1,(char*)"First Student",(char*)"Nowhere Street"); 
}
void test_Two()
{ 
    run_test(-1, 23561,(char*)"Second Student",(char*)"Third  Street"); 
}
void test_Three()
{ 
    run_test(71, 23561,(char*)"Third after Second  Student",(char*)"Third  Avenue,178"); 
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






