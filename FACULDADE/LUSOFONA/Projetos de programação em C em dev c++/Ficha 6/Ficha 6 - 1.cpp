#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAX_LEN 100

int is_palindrome(char str[], int start, int end)
{
    if (start >= end)
        return 1;
    if (str[start] != str[end])
        return 0;
    return is_palindrome(str, start + 1, end - 1);
}

int count_chars(char str[])
{
    int vogals = 0, cons = 0, digits = 0, spaces = 0;
    int i, len = strlen(str);
    for (i = 0; i < len; i++)
    {
        if (isalpha(str[i]))
        {
            if (str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u' ||
                str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U')
                vogals++;
            else
                cons++;
        }
        else if (isdigit(str[i]))
            digits++;
        else if (str[i] == ' ')
            spaces++;
    }
    printf("Vogais: %d\n", vogals);
    printf("Consoantes: %d\n", cons);
    printf("Digitos: %d\n", digits);
    printf("Espacos: %d\n", spaces);
}

void remove_non_alpha_spaces(char str[])
{
    int i, j = 0;
    int len = strlen(str);
    for (i = 0; i < len; i++)
    {
        if (isalpha(str[i]) || str[i] == ' ')
            str[j++] = str[i];
    }
    str[j] = '\0';
}



int main()
{
    char str[MAX_LEN];
    printf("insira a string: ");
    gets(str);
    remove_non_alpha_spaces(str);
    count_chars(str);
    if (is_palindrome(str, 0, strlen(str) - 1))
        printf("A string e um palindromo\n");
    else
        printf("A string nao e um palindromo\n");
    return 0;
}

