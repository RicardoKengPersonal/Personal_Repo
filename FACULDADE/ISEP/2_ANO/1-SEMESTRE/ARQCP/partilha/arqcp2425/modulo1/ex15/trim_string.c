void trim_string(char* str)
{
	char *src = str;
	char *dst = str;
	int space_counter = 0;
	
	while(*src == ' ')
	{
		src++;
	}
	
	while(*src !='\0')
	{
		if(*src !=' ')
		{
			*dst = *src;
			dst++;
			space_counter = 0;
		}
		else if(!space_counter)
		{
			*dst =' ';
			dst++;
			space_counter = 1;
		}
		src++;
	}
	
	if(*(dst-1) == ' ')
	{
		dst--;
	}
	
	*dst = '\0';
}
