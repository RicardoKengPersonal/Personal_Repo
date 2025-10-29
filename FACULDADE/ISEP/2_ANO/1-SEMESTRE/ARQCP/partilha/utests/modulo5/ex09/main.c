#include <string.h>  
#include "../../unity.h"
#include "asm.h" 



short   call_func(short (*f)(structB *),  structB*  s);
short * call_func3(short * (*f)(structB *),  structB*  s);  





void setUp(void) {
    // set stuff up here
}

void tearDown(void) {
    // clean stuff up here
}



void run_test1( short  w  )
{
	structA sa; 	
	structB sb; 	
	short result; 
    // setup 
        memset(&sa,0xaa,sizeof(sa)); 	 
        memset(&sb,0xaa,sizeof(sb)); 	 
	sb.a.x=w;

	result=call_func(fun1,&sb); 
        TEST_ASSERT_EQUAL_INT16(w,result);
        TEST_ASSERT_EQUAL_INT((int)0xaaaaaaaa,sb.a.y);

} 

void run_test2( short  w  )
{
	structA sa; 	
	structB sb; 	
	short result; 
    // setup 
        memset(&sa,0xaa,sizeof(sa)); 	 
        memset(&sb,0xaa,sizeof(sb)); 	 
	sb.z=w;

	result=call_func(fun2,&sb); 
        TEST_ASSERT_EQUAL_INT16(w,result);
        TEST_ASSERT_EQUAL_INT8(0xaa,*(char*)(&(sb.z)+1));
        TEST_ASSERT_EQUAL_INT8(0xaa,*(char*)(&(sb.e[2])+1));

}

void run_test3( )
{
	structA sa; 	
	structB sb; 	
	short * result; 
    // setup 
        memset(&sa,0xaa,sizeof(sa)); 	 
        memset(&sb,0xaa,sizeof(sb)); 	 
	sb.b=&sa;

	result=call_func3(fun3,&sb); 
        TEST_ASSERT_EQUAL_PTR(&sb.z,result);

}

void run_test4( short  w  )
{
	structA sa; 	
	structB sb; 	
	short result; 
    // setup 
        memset(&sa,0xaa,sizeof(sa)); 	 
        memset(&sb,0xaa,sizeof(sb)); 	 
	sb.b->x=w;

	result=call_func(fun4,&sb); 
        TEST_ASSERT_EQUAL_INT16(w,result);
        TEST_ASSERT_EQUAL_INT((int)0xaaaaaaaa,sb.b->y);

} 



void test_One1()
{ 
    run_test1(1); 
}
void test_MinusOne1()
{ 
    run_test1(-1); 
}
void test_Zero1()
{ 
    run_test1(0); 
}

void test_One2()
{ 
    run_test1(1); 
}
void test_MinusOne2()
{ 
    run_test1(-1); 
}
void test_Zero2()
{ 
    run_test1(0); 
}

void test_3()
{ 
    run_test3(); 
}


void test_One4()
{ 
    run_test1(1); 
}
void test_MinusOne4()
{ 
    run_test1(-1); 
}
void test_Zero4()
{ 
    run_test1(0); 
}
int main()
  { 
    UNITY_BEGIN();
    RUN_TEST(test_One1);
    RUN_TEST(test_MinusOne1);
    RUN_TEST(test_Zero1);
    RUN_TEST(test_One2);
    RUN_TEST(test_MinusOne2);
    RUN_TEST(test_Zero2);
    RUN_TEST(test_3);
    RUN_TEST(test_One4);
    RUN_TEST(test_MinusOne4);
    RUN_TEST(test_Zero4);
    return UNITY_END();  

  } 






