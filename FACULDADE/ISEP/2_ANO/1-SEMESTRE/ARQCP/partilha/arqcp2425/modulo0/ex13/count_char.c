char get_ascii_char(int c)
{
	return (char)c;
}

int count_char(char str[],int c)
{
	char char_verif = get_ascii_char(c);
	int i = 0;
	int count = 0;
	
	while(str[i] != 0)
	{
		if(str[i]==char_verif)
		{
			count++;
		}
		i++;
	}
	
	return count;
}

