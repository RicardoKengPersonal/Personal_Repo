void capitalize(char *str)
{
    while (*str != '\0') // While loop to go through the string 
    {
        if (*str >= 'a' && *str <= 'z') // Lowercase?
        {
            *str = *str - 32; // Change to Uppercase 
        }
        str++; //Next character
    }
}

