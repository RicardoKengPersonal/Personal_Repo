#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "asm.h"
#define BUFFER_SIZE 5
#define BUFFER_LENGTH 5

void func_extract_data();
void func_get_number_binary();
void func_get_number();
void func_format_command();
void func_enqueue_value();
void func_dequeue_value();
void func_get_n_element();
void func_move_to_array();
void func_sort_array();
void func_median();

int main() 
{
	int choice;
	
    do{
        printf("\n=== Menu ===\n");
        printf("1. USAC01- Extract Data\n");
        printf("2. USAC02- Get Number Binary\n");
        printf("3. USAC03- Get Number\n");
        printf("4. USAC04- Format Command\n");
        printf("5. USAC05- Enqueue Value\n");
        printf("6. USAC06- Dequeue Value\n");
        printf("7. USAC07- Get N Elements\n");
        printf("8. USAC08- Move to Array\n");
        printf("9. USAC09- Sort Array\n");
        printf("10. USAC10- Get Median\n");
        printf("0. Exit\n");
        printf("Option: ");
        scanf("%d", &choice);

        switch (choice) 
        {
            case 1:
                func_extract_data();
                break;
                
            case 2:
                func_get_number_binary();
                break;
                
            case 3:
				func_get_number();
				break;
				
            case 4:
                func_format_command();
                break;
                
            case 5:    
				func_enqueue_value();
				break;
				
            case 6:
                func_dequeue_value();
                break;
                
            case 7:
                func_get_n_element();
                break;
                
            case 8:
                func_move_to_array();
                break;
            case 9:
                func_sort_array();
                break;
                
            case 10:
				func_median();
				break;
				
            case 0:
                printf("Closing...\n");
                break;
                
            default:
                printf("Invalid Option! Try again.\n");
        }
        
    } while (choice != 0);
	
	return 0;
}

//USAC01

void func_extract_data() 
{
	
	char str [] ="TEMP&unit:celsius&value:20#HUM&unit:percentage&value:80";
	char token [] ="TEMP";
	char token2 [] = "HUM";
	char token3 [] = "temp";
	char token4 [] = "EMP";
	char unit[20];
	int value;
	
	int res = extract_data(str,token, unit, &value);
	printf("%d: %s,%d\n", res, unit, value); //1: celsius,20

    res = extract_data(str, token2, unit, &value);
    printf("%d: %s,%d\n", res, unit, value); //1: percentage,80

	res = extract_data(str, token3, unit, &value);
    printf("%d: %s,%d\n", res, unit, value); //0: ,0

	res = extract_data(str, token4, unit, &value);
    printf("%d: %s,%d\n", res, unit, value); //0: ,0
}

//USAC02

void func_get_number_binary() 
{
	
	int value = 26;
	int value2 = 31;
	int value3 = 0;
	int value4 = -1;
	char bits[5];
	
	int res = get_number_binary(value, bits);
	printf("%d: %d, %d, %d, %d, %d\n",res, bits[4], bits[3],bits[2],bits[1],bits[0]); //1: 1,1,0,1,0

	res = get_number_binary(value2, bits);
	printf("%d: %d, %d, %d, %d, %d\n",res, bits[4], bits[3],bits[2],bits[1],bits[0]); //1: 1,1,1,1,1

	res = get_number_binary(value3, bits);
	printf("%d: %d, %d, %d, %d, %d\n",res, bits[4], bits[3],bits[2],bits[1],bits[0]); //1: 0,0,0,0,0

	res = get_number_binary(value4, bits);
	printf("%d: %d, %d, %d, %d, %d\n",res, bits[4], bits[3],bits[2],bits[1],bits[0]); //0:

}

//USAC03

void func_get_number() 
{
	int value;
	char str[] = " 89 ";
	char str2[] = "8--9";
	char str3[] = "1234";
	char str4[] = "  42\t";
	char str5[] = "abc";
	char str6[] = "1 2";
	char str7[] = " 80";
	char str8[] = " -6 ";
	char str9[] = "";
	char str10[] = "1";
	char str11[] = " 0   ";
	char str12[] = " 333    ";
	
	int res = get_number(str, &value);
	printf("%d: %d\n",res, value); //1: 89

	res = get_number(str2, &value);
	printf("%d: %d\n",res, value); //0: -1

	res = get_number(str3, &value);
    printf("%d: %d\n", res, value); // 1: 1234

    res = get_number(str4, &value);
    printf("%d: %d\n", res, value); // 1: 42

    res = get_number(str5, &value);
    printf("%d: %d\n", res, value); // 0: -1

    res = get_number(str6, &value);
    printf("%d: %d\n", res, value); // 0: -1

	res = get_number(str7, &value);
    printf("%d: %d\n", res, value); // 1: 80

	res = get_number(str8, &value);
    printf("%d: %d\n", res, value); // 0: -1

	res = get_number(str9, &value);
    printf("%d: %d\n", res, value); // 0: -1

	res = get_number(str10, &value);
    printf("%d: %d\n", res, value); // 1: 1

	res = get_number(str11, &value);
    printf("%d: %d\n", res, value); // 1: 0

	res = get_number(str12, &value);
    printf("%d: %d\n", res, value); // 1: 333
	
}

//USAC04

void func_format_command() 
{
	int value = 26;
	char str [] = "oN";
	char cmd [20];
	
	int res = format_command( str , value , cmd );
	
	printf (" %d: %s\n " ,res , cmd); // 1: ON ,1 ,1 ,0 ,1 ,0
	
	printf("\n");
	
	char str2 [] = "aaa";
	
	res = format_command (str2, value , cmd ) ;
	printf (" %d: %s\n" ,res , cmd ); // 0:
	
}

//USAC05

void func_enqueue_value() 
{
	int buffer[BUFFER_SIZE] = {0};
	int head = 0;
	int tail = 0;
	int result;
	
	for (int i = 1; i <= 7; i++) 
	{
		result = enqueue_value(buffer, BUFFER_SIZE, &tail, &head, i);
		
		printf("Inserted %d, Buffer full: %d\n", i, result);

		printf("Buffer: ");
		
		for (int j = 0; j < BUFFER_SIZE; j++) 
		{
			printf("%d ", buffer[j]);
		}
		printf("\nHead: %d, Tail: %d\n", head, tail);
	}
	
	
}

//USAC06

void func_dequeue_value() 
{
	int buffer[BUFFER_LENGTH] = {0};
    int head = 0;
    int tail = 0;
    int value;

    buffer[0] = 10;
    buffer[1] = 20;
    buffer[2] = 30;
    buffer[3] = 40;
    buffer[4] = 50;
    head = 0;
    tail = 1;  

    if (dequeue_value(buffer, BUFFER_LENGTH, &tail, &head, &value)) 
    {
        printf("Valor removido: %d\n", value);
        
    } else {
		
        printf("Buffer está vazio, nada foi removido.\n");
    }

    printf("Buffer após remoção: ");
    
    for (int i = 0; i < BUFFER_LENGTH; i++) 
    {
        printf("%d ", buffer[i]);
    }
    
    printf("\n");

    printf("Head: %d, Tail: %d\n", head, tail);	
}

//USAC07

void func_get_n_element() 
{
	int buffer[5] = {1, 2, 3, 4, 5}; 
    int length = 5;
    int tail = 3;
    int head = 1; 

    int n_elements = get_n_element(buffer, length, &tail, &head);
    
    printf("Número de elementos armazenados no buffer: %d\n", n_elements);

}

//USAC08

void func_move_to_array() 
{
	int tail = 2;
    int head = 7;
    int length = 10;
    int buffer[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};  
    int n = 4; 
    int array[10] = {0};

    int result = move_n_to_array(buffer, length, &tail, &head, n, array);

    if (result) 
    {
        printf("Resulting Array: ");
        
        for (int i = 0; i < n; i++) 
        {
            printf("%d ", array[i]);
        }
        printf("\n");
        
    } else {
        printf("Missing elements for the buffer.\n");
    }	
}

//USAC09

void func_sort_array() 
{
	int vec[] = {-1,-3,-4,-2};
    int length = 4;
    
    printf("\nOriginal array:\n");
    
    for(int i = 0; i < length; i++)
    {
		printf("Vec[%d] = %d\n",i,vec[i]);
	}

    printf("Array sorted in ascending order:");
    
    int result_asc = sort_array(vec, length, 1);
    
    printf(" %d (0 - Unsuccesful sort, 1 - Succesful sort)\n", result_asc);
    
    if (result_asc) 
    {
        printf("Sorted Array:\n");
        
        for (int i = 0; i < length; i++) 
        {
            printf("Vec[%d] = %d\n",i,vec[i]);
        }
        
        printf("\n");
        
    } else {
        printf("Error sorting array in ascending order.\n");
    }

    printf("Array sorted in descending order:");
    
    int result_desc = sort_array(vec, length, 0);
    
    printf(" %d (0 - Unsuccesful sort, 1 - Succesful sort)\n", result_desc);
    
    if (result_desc) 
    {
        printf("Sorted Array:\n");
        
        for (int i = 0; i < length; i++) 
        {
            printf("Vec[%d] = %d\n",i,vec[i]);
        }
        printf("\n");
        
    } else {
        printf("Error sorting array in descending order.\n");
    }
	
}

//USAC10

void func_median()
{
	int vec[] = {-1,-3,-4,-2};
    int length = 4;
    int me = 0; 

    int result_med = median(vec, length, &me);
    
    printf("\nUSAC10 - Median of an array.\n");
    
    printf("\nArray:\n");
    
    for(int i = 0; i < length; i++)
    {
		printf("Vec[%d] = %d\n",i,vec[i]);
	}
    
    printf("\n(0 - Unsuccesful, 1 - Successful):%d\n", result_med);
    
    if (result_med) 
    {
        printf("Median: %d\n", me);
    } else {
		
        printf("Error.\n");
    }
	
}



