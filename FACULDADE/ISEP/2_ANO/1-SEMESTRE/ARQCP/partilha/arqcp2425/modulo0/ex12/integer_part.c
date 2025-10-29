int get_ascii_code(char c)
{
	return (int)c;
}

int string_to_int(char str[])
{
	int integer = 0;
	int i = 0;
	
	while(str[i] != 0){
		integer = integer*10 + get_ascii_code(str[i]-'0');
		i++;
	}
	return integer;
}


int integer_part(char x[])
{
	char int_half[15];
	int i = 0;
	
	while(x[i] != '.')
	{
		int_half[i] = x[i];
		i++;
	}
	
	int_half[i] = 0;
	int num1 = string_to_int(int_half);
	return num1;
}

int fractional_part(char x[])
{
	char float_half[15];
	int i = 0;
	
	while (x[i] != '.'){
		i++; 
	}
	i++;
	
	int u = 0;
	while (x[i] != 0){
		float_half[u] = x[i];
		i++;
		u++; 
	}
	
	int num2 = 0;
	num2 = string_to_int(float_half);
	return num2;
}
