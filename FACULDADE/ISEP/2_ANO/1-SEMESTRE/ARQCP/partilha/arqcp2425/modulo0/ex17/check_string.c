int soma(int a, int b)
{
    return a + b;
}

int get_ascii_code(char c)
{
    return (int)c;
}

int fake_hash(char str[])
{
    int i = 0;
    int sum = 0;
    int total = 0;
    
    while (str[i] != 0)
    {
        sum = get_ascii_code(str[i]); 
        total = soma(sum, total);     
        i++;
    }
    
    return total;
}

int check_string(char str[], int h)
{
    int current_hash = fake_hash(str);
    
    return (current_hash == h) ? 1 : 0;
}


