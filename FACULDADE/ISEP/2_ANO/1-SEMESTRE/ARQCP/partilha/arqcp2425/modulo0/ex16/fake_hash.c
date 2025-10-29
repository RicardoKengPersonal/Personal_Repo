int soma (int a, int b)
{
    return a+b;
}

int get_ascii_code(char c)
{
	return (int)c;
}

int fake_hash(char str[])
{
	char array_x[1];
	int i = 0;
	int sum = 0;
	int total = 0;
	
	while(str[i] !=0)
	{
		array_x[i] = str[i];
		sum = get_ascii_code(array_x[i]);
		total = soma(sum, total);
		i++;
	}
	
	return total;
}
