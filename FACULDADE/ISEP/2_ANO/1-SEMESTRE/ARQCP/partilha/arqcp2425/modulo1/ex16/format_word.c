void format_word(char *str)
{
	char *src = str;
	char *dst = str;
	int isNewWord = 1;
	
	while(*src == ' ')
	{
		src++;
	}
	
	while(*src != '\0')
	{
		if(*src != ' ')
		{
			if(isNewWord)
			{
				if(*src >= 'a' && *src <= 'z')
				{
					*dst = *src - ('a' - 'A');
					dst++;
				}
				else
				{
					*dst = *src;
					dst++;
				}
				isNewWord = 0;
			}
			else 
			{
				if(*src >= 'A' && *src <= 'Z')
				{
					*dst = *src + ('a'-'A');
					dst++;
				}
				else
				{
					*dst = *src;
					dst++;
				}
			}
		}
		else
		{
			*dst = ' ';
			dst++;
			while(*src == ' ')
			{
				src++;
			}
			isNewWord = 1;
			continue;
		}
		src++;
	}
	
	if(*(dst-1) == ' ')
	{
		dst--;
	}
	
	*dst = '\0';
}
