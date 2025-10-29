int count_words(char* str)
{
	int i = 0;
    int word_count = 0;
    int in_word = 0;
    
    while (*str !='\0') 
    {
        if (*str != ' ') 
        { 
            if (in_word == 0)
            {
				word_count++;
                in_word = 1; 
            }
        } 
        else 
        {
            in_word = 0;
        }
        i++;
	}

    return word_count;
}
