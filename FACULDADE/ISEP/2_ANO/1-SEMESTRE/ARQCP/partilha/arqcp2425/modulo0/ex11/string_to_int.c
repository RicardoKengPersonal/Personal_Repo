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
